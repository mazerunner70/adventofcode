import type { Meta, StoryObj } from '@storybook/react';

import Render from '@app/components/days/day01/render';

const meta: Meta<typeof Render> = {
    component: Render,
};

export default meta;
type Story = StoryObj<typeof meta>;

// More on writing stories with args: https://storybook.js.org/docs/writing-stories/args
export const StartingSearchLine1: Story = {
    args: {
        data: "\"1abc2\n" +
            "pqr3stu8vwx\n" ,
        tickState: {
            searchState: [
                {
                    line: "1abc2",
                    searchIndex: 0,
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
export const StartingSearchLine2: Story = {
    args: {
        data: "\"1abc2\n" +
            "pqr3stu8vwx\n",
        tickState: {
            searchState: [
                {
                    line: "1abc2",
                    searchIndex: 0,
                    foundLeftIndex: 0,
                    foundRightIndex: 4,
                    valueFound: 12
                },
                {
                    line: "pqr3stu8vwx",
                    searchIndex: 0,
                    foundLeftIndex: -1,
                    foundRightIndex: -1,
                    valueFound: null
                }

            ],
            total: 0
        },
        uiActions: {
            action: 7,
            param: "1"
        }
    },

};
export const FindingLeft: Story = {
    args: {
        data: "\"1abc2\n" +
            "pqr3stu8vwx\n" +
            "a1b2c3d4e5f\n" +
            "treb7uchet\"",
        tickState: {
            searchState: [
                {
                    line: "1abc2",
                    searchIndex: 4,
                    foundLeftIndex: 0,
                    foundRightIndex: -1,
                    valueFound: null
                },
                {
                    line: "pqr3stu8vwx",
                    searchIndex: -1,
                    foundLeftIndex: -1,
                    foundRightIndex: -1,
                    valueFound: null
                }

            ],
            total: 0
        },
        uiActions: {
            action: 5,
            param: "0"
        }
    }
};