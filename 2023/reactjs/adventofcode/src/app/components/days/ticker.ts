

export interface IRunState {
    searchState: LineSearchState[]
    total: number
}

export interface LineSearchState {
    line: string
    searchIndex: number,
    foundLeftIndex: number,
    foundRightIndex: number,
    valueFound: number | null
}

export interface UIActions {
    totalFound: boolean
}

export interface TickResult {
    uiActions: UIActions,
    runState: IRunState
}

export function tick(runState: IRunState): TickResult {
    function isNumber(valueFound: number | null): valueFound is number {
        return valueFound !== undefined;
    }
    const finishedLines = runState.searchState.map((searchState) => searchState.valueFound).filter(isNumber)
    const allLinesFound = finishedLines.length === runState.searchState.length
    const totalSoFar = finishedLines.reduce((acc, value) => acc + value, 0)
    if (allLinesFound) {
        totalFound: true
        return {
            uiActions: {totalFound: true},
            runState: {...runState, total: totalSoFar}
        }
    }

    const searchStateIndextoProcess = runState.searchState.findIndex((searchState) => searchState.valueFound === null)
    

}