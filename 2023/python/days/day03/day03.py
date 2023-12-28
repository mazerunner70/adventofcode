from pathlib import Path

class Day03:

    def __init__(self):
        self.dayno = 3
    def does_string_contain_digit(self, string):
        for char in string:
            if char.isdigit():
                return True
        return False

    def extract_number_from_string(self, string, start_index=0):
        number = ""
        for char in string[start_index:]:
            if char.isdigit():
                number += char
            else:
                return number
        return number

    def is_not_period_or_digit_in_string(self, string):
        for char in string:
            if not (char.isdigit() or char == "."):
                return True
        return False


    def is_any_symbol_around_string(self, grid, row_number, col_number, string_len):
        top = max(0, row_number - 1)
        left = max(0, col_number - 1)
        bottom = min(row_number + 1, len(grid) - 1)
        right = min(col_number + string_len, len(grid[0]) - 1)
        ab = self.is_not_period_or_digit_in_string(grid[top][left:right+1])
        bel = self.is_not_period_or_digit_in_string(grid[bottom][left:right+1])
        le = self.is_not_period_or_digit_in_string(grid[row_number][left])
        ri = self.is_not_period_or_digit_in_string(grid[row_number][right])
        return ab or bel or le or ri

    def symbol_position_in_string(self, rn, cn, string):
        for i in range(len(string)):
            if self.is_not_period_or_digit_in_string(string[i]):
                return (rn, cn+i)
        return None

    def symbol_position_around_string(self, grid, rn, cn, string_len):
        top = max(0, rn - 1)
        left = max(0, cn - 1)
        bottom = min(rn + 1, len(grid) - 1)
        right = min(cn + string_len, len(grid[0]) - 1)
        ab = self.symbol_position_in_string(top, left, grid[top][left:right+1])
        bel = self.symbol_position_in_string(bottom, left, grid[bottom][left:right+1])
        le = self.symbol_position_in_string(rn, left, grid[rn][left])
        ri = self.symbol_position_in_string(rn, right, grid[rn][right])
        return ab or bel or le or ri

    def parse_grid_line2(self, grid, row_number):
        res = []
        if self.does_string_contain_digit(grid[row_number]):
            i = 0
            while i < len(grid[row_number]):
                if grid[row_number][i].isdigit():
                    number = self.extract_number_from_string(grid[row_number], i)
                    if self.is_any_symbol_around_string(grid, row_number, i, len(number)):
                        res.append(int(number))
                    i += len(number)
                else:
                    i += 1
        return res

    def parse_grid_line(self, grid, row_number):
        res = []
        if self.does_string_contain_digit(grid[row_number]):
            i = 0
            while i < len(grid[row_number]):
                if grid[row_number][i].isdigit():
                    number = self.extract_number_from_string(grid[row_number], i)
                    sym_pos = self.symbol_position_around_string(grid, row_number, i, len(number))
                    if sym_pos:
                        res.append([int(number), sym_pos])
                    i += len(number)
                else:
                    i += 1
        return res

    def parsegrid(self, grid):
        res = []
        for row_number in range(len(grid)):
            res += self.parse_grid_line(grid, row_number)
        return res

    def part1(self, data):
        grid = data.splitlines()
        return sum([x[0] for x in self.parsegrid(grid)])

    def findpairs(self, parts):
        res = []
        for i in range(len(parts)):
            for j in range(i+1, len(parts)):
                if parts[i][1] == parts[j][1]:
                    res.append([parts[i][0], parts[j][0]])
        return res

    def part2(self, data):
        grid = data.splitlines()
        return sum([x[0]*x[1] for x in self.findpairs(self.parsegrid(grid))])

    def execute(self, partno, data):
        func = self.part1 if partno == 1 else self.part2
        return func(data)

if __name__ == "__main__":
    print(part1("test.txt"))
    print(part1("input.txt"))
    print(part2("test.txt"))
    print(part2("input.txt"))