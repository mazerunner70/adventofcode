import dayrunner from "./days/dayrunner";
import Table from "./components/table";
import {useEffect, useMemo, useState} from "react";
import "./summary.css"

async function rundays() {
    console.log("got here")
    await dayrunner()
    console.log("done")
}

/*
    [
        day: {
            number: 1,
            part1: {
                test: {
                    reference: 123,
                    result: 123,
                    icon: true
                },
                full: {
                    reference: 123,
                    result: 123,
                    icon: true
                },
            part2: {
                test: {
                    reference: 123,
                    result: 123,
                    icon: true
                },
                full: {
                    reference: 123,
                    result: 123,
                    icon: true
                },
    ]
 */
const Genres = ({ value }) => {
    console.log("value", value)
    // Loop through the array and create a badge-like component instead of a comma-separated string
    return (
        <>
            <span className={value? "badge-pass" : "badge-fail"}>
                {value? "Pass" : "Fail"}
            </span>
        </>
    );
};
function Summary() {
    const columns = useMemo(
        () => [
            {
                // first group - TV Show
                Header: "Day",
                // First group columns
                columns: [
                    {
                        Header: "Number",
                        accessor: "day.number",
                    }
                ],
            },
            {
                // first group - TV Show
                Header: "Part 1 Test",
                // First group columns
                columns: [
                    {
                        Header: "TestReference",
                        accessor: "day.part1.test.reference",
                    },
                    {
                        Header: "Result",
                        accessor: "day.part1.test.result",
                    },
                    {
                        Header: "Success",
                        accessor: "day.part1.test.icon",
                        Cell: ({ cell: { value } }) => <Genres value={value} />
                    }
                ],
            },
            {
                // first group - TV Show
                Header: "Part 1 Full",
                // First group columns
                columns: [
                    {
                        Header: "TestReference",
                        accessor: "day.part1.full.reference",
                    },
                    {
                        Header: "Result",
                        accessor: "day.part1.full.result",
                    },
                    {
                        Header: "Success",
                        accessor: "day.part1.full.icon",
                        Cell: ({ cell: { value } }) => <Genres value={value} />
                    }
                ],
            },
            {
                // Second group - Details
                Header: "Part 2 Test",
                // Second group columns
                columns: [
                    {
                        Header: "Reference",
                        accessor: "day.part2.test.reference",
                    },
                    {
                        Header: "Result",
                        accessor: "day.part2.test.result",
                    },
                    {
                        Header: "Success",
                        accessor: "day.part2.test.icon",
                        Cell: ({ cell: { value } }) => <Genres value={value} />
                    }
                ],
            },
            {
                // Second group - Details
                Header: "Part 2 Full",
                // Second group columns
                columns: [
                    {
                        Header: "Reference",
                        accessor: "day.part2.full.reference",
                    },
                    {
                        Header: "Result",
                        accessor: "day.part2.full.result",
                    },
                    {
                        Header: "Success",
                        accessor: "day.part2.full.icon",
                        Cell: ({ cell: { value } }) => <Genres value={value} />
                    }
                ],
            },
        ], []
    );
    const [data, setData] = useState([]);
    useEffect(() => {
        (async () => {
            const result = await dayrunner()
            console.log("11", result)
            setData(result);
        })();
    }, []);
    console.log("d", data)
    return (
        <>
            <div className="board-row">
                <button onClick={rundays}>Start Run</button>
            </div>
            <div className="summary">
                <Table columns={columns} data={data} />
            </div>
        </>
    )
}

export default Summary;