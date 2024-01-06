'use client'
import {useContext, useEffect, useState } from 'react';
import styles from './styles.module.css'
import {InitialisedContext} from "@app/context/Initialised.context";
import Advent
    from "../../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/advent";



export default function Page() {
    const { state, dispatch } = useContext(InitialisedContext);
    const [config , setConfig] = useState(null);
    const fetchConfig = async () => {
        const resp = await fetch("/assets/config.json");
        const config = await resp.json();
        setConfig(config);
    }
    useEffect(() => {
        if (state.initialised) {
            fetchConfig();
        }
    }, [state]);


    return (
        <div>
            <h1>Works</h1>
            { state.initialised && config && <Advent data={config}/>}
        </div>

    )

}