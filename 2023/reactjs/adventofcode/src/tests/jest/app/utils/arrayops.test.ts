import {
    pairwise
} from "@app/utils/ArrayOps";


describe("arrayopstest", () => {
    it("should return 12 for 1abc2", () => {
        const arr = [0, 1, 2, 3, 4]
        const result = pairwise(arr)
        expect(result).toEqual([[0, 1], [1, 2], [2, 3], [3, 4]])
    });
}   )
