// import styled from "styled-components";
// import TaskSelection, {
//   ISelectionConfig,
// } from "@app/components/aoc/taskselection";
// import React, { useEffect, useReducer } from "react";
// import { VCRControls } from "@app/components/aoc/vcrcontrols/vcrcontrols";
// import ProgressPanel from "@app/components/aoc/progresspanel/progresspanel";
// import { familjenGrotesk } from "@app/styles/fonts";
//
// import {
//   fetchInputDataByTaskIdAndType,
//   initialiseInputData,
// } from "@app/apiclient/inputdata";
// import { fetchTicksByInputDataAndTickNumberRange } from "@app/apiclient/tick";
// import RenderPane from "@app/components/aoc/taskpane/renderpane";
// import Panel from "@app/components/base/panel";
// import {
//   asEvents,
//   build_line_search_states,
// } from "@app/components/tasks/day01/p1/stateengine";
// import taskStateReducer, {
//   ITaskProps2,
// } from "@app/components/aoc/taskpane/taskreducer";
// import { useTicker } from "@app/components/aoc/taskpane/useticker";
// import {
//   LineSearchState,
//   SearchEventType,
// } from "@app/components/tasks/day01/p1/types";
//
// const TaskPanel = styled.div`
//   ${familjenGrotesk.style};
//   width: 100%;
//   height: 100%;
//   position: relative;
// `;
//
// const PaddingContainer = styled.div`
//   padding: 10px;
// `;
// const PaddingCentredContainer = styled.div`
//   padding: 10px;
//   margin: 0 auto;
//   height: 100%;
// `;
// const PaddingRightContainer = styled.div`
//   position: absolute;
//   padding: 10px;
//   right: 0;
// `;
// const RowContainer = styled.div`
//   display: flex;
//   flex-direction: row;
// `;
// const ColumnContainer = styled.div`
//   display: flex;
//   flex-direction: column;
// `;
//
// export interface ITaskProps {
//   day: number;
//   part: number;
//   testDataUsed: boolean;
//   taskId: number;
// }
//
// export interface ITaskPaneProps {
//   tasks: ITaskProps[];
// }
//
// export default function TaskPane({ data }: { data: ITaskPaneProps }) {
//   const initialTaskState: ITaskProps2 = {
//     dayNumber: 1,
//     partNumber: 1,
//     testDataUsed: true,
//     speed: 0,
//     inputData: null,
//     totalTicks: 0,
//     ticksState: [],
//     linesCompleted: [],
//   };
//   const [taskState, dispatch] = useReducer(taskStateReducer, initialTaskState);
//
//   console.log("dd", data);
//   const daylist: number[] = [...new Set(data.tasks?.map((task) => task.day))];
//
//   const selectionState: ISelectionConfig = {
//     day: taskState.dayNumber,
//     part: taskState.partNumber,
//     testData: taskState.testDataUsed,
//     onDayChange: (newDay: number) =>
//       dispatch({ type: "SET_DAY", dayNumber: newDay }),
//     onPartChange: (newPart: number) =>
//       dispatch({ type: "SET_PART", partNumber: newPart }),
//     onTestDataChange: (newState: boolean) =>
//       dispatch({ type: "SET_TEST_DATA_USED", testDataUsed: newState }),
//   };
//
//   console.log("taskstate: ", taskState);
//
//   function onSpeedChange(speed: number): void {
//     if (taskState.ticksState.length > 0) {
//       dispatch({ type: "SET_SPEED", speed: speed });
//     }
//   }
//
//   const currTick = useTicker(
//     (prevTick) => {
//       return Math.min(
//         Math.max(0, prevTick + taskState.speed),
//         taskState.totalTicks - 1,
//       );
//     },
//     taskState.speed !== 0 ? 1000 : null,
//     0,
//   );
//
//   useEffect(() => {
//     console.log("iddata", data.tasks);
//     const taskId = data.tasks?.find(
//       (task) =>
//         task.day === taskState.dayNumber &&
//         task.part === taskState.partNumber &&
//         task.testDataUsed === taskState.testDataUsed,
//     )?.taskId;
//     if (taskId) {
//       console.log("iddataT", taskId, taskState.testDataUsed);
//       fetchInputDataByTaskIdAndType(taskId, taskState.testDataUsed)
//         .then((res) => {
//           console.log("initInputData", res);
//           dispatch({ type: "SET_INPUT_DATA", inputData: res });
//           return res.id;
//         })
//         .then((inputDataId) => {
//           return initialiseInputData(inputDataId);
//         })
//         .then((res) => {
//           console.log("initTicks", res);
//           return res.data.buildTicks.inputData.tickCount;
//         })
//         .then((tickCount) => {
//           dispatch({ type: "SET_TOTAL_TICKS", totalTicks: tickCount });
//         });
//     }
//   }, [data, taskState.dayNumber, taskState.partNumber, taskState.testDataUsed]);
//
//   function deriveCompletedLineStates(
//     ticks: LineSearchState[],
//   ): LineSearchState[] {
//     return ticks
//       .filter((t) => t.event_type === SearchEventType.ValueCalculated)
//       .map((v) => {
//         return { ...v, event_type: SearchEventType.Idle };
//       });
//   }
//
//   useEffect(() => {
//     console.log("tickretrieve");
//     if (taskState.inputData) {
//       console.log("tickretrieve2", taskState.totalTicks);
//       fetchTicksByInputDataAndTickNumberRange(
//         taskState.inputData,
//         1,
//         taskState.totalTicks,
//       ).then((res) => {
//         console.log("tickretrieve3", res);
//         const events = asEvents(res.ticks.map((t) => t.tickOutcome));
//         const lineSearchStates = build_line_search_states(events);
//         console.log("tickretrieve4", lineSearchStates);
//         dispatch({
//           type: "SET_LINES_STATE",
//           ticksState: lineSearchStates,
//           linesCompleted: deriveCompletedLineStates(lineSearchStates),
//         });
//       });
//     }
//   }, [taskState.totalTicks]);
//
//   console.log("tickstate", currTick, taskState.speed);
//
//   return (
//     <TaskPanel>
//       <ColumnContainer>
//         <RowContainer>
//           <PaddingContainer>
//             <TaskSelection daylist={daylist} selectionConfig={selectionState} />
//           </PaddingContainer>
//           <PaddingCentredContainer>
//             <Panel title={"Demo"} shadowed={true}>
//               {taskState.inputData &&
//                 taskState.inputData.data &&
//                 taskState.ticksState.length > currTick && (
//                   <RenderPane
//                     taskProps={{
//                       data: taskState.inputData.data,
//                       tick: taskState.ticksState[currTick],
//                       linesCompleted: taskState.linesCompleted,
//                     }}
//                   />
//                 )}
//             </Panel>
//           </PaddingCentredContainer>
//         </RowContainer>
//         <RowContainer>
//           <PaddingContainer>
//             <VCRControls
//               speedState={taskState.speed}
//               onSpeedChange={onSpeedChange}
//             />
//           </PaddingContainer>
//           <PaddingRightContainer>
//             <ProgressPanel
//               speedState={taskState.speed}
//               progressData={{
//                 currentTick: currTick,
//                 totalTicks: taskState.totalTicks,
//               }}
//             />
//           </PaddingRightContainer>
//         </RowContainer>
//       </ColumnContainer>
//     </TaskPanel>
//   );
// }
