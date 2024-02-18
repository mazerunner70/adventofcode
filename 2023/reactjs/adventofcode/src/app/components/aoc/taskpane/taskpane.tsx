import styled from "styled-components";
import TaskSelection, {
  ISelectionConfig,
} from "@app/components/aoc/taskselection";
import React, { useEffect } from "react";
import { VCRControls } from "@app/components/aoc/vcrcontrols/vcrcontrols";
import ProgressPanel, {
  IProgressData,
} from "@app/components/aoc/progresspanel/progresspanel";
import { familjenGrotesk } from "@app/styles/fonts";
import calcWorker from "@app/components/days/day01/p1/tickcalc";
import { asWorker } from "@app/utils/WebWorker";
import exWorker from "@app/components/days/day01/p1/example1";
import {IInputData, fetchInputDataByTaskIdAndType, initialiseInputData } from "@app/apiclient/inputdata";
import { fetchTicksByInputDataAndTickNumberRange } from "@app/apiclient/tick";

const TaskPanel = styled.div`
  ${familjenGrotesk.style};
  width: 100%;
  height: 500px;
  position: relative;
`;

const TopLeft = styled.div`
  position: absolute;
  top: 0;
  left: 0;
`;
const TopRight = styled.div`
  position: absolute;
  top: 0;
  right: 0;
`;
const BottomLeft = styled.div`
  position: absolute;
  bottom: 0;
  left: 0;
`;
const BottomRight = styled.div`
  position: absolute;
  bottom: 0;
  right: 0;
`;

export interface ITaskProps {
  day: number;
  part: number;
  testDataUsed: boolean;
  taskId: number;
}



export interface ITaskPaneProps {
  tasks: ITaskProps[];
}

export default function TaskPane( {data} : {data:ITaskPaneProps} ) {
  const [dayNumberState, setDayNumberState] = React.useState<number>(1);
  const [partState, setPartState] = React.useState<number>(1);
  const [testDataUsedState, setTestDataUsedState] =
    React.useState<boolean>(true);
  const [speedState, setSpeedState] = React.useState<number>(0);
  const [inputDataState, setInputDataState] = React.useState<IInputData | null>(null);
  const [progressData, setProgressData] = React.useState<IProgressData>({
    totalTicks: 0,
    currentTick: 0,
  });

  console.log("dd", data);
  const daylist: number[] = [...new Set(data.tasks?.map((task) => task.day))];

  const selectionState: ISelectionConfig = {
    day: dayNumberState,
    part: partState,
    testData: testDataUsedState,
    onDayChange: (newDay: number) => setDayNumberState(newDay),
    onPartChange: (newPart: number) => setPartState(newPart),
    onTestDataChange: (newState: boolean) => setTestDataUsedState(newState),
  };

  console.log(dayNumberState, partState, testDataUsedState);

  function onSpeedChange(speed: number): void {
    setSpeedState(speed);
  }


  useEffect(() => {
    console.log("iddata", data.tasks);
    const taskId = data.tasks?.find(task=>task.day === dayNumberState && task.part === partState && task.testDataUsed === testDataUsedState)?.taskId;
    if (taskId) {
      console.log("iddataT", taskId, testDataUsedState);
      fetchInputDataByTaskIdAndType(taskId, testDataUsedState)
        .then((res) => {console.log("initInputData", res);setInputDataState(res); return res.id})
        .then((inputDataId) => {return initialiseInputData(inputDataId)})
        .then((res) => {console.log("initTicks", res);return res.data.buildTicks.inputData.tickCount;})
        .then((tickCount) => {setProgressData({...progressData, totalTicks: tickCount});})
    }
  }, [data, dayNumberState, partState, testDataUsedState]);

  useEffect(() => {
    console.log("tickretrieve");
    if (inputDataState) {
      console.log("tickretrieve2");
      fetchTicksByInputDataAndTickNumberRange(inputDataState, 0, 0)
        .then((res) => {console.log("tickretrieve3", res.endstate.uiActions.param);return res;})
    }
  }, [inputDataState]);

  useEffect(() => {
    console.log("dddd", data);
    if (data.tasks?.length > 0) {
      console.log("ddd", data.tasks[0].taskId);
      // const worker: Worker = asWorker(exWorker);
      // // const worker = new Worker(new URL("@app/components/days/day01/p1/tickcalc.ts", import.meta.url));
      // // const worker = new Worker("javascript/example.js", { type: "classic" });
      // // const worker = asWorker(calcWorker);
      // worker.onmessage = (e: MessageEvent<number>) => {
      //   console.log("e", e);
      //   setProgressData({ ...progressData, totalTicks: e.data });
      // };
      // worker.postMessage({ filename: data.tasks[0].taskId });
    }
  }, [data]);

  return (
    <TaskPanel>
      <TopLeft>
        <TaskSelection daylist={daylist} selectionConfig={selectionState} />
      </TopLeft>
      <BottomLeft>
        <VCRControls speedState={speedState} onSpeedChange={onSpeedChange} />
      </BottomLeft>
      <BottomRight>
        <ProgressPanel speedState={speedState} progressData={progressData} />
      </BottomRight>
    </TaskPanel>
  );
}
