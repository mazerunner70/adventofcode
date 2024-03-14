import { AdventSelected } from "@app/contexts/Advent.context";
import { H2 } from "../../base/text/textwrappers";
import { EvenDistributed, OutlinedBox } from "./styled";
import Panel from "@app/components/base/panel";

export default function DetailHeader({ config }: { config: AdventSelected }) {
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
