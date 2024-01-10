import { AdventSelected } from "@app/context/Advent.context";



export default function DetailHeader({config}:{ config: AdventSelected }) {
    console.log(config);
    return (
        <div>
            <h1>Detail View</h1>
            <p>{config.dayno}: {config.partName}: {config.envName} : {config.envConfig.filename}: {config.envConfig.reference}</p>
        </div>
    )
}
