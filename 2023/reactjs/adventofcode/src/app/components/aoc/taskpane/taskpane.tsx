import styled from "styled-components";
import TaskSelection, { ISelectionConfig} from "@app/components/aoc/taskselection";
import React from "react";
import {
    VCRControls
} from "@app/components/aoc/vcrcontrols/vcrcontrols";
import {
    IVCRControlsProps
} from "../../../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/aoc/vcrcontrols/vcrcontrols";


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

    return (
        <TaskPanel>
            <TaskSelection daylist={daylist} selectionConfig={selectionState}/>
            <VCRControls speedState={speedState} onSpeedChange={onSpeedChange}/>
        </TaskPanel>
    )
}