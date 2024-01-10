import { AdventSelected } from "@app/context/Advent.context";
import {H1, H2} from "../base/text/textwrappers";
import { EvenDistributed } from "./styled";



export default function DetailHeader({config}:{ config: AdventSelected }) {
    //console.log(config);
    return (
        <EvenDistributed>
            <H1>Detail View</H1>
            <H2>Day {config.dayno.toString().padStart(2, "0")}</H2>
            <H2>{config.partName}</H2>
            <H2>Run Data: {config.envName}</H2>
            <H2>Datafile: {config.envConfig.filename}</H2>
            <H2>Expected: {config.envConfig.reference}</H2>
        </EvenDistributed>
    )
}
