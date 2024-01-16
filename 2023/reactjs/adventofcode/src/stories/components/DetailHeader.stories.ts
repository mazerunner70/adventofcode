import type { Meta, StoryObj } from "@storybook/react";

import DetailHeader from "@app/components/detailheader/detailheader";

const meta: Meta<typeof DetailHeader> = {
  component: DetailHeader,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const Detail: Story = {
  args: {
    config: {
      dayno: 1,
      partName: "Part 1",
      envName: "Test",
      envConfig: {
        filename: "test.txt" + "",
        reference: "142",
      },
    },
  },
};
