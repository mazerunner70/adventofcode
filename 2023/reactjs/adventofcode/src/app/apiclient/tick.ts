import { IInputData } from "@app/apiclient/inputdata";

export interface LineSearchState {
  searchIndex: number;
  foundLeftIndex: number;
  foundRightIndex: number;
  valueFound: number | null;
}

export enum Action {
  NoAction,
  TotalFound,
  ValueFoundForLine,
  DigitFoundForRight,
  SearchingFromRight,
  DigitFoundForLeft,
  SearchingFromLeft,
  StartingSearch,
}
export interface UiActions {
  action: Action;
  param: string;
}
export interface ITickOutcome {
  uiActions: UiActions;
  tickState: {
    linesSearchstate: LineSearchState[];
    total: number | null;
    completed: boolean;
  };
}

export interface ITickState {
  id: number;
  tickNumber: number;
  tickOutcome: ITickOutcome;
}

export interface ITicksState {
  ticks: ITickState[];
}

export const fetchTicksByInputDataAndTickNumberRange = async (
  inputData: IInputData,
  startTick: number,
  endTick: number,
): Promise<ITicksState> => {
  return fetch("http://localhost:8000/aoc", {
    method: "POST",
    body: JSON.stringify({
      query:
        "query {\n" +
        "  ticksInRange(inputDataId: " +
        inputData.id +
        ", startTick: " +
        startTick +
        ", endTick: " +
        endTick +
        ") {\n" +
        "    id\n" +
        "    tickNumber\n" +
        "    endstate\n" +
        "  }\n" +
        "}",
    }),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => res.json())
    .then((res) => res.data.ticksInRange)
    .then((res) => {
      return {
        ticks: res.map((t) => {
          console.log("bare", t);
          return {
            id: t.id,
            tickNumber: t.tickNumber,
            tickOutcome: JSON.parse(t.endstate),
          };
        }),
      };
    });
};
