"use client"

import React, { createContext, useReducer, type Dispatch, } from "react";

export interface AdventSelected {
    dayno: number,
    partName: string,
    envName: string,
    envConfig: IAdventEnv
}

export interface AdventDayRunState {
    dataState: { [dayenv: string]:AdventRunState}
}
export interface AdventRunState {
    data: string
}

const initialState: {selected: AdventSelected | null, runState: AdventDayRunState | null} = {
    selected: null,
    runState: {dataState:{}}
};

const reducer = (state, action) => {
    console.log("reducer", action)
    switch (action.type) {
        case "SET_SELECTED": {
            console.log("SET_SELECTED", action.payload)
            return { ...state, selected: action.payload };
        }
        case "SET_RUNSTATE": {
            console.log("SET_RUNSTATE", action.payload)
            let dayenv = action.payload.dayenv
            let data = action.payload.data
            let newState = { ...state, runState: { ...state.runState, dataState: {...state.runState.dataState, [dayenv]: {data}}  } };
            console.log("newState", newState)
            return newState;
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

export interface IAdvent {
    config: IConfig[];
}

export interface IConfig {
    dayno: number;
    part1: IPart;
    part2: IPart;
}

export interface IPart {
    test: IAdventEnv;
    full: IAdventEnv;
}

export interface IAdventEnv {
    filename:      string;
    reference: string;
}

