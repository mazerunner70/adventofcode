import { ITickState } from "./ticker";

const calcTotalTicks = async (
    dayno: number,
    envName: string,
    filename: string,
) => {
    const resp = await fetch(
        `/assets/day${dayno.toString().padStart(2, "0")}/${filename}`,
    );
    const d = await resp.text();
    //dispatch({ type: "SET_RUNSTATE", payload: { dayenv:dayno+envName, data:d } });

    console.log("part1")
    const lines = data.split("\n")
    const startState: ITickState = {
        searchState: lines.map((line) => {
                line: line,
                searchIndex:-1,
                foundLeftIndex:-1,
                foundRightIndex:-1,
                valueFound: null
        }),
        total: 0
    };
    const tickResult = tick(startState)
    const ctr = 1;
    while (tickResult.uiActions.action !== Action.TotalFound) {
        tickResult = tick(tickResult.tickState)
        ctr++
    }
    return ctr;
};

self.onmessage = (event) => {
    const result = calcTotalTicks();
    self.postMessage(result);
};