from django.db import transaction

from adventofcode.models import Tick
from adventofcode.services.initialisers.day01part1algorithm1 import Day01Part1Algorithm1
from adventofcode.services.initialisers.day01part1algorithm2 import Day01Part1Algorithm2


class InputDataService:
    def __init__(self, inputData):
        self.inputData = inputData

    def event_processor(self, ticknumber, str):
        newTick = Tick(input_data=self.inputData, tick_number=ticknumber, endstate=str)
        newTick.save()

    @transaction.atomic
    def initialise(self):
        algo = Day01Part1Algorithm2(self.inputData.data)
        print("Initialising "+self.inputData.data)
        algo.initialise(self.event_processor)