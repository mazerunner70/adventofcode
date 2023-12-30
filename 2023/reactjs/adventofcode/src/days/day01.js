
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

// export default day01execute;
export default Day01;