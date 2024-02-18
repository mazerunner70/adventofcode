import Panel from "@app/components/base/panel";
import {EvenDistributed, OutlinedBox, PulsingText} from "./styled";
import { H2 } from "@app/components/base/text/textwrappers";



export interface IProgressData {
    totalTicks: number;
    currentTick: number;
}


export default function ProgressPanel({ speedState, progressData }: {speedState: number; progressData: IProgressData} ) {

    const statusText = progressData.totalTicks == 0? "Initialising" : speedState == 0 ? "Idle" : "Running";

    //console.log(config);
    return (
        <Panel shadowed={true}>
            <EvenDistributed>
                <H2>Status</H2>
                <PulsingText>{statusText}</PulsingText>
                <H2>Ticks</H2>
                <PulsingText>{progressData.currentTick} of {progressData.totalTicks}</PulsingText>
            </EvenDistributed>
        </Panel>
    );
}