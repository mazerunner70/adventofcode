import {ITickState, tick} from "@app/components/days/day01/ticker"


describe("ticker test", () => {
    it("should return foundleft for 1abc2", () => {
        const tickState: ITickState = {
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
        }
        const result = tick(tickState)
        expect(result).toEqual({
            uiActions: {action: 5, param: "0"},
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
            }
        })
    });
})