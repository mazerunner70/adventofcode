import { AdventContext } from "@app/context/Advent.context";
import {useContext, useEffect, useState} from "react";
import { WordGrid, SearchLetter, FoundLetter,
    RespTable, RespTableCaption, RespTableHeader, TableHeaderCell, RespTableBody,
    TableRow, TableCell, TableFooter, TableFooterCell} from "./styled"
import {
    pairwise
} from "@app/utils/ArrayOps";


 function DisplayLine({line, searchIndex,foundLeftIndex,foundRightIndex, valueFound}: WordSearchState): JSX.Element {
     var indexes: number[] = [0, searchIndex, foundLeftIndex, foundRightIndex, line.length].filter(v=>v != -1).sort()
     //console.log(">>", indexes)
     const slices2: string[] = indexes.map((v,i) => line.slice(v, indexes[i+1]))
     const slices: string[] = Array.from(pairwise(indexes)).map((s, i) => line.slice(s[0], s[1]))
     //console.log(">>", slices)
    return ( <TableRow>
        <TableCell >
            {slices.map((slice, i) => {
                let initialLetter = slice[0]
                let restWord = slice.slice(1)
                //console.log("<<", initialLetter, restWord)
                return ( i == 0 ?
                    <span key={i}>{slice}</span>
                        :
                    <span key={i}>
                        {indexes[i-1] === searchIndex?<SearchLetter>{initialLetter}</SearchLetter> : <FoundLetter>{initialLetter}</FoundLetter>}
                            <span>{restWord}</span>
                    </span>

                )
            })}
        </TableCell>
            <TableCell>{valueFound}</TableCell>
        </TableRow>

    )
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
            //console.log(new Date());
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
                <WordGrid>
                    <RespTable>
                        <RespTableHeader>
                            <TableHeaderCell>Word</TableHeaderCell>
                            <TableHeaderCell>Found</TableHeaderCell>
                        </RespTableHeader>
                        <RespTableBody>

                            {lines?.map((line, i) => {
                    const rs = runState?.searchState[i]
                    return (
                        rs &&
                        <DisplayLine key={i} line={rs.line} searchIndex={rs.searchIndex} foundLeftIndex={rs.foundLeftIndex} foundRightIndex={rs.foundRightIndex} valueFound={rs.valueFound}/>
                    )
                })}
                        </RespTableBody>
                    </RespTable>
                </WordGrid>
            </div>
        </div>
    )
}