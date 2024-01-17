'use client'

import {useContext, useEffect, useState} from 'react';
import { AdventContext } from '@app/context/Advent.context';
import DetailHeader from "@app/components/detailheader/detailheader";
import Day01P1 from "@app/components/days/day01/day01p1";


export default function Page() {
    const { state, dispatch } = useContext(AdventContext);
    const [data, setData] = useState("");
    const fetchData = async (dayno: number, envName: string, filename: string) => {
        const resp = await fetch(`/assets/day${dayno.toString().padStart(2, "0")}/${filename}`);
        const d = await resp.text();
        //dispatch({ type: "SET_RUNSTATE", payload: { dayenv:dayno+envName, data:d } });
        setData(d);
    }

    useEffect(() => {
        if (state.selected.dayno !== 0) {
            fetchData(state.selected.dayno, state.selected.envName, state.selected.envConfig.filename);
        }
    }, [state.selected.dayno, state.selected.envName, state.selected.envConfig.filename]);
    //console.log("--", state)
    return (
        <div>
                {state.selected.dayno !== 0 &&
                    <div>
                        <DetailHeader config={state.selected}/>
                        {state.selected.dayno === 1 && state.selected.partName === "Part 1" &&
                            <div>
                                <h1>Day 1 Part 1</h1>
                                <Day01P1 envName={state.selected.envName} data={data}/>
                            </div>
                        }
                    </div>
                }
        </div>

    )

}