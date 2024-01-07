import AdventDay
    from "@app/components/adventday";


export default function Advent({data}: {data}) {
    console.log("h1")
    return (
        <div>
            Hello!!
            {JSON.stringify(data)}
            {data?.map((dayConfig) =>  <AdventDay key= {dayConfig.dayno} dayConfig={dayConfig}/>)}
        </div>
    )
}