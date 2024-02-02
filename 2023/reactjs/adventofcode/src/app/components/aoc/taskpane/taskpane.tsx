import styled from "styled-components";
import TaskSelection, { ISelectionConfig} from "@app/components/aoc/taskselection";
import React, {useEffect} from "react";
import {
    VCRControls
} from "@app/components/aoc/vcrcontrols/vcrcontrols";
import ProgressPanel
    , { IProgressData }    from "@app/components/aoc/progresspanel/progresspanel";


const TaskPanel = styled.div`
    width: 100%;
    height: 100%;
    border: 1px solid black
`;

export interface ITaskProps {
    day: number;
    part: number;
    testDataUsed: boolean;
    filename: string;
}


export interface ITaskPaneProps {
    tasks: ITaskProps[]
}

export default function TaskPane ({ data }: { data: ITaskPaneProps }) {
    const [dayNumberState, setDayNumberState] = React.useState<number>(2);
    const [partState, setPartState] = React.useState<number>(1);
    const [testDataUsedState, setTestDataUsedState] = React.useState<boolean>(false);
    const [speedState, setSpeedState] = React.useState<number>(0);
    const [progressData, setProgressData] = React.useState<IProgressData>({totalTicks: 0, currentTick: 0});

    const daylist: number[] = [...new Set(data.tasks.map((task) => task.day))];


    const selectionState: ISelectionConfig = {
        day: dayNumberState,
        part: partState,
        testData: testDataUsedState,
        onDayChange: (newDay: number) => setDayNumberState(newDay),
        onPartChange: (newPart: number) => setPartState(newPart),
        onTestDataChange: (newState: boolean) => setTestDataUsedState(newState),
    }

    console.log(dayNumberState, partState, testDataUsedState);

    function onSpeedChange(speed: number): void {
        setSpeedState(speed);
    }

    useEffect(() => {
        const worker = new Worker('../../../../days/day01/p1/tickcalc.js');
        worker.onmessage = (e: MessageEvent<number>) => {
            setProgressData({...progressData, totalTicks: e.data});
        }
    }, []);



    return (
        <TaskPanel>
            <TaskSelection daylist={daylist} selectionConfig={selectionState}/>
            <VCRControls speedState={speedState} onSpeedChange={onSpeedChange}/>
            <ProgressPanel speedState={speedState} progressData={progressData}/>
        </TaskPanel>
    )
}