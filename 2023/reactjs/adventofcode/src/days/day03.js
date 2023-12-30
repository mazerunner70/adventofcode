
class Day03 {

    getSymbols(data) {
        const lines = data.split('\n');
        const symbols = []
        for (let i = 0; i < lines.length; i++) {
            for (let j = 0; j < lines[i].length; j++) {
                const ch = lines[i][j]
                if (!(ch === '.' || ("0" <= ch && ch <= "9"))) {
                    symbols.push([ch, i, j])
                }
            }
        }
        return symbols
    }

    getNumbers(data) {
        const lines = data.split('\n');
        const numbers = []
        for (let i = 0; i < lines.length; i++) {
            for (const mat of lines[i].replace(/\./g, " ").matchAll(/\d+/g)) {
                numbers.push([mat[0], i, mat.index])
            }
        }
        return numbers
    }

    getNumbersAroundSymbol(numbers, row, col) {
        const res = []
        for (const num of numbers) {
            if (num[1] -1 <= row && row <= num[1] +1 && num[2] -1 <= col && col <= num[2] + num[0].length) {
                res.push([row, col, num])
            }
        }
        return res
    }

    part1(data) {
        const symbols = this.getSymbols(data)
        const numbers = this.getNumbers(data)
        const result = []
        for (const sym of symbols) {
            const numbersAround = this.getNumbersAroundSymbol(numbers, sym[1], sym[2])
            result.push(numbersAround)
        }
        return result.flat().map(x => Number(x[2][0])).reduce((a, b) => a + b, 0).toString()
    }
    part2(data) {
        const symbols = this.getSymbols(data)
        const numbers = this.getNumbers(data)
        const result = []
        for (const sym of symbols) {
            const numbersAround = this.getNumbersAroundSymbol(numbers, sym[1], sym[2])
            if (numbersAround.length === 2) {
                result.push(numbersAround.map(x => x[2][0]).reduce((a, b) => a * b, 1))
            }
        }
        return result.reduce((a, b) => a + b, 0).toString();
    }

    execute(partno, data) {
        console.log("execute", partno)
        const res = partno === 1? this.part1(data) : this.part2(data)
        console.log("execute", partno, res)
        return res
    }
}

export default Day03;