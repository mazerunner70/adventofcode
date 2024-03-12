import type { Meta, StoryObj } from "@storybook/react";

import Render from "@app/components/tasks/day01/p1-old/render";

const meta: Meta<typeof Render> = {
  component: Render,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const StartingSearchLine1Pos0: Story = {
  args: {
    data: "1abc2\npqr3stu8vwx\n",
    tickOutcome: {
      event_type: 1,
      line_number: 0,
      search_index: 0,
      found_left_index: -1,
      found_right_index: -1,
      value_found: "",
      total: 0,
    },
    linesCompleted: [],
  },
};
export const StartingSearchLine1Pos1: Story = {
  args: {
    data: "1abc2\npqr3stu8vwx\n",
    tickOutcome: {
      event_type: 1,
      line_number: 0,
      search_index: 1,
      found_left_index: -1,
      found_right_index: -1,
      value_found: "",
      total: 0,
    },
    linesCompleted: [],
  },
};
