import {AdventContext} from "@app/context/Advent.context";
import {useContext, useEffect, useState} from "react";
import {Action, ITickState, LineSearchState, UIActions} from "./ticker";
import {tick} from "@app/components/days/day01/ticker";
import Render from "@app/components/days/day01/render";

export default function Day01P1(): JSX.Element {

    const { state, dispatch } = useContext(AdventContext);
    const [tickState, setTickState] = useState<ITickState>({searchState: [], total: 0});
    const [uiActions, setUiActions] = useState<UIActions>({action: Action.NoAction, param: ""});

    const dayEnv = "1"+state.selected.envName;
    console.log("dayEnv", dayEnv)
    console.log("state", state)
    const data = state.runState.dataState[dayEnv]?.data?? "";
    console.log("data", data)
    function buildSearchState(line: string): LineSearchState {
        return {line, searchIndex: -1, foundLeftIndex: -1, foundRightIndex: -1, valueFound: null}
    }
    if (data && tickState.searchState.length === 0 ) {
        setTickState({searchState: Array.from(data.split("\n"), buildSearchState), total: 0})
    }

    useEffect(() => {
        const interval = setInterval(() => {
            //console.log(new Date());
        }, 1000);

        return () => clearInterval(interval);
    }, []);

    useEffect(() => {
        localTick()
    }, []);
    function localTick() {
        if (!tickState) {
            return
        }
        const newState = tick(tickState)
        console.log("NewState", newState)
        setTickState(newState.runState)
        setUiActions(newState.uiActions)
    }

   return (
       <Render  data = {data} tickState = {tickState} uiActions = {uiActions} />
   )
}