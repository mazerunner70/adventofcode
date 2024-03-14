import { IInputData } from "@app/apiclient/inputdata";
import { LineSearchState } from "@app/components/tasks/day01/p1/stateengine";

export interface ITaskProps2 {
  dayNumber: number;
  partNumber: number;
  testDataUsed: boolean;
  speed: number;
  inputData: IInputData | null;
  totalTicks: number;
  ticksState: LineSearchState[];
  linesCompleted: LineSearchState[];
}

export default function taskStateReducer(
  task: ITaskProps2,
  action,
): ITaskProps2 {
  switch (action.type) {
    case "SET_DAY":
      if (task.dayNumber !== action.dayNumber)
        return { ...task, dayNumber: action.dayNumber };
      break;
    case "SET_PART":
      if (task.partNumber !== action.partNumber)
        return { ...task, partNumber: action.partNumber };
      break;
    case "SET_TEST_DATA_USED":
      if (task.testDataUsed !== action.testDataUsed)
        return { ...task, testDataUsed: action.testDataUsed };
      break;
    case "SET_SPEED":
      if (task.speed !== action.speed) return { ...task, speed: action.speed };
      break;
    case "SET_INPUT_DATA":
      if (task.inputData !== action.inputData)
        return { ...task, inputData: action.inputData };
      break;
    case "SET_TOTAL_TICKS":
      if (task.totalTicks !== action.totalTicks)
        return { ...task, totalTicks: action.totalTicks };
      break;
    // case "SET_CURRENT_TICK":
    //   if (task.currentTick !== action.currentTick)
    //     return { ...task, currentTick: action.currentTick };
    //   break;
    case "SET_TICKS_STATE":
      if (task.ticksState !== action.ticksState)
        return { ...task, ticksState: action.ticksState };
      break;
    case "SET_LINES_COMPLETED":
      if (task.linesCompleted !== action.linesCompleted)
        return { ...task, linesCompleted: action.linesCompleted };
      break;
    case "SET_LINES_STATE":
      if (
        task.linesCompleted !== action.linesCompleted &&
        task.ticksState !== action.ticksState
      )
        return {
          ...task,
          ticksState: action.ticksState,
          linesCompleted: action.linesCompleted,
        };
  }
  return task;
}
