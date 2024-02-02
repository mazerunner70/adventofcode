import type { Meta, StoryObj } from "@storybook/react";

import Render from "@app/components/days/day01/p1/render";

const meta: Meta<typeof Render> = {
  component: Render,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const StartingSearchLine1: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 0,
          foundLeftIndex: -1,
          foundRightIndex: -1,
          valueFound: null,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: -1,
          foundLeftIndex: -1,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    },
    uiActions: {
      action: 7,
      param: "0",
    },
  },
};
export const StartingSearchLine2: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 0,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: 0,
          foundLeftIndex: -1,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    },
    uiActions: {
      action: 7,
      param: "1",
    },
  },
};
export const FindingLeft: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 4,
          foundLeftIndex: 0,
          foundRightIndex: -1,
          valueFound: null,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: -1,
          foundLeftIndex: -1,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    },
    uiActions: {
      action: 5,
      param: "0",
    },
  },
};
export const ContinuingSearchLeftLine2: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 0,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: 1,
          foundLeftIndex: -1,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    },
    uiActions: {
      action: 6,
      param: "1",
    },
  },
};
export const FoundLeftLine2: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 0,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: 10,
          foundLeftIndex: 3,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    },
    uiActions: {
      action: 5,
      param: "1",
    },
  },
};
export const ContinuingSearchRightLine2: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 0,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: 9,
          foundLeftIndex: 3,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    },
    uiActions: {
      action: 4,
      param: "1",
    },
  },
};
export const LandedOnRightLine2: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 0,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: 7,
          foundLeftIndex: 3,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    },
    uiActions: {
      action: 4,
      param: "1",
    },
  },
};
export const FoundRightLine2: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 0,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: -1,
          foundLeftIndex: 3,
          foundRightIndex: 7,
          valueFound: null,
        },
      ],
      total: 0,
    },
    uiActions: {
      action: 3,
      param: "1",
    },
  },
};
export const FoundBothLine2: Story = {
  args: {
    data: '"1abc2\n' + "pqr3stu8vwx\n",
    tickState: {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 0,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: -1,
          foundLeftIndex: 3,
          foundRightIndex: 7,
          valueFound: 38,
        },
      ],
      total: 50,
    },
    uiActions: {
      action: 2,
      param: "1",
    },
  },
};
