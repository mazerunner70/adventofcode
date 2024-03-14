import { createContext } from "react";
import { TickProps } from "@app/components/aoc/tickerpane/tickerpane";

export const TaskSelectionContext = createContext({
  dayNumber: 1,
  partNumber: 1,
  testDataUsed: true,
});

export const TickerStateContext = createContext<TickProps | null>(null);
