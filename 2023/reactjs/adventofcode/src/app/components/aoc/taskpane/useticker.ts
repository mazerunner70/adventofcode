import { useState } from "react";
import useInterval from "@app/utils/useinterval";

export function useTicker(
  increaseTick: (prevTick: number) => number,
  delay: number | null = null,
  startTick: number = 0,
): [number, (tick: number) => void] {
  const [tickState, setTickState] = useState<number>(startTick);
  useInterval(() => {
    setTickState(increaseTick(tickState));
  }, delay);
  return [tickState, setTickState];
}
