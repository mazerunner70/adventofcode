import {
  RowProps,
  TableRenderer,
} from "@app/components/renderers/table/TableRenderer";
import {
  Highlight,
  StringHighlighter,
} from "@app/components/tasks/day01/util/StringHighlighter";
import { TickerStateContext } from "@app/contexts/contexts";
import { useContext, useMemo } from "react";

import { TickProps } from "@app/components/aoc/tickerpane/tickerpane";
import { highlightRules } from "./builder";
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
      return useMemo(
        () => highlightCellRenderer(rowProps, columnNumber),
        [rowProps, columnNumber],
      );
    default:
      return <div>{rowProps.columns[columnNumber]}</div>;
  }
};
const cellTextRenderer = (rowProps: RowProps, columnNumber: number) => {
  return <div>{rowProps.columns[columnNumber]}</div>;
};

const detailColumns = [
  { name: "Text", renderer: cellRenderer },
  { name: "Value", renderer: cellRenderer },
];

const summaryColumns = [{ name: "Total", renderer: cellTextRenderer }];

export const Day01p1 = () => {
  const tickerState: TickProps | null = useContext(TickerStateContext);
  const detailCurrentRowsState = useDetailState(tickerState);

  const totalSoFar = detailCurrentRowsState
    ?.map((r) => parseInt(r.columns[1] === "" ? "0" : r.columns[1]))
    .reduce((a, b) => a + b, 0);

  const summaryRows = [{ columns: [totalSoFar.toString()], params: [] }];

  return (
    <Panel shadowed={true}>
      <HeightLimitedDiv>
        {detailCurrentRowsState && (
          <>
            <TableRenderer
              key={2}
              columns={summaryColumns}
              rows={summaryRows}
            />
            <TableRenderer
              key={1}
              columns={detailColumns}
              rows={detailCurrentRowsState}
            />
          </>
        )}
      </HeightLimitedDiv>
    </Panel>
  );
};
