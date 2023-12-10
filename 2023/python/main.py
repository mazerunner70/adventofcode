from pathlib import Path

adventday = 1

def getresourcefile(filename):
    return Path.cwd() / "input" / f"day{adventday:02d}" / filename

def readfile(filename):
    with getresourcefile(filename).open() as f:
        return f.read()
def getdigitwords():
    return ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']

def reversedigitwords():
    return [w[::-1] for w in getdigitwords()]

def stringstartswith(str, words):
    rl = [pair for pair in enumerate(words) if str.startswith(pair[1])]
    return rl[0][0] if rl else None



def getfirstdigit(line, digitwords):
    l = 0
    while l < len(line) and not (line[l].isdigit() or stringstartswith(line[l:], digitwords)):
        l += 1
    fdw = stringstartswith(line[l:], digitwords)
    return fdw if fdw else int(line[l])
def getfirstlastdigit(line, dw, rdw):
    l = getfirstdigit(line, dw)
    r = getfirstdigit(line[::-1], rdw)
    return l, r

def combinedigits(digits):
    return digits[0] * 10 + digits[1]


def day01(filename):
    s = readfile(filename)
    dw = getdigitwords()
    rdw = reversedigitwords()
    p = [combinedigits(getfirstlastdigit(line, dw, rdw)) for line in s.splitlines()]
    return sum(p)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(readfile('test.txt'))
    # print(day01('test.txt'))
    print(day01('input.txt'))

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
