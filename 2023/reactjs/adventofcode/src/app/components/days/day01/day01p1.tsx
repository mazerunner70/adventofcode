import {AdventContext} from "@app/context/Advent.context";
import {useContext, useEffect, useState} from "react";
import {Action, ITickState, LineSearchState, UIActions} from "./ticker";
import {tick} from "@app/components/days/day01/ticker";
import Render from "@app/components/days/day01/render";

export default function Day01P1({envName, data}:{envName: string, data:string}): JSX.Element {

    const [tickState, setTickState] = useState<ITickState>({searchState: [], total: 0});
    const [uiActions, setUiActions] = useState<UIActions>({action: Action.NoAction, param: ""});
    const dayEnv = "1"+envName;
    //console.log("entered Day01P1", tickState, uiActions, dayEnv, data)
    function buildSearchState(line: string): LineSearchState {
        return {line, searchIndex: -1, foundLeftIndex: -1, foundRightIndex: -1, valueFound: null}
    }

    useEffect(() => {
        const searchState = data.split("\n").map(buildSearchState)
        const initTickState = {...tickState, searchState}
        const tickResult = tick(initTickState)
        console.log("Initial tickResult", tickResult)
        setTickState(tickResult.tickState)
        setUiActions(tickResult.uiActions)
    }, [data]);

    function localTick() {
        const tickResult = tick(tickState)
        console.log("localTick", tickResult)
        setTickState(tickResult.tickState)
        setUiActions(tickResult.uiActions)
    }

    useEffect(() => {
        const interval = setInterval(() => {
            // console.log(new Date());
            //localTick()
        }, 4000);
        return () => clearInterval(interval);
    }, []);


   return (
       <div>
           <button onClick={localTick}>Tick</button>
           <Render  data = {data} tickState = {tickState} uiActions = {uiActions} />
       </div>
   )
}