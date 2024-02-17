"use client";

import { useContext, useEffect, useState } from "react";
import { AdventContext } from "@app/context/Advent.context";
import DetailHeader from "@app/components/aoc/detailheader/detailheader";
import Day01P1 from "@app/components/days/day01/p1/day01p1";
import TaskPane, {
  ITaskPaneProps, ITaskProps
} from "@app/components/aoc/taskpane/taskpane";

export default function Page() {
//  const { state, dispatch } = useContext(AdventContext);
  const [data, setData] = useState<ITaskPaneProps>({tasks:[]});

  const fetchData2 = async () => {
    const data = await fetch(
      "http://localhost:8000/aoc",
      {
        method: "POST",
        body: JSON.stringify({
          query: 'query {\n' +
            '  allAdventDays {\n' +
            '    id\n' +
            '    dayNumber\n' +
            '    description\n' +
            '    tasks {\n' +
            '      taskId\n' +
            '      taskNumber\n' +
            '      description\n' +
            '      inputData {\n' +
            '        inputType\n' +
            '      }\n' +
            '    }\n' +
            '  }\n' +
            '}',
        }),
        headers: {
          "Content-Type": "application/json",
        },
      }
    ).then((res) => res.json());
    const tasks : ITaskProps[] = [];
    const adventdays = data.data.allAdventDays;
    for (let i = 0; i < adventdays.length; i++) {
      tasks.push({ day: adventdays[i].dayNumber, part: 1, testDataUsed: true, taskId: adventdays[i].tasks[0].taskId });
      tasks.push({ day: adventdays[i].dayNumber, part: 1, testDataUsed: false, taskId: adventdays[i].tasks[0].taskId });
      tasks.push({ day: adventdays[i].dayNumber, part: 2, testDataUsed: true, taskId: adventdays[i].tasks[1].taskId });
      tasks.push({ day: adventdays[i].dayNumber, part: 2, testDataUsed: false, taskId: adventdays[i].tasks[1].taskId });
    }
    setData({tasks});
    console.log("datagraphql", data);
    console.log("datagraphql-xfrm", {tasks});
  }

  console.log("aa", data)
  useEffect(() => {
    console.log("aaa", data)
    if (data.tasks.length === 0) {
      console.log("aaaa", data)
      // fetchData();
      fetchData2();
    }
  }, [ ]);
  //console.log("--", state)
  return (
    <div>
      {data.tasks && <TaskPane data={data} />}
    </div>
  );
}
