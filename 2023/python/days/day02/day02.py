from itertools import product
from math import prod
from pathlib import Path


class Day02:
    def __init__(self):
        self.dayno = 2
        self.colours = ["red", "blue", "green"]

    def parse_colour(self, s: str):
        return s.strip().split(" ")

    def parse_grab(self, s: str):
        return [self.parse_colour(c) for c in s.split(",")]

    def parse_game(self, grabs: str):
        return [self.parse_grab(gr) for gr in grabs.split(";")]

    def parse_line(self, line: str):
        l = line.split(":")
        return l[0].split(" ")[1], self.parse_game(l[1])

    def calcmin_needed(self, parsedline):
        linemaxs = []
        for g in parsedline:
            num = int(g[0])
            mx_col = {co: 0 for co in self.colours}
            cols = [gr for grabs in g[1] for gr in grabs ]
            for c in cols:
                mx_col[c[1]] = max(mx_col[c[1]], int(c[0]))
            linemaxs.append([int(num), mx_col])
        return linemaxs

    def game1(self, lines, constr):
        l = lines.split("\n")
        gmes = [self.parse_line(line) for line in l]
        minsneeded = self.calcmin_needed(gmes)
        allowed = []
        for mins in minsneeded:
            m = True
            for c in self.colours:
                if constr[c] < mins[1][c]:
                    m = False
            if m:
                allowed.append(mins[0])
        return sum(allowed)

    def game2(self, lines, constr):
        l = lines.split("\n")
        gmes = [self.parse_line(line) for line in l]
        minsneeded = self.calcmin_needed(gmes)
        pwr = []
        for gm in minsneeded:
            ns = [gm[1][i] for i in gm[1]]
            ns = prod(ns)
            pwr.append(ns)
        return sum(pwr)

    def execute(self, partno, data):
        func = self.game1 if partno == 1 else self.game2
        return func(data, {"red": 12, "blue": 14, "green":13})

if __name__ == "__main__":
    s = readfile("test.txt")
    g = game1(s, {"red": 12, "blue": 14, "green":13})
    print(g, game2(s))
    s1 = readfile("input.txt")
    g1 = game1(s1, {"red": 12, "blue": 14, "green":13})
    print(g1, game2(s1))


