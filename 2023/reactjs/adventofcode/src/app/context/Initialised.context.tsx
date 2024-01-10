"use client"

import React, { createContext, useReducer, type Dispatch, } from "react";

const initialState = {
    initialised: false,
};

const reducer = (state, action) => {
    switch (action.type) {
        case "SET_INITIALISED": {
            console.log("SET_INITIALISED", action.payload)
            return { ...state, initialised: action.payload };
        }
        default: {
            console.log("default", action)
            return state;
        }
    }
};

export const InitialisedContext = createContext({
    state: initialState,
    dispatch: (() => undefined) as Dispatch<any>,
});

export const InitialisedContextProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <InitialisedContext.Provider value={{ state, dispatch }}>
            {children}
        </InitialisedContext.Provider>
    );
};