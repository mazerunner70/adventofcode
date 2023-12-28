from unittest import TestCase

from days.common.fileresource import DataLoader


class TestWrapper:
    def __init__(self, testresults):
        self.testresults = testresults

    def execute(self, testcase, day):
        for partno in self.testresults.keys():
            for testI in self.testresults[partno]:
                fileres = DataLoader(day.dayno)
                tname = f"day {day.dayno:02d}: part {partno}: {testI[0]}"
                print(f"{tname}-> running")
                res = day.execute(partno, fileres.readfile(testI[0]))
                print(f"{tname} = {res}")
                testcase.assertEqual(testI[1], res, f"part {partno} input {testI[0]}")
