import type { Meta, StoryObj } from "@storybook/react";

import { StringHighlighter } from "@app/components/days/day01/util/StringHighlighter";

const meta: Meta<typeof StringHighlighter> = {
  component: StringHighlighter,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const NoHighlights: Story = {
  args: {
    highlights: [],
    text: "This is a test string",
  },
};
export const OneHighlightStart: Story = {
  args: {
    highlights: [{ index: 0, length: 4, textColour: "red" }],
    text: "This is a test string",
  },
};
export const OneHighlightEnd: Story = {
  args: {
    highlights: [{ index: 17, length: 4, textColour: "red" }],
    text: "This is a test string",
  },
};
export const OneHighlightMid: Story = {
  args: {
    highlights: [{ index: 13, length: 4, textColour: "red" }],
    text: "This is a test string",
  },
};
export const TwoHighlightStartEnd: Story = {
  args: {
    highlights: [
      { index: 0, length: 4, textColour: "red" },
      { index: 17, length: 4, textColour: "blue" },
    ],
    text: "This is a test string",
  },
};
export const TwoHighlightReversed: Story = {
  args: {
    highlights: [
      { index: 17, length: 4, textColour: "red" },
      { index: 0, length: 4, textColour: "blue" },
    ],
    text: "This is a test string",
  },
};
export const TwoHighlightOverlap: Story = {
  args: {
    highlights: [
      { index: 3, length: 4, textColour: "red" },
      { index: 0, length: 4, textColour: "blue" },
    ],
    text: "This is a test string",
  },
};
