import fs from "fs";
import path from "path";

import Day01 from "../../../days/day01";

function loadFileAsString(dayno, filename) {
    return fs.readFileSync(path.join(__dirname, `../../../../public/assets/day${dayno.toString().padStart(2, "0")}/${filename}`), "utf8")
}

describe("day01 part 1", () => {
    it("should return 12 for 1abc2", () => {
        const day01 = new Day01()
        expect(day01.execute(1, "1abc2")).toBe("12" );
    });
    it("should return 12 for pqr3stu8vwx", () => {
        const day01 = new Day01()
        expect(day01.execute(1, "pqr3stu8vwx")).toBe("38" );
    });
    it("should return 12 for 1abc2\npqr3stu8vwx", () => {
        const day01 = new Day01()
        expect(day01.execute(1, "1abc2\npqr3stu8vwx")).toBe("50" );
    });
    it("should return 12 for 1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet", () => {
        const day01 = new Day01()
        expect(day01.execute(1, "1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet")).toBe("142" );
    });
    it("should return 55 for 5p", () => {
        const day01 = new Day01()
        expect(day01.execute(1, "5p")).toBe("55");
    });
    it("should read input file and return 54159", () => {
        const day01 = new Day01()
        expect(day01.execute(1, loadFileAsString(1, "input.txt"))).toBe("54159");
    });
})
 describe("day 01 part 2", () => {
     it("should return 29 for two1nine", () => {
         const day01 = new Day01()
         expect(day01.execute(2, "two1nine")).toBe("29")
     })
     it("should return 55 for 5p", () => {
         const day01 = new Day01()
         expect(day01.execute(2, "5p")).toBe("55");
     });
 })