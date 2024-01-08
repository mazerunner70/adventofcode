import AdventDayPart from "@app/components/adventdaypart/AdventDayPart";
import {IConfig} from "@app/context/Advent.context";

export default function AdventDay({dayConfig}:{dayConfig: IConfig}) {
    console.log(dayConfig)
    return (
        <div>
            <h1>Day {dayConfig.dayno}</h1>
            <AdventDayPart dayPartConfig={dayConfig['part1']} partName="Part 1" dayno={dayConfig.dayno}/>
            <AdventDayPart dayPartConfig={dayConfig['part2']} partName="Part 2" dayno={dayConfig.dayno}/>
        </div>
    )

}