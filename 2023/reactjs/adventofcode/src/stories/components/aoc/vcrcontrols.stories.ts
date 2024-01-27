import type { Meta, StoryObj } from "@storybook/react";

import {VCRControls} from "@app/components/aoc/vcrcontrols/vcrcontrols";

const meta: Meta<typeof VCRControls> = {
    component: VCRControls,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const Detail: Story = {
    args: {

    }
};