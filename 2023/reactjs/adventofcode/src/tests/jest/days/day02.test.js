import fs from "fs";
import path from "path";

import Day02 from "../../../days/day02";

function loadFileAsString(dayno, filename) {
    return fs.readFileSync(path.join(__dirname, `../../../../public/assets/day${dayno.toString().padStart(2, "0")}/${filename}`), "utf8")
}

describe("day02 part 1", () => {
    it("should return 12 for Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", () => {
        const day = new Day02()
        expect(day.execute(1, "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")).toBe("1");
    });
    it("should return 12 for Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", () => {
        const day = new Day02()
        expect(day.execute(1, "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")).toBe("0");
    });
    it("should return 12 for Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", () => {
        const day = new Day02()
        expect(day.execute(1, "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")).toBe("1");
    });
    it("should return 12 for Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", () => {
        const day = new Day02()
        expect(day.execute(1, "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")).toBe("3");
    });
    it("should read input file and return 2727", () => {
        const day = new Day02()
        expect(day.execute(1, loadFileAsString(2, "input.txt"))).toBe("2727");
    });
})

describe("day 02 part 2", () => {
    it("should return 12 for Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", () => {
        const day = new Day02()
        expect(day.execute(2, "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")).toBe("48");
    });
    it("should return 12 for Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", () => {
        const day = new Day02()
        expect(day.execute(2, "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")).toBe("60");
    });
    it("should read input file and return 56580", () => {
        const day = new Day02()
        expect(day.execute(2, loadFileAsString(2, "input.txt"))).toBe("56580");
    });
})