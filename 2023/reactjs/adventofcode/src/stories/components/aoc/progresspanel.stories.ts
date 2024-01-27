import type { Meta, StoryObj } from "@storybook/react";

import DetailHeader from "@app/components/aoc/detailheader/detailheader";
import ProgressPanel
    from "../../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/aoc/progresspanel/progresspanel";

const meta: Meta<typeof ProgressPanel> = {
    component: ProgressPanel,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const Detail: Story = {
    args: {
        config: {
        },
    }
};