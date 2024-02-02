import {pairwise} from "@app/utils/ArrayOps";
import {
    FoundLetter,
    RespTable, RespTableBody,
    RespTableHeader,
    SearchLetter,
    TableCell, TableHeaderCell,
    TableRow,
    WordGrid
} from "./styled";
import {Action, ITickState, LineSearchState, UIActions} from "@app/components/days/day01/p1/ticker";
import {useEffect, useRef} from "react";
import gsap from "gsap";



function RenderLine({line, searchIndex, foundLeftIndex, foundRightIndex, valueFound, uiActions}: LineSearchState & {uiActions: UIActions}): JSX.Element {

    const tableCellRef = useRef(null);
    const foundValueRef= useRef(null);
    useEffect(() => {
        //console.log("RenderLine useEffect", uiActions, Action.StartingSearch)
        if (uiActions.action === Action.StartingSearch) {
            const scaleTween = gsap.to(tableCellRef.current, {scale:1.1, repeat:3, x:5, yoyo:true, paused:true, color: "yellow"});
            scaleTween.play()
            //console.log("scaleTween1")
        }
        if (uiActions.action === Action.ValueFoundForLine) {
            const scaleTween = gsap.to(foundValueRef.current, {scale:1.1, repeat:3, x:5, yoyo:true, paused:true, color: "yellow"});
            scaleTween.play()
            //console.log("scaleTween2")
        }
    }, [uiActions.action]);
    function compareNumbers(a, b) {
        return a - b;
    }
    const indexes: number[] = [0, searchIndex, foundLeftIndex, foundRightIndex, line.length].filter(v => v != -1).sort(compareNumbers);
    //console.log(">", indexes)
    const slices: string[] = Array.from(pairwise(indexes)).map((s, i) => line.slice(s[0], s[1]))
    //console.log(">>", slices)
    return (<TableRow>
                <TableCell ref={tableCellRef}>
                    {slices.map((slice, i) => {
                        const headSlice = slice[0]
                        const tailSlice = slice.slice(1)
                        return (
                            i === 0 ?
                            <span key={i}>{slice}</span>
                            :
                            <span key={i}>{indexes[i] === foundLeftIndex || indexes[i] === foundRightIndex?
                                    <FoundLetter>{headSlice}</FoundLetter>
                                        : indexes[i] === searchIndex?
                                    <SearchLetter>{headSlice}</SearchLetter>
                                        :
                                    <span>{headSlice}</span>
                                }
                                <span>{tailSlice}</span>
                            </span>
                        )
                                            })}
                </TableCell>
            <TableCell ref={foundValueRef}>{valueFound}</TableCell>
        </TableRow>
    )
}
const noUIActions:UIActions = {
    action: Action.NoAction,
    param: ""
}
export default function Render({data, tickState, uiActions}:
{data:string, tickState:ITickState, uiActions: UIActions}): JSX.Element {
    const foundValueRef= useRef(null);
    useEffect(() => {

        if (uiActions.action === Action.ValueFoundForLine) {
            const scaleTween = gsap.to(foundValueRef.current, {scale:1.1, repeat:3, x:5, yoyo:true, paused:true, color: "yellow"});
            scaleTween.play()
            console.log("scaleTween2")
        }
    }, [uiActions.action]);
    const lines = data.split("\n");
    //console.log("entered Render Day01", lines, data, tickState, uiActions);
    return (
        <div>
            <div>
                <WordGrid>
                    <RespTable>
                        <RespTableBody>
                            <TableRow>
                                <TableCell>Total</TableCell>
                                <TableCell ref={foundValueRef}>{tickState.total}</TableCell>
                            </TableRow>
                        </RespTableBody>
                    </RespTable>
                    <RespTable>
                        <RespTableHeader>
                            <TableHeaderCell>Word</TableHeaderCell>
                            <TableHeaderCell>Found</TableHeaderCell>
                        </RespTableHeader>
                        <RespTableBody>
                            {lines.map((line, i) => {
                                const rs = tickState.searchState[i]
                                return (
                                    rs &&
                                    <RenderLine key={i} uiActions={uiActions.param === i.toString()? uiActions:noUIActions} {...rs} />
                                )
                            })}
                        </RespTableBody>
                    </RespTable>
                </WordGrid>
            </div>
        </div>
    )
}