from unittest import TestCase

from days.day02.day02 import Day02
from test.testwrapper import TestWrapper


class TestDay02(TestCase):
    def test_execute(self):
        testwrapper = TestWrapper({1: [('test.txt', 8), ('input.txt', 2727)], 2: [('test.txt', 2286), ('input.txt', 56580)]})
        testwrapper.execute(self, Day02())
