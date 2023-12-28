from days.common.fileresource import DataLoader

class Day01(object):

    def __init__(self):
        self.dayno = 1
        self.dw = self.getdigitwords()
        self.rdw = self.reversedigitwords()


    def getdigitwords(self):
        return ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']

    def reversedigitwords(self):
        return [w[::-1] for w in self.getdigitwords()]

    def stringstartswith(self, str, words):
        rl = [pair for pair in enumerate(words) if str.startswith(pair[1])]
        return rl[0][0] if rl else None

    def getfirstdigitNumeric(self, line):
        l = 0
        while l < len(line) and not line[l].isdigit():
            l += 1
        return [l, int(line[l])] if l < len(line) else [l, None]

    def getfirstdigitWords(self, line, digitwords):
        l = 0
        while l < len(line) and not self.stringstartswith(line[l:], digitwords):
            l += 1
        return l, self.stringstartswith(line[l:], digitwords)

    def getfirstdigitorword(self, line, digitwords):
        ld, fdn = self.getfirstdigitWords(line, digitwords)
        lw, fdw = self.getfirstdigitNumeric(line)
        return fdn if ld < lw else fdw
    # def getfirstlastdigitorword(self, line):
    #     l = self.getfirstdigitorword(line, self.dw)
    #     r = self.getfirstdigitorword(line[::-1], self.rdw)
    #     return l, r
    def getfirstlastdigit(self,line):
        l = self.getfirstdigitNumeric(line)[1]
        r = self.getfirstdigitNumeric(line[::-1])[1]
        return [l, r]
    def getfirstlastdigitorword(self,line):
        l = self.getfirstdigitorword(line, self.dw)
        r = self.getfirstdigitorword(line[::-1], self.rdw)
        return [l, r]

    def combinedigits(self, digits):
        return digits[0] * 10 + digits[1]


    def execute(self, partno, data):
        func = self.getfirstlastdigit if partno == 1 else self.getfirstlastdigitorword
        p = [self.combinedigits(func(line)) for line in data.splitlines()]
        return sum(p)



# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    fileres = DataLoader(1)
    print(fileres.readfile('test.txt'))
    # print(day01('test.txt'))
    day01 = Day01()
    print(day01.execute(fileres.readfile('input.txt')))

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
