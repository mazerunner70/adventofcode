import type { Meta, StoryObj } from "@storybook/react";

import DetailHeader from "@app/components/aoc/detailheader/detailheader";
import ProgressPanel
    from "../../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/aoc/progresspanel/progresspanel";
import TaskSelection
    from "../../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/aoc/taskselection";

const meta: Meta<typeof TaskSelection> = {
    component: TaskSelection,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const Detail: Story = {
    args: {
        daylist: [1, 2, 3, 4],
        selectionConfig: {
            day: 1,
            part: 1,
            testData: false,
            onDayChange: (day: number) => {console.log(day)},
            onPartChange: (part: number) => {console.log(part)},
            onTestDataChange: (testData: boolean) => {console.log(testData)},
        }
    }
};