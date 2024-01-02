import { configureStore } from '@reduxjs/toolkit';
import adventReducer from '../features/advent/adventSlice';

export const store = configureStore({
    reducer: {
        advent: adventReducer,
    },
});

export type AppDispatch = typeof store.dispatch
export type RootState = ReturnType<typeof store.getState>
export type AppThunk<ReturnType = void> = ThunkAction<
    ReturnType,
    RootState,
    unknown,
Action<string>
>