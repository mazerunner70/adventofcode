import type { Meta, StoryObj } from "@storybook/react";

import Panel from "@app/components/base/panel";

const meta: Meta<typeof Panel> = {
  component: Panel,
};

export default meta;
type Story = StoryObj<typeof meta>;

export const Basic: Story = {
  args: {
    children: <span>Test</span>,
    title: "title",
    subheading: "subheading",
    shadowed: true,
  },
};
export const NoSubheading: Story = {
  args: {
    children: <span>Test</span>,
    title: "title",
    shadowed: true,
  },
};
export const Noheadings: Story = {
  args: {
    children: <span>Test</span>,
    shadowed: true,
  },
};
