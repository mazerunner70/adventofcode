from adventofcode.models import Tick
from adventofcode.services.initialisers.day01part1algorithm1 import Day01Part1Algorithm1


class InputDataService:
    def __init__(self, inputData):
        self.inputData = inputData

    def tickProcessor(self, ticknumber, jsonString):
        newTick = Tick(input_data=self.inputData, tick_number=ticknumber, endstate=jsonString)
        newTick.save()

    def initialise(self):
        algo = Day01Part1Algorithm1(self.inputData.data)
        algo.initialise(self.tickProcessor)