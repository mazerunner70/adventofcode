from unittest import TestCase

from days.common.fileresource import DataLoader
from days.day01.day01 import Day01
from test.testwrapper import TestWrapper


class TestDay01(TestCase):

    def test_execute(self):
        testwrapper = TestWrapper({1: [('test.txt', 142), ('input.txt', 54159)], 2: [('test2.txt', 281), ('input.txt', 53866)]})
        testwrapper.execute(self, Day01())

