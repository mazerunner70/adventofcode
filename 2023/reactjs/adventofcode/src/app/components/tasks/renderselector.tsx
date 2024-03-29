import { useContext } from "react";
import { TaskSelectionContext } from "@app/contexts/contexts";
import { Day01p1 } from "@app/components/tasks/day01/p1/day01p1";

export default function RenderSelector() {
  const taskSelected = useContext(TaskSelectionContext);

  const components = {
    1: {
      1: {
        true: <Day01p1 />,
        false: <Day01p1 />,
      },
      2: {
        true: <Day01p1 />,
        false: <Day01p1 />,
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
