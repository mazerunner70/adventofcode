
class Day01 {


    part1(data) {
        console.log("part1")
        return data.split("\n").map((line) => {
            const x = line.match(/\d/g)
            return Number(x[0]+x.at(-1))
        }).reduce((a,b) => a+b).toString()
    }

    part2(data) {
        const digitWords = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
        const digitWordsregex = digitWords.reduce((a,b) => a+"|"+b, "\\d")
        return data.split("\n").map((line) => {
            const x = line.match(`(?=(${digitWordsregex})).*(${digitWordsregex})`).map(word =>
                Number(word) || digitWords.indexOf(word) ).slice(1, 3).join("")
            return Number(x)
        }).reduce((a,b) => a+b).toString()
    }

    execute(partno, data) {
        console.log("execute", partno)
        const res = partno === 1? this.part1(data) : this.part2(data)
        console.log("execute", partno, res)
        return res
    }

}

async function day01execute(partno, data) {
    console.log(partno);
    console.log(data)
    const day01 = new Day01()
    const sum = day01.execute(partno, data)
    console.log(sum)
    const myFirstPromise = new Promise((resolve, reject) => {
        // We call resolve(...) when what we were doing asynchronously was successful, and reject(...) when it failed.
        // In this example, we use setTimeout(...) to simulate async code.
        // In reality, you will probably be using something like XHR or an HTML API.
        setTimeout(() => {
            resolve("Success!"); // Yay! Everything went well!
        }, 2500);
    });
    myFirstPromise.then((successMessage) => {
        // successMessage is whatever we passed in the resolve(...) function above.
        // It doesn't have to be a string, but if it is only a succeed message, it probably will be.
        console.log(`Yay! ${successMessage}`);
    });
    return myFirstPromise;
}

// export default day01execute;
export default Day01;