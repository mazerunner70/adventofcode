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
  const fetchData = async () => {
    const resp = await fetch("/assets/config.json");
    const d = await resp.json();
    //dispatch({ type: "SET_RUNSTATE", payload: { dayenv:dayno+envName, data:d } });
    const tasks : ITaskProps[] = [];
    for (let i = 0; i < d.length; i++) {
      tasks.push({ day: d[i].dayno, part: 1, testDataUsed: true, filename: d[i].part1.test.filename });
      tasks.push({ day: d[i].dayno, part: 1, testDataUsed: false, filename: d[i].part1.full.filename });
      tasks.push({ day: d[i].dayno, part: 2, testDataUsed: true, filename: d[i].part2.test.filename });
      tasks.push({ day: d[i].dayno, part: 2, testDataUsed: false, filename: d[i].part2.full.filename });
    }
    setData({tasks});
  };
  console.log("aa", data)
  useEffect(() => {
    console.log("aaa", data)
    if (data.tasks.length === 0) {
      console.log("aaaa", data)
      fetchData();
    }
  }, [ ]);
  //console.log("--", state)
  return (
    <div>
      {data.tasks && <TaskPane data={data} />}
    </div>
  );
}
