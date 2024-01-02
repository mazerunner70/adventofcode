import { fetchConfig, fetchDayConfig } from "./adventAPI";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";





export const initialState: AdventState = {
    days: [],
    calculationStatus: "idle",
}

export const fetchConfigAsync = createAsyncThunk(
    "advent/fetchConfig",
    async () => {
        const response = await fetchConfig()
        // The value we return becomes the `fulfilled` action payload
        return response
    },
)
export const fetchDayConfigAsync = createAsyncThunk(
    "advent/fetchDayConfig",
    async () => {
        const response = await fetchDayConfig()
        // The value we return becomes the `fulfilled` action payload
        return response.data
    },
)

export const adventSlice = createSlice({
    name: "advent",
    initialState,
    reducers: {
        setAdventConfig(state, action) {
            state.days = action.payload
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchConfigAsync.pending, (state) => {
                state.calculationStatus = "loading"
            })
            .addCase(fetchConfigAsync.fulfilled, (state, action) => {
                state.calculationStatus = "done"
                state.days = action.payload
            })
    },
    
})

export const { setAdventConfig } = adventSlice.actions

export const selectAdventConfig = (state: RootState) => state.advent.days

export default adventSlice.reducer

