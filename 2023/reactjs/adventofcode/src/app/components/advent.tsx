import AdventDay
    from "../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/adventday";


export default function Advent({data}: {data}) {
    console.error("h1")
    console.log(data.toString())
    return (
        <div>
            Hello!!
            {JSON.stringify(data['config'])}
            {data['config'].map((dayConfig) => {
                return <AdventDay dayConfig={dayConfig}/>
            })}
        </div>
    )
}