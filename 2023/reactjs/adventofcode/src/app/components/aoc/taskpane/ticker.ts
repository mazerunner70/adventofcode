import { useState } from "react";
import useInterval from "@app/utils/useinterval";

export function useTicker(
  increaseTick: (prevTick: number) => number,
  delay: number | null = null,
  startTick: number = 0,
) {
  const [tickState, setTickState] = useState(startTick);
  useInterval(() => {
    setTickState(increaseTick(tickState));
  }, delay);
  return tickState;
}
