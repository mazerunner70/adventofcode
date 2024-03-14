import { AdventContext } from "@app/contexts/Advent.context";
import { useContext, useEffect, useState } from "react";
import {
  Action,
  ITickState,
  LineSearchState,
  UIActions,
} from "@app/components/tasks/day01/p1-old/useticker";
import { tick } from "@app/components/tasks/day01/p1-old/useticker";
import Render from "@app/components/tasks/day01/p1-old/render";
import useInterval from "@app/utils/useinterval";

export default function Day01P1({
  envName,
  data,
}: {
  envName: string;
  data: string;
}): JSX.Element {
  const [tickState, setTickState] = useState<ITickState>({
    searchState: [],
    total: 0,
  });
  const [uiActions, setUiActions] = useState<UIActions>({
    action: Action.NoAction,
    param: "",
  });
  const [delay, setDelay] = useState(1000); // Initial state for the delay
  const [isRunning, setIsRunning] = useState(true); // State to start or pause the interval
  const dayEnv = "1" + envName;
  //console.log("entered Day01P1", tickState, uiActions, dayEnv, data)
  function buildSearchState(line: string): LineSearchState {
    return {
      line,
      searchIndex: -1,
      foundLeftIndex: -1,
      foundRightIndex: -1,
      valueFound: null,
    };
  }

  useEffect(() => {
    const searchState = data.split("\n").map(buildSearchState);
    const initTickState = { ...tickState, searchState };
    const tickResult = tick(initTickState);
    console.log("Initial tickResult", tickResult);
    setTickState(tickResult.tickState);
    setUiActions(tickResult.uiActions);
  }, [data]);

  function localTick() {
    const tickResult = tick(tickState);
    console.log("localTick", tickResult);
    setTickState(tickResult.tickState);
    setUiActions(tickResult.uiActions);
  }

  useInterval(
    () => {
      // Your custom logic here
      localTick();
    },
    isRunning ? delay : null, // Passing null to pause the interval
  );

  const startPlayer = () => setIsRunning(true);
  const stopPlayer = () => setIsRunning(false);
  const speedUp = () => setDelay(delay / 2);
  const slowDown = () => setDelay(delay * 2);

  return (
    <div>
      <button onClick={startPlayer}>Start</button>
      <button onClick={stopPlayer}>Stop</button>
      <button onClick={speedUp}>SpeedUp</button>
      <button onClick={slowDown}>SlowDown</button>
      <Render data={data} tickState={tickState} uiActions={uiActions} />
    </div>
  );
}
