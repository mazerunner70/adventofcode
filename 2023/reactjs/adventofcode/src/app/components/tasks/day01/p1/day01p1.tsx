import {
  RowProps,
  TableRenderer,
} from "@app/components/renderers/table/TableRenderer";
import {
  Highlight,
  StringHighlighter,
} from "@app/components/tasks/day01/util/StringHighlighter";
import { TickerStateContext } from "@app/contexts/contexts";
import { useContext, useEffect, useState } from "react";
import { fetchTicksByInputDataAndTickNumberRange } from "@app/apiclient/tick";
import {
  asEvents,
  build_line_search_states,
} from "@app/components/tasks/day01/p1/stateengine";
import { TickProps } from "@app/components/aoc/tickerpane/tickerpane";
import {
  buildCompletedLineStates,
  buildLineState,
  highlightRules,
} from "./builder";
import { IRow, ITicksState, LineSearchState, SearchEventType } from "./types";

const highlightCellRenderer = (rowProps: RowProps, columnNumber: number) => {
  const rules: Highlight[] = highlightRules(rowProps);
  return (
    <StringHighlighter
      highlights={rules}
      text={rowProps.columns[columnNumber]}
    />
  );
};
const cellRenderer = (rowProps: RowProps, columnNumber: number) => {
  switch (columnNumber) {
    case 0:
      return highlightCellRenderer(rowProps, columnNumber);
    default:
      return <div>{rowProps.columns[columnNumber]}</div>;
  }
};
const columns = [
  { name: "Column 1", renderer: cellRenderer },
  { name: "Column 2", renderer: cellRenderer },
];

export const Day01p1 = () => {
  //Holds the state of the lines for rendering the table
  const [ticksState, setTicksState] = useState<ITicksState>({
    linesearchState: [],
    linesCompleted: [],
    emptyLiness: [],
  });
  // Holds the rows of text to populat ethe tickstate from
  const [dataRowsState, setDataRowsState] = useState<string[]>([]);
  // Holds the current tick state to render
  const [currentRowsState, setCurrentRowsState] = useState<IRow[]>();
  const tickerState: TickProps | null = useContext(TickerStateContext);

  function buildEmptyLines(dataRows: string[]): IRow[] {
    return dataRows.map((v) => {
      return { columns: [v, ""], params: [] };
    });
  }

  useEffect(() => {
    console.log("tickretrieve", tickerState);
    if (tickerState) {
      if (tickerState.inputData) {
        const dataRows = tickerState.inputData.data.split("\n");
        setDataRowsState(dataRows);
        console.log("tickretrieve2", tickerState.totalTicks);
        fetchTicksByInputDataAndTickNumberRange(
          tickerState.inputData,
          1,
          tickerState.totalTicks,
        ).then((res) => {
          console.log("tickretrieve3", res);
          const events = asEvents(res.ticks.map((t) => t.tickOutcome));
          const lineSearchStates = build_line_search_states(events);
          console.log("tickretrieve4", lineSearchStates);
          setTicksState({
            linesearchState: lineSearchStates,
            linesCompleted: buildCompletedLineStates(
              lineSearchStates,
              dataRows,
            ),
            emptyLiness: buildEmptyLines(dataRows),
          });
        });
      }
    }
  }, [tickerState?.inputData, tickerState?.totalTicks]);

  useEffect(() => {
    console.log("tickstate", tickerState?.currentTick, ticksState);
    if (tickerState !== null && ticksState.linesearchState.length > 0) {
      const currTickNumber = tickerState.currentTick;
      const searchState = ticksState.linesearchState[currTickNumber];
      const dataRow = dataRowsState[searchState.line_number];
      const currRow = buildLineState(searchState, dataRow);
      console.log("tickstate2", currRow, searchState, dataRow);
      setCurrentRowsState([
        ...ticksState.linesCompleted.slice(0, searchState.line_number),
        currRow,
        ...ticksState.emptyLiness.slice(searchState.line_number + 1),
      ]);
      console.log(
        "tickstate3",
        ticksState.linesCompleted.slice(0, searchState.line_number),
        currRow,
        ticksState.emptyLiness.slice(searchState.line_number + 1),
      );
    }
  }, [tickerState?.currentTick, ticksState]);

  return (
    <div>
      {currentRowsState && (
        <TableRenderer columns={columns} rows={currentRowsState} />
      )}
    </div>
  );
};
