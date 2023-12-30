import fs from "fs";
import path from "path";
import Day03 from "../../../days/day03";

function loadFileAsString(dayno, filename) {
    return fs.readFileSync(path.join(__dirname, `../../../../public/assets/day${dayno.toString().padStart(2, "0")}/${filename}`), "utf8")
}

describe("day03.js part 1", () => {
    it("should return 1 for ...*......\n..35..633.", () => {
        const day = new Day03()
        expect(day.execute(1, "...*......\n..35..633.")).toBe("35");
    });
    it("should return 1 for ...*......\n..35..633.\n......#...", () => {
        const day = new Day03()
        expect(day.execute(1, "...*......\n..35..633.\n......#...")).toBe("668");
    });
    it("should return 1 for ......755.\n...$.*....", () => {
        const day = new Day03()
        expect(day.execute(1, "......755.\n...$.*....")).toBe("755");
    });
    it("should return 1 for .....+.58.\n..592.....", () => {
        const day = new Day03()
        expect(day.execute(1, ".....+.58.\n..592.....")).toBe("592");
    });
    it("should return 1 for 467..114..\n...*......", () => {
        const day = new Day03()
        expect(day.execute(1, "467..114..\n...*......")).toBe("467");
    });
    it("should return 1 for 467..114..\n...*......\n..35..633.", () => {
        const day = new Day03()
        expect(day.execute(1, "467..114..\n...*......\n..35..633.")).toBe("502");
    });
    it("should return 1 for 467..114..\n...*......\n..35..633.\n......#...", () => {
        const day = new Day03()
        expect(day.execute(1, "467..114..\n...*......\n..35..633.\n......#...")).toBe("1135");
    });
    it("should return 1 for 467..114..\n...*......\n..35..633.\n......#...\n617*......", () => {
        const day = new Day03()
        expect(day.execute(1, "467..114..\n...*......\n..35..633.\n......#...\n617*......")).toBe("1752");
    });
    it("should read input file and return 2727", () => {
        const day = new Day03()
        expect(day.execute(1, loadFileAsString(3, "test.txt"))).toBe("4361");
    });
    it("should read input file and return 509115", () => {
        const day = new Day03()
        expect(day.execute(1, loadFileAsString(3, "input.txt"))).toBe("509115");
    });
});

describe("Day 3 part 2", () => {
    it("should return 1 for 467..114..\n...*......\n..35..633.", () => {
        const day = new Day03()
        expect(day.execute(2, "467..114..\n...*......\n..35..633.")).toBe("16345");
    });
    it("should read input file and return 467835", () => {
        const day = new Day03()
        expect(day.execute(2, loadFileAsString(3, "test.txt"))).toBe("467835");
    });
    it("should read input file and return 75220503", () => {
        const day = new Day03()
        expect(day.execute(2, loadFileAsString(3, "input.txt"))).toBe("75220503");
    });
})