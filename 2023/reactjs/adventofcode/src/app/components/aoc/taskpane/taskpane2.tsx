import { ITaskPaneProps } from "@app/components/aoc/taskpane/taskpane";
import { TaskMap } from "@app/apiclient/AvailableTasks";
import TaskSelection, {
  ISelectionConfig,
} from "@app/components/aoc/taskselection";
import React, { createContext, ReactNode, useEffect, useState } from "react";
import {
  ColumnContainer,
  PaddingContainer,
  RowContainer,
  TaskPanel,
} from "@app/components/aoc/taskpane/styled";
import TickerPane, {
  IInputData,
} from "@app/components/aoc/tickerpane/tickerpane";
import { fetchInputDataByTaskIdAndType } from "@app/apiclient/inputdata";
import { TaskSelectionContext } from "@app/contexts/contexts";
import RenderSelector from "@app/components/tasks/renderselector";

export interface TaskSelected {
  dayNumber: number;
  partNumber: number;
  testDataUsed: boolean;
}

export default function TaskPane2({
  taskMap,
  children,
}: {
  taskMap: TaskMap;
} & { children?: ReactNode }) {
  const [inputDataState, setInputDataState] = useState<IInputData | null>(null);
  const [taskSelected, setTaskSelected] = useState<TaskSelected>({
    dayNumber: 1,
    partNumber: 1,
    testDataUsed: true,
  });
  const selectionState: ISelectionConfig = {
    day: taskSelected.dayNumber,
    part: taskSelected.partNumber,
    testData: taskSelected.testDataUsed,
    onDayChange: (newDay: number) =>
      setTaskSelected({ ...taskSelected, dayNumber: newDay }),
    onPartChange: (newPart: number) =>
      setTaskSelected({ ...taskSelected, partNumber: newPart }),
    onTestDataChange: (newState: boolean) =>
      setTaskSelected({ ...taskSelected, testDataUsed: newState }),
  };

  useEffect(() => {
    console.log("taskSelected", taskSelected);
    console.log("taskMap", taskMap);
    const k = taskMap
      .get(taskSelected.dayNumber)
      ?.get(taskSelected.partNumber)
      ?.get(taskSelected.testDataUsed);
    console.log("k", k);
    if (k) {
      fetchInputDataByTaskIdAndType(k.taskId, k.testDataUsed).then((res) => {
        setInputDataState(res);
      });
    }
  }, [taskSelected, taskMap]);

  return (
    <TaskSelectionContext.Provider value={taskSelected}>
      <TaskPanel>
        <ColumnContainer>
          <RowContainer>
            <PaddingContainer>
              <TaskSelection
                daylist={Array.from(taskMap.keys())}
                selectionConfig={selectionState}
              />
            </PaddingContainer>
            {inputDataState && (
              <TickerPane inputData={inputDataState}>
                <RenderSelector />
              </TickerPane>
            )}
          </RowContainer>
        </ColumnContainer>
      </TaskPanel>
    </TaskSelectionContext.Provider>
  );
}
