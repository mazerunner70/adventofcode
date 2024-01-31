import Panel from "@app/components/base/panel";
import { EvenDistributed, OutlinedBox } from "./styled";
import { H2 } from "@app/components/base/text/textwrappers";



export interface IProgressData {

}


export default function ProgressPanel({ config }: { config: IProgressData }) {
    //console.log(config);
    return (
        <Panel shadowed={true}>
            <EvenDistributed>
                <H2>Status</H2>
                <OutlinedBox>Idle</OutlinedBox>
                <H2>Output</H2>
                <OutlinedBox>--</OutlinedBox>
            </EvenDistributed>
        </Panel>
    );
}