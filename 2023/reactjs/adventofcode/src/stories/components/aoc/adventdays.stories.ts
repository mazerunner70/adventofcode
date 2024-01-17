import type { Meta, StoryObj } from "@storybook/react";

import AdventDays from "@app/components/aoc/sidepanel/adventdays";

const meta: Meta<typeof AdventDays> = {
  component: AdventDays,
};

export default meta;
type Story = StoryObj<typeof meta>;

export const NoDays: Story = {
  args: {
    data: [],
  },
};

export const OneDay: Story = {
  args: {
    data: [
      {
        dayno: 1,
        part1: {
          test: {
            filename: "test.txt",
            reference: "142",
          },
          full: {
            filename: "input.txt",
            reference: "54159",
          },
        },
        part2: {
          test: {
            filename: "test2.txt",
            reference: "281",
          },
          full: {
            filename: "input.txt",
            reference: "53866",
          },
        },
      },
    ],
  },
};

export const ThreeDays: Story = {
  args: {
    data: [
      {
        dayno: 1,
        part1: {
          test: {
            filename: "test.txt",
            reference: "142",
          },
          full: {
            filename: "input.txt",
            reference: "54159",
          },
        },
        part2: {
          test: {
            filename: "test2.txt",
            reference: "281",
          },
          full: {
            filename: "input.txt",
            reference: "53866",
          },
        },
      },
      {
        dayno: 2,
        part1: {
          test: {
            filename: "test.txt",
            reference: "8",
          },
          full: {
            filename: "input.txt",
            reference: "2727",
          },
        },
        part2: {
          test: {
            filename: "test.txt",
            reference: "2286",
          },
          full: {
            filename: "input.txt",
            reference: "56580",
          },
        },
      },
      {
        dayno: 3,
        part1: {
          test: {
            filename: "test.txt",
            reference: "4361",
          },
          full: {
            filename: "input.txt",
            reference: "509115",
          },
        },
        part2: {
          test: {
            filename: "test.txt",
            reference: "467835",
          },
          full: {
            filename: "input.txt",
            reference: "75220503",
          },
        },
      },
    ],
  },
};
