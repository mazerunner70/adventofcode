

class Day02 {

    part1(data) {
        return data.split("\n").map((line) => {
            const [game, reveals] = line.split(":")
            const gameNo = game.split(" ")[1]
            const colCounts = reveals.split(";").flatMap(x=>x.split(",")).map((x) => x.trim()).map(x=>x.split(" "))
            var tot = [0, 0, 0]
            const col = ["red", "green", "blue"]
            for (let i = 0; i < colCounts.length; i++) {
                tot[i] = colCounts.filter((x) => x[1] === col[i]).map((x) => Number(x[0])).reduce((a, b) => Math.max(a, b), 0)
            }
            return  (tot[0] <= 12 && tot[1] <= 13 && tot[2] <= 14) ? Number(gameNo) : 0;
        }).reduce((a, b) => a + b).toString()
    }

    part2(data) {
        return data.split("\n").map((line) => {
            const [game, reveals] = line.split(":")
            const gameNo = game.split(" ")[1]
            const colCounts = reveals.split(";").flatMap(x=>x.split(",")).map((x) => x.trim()).map(x=>x.split(" "))
            var tot = [0, 0, 0]
            const col = ["red", "green", "blue"]
            for (let i = 0; i < colCounts.length; i++) {
                tot[i] = colCounts.filter((x) => x[1] === col[i]).map((x) => Number(x[0])).reduce((a, b) => Math.max(a, b), 0)
            }
            return tot[0] * tot[1] * tot[2]
        }).reduce((a, b) => a + b).toString()
    }

    execute(partno, data) {
        console.log("execute", partno)
        const res = partno === 1? this.part1(data) : this.part2(data)
        console.log("execute", partno, res)
        return res
    }
}

export default Day02;