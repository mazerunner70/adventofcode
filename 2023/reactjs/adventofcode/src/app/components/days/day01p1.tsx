import { AdventContext } from "@app/context/Advent.context";
import {useContext, useEffect, useState} from "react";
import { WordGrid, SearchLetter, FoundLetter,
    RespTable, RespTableCaption, RespTableHeader, TableHeaderCell, RespTableBody,
    TableRow, TableCell, TableFooter, TableFooterCell} from "./styled"


 function DisplayLine({line, searchIndex,foundLeftIndex,foundRightIndex, valueFound}: WordSearchState): JSX.Element {
     var indexes: number[] = [searchIndex, foundLeftIndex, foundRightIndex].filter(v=>v != -1).sort()
     console.log(">>", indexes)
     var slices: string[] = [line.slice(0, indexes[0])]
     for (var i = 0; i < indexes.length-1; i++) {
         slices.push(line.slice(indexes[i], indexes[i+1]))
     }
     slices.push(line
         .slice(indexes[indexes.length-1]))
     console.log(">>", slices)
    return (
        <WordGrid>
            <RespTable>
                <RespTableHeader>
                    <TableHeaderCell>Word</TableHeaderCell>
                    <TableHeaderCell>Found</TableHeaderCell>
                </RespTableHeader>
                <RespTableBody>
                    {slices.map((slice, i) => {
                        let initialLetter = slice[0]
                        let restWord = slice.slice(1)
                            return (
                                <TableRow>
                                    <TableCell>
                                        {i == 0 && <span>{slice}</span>}
                                        {i > 0 &&
                                            <span>
                                                {indexes[i-1] === searchIndex?<SearchLetter>{initialLetter}</SearchLetter> : <FoundLetter>{initialLetter}</FoundLetter>}
                                                    <span>{restWord}</span>
                                            </span>
                                        }
                                    </TableCell>
                                    <TableCell>{valueFound}</TableCell>
                                </TableRow>)
                    })}
                </RespTableBody>
            </RespTable>
        </WordGrid>
    )
 }

 interface IRunState {
     searchState: WordSearchState[]
     total: number | null
 }
 interface WordSearchState {
    line: string
    searchIndex: number,
    foundLeftIndex: number,
    foundRightIndex: number,
     valueFound: string | null
 }

export default function Day01P1(): JSX.Element {

    const { state, dispatch } = useContext(AdventContext);
    const [runState, setRunState] = useState<IRunState | null>(null);


    var lines: string[] | null = null;
    if (state?.selected) {
        let dayenv = state?.selected?.dayno+state?.selected?.envName;
        let data = state?state.runState?state.runState.dataState[dayenv]?.data:null:null;
        if (data) {
            lines = data.split("\n");
            //let maxWidth = lines.map((line) => line.length).reduce((a,b) => a>b?a:b);
        }
    }
    function buildSearchState(line: string): WordSearchState {
        return {line, searchIndex: -1, foundLeftIndex: -1, foundRightIndex: -1, valueFound: null}
    }
    if (lines && !runState) {
        setRunState({searchState: Array.from(lines, buildSearchState), total: null})
    }

    useEffect(() => {
        const interval = setInterval(() => {
            //setTime(new Date());
        }, 1000);

        return () => clearInterval(interval);
    }, []);

    function tick() {
        if (!runState) {
            return
        }
        if (runState.total) {
            return
        }

    }

    return (
        <div>
            <h2>Day 1p1 fc</h2>
            {state?.selected?.dayno}
            <div>
                {lines?.map((line, i) => {
                    const rs = runState?.searchState[i]
                    return (
                        rs &&
                        <DisplayLine line={rs.line} searchIndex={rs.searchIndex} foundLeftIndex={rs.foundLeftIndex} foundRightIndex={rs.foundRightIndex} valueFound={rs.valueFound}/>
                    )
                })}
            </div>
        </div>
    )
}