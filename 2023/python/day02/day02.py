from itertools import product
from math import prod
from pathlib import Path


adventday = 2
def getresourcefile(filename):
    return Path.cwd()/".." / "input" / f"day{adventday:02d}" / filename

def readfile(filename):
    with getresourcefile(filename).open() as f:
        return f.read()

colours = ["red", "blue", "green"]

def parse_colour(s: str):
    return s.strip().split(" ")

def parse_grab(s: str):
    return [parse_colour(c) for c in s.split(",")]

def parse_game(grabs: str):
    return [parse_grab(gr) for gr in grabs.split(";")]

def parse_line(line: str):
    l = line.split(":")
    return l[0].split(" ")[1], parse_game(l[1])

def calcmin_needed(parsedline):
    linemaxs = []
    for g in parsedline:
        num = int(g[0])
        mx_col = {co: 0 for co in colours}
        cols = [gr for grabs in g[1] for gr in grabs ]
        for c in cols:
            mx_col[c[1]] = max(mx_col[c[1]], int(c[0]))
        linemaxs.append([int(num), mx_col])
    return linemaxs

def game1(lines, constr):
    l = lines.split("\n")
    gmes = [parse_line(line) for line in l]
    minsneeded = calcmin_needed(gmes)
    allowed = []
    for mins in minsneeded:
        m = True
        for c in colours:
            if constr[c] < mins[1][c]:
                m = False
        if m:
            allowed.append(mins[0])
    return sum(allowed)

def game2(lines):
    l = lines.split("\n")
    gmes = [parse_line(line) for line in l]
    minsneeded = calcmin_needed(gmes)
    pwr = []
    for gm in minsneeded:
        print(gm[1])
        ns = [gm[1][i] for i in gm[1]]
        ns = prod(ns)
        pwr.append(ns)
    return sum(pwr)

if __name__ == "__main__":
    s = readfile("test.txt")
    g = game1(s, {"red": 12, "blue": 14, "green":13})
    print(g, game2(s))
    s1 = readfile("input.txt")
    g1 = game1(s1, {"red": 12, "blue": 14, "green":13})
    print(g1, game2(s1))


