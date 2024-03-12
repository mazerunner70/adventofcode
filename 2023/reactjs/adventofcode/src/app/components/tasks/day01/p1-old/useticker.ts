 

export interface ITickState {
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

export enum Action {
    NoAction,
    TotalFound,
    ValueFoundForLine,
    DigitFoundForRight,
    SearchingFromRight,
    DigitFoundForLeft,
    SearchingFromLeft,
    StartingSearch
}

export interface UIActions {
    action: Action,
    param: string
}

export interface TickResult {
    uiActions: UIActions,
    tickState: ITickState
}

function uiActionsFoundAllLines(runState: ITickState): boolean {
    return runState.searchState.every((searchState) => searchState.valueFound !== null)
}

function uiActionsFindNextLineThat(pred: (lineSearchState: LineSearchState)=>boolean, lines: LineSearchState[]): number {
    return lines.findIndex(pred)
}
type Predicate<T> = (t: T) => boolean
type LineSearchPredicate = Predicate<LineSearchState>
const not = (pred: LineSearchPredicate) => (searchState: LineSearchState) => !pred(searchState)
const and = (preds: LineSearchPredicate[]) => (searchState: LineSearchState) => preds.every((pred: LineSearchPredicate) => pred(searchState))
const hasNotFoundValue: LineSearchPredicate = (searchState: LineSearchState): boolean => searchState.valueFound === null
const hasFoundLeftIndex = (searchState: LineSearchState): boolean => searchState.foundLeftIndex !== -1
const hasFoundRightIndex = (searchState: LineSearchState): boolean => searchState.foundRightIndex !== -1
const hasStartedSearching = (searchState: LineSearchState): boolean => searchState.searchIndex !== -1
const hasNotStartedSearching = (searchState: LineSearchState): boolean => searchState.searchIndex === -1
const hasStartedSearchingFromLeft = (searchState: LineSearchState): boolean => and([hasNotFoundValue, not(hasFoundLeftIndex), hasStartedSearching])(searchState)
const hasStartedSearchingFromRight = (searchState: LineSearchState): boolean => and([hasNotFoundValue, hasFoundLeftIndex, not(hasFoundRightIndex), hasStartedSearching])(searchState)
const isReadyToFindValue = (searchState: LineSearchState): boolean => and([hasNotFoundValue, hasFoundLeftIndex, hasFoundRightIndex])(searchState)
export function tick(runState: ITickState): TickResult {
    //console.log("tick", runState)
    // If all lines have been searched, return the total
    function isNumber(valueFound: number | null): valueFound is number {
        return valueFound !== undefined;
    }
    if (uiActionsFoundAllLines(runState)) {
        const total = runState.searchState.reduce((acc, lss) => acc + (lss.valueFound?lss.valueFound:0), 0)
        return {
            uiActions: {action: Action.TotalFound, param: total.toString()},
            tickState: {...runState, total: total}
        }
    }

    //Find line that is ready to show value
    const findRowIndexReadyToFindValue = uiActionsFindNextLineThat(isReadyToFindValue, runState.searchState)
    if (findRowIndexReadyToFindValue !== -1) {
        const searchStateToProcess = runState.searchState[findRowIndexReadyToFindValue]
        const valueFound = searchStateToProcess.line[searchStateToProcess.foundLeftIndex] + searchStateToProcess.line[searchStateToProcess.foundRightIndex]
        return {
            uiActions: {action: Action.ValueFoundForLine, param: findRowIndexReadyToFindValue.toString()},
            tickState: {
                ...runState,
                searchState: [
                    ...runState.searchState.slice(0, findRowIndexReadyToFindValue),
                    {...searchStateToProcess, valueFound: parseInt(valueFound), searchIndex: -2},
                    ...runState.searchState.slice(findRowIndexReadyToFindValue + 1)
                ],
                total: runState.searchState.reduce((acc, lss) => acc + (lss.valueFound?lss.valueFound:0), 0) + parseInt(valueFound)
            }
        }
    }

    const isDigit: (s: string) => boolean = s => !isNaN(Number(s))

    const findRowIndexSearchingFromRight = uiActionsFindNextLineThat(hasStartedSearchingFromRight, runState.searchState)
    if (findRowIndexSearchingFromRight !== -1) {
        const searchStateToProcess = runState.searchState[findRowIndexSearchingFromRight]
        if (isDigit(searchStateToProcess.line[searchStateToProcess.searchIndex])) {
            const foundRightIndex = searchStateToProcess.searchIndex
            const searchIndex = -1
            return {
                uiActions: {action: Action.DigitFoundForRight, param: findRowIndexSearchingFromRight.toString()},
                tickState: {
                    ...runState,
                    searchState: [
                        ...runState.searchState.slice(0, findRowIndexSearchingFromRight),
                        {...searchStateToProcess, foundRightIndex: foundRightIndex, searchIndex: searchIndex},
                        ...runState.searchState.slice(findRowIndexSearchingFromRight + 1)
                    ]
                }
            }
        }
        return {
            uiActions: {action: Action.SearchingFromRight, param: findRowIndexSearchingFromRight.toString()},
            tickState: {
                ...runState,
                searchState: [
                    ...runState.searchState.slice(0, findRowIndexSearchingFromRight),
                    {...searchStateToProcess, searchIndex: searchStateToProcess.searchIndex - 1},
                    ...runState.searchState.slice(findRowIndexSearchingFromRight + 1)
                ]
            }
        }
    }
    const findRowIndexSearchingFromLeft = uiActionsFindNextLineThat(hasStartedSearchingFromLeft, runState.searchState)
    if (findRowIndexSearchingFromLeft !== -1) {
        const searchStateToProcess = runState.searchState[findRowIndexSearchingFromLeft]
        if (isDigit(searchStateToProcess.line[searchStateToProcess.searchIndex])) {
            const foundLeftIndex = searchStateToProcess.searchIndex
            const searchIndex = searchStateToProcess.line.length-1
            return {
                uiActions: {action: Action.DigitFoundForLeft, param: findRowIndexSearchingFromLeft.toString()},
                tickState: {
                    ...runState,
                    searchState: [
                        ...runState.searchState.slice(0, findRowIndexSearchingFromLeft),
                        {...searchStateToProcess, foundLeftIndex: foundLeftIndex, searchIndex: searchIndex},
                        ...runState.searchState.slice(findRowIndexSearchingFromLeft + 1)
                    ]
                }
            }
        }
        return {
            uiActions: {action: Action.SearchingFromLeft, param: findRowIndexSearchingFromLeft.toString()},
            tickState: {
                ...runState,
                searchState: [
                    ...runState.searchState.slice(0, findRowIndexSearchingFromLeft),
                    {...searchStateToProcess, searchIndex: searchStateToProcess.searchIndex + 1},
                    ...runState.searchState.slice(findRowIndexSearchingFromLeft + 1)
                ]
            }
        }
    }
    const findRowIndexNotStartedSearching = uiActionsFindNextLineThat(hasNotStartedSearching, runState.searchState)
    if (findRowIndexNotStartedSearching !== -1) {
        const searchStateToProcess = runState.searchState[findRowIndexNotStartedSearching]
        return {
            uiActions: {action: Action.StartingSearch, param: findRowIndexNotStartedSearching.toString()},
            tickState: {
                ...runState,
                searchState: [
                    ...runState.searchState.slice(0, findRowIndexNotStartedSearching),
                    {...searchStateToProcess, searchIndex: 0},
                    ...runState.searchState.slice(findRowIndexNotStartedSearching + 1)
                ]
            }
        }
    }
    throw new Error("Should not reach here")
}