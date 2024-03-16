import { TaskMap } from "@app/apiclient/AvailableTasks";
import TaskSelection, {
  ISelectionConfig,
} from "@app/components/aoc/taskselection";
import React, { ReactNode, useEffect, useState } from "react";
import {
  PaddingContainer,
  TaskPanel,
} from "@app/components/aoc/taskpane/styled";
import {
  fetchInputDataByTaskIdAndType,
  IInputData,
} from "@app/apiclient/inputdata";
import { TaskSelectionContext } from "@app/contexts/contexts";
import RenderSelector from "@app/components/tasks/renderselector";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
} from "@app/components/base/htmltable/styled";
import TickerPane from "@app/components/aoc/tickerpane/tickerpane";

export interface TaskSelected {
  dayNumber: number;
  partNumber: number;
  testDataUsed: boolean;
}

export default function TaskPane2({
  taskMap,
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
        <TableContainer>
          <Table>
            <TableBody>
              <TableRow>
                <TableCell>
                  <PaddingContainer>
                    <TaskSelection
                      daylist={Array.from(taskMap.keys())}
                      selectionConfig={selectionState}
                    />
                  </PaddingContainer>
                </TableCell>
                <TableCell>
                  {inputDataState && (
                    <TickerPane inputData={inputDataState}>
                      <RenderSelector />
                    </TickerPane>
                  )}
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </TableContainer>
      </TaskPanel>
    </TaskSelectionContext.Provider>
  );
}
