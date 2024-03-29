import { SearchEvent, SearchEventType, LineSearchState } from "./types";

function as_event(str: string): SearchEvent {
  const [event_type, param] = str.split("-");
  return {
    event_type: parseInt(event_type) as SearchEventType,
    param,
  };
}

export function asEvents(strings: string[]): SearchEvent[] {
  return strings.map(as_event);
}
export function emptySearchState(line_number: number): LineSearchState {
  return {
    event_type: SearchEventType.Idle,
    line_number: line_number,
    search_index: -1,
    found_left_index: -1,
    found_left_length: 1,
    found_right_index: -1,
    found_right_length: 1,
    value_found: "",
    total: 0,
  };
}
export function build_line_search_states(
  events: SearchEvent[],
): LineSearchState[] {
  const lineSearchStates: LineSearchState[] = [];
  let line_number = 0;
  let lineSearchState: LineSearchState = emptySearchState(line_number);
  for (const event of events) {
    console.log("--event", event);
    lineSearchState.event_type = event.event_type;
    switch (event.event_type) {
      case SearchEventType.SearchingFromLeft:
        lineSearchState.search_index = parseInt(event.param);
        break;
      case SearchEventType.FoundLeftIndex: {
        console.log("event.param", event.param);
        const params = event.param.split(",");
        lineSearchState.found_left_index = parseInt(params[0]);
        lineSearchState.found_left_length =
          params.length == 1 ? 1 : parseInt(params[1]);
        lineSearchState.search_index = -1;
        break;
      }
      case SearchEventType.SearchingFromRight:
        lineSearchState.search_index = parseInt(event.param);
        break;
      case SearchEventType.FoundRightIndex: {
        const params = event.param.split(",");
        lineSearchState.found_right_index = parseInt(params[0]);
        lineSearchState.found_right_length =
          params.length == 1 ? 1 : parseInt(params[1]);
        lineSearchState.search_index = -1;
        break;
      }
      case SearchEventType.ValueCalculated:
        lineSearchState.value_found = event.param;
        lineSearchState.total += parseInt(event.param);
        break;
    }
    lineSearchStates.push(lineSearchState);
    if (event.event_type === SearchEventType.ValueCalculated) {
      line_number++;
      const total = lineSearchState.total;
      lineSearchState = emptySearchState(line_number);
      lineSearchState.total = total;
    } else {
      lineSearchState = { ...lineSearchState };
    }
  }
  return lineSearchStates;
}
