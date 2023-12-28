from unittest import TestCase

from days.day04.day04 import Day04
from test.testwrapper import TestWrapper


class TestDay04(TestCase):
    def test_execute(self):
        testwrapper = TestWrapper({1: [('test.txt', 13), ('input.txt', 24542)]})
        testwrapper.execute(self, Day04())
