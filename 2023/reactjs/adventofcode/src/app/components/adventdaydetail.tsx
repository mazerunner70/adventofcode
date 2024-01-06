


export default function AdventDayDetail({dayno, env, inputUrl, target}: {dayno, env, inputUrl, target}) {
    return (
        <div>
            <span>Day {dayno}</span>
            <span>env: {env}</span>
            <span>inputUrl: {inputUrl}</span>
            <span>target: {target}</span>
        </div>
    )
}