"use client";
import { useEffect, useState } from "react";
import { fetchAvailableTasks, TaskMap } from "@app/apiclient/AvailableTasks";
import TaskPane2 from "@app/components/aoc/taskpane/taskpane2";

export default function Page() {
  const [taskMap, setTaskMap] = useState<TaskMap | null>(null);
  useEffect(() => {
    console.log("aaas", taskMap);
    if (taskMap === null) {
      fetchAvailableTasks().then((taskMap) => {
        setTaskMap(taskMap);
      });
    }
  }, []);

  return (
    <div>
      {taskMap && (
        <TaskPane2 taskMap={taskMap}>
          <div>test</div>
        </TaskPane2>
      )}
    </div>
  );
}
