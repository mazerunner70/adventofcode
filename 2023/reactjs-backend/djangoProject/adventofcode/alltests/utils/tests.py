import json

from django.test import TestCase
from adventofcode.services.initialisers.day01part1algorithm1 import UIActions, TickState, TickResult, LineSearchState, Action
from adventofcode.utils import Serialiser

# Create your alltests here.

class TestSerialiser(TestCase):

    def test_serialiser(self):
        uiAction = UIActions(Action.StartingSearch, "0")
        js = Serialiser.to_json_camel2(uiAction, [UIActions], [Action])
        # js = json.loads('{"action": 7, "param": "0"}')
        jsref = json.loads('{"action": 7, "param": "0"}')
        print(jsref['action'])
        print(js['action'])
        print(js['action'] == jsref['action'])
        self.assertEqual(js, jsref)