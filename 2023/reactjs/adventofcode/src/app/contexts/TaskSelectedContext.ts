import { createContext } from "react";

export const TaskSelectedContext = createContext({
  dayNumber: 1,
  partNumber: 1,
  testDataUsed: true,
});
