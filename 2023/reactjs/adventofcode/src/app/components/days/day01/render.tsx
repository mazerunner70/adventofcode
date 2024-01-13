import {pairwise} from "@app/utils/ArrayOps";
import {
    FoundLetter,
    RespTable, RespTableBody,
    RespTableHeader,
    SearchLetter,
    TableCell, TableHeaderCell,
    TableRow,
    WordGrid
} from "@app/components/days/day01/styled";
import {Action, ITickState, LineSearchState, UIActions} from "@app/components/days/day01/ticker";


function RenderLine({line, searchIndex, foundLeftIndex, foundRightIndex, valueFound, uiActions}: LineSearchState & {uiActions: UIActions}): JSX.Element {
    const indexes: number[] = [0, searchIndex, foundLeftIndex, foundRightIndex, line.length].filter(v => v != -1).sort();
    //console.log(">>", indexes)
    const slices: string[] = Array.from(pairwise(indexes)).map((s, i) => line.slice(s[0], s[1]))
    //console.log(">>", slices)
    return (<TableRow>
                <TableCell >
                    {slices.map((slice, i) => {
                        const initialLetter = slice[0]
                        const restWord = slice.slice(1)
                        //console.log("<<", initialLetter, restWord)
                        return ( i == 0 ?
                                <span key={i}>{slice}</span>
                                :
                                <span key={i}>{indexes[i-1] === searchIndex?
                                    <SearchLetter>{initialLetter}</SearchLetter>
                                    :
                                    <FoundLetter>{initialLetter}</FoundLetter>}
                                <span>{restWord}</span>
                        </span>
                        )
                    })}
                </TableCell>
            <TableCell>{valueFound}</TableCell>
        </TableRow>
    )
}
const noUIActions:UIActions = {
    action: Action.NoAction,
    param: ""
}
export default function Render({data, tickState, uiActions}:
{data:string, tickState:ITickState, uiActions: UIActions}): JSX.Element {

    const lines = data.split("\n");
    console.log(lines)
    return (
        <div>
            <div>
                <WordGrid>
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