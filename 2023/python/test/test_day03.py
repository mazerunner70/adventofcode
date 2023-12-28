from unittest import TestCase
from days.day03.day03 import Day03
from test.testwrapper import TestWrapper


class TestDay02(TestCase):
    def test_execute(self):
        testwrapper = TestWrapper({1: [('test.txt', 4361), ('input.txt', 509115)], 2: [('test.txt', 467835), ('input.txt', 75220503)]})
        testwrapper.execute(self, Day03())
