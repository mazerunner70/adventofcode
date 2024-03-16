import {
  highlightRules,
  buildCompletedLineStates,
  buildEmptyLines,
} from "@app/components/tasks/day01/p1/builder";
import {
  SearchEventType,
  LineSearchState,
} from "@app/components/tasks/day01/p1/types";

describe("Day01 part1 tests", () => {
  it("highlight builder tests1", () => {
    const rules = highlightRules({
      columns: ["This is a line of text", "2"],
      params: ["-1", "-1", "-1"],
    });
    expect(rules.length).toBe(0);
  });
  it("highlight builder tests2", () => {
    const rules = highlightRules({
      columns: ["This is a line of text", "2"],
      params: ["0", "-1", "-1"],
    });
    expect(rules.length).toBe(1);
    expect(rules[0].index).toBe(0);
    expect(rules[0].length).toBe(1);
    expect(rules[0].textColour).toBe("red");
  });
  it("buildcompletedlines tests", () => {
    const lineSearchState: LineSearchState[] = [
      {
        event_type: SearchEventType.SearchingFromRight,
        line_number: 0,
        search_index: 0,
        found_left_index: -1,
        found_right_index: -1,
        value_found: "",
        total: 0,
      },
      {
        event_type: SearchEventType.ValueCalculated,
        line_number: 1,
        search_index: -1,
        found_left_index: -1,
        found_right_index: -1,
        value_found: "2",
        total: 2,
      },
    ];
    const dataRows = ["This is a line of text", "This is another line of text"];
    const rows = buildCompletedLineStates(lineSearchState, dataRows);
    expect(rows.length).toBe(1);
    expect(rows[0].columns[0]).toBe("This is a line of text");
    expect(rows[0].columns[1]).toBe("2");
  });
  it("buildcompletedlines tests", () => {
    const lineSearchState: LineSearchState[] = [
      {
        event_type: SearchEventType.ValueCalculated,
        line_number: 0,
        search_index: 0,
        found_left_index: -1,
        found_right_index: -1,
        value_found: "5",
        total: 2,
      },
      {
        event_type: SearchEventType.ValueCalculated,
        line_number: 1,
        search_index: -1,
        found_left_index: -1,
        found_right_index: -1,
        value_found: "3",
        total: 8,
      },
    ];
    const dataRows = ["This is a line of text", "This is another line of text"];
    const rows = buildCompletedLineStates(lineSearchState, dataRows);
    expect(rows.length).toBe(2);
    expect(rows[0].columns[0]).toBe("This is a line of text");
    expect(rows[0].columns[1]).toBe("5");
  });
  it("buildEmptyLines tests", () => {
    const dataRows = ["This is a line of text", "This is another line of text"];
    const rows = buildEmptyLines(dataRows);
    expect(rows.length).toBe(2);
    expect(rows[0].columns[0]).toBe("This is a line of text");
    expect(rows[0].columns[1]).toBe("");
    expect(rows[0].params.length).toBe(3);
  });
});
