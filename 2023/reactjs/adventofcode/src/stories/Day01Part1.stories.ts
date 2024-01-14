import type { Meta, StoryObj } from '@storybook/react';

import Render from '@app/components/days/day01/render';

const meta = {
    title: 'Dat01 Part ',
    component: Render,
    parameters: {
        // Optional parameter to center the component in the Canvas. More info: https://storybook.js.org/docs/configure/story-layout

    },
    // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/writing-docs/autodocs
    tags: ['autodocs'],
    // More on argTypes: https://storybook.js.org/docs/api/argtypes
    argTypes: {

    },
} satisfies Meta<typeof Render>;

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const StartingSearch: Story = {
    args: {
        data: "\"1abc2\n" +
            "pqr3stu8vwx\n" +
            "a1b2c3d4e5f\n" +
            "treb7uchet\"",
        tickState: {
            searchState: [
                {
                    line: "1abc2",
                    searchIndex: -1,
                    foundLeftIndex: -1,
                    foundRightIndex: -1,
                    valueFound: null
                },
                {
                    line: "pqr3stu8vwx",
                    searchIndex: -1,
                    foundLeftIndex: -1,
                    foundRightIndex: -1,
                    valueFound: null
                },
                {
                    line: "a1b2c3d4e5f",
                    searchIndex: -1,
                    foundLeftIndex: -1,
                    foundRightIndex: -1,
                    valueFound: null
                },
                {
                    line: "treb7uchet",
                    searchIndex: -1,
                    foundLeftIndex: -1,
                    foundRightIndex: -1,
                    valueFound: null
                }
            ],
            total: 0
        },
        uiActions: {
            action: 7,
            param: "0"
        }
    },
};
