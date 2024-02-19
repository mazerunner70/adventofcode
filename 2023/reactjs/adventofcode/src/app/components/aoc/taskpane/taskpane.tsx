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
import {ITicksState, fetchTicksByInputDataAndTickNumberRange } from "@app/apiclient/tick";
import Task from "@app/components/aoc/taskpane/task";
import Panel from "@app/components/base/panel";

const TaskPanel = styled.div`
  ${familjenGrotesk.style};
  width: 100%;
  height: 100%;
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
const PaddingContainer = styled.div`
    padding: 10px;`
const PaddingCentredContainer = styled.div`
    padding: 10px;
    margin: 0 auto;
    height: 100%;
`
const PaddingRightContainer = styled.div`
    position: absolute;
    padding: 10px;
    right: 0;
`
const RowContainer = styled.div`
    display: flex;
    flex-direction: row;
`
const ColumnContainer = styled.div`
  display: flex;
    flex-direction: column;
`

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
  const [ticksState, setTicksState] = React.useState<ITicksState>({ticks: []});


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

  function incrementTick(): void {
    const newTick = Math.min(Math.max(1, progressData.currentTick + speedState), progressData.totalTicks);
    if (progressData.currentTick !== newTick) {
      setProgressData({
        ...progressData,
        currentTick: newTick,
      });
    }
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
      fetchTicksByInputDataAndTickNumberRange(inputDataState, 1, 34)
        .then((res) => {
          console.log("tickretrieve3", res);
          setTicksState(res);
        })
    }
  }, [inputDataState]);

  useEffect(() => {
    //Implementing the setInterval method
    const interval = setInterval(() => {
      if (ticksState.ticks.length > 0)
        incrementTick();
    }, 1000);
    //Clearing the interval
    return () => clearInterval(interval);
  }, [progressData, speedState]);

  console.log("tickstate", ticksState, progressData);

  return (
    <TaskPanel>
      <ColumnContainer >
        <RowContainer>
          <PaddingContainer>
            <TaskSelection daylist={daylist} selectionConfig={selectionState}/>
          </PaddingContainer>
          <PaddingCentredContainer>
            <Panel title={"Demo"} shadowed={true}>
              {inputDataState && inputDataState.data && ticksState.ticks.length > progressData.currentTick &&
                  <Task taskProps={{data: inputDataState.data, tick: ticksState.ticks[progressData.currentTick]}}/>}
            </Panel>
          </PaddingCentredContainer>
        </RowContainer>
        <RowContainer>
          <PaddingContainer>
            <VCRControls speedState={speedState} onSpeedChange={onSpeedChange} />
          </PaddingContainer>
          <PaddingRightContainer>
            <ProgressPanel speedState={speedState} progressData={progressData} />
          </PaddingRightContainer>
        </RowContainer>
      </ColumnContainer>
    </TaskPanel>
  );
}
