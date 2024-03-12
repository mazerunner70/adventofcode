import { useContext } from "react";
import { TaskSelectedContext } from "@app/contexts/TaskSelectedContext";
import { Day01p1 } from "@app/components/tasks/day01/p1/day01p1";

export default function RenderSelector() {
  const taskSelected = useContext(TaskSelectedContext);

  const components = {
    1: {
      1: {
        true: <Day01p1 />,
        false: <div>Day 1 Part 1 False</div>,
      },
      2: {
        true: <div>Day 1 Part 2 True</div>,
        false: <div>Day 1 Part 2 False</div>,
      },
    },
    2: {
      1: {
        true: <div>Day 2 Part 1 True</div>,
        false: <div>Day 2 Part 1 False</div>,
      },
      2: {
        true: <div>Day 2 Part 2 True</div>,
        false: <div>Day 2 Part 2 False</div>,
      },
    },
  };

  return components[taskSelected.dayNumber][taskSelected.partNumber][
    taskSelected.testDataUsed
  ];
}
