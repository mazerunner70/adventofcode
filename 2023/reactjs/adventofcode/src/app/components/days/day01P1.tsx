import { AdventContext } from "@app/context/Advent.context";
import {useContext, useEffect, useState} from "react";


return data.split("\n").map((line) => {
    const x = line.match(/\d/g)
    return Number(x[0]+x.at(-1))
}).reduce((a,b) => a+b).toString()

export interface Advent {
    config: Config[];
}

export interface Config {
    dayno: number;
    part1: Part;
    part2: Part;
}

export interface Part {
    test: Full;
    full: Full;
}

export interface Full {
    file:      File;
    reference: string;
}

export enum File {
    InputTxt = "input.txt",
    Test2Txt = "test2.txt",
    TestTxt = "test.txt",
}

export default function Day01P1() {

    const [config, setConfig] = useState<Config[] | null>(null);
    const [data, setData] = useState<string | null>(null);
    const { state, dispatch } = useContext(AdventContext);
    const fetchConfig = async () => {
        const resp = await fetch("/assets/config.json");
        const config = await resp.json();
        setConfig(config);
    }
    const fetchData = async (dayno, filename) => {
        const resp = await fetch(`/assets/day${dayno.toString().padStart(2, "0")}/${filename}`);
        const d = await resp.json();
        setData(d);
    }
    useEffect(() => {
        if (state.selected) {
            fetchConfig();
            if (config) {
                fetchData(state.selected.dayno, config)

            }
            fetchData()
        }
    }, [state.selected]);

    return (
        <div>
            <h2>Day 1</h2>
            <p>Part 1: {countSteps(data)}</p>
            <p>Part 2: {execute(data)}</p>
        </div>
    )
}