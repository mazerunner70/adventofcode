import AdventDay
    from "@app/components/adventday";
import { IAdvent, IConfig } from "@app/context/Advent.context";


export default function Advent({data}: {data: IConfig[]}) {
    return (
        <div>
            {data?.map((dayConfig:IConfig) =>  <AdventDay key= {dayConfig.dayno} dayConfig={dayConfig}/>)}
        </div>
    )
}