import { Action, ITickState, LineSearchState, tick } from "./ticker";
export default () => {
  function buildSearchState(line: string): LineSearchState {
    return {
      line,
      searchIndex: -1,
      foundLeftIndex: -1,
      foundRightIndex: -1,
      valueFound: null,
    };
  }
  async function calcTotalTicks(filename: string) {
    const resp = fetch(`/assets/day01/${filename}`).then((resp) => resp.text());
    //dispatch({ type: "SET_RUNSTATE", payload: { dayenv:dayno+envName, data:d } });
    const data = await resp;
    console.log("day01 part1");
    const lines = data.split("\n");
    const startState: ITickState = {
      searchState: lines.map((line) => buildSearchState(line)),
      total: 0,
    };
    const tickResult = tick(startState);
    let ctr = 1;
    while (ctr < 10 && tickResult.uiActions.action !== Action.TotalFound) {
      //tickResult = tick(tickResult.tickState);
      console.log("tickResult", ctr, tickResult);
      ctr++;
    }
    return ctr;
  }

  onmessage = async (event) => {
    calcTotalTicks(event.data.filename).then((result) => {
      self.postMessage(result);
    });
  };
  // console.log("event1", event);
  // postMessage(5);
  // };
};
