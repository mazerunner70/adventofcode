"use client"

import React, { createContext, useReducer, type Dispatch, } from "react";

interface AdventSelected {
    dayno: string,
    partName: string,
    envName: string
}

const initialState: {selected: AdventSelected | null, runState: {} | null} = {
    selected: null,
    runState: {}
};

const reducer = (state, action) => {
    switch (action.type) {
        case "SET_SELECTED": {
            console.log("SET_SELECTED", action.payload)
            return { ...state, selected: action.payload };
        }
        case "SET_RUNSTATE": {
            console.log("SET_RUNSTATE", action.payload)
            let pk = action.payload.pk
            let pv = action.payload.pv
            return { ...state, runState: { ...state.runState, pk : pv } };
        }
        default: {
            console.log("default", action)
            return state;
        }
    }
};

export const AdventContext = createContext({
    state: initialState,
    dispatch: (() => undefined) as Dispatch<any>,
});

export const AdventContextProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <AdventContext.Provider value={{ state, dispatch }}>
            {children}
        </AdventContext.Provider>
    );
};