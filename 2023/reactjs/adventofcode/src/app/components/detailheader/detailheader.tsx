import { AdventSelected } from "@app/context/Advent.context";
import { H2 } from "../base/text/textwrappers";
import { EvenDistributed, Heading } from "./styled";
import Panel from "@app/components/base/panel";

export default function DetailHeader({ config }: { config: AdventSelected }) {
  //console.log(config);
  return (
    <EvenDistributed>
      <Heading>Detail View</Heading>
      <Panel shadowed={true}>
        <H2>Day {config.dayno.toString().padStart(2, "0")} </H2>
        <H2>{config.partName}</H2>
        <H2>Run Data: {config.envName}</H2>
        <H2>Datafile: {config.envConfig.filename}</H2>
        <H2>Expected: {config.envConfig.reference}</H2>
      </Panel>
    </EvenDistributed>
  );
}
