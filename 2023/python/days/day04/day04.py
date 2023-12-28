


class Day04:

    class Card:
        def __init__(self, cardno, winning, numbershad):
            self.cardno = cardno
            self.winning = winning
            self.numbershad = numbershad

    def __init__(self):
        self.dayno = 4

    def parseline(self, line):
        parts = line.split(':')
        cardno = int(parts[0].split()[1])
        grps = [x.strip() for x in parts[1].split('|')]
        winning = [int(x) for x in grps[0].split()]
        numbershad = [int(x) for x in grps[1].split()]
        return self.Card(cardno, winning, numbershad)

    def calculatewinning(self, card):
        matches = []
        for n in card.numbershad:
            if n in card.winning:
                matches.append(n)
        return matches

    def calcpart1score(self, card):
        matches = self.calculatewinning(card)
        return pow(2, len(matches) - 1) if len(matches) > 0 else 0

    def calmatches(self, cards):
        matches = []
        for c in cards:
            matches.append(self.calculatewinning(c))
        return matches

    def calc_accum_score(self, matches, rane, acc):
        for i in rane:
            if len(matches[i]) > 0:

                self.calc_accum_score(matches, i+1, i+1+len(matches[i]), acc)

    def execute(self, partno, data):
        cards = [self.parseline(line) for line in data.splitlines()]
        winnings = [self.calcpart1score(card) for card in cards]
        return sum(winnings)





if __name__ == '__main__':
    print(readfile('test.txt'))
    # print(day01('test.txt'))
    print(day01('input.txt'))