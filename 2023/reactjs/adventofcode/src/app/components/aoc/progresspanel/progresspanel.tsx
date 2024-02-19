import Panel from "@app/components/base/panel";
import {EvenDistributed, OutlinedBox, PulsingText} from "./styled";
import { H3, H4 } from "@app/components/base/text/textwrappers";

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
                <H4>Status</H4>
                <PulsingText><H3>{statusText}</H3></PulsingText>
                <H4>Ticks</H4>
                <PulsingText><H3>{progressData.currentTick} of {progressData.totalTicks}</H3></PulsingText>
            </EvenDistributed>
        </Panel>
    );
}