import { ITickState, tick } from "@app/components/days/day01/p1-old/useticker";

describe("ticker test", () => {
  it("should return foundleft for 1abc2", () => {
    const tickState: ITickState = {
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
    };
    const result = tick(tickState);
    expect(result).toEqual({
      uiActions: { action: 5, param: "0" },
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
    });
  });
  it("should return foundleft for pqr3stu8vwx", () => {
    const tickState: ITickState = {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 4,
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
    };
    const result = tick(tickState);
    expect(result).toEqual({
      uiActions: { action: 6, param: "1" },
      tickState: {
        searchState: [
          {
            line: "1abc2",
            searchIndex: 4,
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
    });
  });
  it("continuing search for pqr3stu8vwx", () => {
    const tickState: ITickState = {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 4,
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
    };
    const result = tick(tickState);
    expect(result).toEqual({
      uiActions: { action: 6, param: "1" },
      tickState: {
        searchState: [
          {
            line: "1abc2",
            searchIndex: 4,
            foundLeftIndex: 0,
            foundRightIndex: 4,
            valueFound: 12,
          },
          {
            line: "pqr3stu8vwx",
            searchIndex: 2,
            foundLeftIndex: -1,
            foundRightIndex: -1,
            valueFound: null,
          },
        ],
        total: 0,
      },
    });
  });
  it("foundleft for for pqr3stu8vwx", () => {
    const tickState: ITickState = {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 4,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: 3,
          foundLeftIndex: -1,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    };
    const result = tick(tickState);
    expect(result).toEqual({
      uiActions: { action: 5, param: "1" },
      tickState: {
        searchState: [
          {
            line: "1abc2",
            searchIndex: 4,
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
    });
  });
  it("continuing findright for pqr3stu8vwx", () => {
    const tickState: ITickState = {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 4,
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
    };
    const result = tick(tickState);
    expect(result).toEqual({
      uiActions: { action: 4, param: "1" },
      tickState: {
        searchState: [
          {
            line: "1abc2",
            searchIndex: 4,
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
    });
  });
  it("landed on right for pqr3stu8vwx", () => {
    const tickState: ITickState = {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 4,
          foundLeftIndex: 0,
          foundRightIndex: 4,
          valueFound: 12,
        },
        {
          line: "pqr3stu8vwx",
          searchIndex: 8,
          foundLeftIndex: 3,
          foundRightIndex: -1,
          valueFound: null,
        },
      ],
      total: 0,
    };
    const result = tick(tickState);
    expect(result).toEqual({
      uiActions: { action: 4, param: "1" },
      tickState: {
        searchState: [
          {
            line: "1abc2",
            searchIndex: 4,
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
    });
  });
  it("found right for pqr3stu8vwx", () => {
    const tickState: ITickState = {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 4,
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
    };
    const result = tick(tickState);
    expect(result).toEqual({
      uiActions: { action: 3, param: "1" },
      tickState: {
        searchState: [
          {
            line: "1abc2",
            searchIndex: 4,
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
    });
  });
  it("found both for pqr3stu8vwx", () => {
    const tickState: ITickState = {
      searchState: [
        {
          line: "1abc2",
          searchIndex: 4,
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
    };
    const result = tick(tickState);
    expect(result).toEqual({
      uiActions: { action: 2, param: "1" },
      tickState: {
        searchState: [
          {
            line: "1abc2",
            searchIndex: 4,
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
    });
  });
});
