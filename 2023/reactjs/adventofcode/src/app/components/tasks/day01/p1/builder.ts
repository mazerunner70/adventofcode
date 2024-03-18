import { RowProps } from "@app/components/renderers/table/TableRenderer";
import { Highlight } from "@app/components/tasks/day01/util/StringHighlighter";
import { LineSearchState, SearchEventType } from "./types";
import { IRow } from "./types";

export function highlightRules(rowProps: RowProps) {
  // console.log("highlightRules", rowProps, parseInt(rowProps.params[0]));
  const rules: Highlight[] = [];
  if (rowProps.params[0] !== "-1") {
    rules.push({
      index: parseInt(rowProps.params[0]),
      length: 1,
      textColour: "red",
    });
  }
  if (rowProps.params[1] !== "-1") {
    rules.push({
      index: parseInt(rowProps.params[1]),
      length: parseInt(rowProps.params[3]),
      textColour: "blue",
    });
  }
  if (rowProps.params[2] !== "-1") {
    rules.push({
      index: parseInt(rowProps.params[2]),
      length: parseInt(rowProps.params[4]),
      textColour: "blue",
    });
  }
  return rules;
}

export function buildCompletedLineStates(
  ticks: LineSearchState[],
  dataRows: string[],
): IRow[] {
  return ticks
    .filter((t) => t.event_type === SearchEventType.ValueCalculated)
    .map((v, i) => {
      return { columns: [dataRows[i], v.value_found], params: [] };
    });
}

export function buildLineState(
  searchState: LineSearchState,
  dataRow: string,
): IRow {
  switch (searchState.event_type) {
    case SearchEventType.Idle:
      return { columns: [dataRow, ""], params: ["-1", "-1", "-1", "1", "1"] };
    case SearchEventType.SearchingFromLeft:
      return {
        columns: [dataRow, ""],
        params: [searchState.search_index.toString(), "-1", "-1", "1", "1"],
      };
    case SearchEventType.FoundLeftIndex:
      return {
        columns: [dataRow, ""],
        params: [
          "-1",
          searchState.found_left_index.toString(),
          "-1",
          searchState.found_left_length.toString(),
          "1",
        ],
      };
    case SearchEventType.SearchingFromRight:
      return {
        columns: [dataRow, ""],
        params: [
          searchState.search_index.toString(),
          searchState.found_left_index.toString(),
          "-1",
          searchState.found_left_length.toString(),
          "1",
        ],
      };
    case SearchEventType.FoundRightIndex:
      return {
        columns: [dataRow, ""],
        params: [
          "-1",
          searchState.found_left_index.toString(),
          (
            searchState.found_right_index -
            searchState.found_right_length +
            1
          ).toString(),
          searchState.found_left_length.toString(),
          searchState.found_right_length.toString(),
        ],
      };
    case SearchEventType.ValueCalculated:
      return {
        columns: [dataRow, searchState.value_found],
        params: ["-1", "-1", "-1", "1", "1"],
      };
  }
}

export function buildEmptyLines(dataRows: string[]): IRow[] {
  return dataRows.map((v) => {
    return { columns: [v, ""], params: ["-1", "-1", "-1"] };
  });
}
