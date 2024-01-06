import AdventDay
    from "@app/components/adventday";


export default function Advent({data}: {data}) {
    console.error("h1")
    return (
        <div>
            Hello!!
            {JSON.stringify(data)}
            {data?.map((dayConfig) => {
                return <AdventDay dayConfig={dayConfig}/>
            })}
        </div>
    )
}