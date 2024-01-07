import AdventDayEnv
    from "@app/components/adventdayenv/AdventDayEnv";


export default function AdventDayPart({dayPartConfig, partName, dayno}:{dayPartConfig: any, partName, dayno}) {
    
    return (
        <div>
            <h2>{partName}</h2>
            <AdventDayEnv key="test" dayEnvConfig={dayPartConfig['test']} envName="Test" partName = {partName} dayno = {dayno}/>
            <AdventDayEnv key="full" dayEnvConfig={dayPartConfig['full']} envName="Full" partName = {partName} dayno = {dayno}/>
        </div>
    )
}
