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
  buildEmptyLines,
  buildLineState,
  highlightRules,
} from "./builder";
import { IRow, ITicksState } from "./types";
import Panel from "@app/components/base/panel";
import { useDetailState } from "@app/components/tasks/day01/p1/usedetailstate";
import styled from "styled-components";

const HeightLimitedDiv = styled.div`
  max-height: 300px;
  overflow-y: auto;
`;

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
  { name: "Text", renderer: cellRenderer },
  { name: "Value", renderer: cellRenderer },
];

export const Day01p1 = () => {
  const tickerState: TickProps | null = useContext(TickerStateContext);

  const currentRowsState = useDetailState(tickerState);

  return (
    <Panel shadowed={true}>
      <HeightLimitedDiv>
        {currentRowsState && (
          <TableRenderer columns={columns} rows={currentRowsState} />
        )}
      </HeightLimitedDiv>
    </Panel>
  );
};
