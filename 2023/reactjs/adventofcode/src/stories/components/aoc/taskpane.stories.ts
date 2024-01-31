import type { Meta, StoryObj } from "@storybook/react";

import TaskPane
    from "@app/components/aoc/taskpane/taskpane";

const meta: Meta<typeof TaskPane> = {
    component: TaskPane,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const Detail: Story = {
    args: {
        data: {
            tasks: [
                {
                    day: 1,
                    part: 1,
                    testDataUsed: false,
                    filename: "day1part1",
                },
                {
                    day: 1,
                    part: 1,
                    testDataUsed: true,
                    filename: "day1part1",
                },                {
                    day: 2,
                    part: 1,
                    testDataUsed: false,
                    filename: "day1part1",
                },
                {
                    day: 2,
                    part: 1,
                    testDataUsed: true,
                    filename: "day1part1",
                },
        ],
        },
    }
};