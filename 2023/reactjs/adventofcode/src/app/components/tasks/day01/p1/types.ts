export interface LineSearchState {
  event_type: SearchEventType;
  line_number: number;
  search_index: number;
  found_left_index: number;
  found_left_length: number;
  found_right_index: number;
  found_right_length: number;
  value_found: string;
  total: number;
}

export enum SearchEventType {
  Idle = 0,
  SearchingFromLeft = 1,
  FoundLeftIndex = 2,
  SearchingFromRight = 3,
  FoundRightIndex = 4,
  ValueCalculated = 5,
}

export interface SearchEvent {
  event_type: SearchEventType;
  param: string;
}

export interface IRow {
  columns: string[];
  params: string[];
}
export interface ITicksState {
  linesearchState: LineSearchState[];
  linesCompleted: IRow[];
  emptyLiness: IRow[];
}
