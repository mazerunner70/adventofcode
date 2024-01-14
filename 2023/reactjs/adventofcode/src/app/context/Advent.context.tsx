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

const initialState: {selected: AdventSelected , runState: AdventDayRunState } = {
    selected: {dayno: 0, partName: "", envName: "", envConfig: {filename: "", reference: ""}},
    runState: {dataState:{}}
};

const reducer = (state, action) => {
    //console.log("reducer", action)
    switch (action.type) {
        case "SET_SELECTED": {
            const newState = { ...state, selected: action.payload };
            console.log("SET_SELECTED", action.payload, newState)
            return newState
        }
        case "SET_RUNSTATE": {
              const dayEnv = action.payload.dayenv
            const data = action.payload.data
            const newState = { ...state, runState: { ...state.runState, dataState: {...state.runState.dataState, [dayEnv]: {data}}  } };
            console.log("SET_RUNSTATE", action.payload, newState)
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

