import Day01 from "./day01";
import Day02 from "./day02";
import Day03 from "./day03";

async function loadConfigJson() {
    return fetch("/assets/config.json").then((d) => {
        return d.json();
    }).then((data) => {
        return data;
    })
}
const dayObjects = [null, new Day01(), new Day02(), new Day03()]

// do test
async function doTest(dayno, daypart, daypartid) {
    const day = dayObjects[dayno]
    return fetch(`/assets/day${dayno.toString().padStart(2, "0")}/${daypart.file}`).then((d) => {
        return d.text();
    }).then((data) => {
        return day.execute(daypartid, data)
    }).then((result) => {
        console.log("doTest", dayno, daypartid, result, daypart.reference, result === daypart.reference)
        return [result, result === daypart.reference]
    })
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

async function dayrunner() {
    return loadConfigJson().then(daysdata => {
        return Promise.all(daysdata.map(daydata => {
            const p1test = doTest(daydata.dayno, daydata.part1.test, 1)
            const p1full = doTest(daydata.dayno, daydata.part1.full, 1)
            const p2test = doTest(daydata.dayno, daydata.part2.test, 2)
            const p2full = doTest(daydata.dayno, daydata.part2.full, 2)
            return Promise.all([p1test, p1full, p2test, p2full]).then((results) => {
                return ({
                    day: {
                        number: daydata.dayno,
                        part1: {
                            test: {
                                reference: daydata.part1.test.reference,
                                result: results[0][0],
                                icon: results[0][1]
                            },
                            full: {
                                reference: daydata.part1.full.reference,
                                result: results[1][0],
                                icon: results[1][1]
                            }
                        },
                        part2: {
                            test: {
                                reference: daydata.part2.test.reference,
                                result: results[2][0],
                                icon: results[2][1]
                            },
                            full: {
                                reference: daydata.part2.full.reference,
                                result: results[3][0],
                                icon: results[3][1]
                            }
                        }
                    }
                })
            })
        })).then((results) => {
            return results
        })
    })
}

export default dayrunner;