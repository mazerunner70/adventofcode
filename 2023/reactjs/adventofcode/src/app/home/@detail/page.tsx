'use client'

import {useContext, useEffect} from 'react';
import styles from './styles.module.css'
import { AdventContext } from '@app/context/Advent.context';
import DetailHeader from "@app/components/detailheader/DetailHeader";
import Day01P1 from "@app/components/days/day01p1";

const handleClick = async () => {
    console.log("here1")
}
export default function Page() {
    const { state, dispatch } = useContext(AdventContext);
    const fetchData = async (dayno: number, envName: string, filename: string) => {
        const resp = await fetch(`/assets/day${dayno.toString().padStart(2, "0")}/${filename}`);
        const d = await resp.text();
        dispatch({ type: "SET_RUNSTATE", payload: { dayenv:dayno+envName, data:d } });
    }

    useEffect(() => {
        if (state.selected) {
            fetchData(state.selected.dayno, state.selected.envName, state.selected.envConfig.filename);
        }
    }, [state.selected]);
    console.log("--", state)
    return (
        <div>
                {state.selected &&
                    <div>
                        <DetailHeader config={state.selected}/>
                        {state.selected.dayno === 1 && state.selected.partName === "Part 1" &&
                            <div>
                                <h1>Day 1 Part 1</h1>
                                <Day01P1 />
                            </div>
                        }
                    </div>
                }
        </div>

    )

}