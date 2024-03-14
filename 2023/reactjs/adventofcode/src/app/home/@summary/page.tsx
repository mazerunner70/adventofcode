"use client";
import { useContext, useEffect, useState } from "react";
import styles from "./styles.module.css";
import { InitialisedContext } from "@app/contexts/Initialised.context";
import { ITaskPaneProps } from "@app/components/aoc/taskpane/taskpane";
import TaskPane from "../../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/aoc/taskpane/taskpane";

export default function Page() {
  const { state, dispatch } = useContext(InitialisedContext);
  const [config, setConfig] = useState<ITaskPaneProps>({ tasks: [] });
  const fetchConfig = async () => {
    const resp = await fetch("/assets/config.json");
    const config = await resp.json();
    console.log(config);
    setConfig(config);
  };
  useEffect(() => {
    if (state.initialised) {
      fetchConfig();
    }
  }, [state]);

  return <div>{state.initialised && config && <TaskPane data={config} />}</div>;
}
