import { useEffect, useMemo, useState } from "react";
import { IInputData } from "@app/apiclient/inputdata";
import { IRow, LineSearchState } from "@app/components/tasks/day01/p1/types";
import { fetchTicksByInputDataAndTickNumberRange } from "@app/apiclient/tick";
import {
  asEvents,
  build_line_search_states,
} from "@app/components/tasks/day01/p1/stateengine";
import {
  buildCompletedLineStates,
  buildEmptyLines,
  buildLineState,
} from "@app/components/tasks/day01/p1/builder";
import { TickProps } from "@app/components/aoc/tickerpane/tickerpane";

interface IDetailState {
  dataRows: string[];
  linesearchState: LineSearchState[];
  linesCompleted: IRow[];
  emptyLiness: IRow[];
}
export function useDetailState(tickProps: TickProps | null) {
  const [detailState, setDetailState] = useState<IDetailState | null>(null);
  function setInitialState(inputData: IInputData, totalTicks: number) {
    const dataRows = inputData.data.split("\n");
    fetchTicksByInputDataAndTickNumberRange(inputData, 1, totalTicks).then(
      (res) => {
        const events = asEvents(res.ticks.map((t) => t.tickOutcome));
        const lineSearchStates = build_line_search_states(events);
        setDetailState({
          dataRows,
          linesearchState: lineSearchStates,
          linesCompleted: buildCompletedLineStates(lineSearchStates, dataRows),
          emptyLiness: buildEmptyLines(dataRows),
        });
      },
    );
  }

  function calcRowsState(
    tickPropsMemo: TickProps | null,
    detailStateMemo: IDetailState | null,
  ): IRow[] {
    if (
      tickPropsMemo &&
      tickPropsMemo.currentTick !== null &&
      detailStateMemo !== null &&
      detailStateMemo.linesearchState.length > 0
    ) {
      const currentSearchTick =
        detailStateMemo.linesearchState[tickPropsMemo.currentTick];
      // console.log(
      //   "currentSearchTick",
      //   currentSearchTick,
      //   detailStateMemo,
      //   tickPropsMemo,
      // );
      const lineNo = currentSearchTick.line_number;
      return [
        ...detailStateMemo.linesCompleted.slice(0, lineNo),
        buildLineState(currentSearchTick, detailStateMemo.dataRows[lineNo]),
        ...detailStateMemo.emptyLiness.slice(lineNo + 1),
      ];
    }
    return [];
  }

  useEffect(() => {
    if (
      tickProps &&
      tickProps.inputData !== null &&
      tickProps.totalTicks !== null
    ) {
      setInitialState(tickProps.inputData, tickProps.totalTicks);
      console.log("initialising detail state");
      console.log("tickProps", tickProps);
    }
  }, [tickProps?.inputData, tickProps?.totalTicks]);

  return calcRowsState(tickProps, detailState);
}
