"use client";
import { useContext, useEffect, useState } from "react";
import styles from "./styles.module.css";
import { InitialisedContext } from "@app/context/Initialised.context";
import Adventdays from "../../components/aoc/sidepanel/adventdays";

export default function Page() {
  const { state, dispatch } = useContext(InitialisedContext);
  const [config, setConfig] = useState(null);
  const fetchConfig = async () => {
    const resp = await fetch("/assets/config.json");
    const config = await resp.json();
    setConfig(config);
  };
  useEffect(() => {
    if (state.initialised) {
      fetchConfig();
    }
  }, [state]);

  return (
    <div>{state.initialised && config && <Adventdays data={config} />}</div>
  );
}
