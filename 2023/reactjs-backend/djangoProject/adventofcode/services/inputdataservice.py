from django.db import transaction

from adventofcode.models import Tick, InputData
from adventofcode.services.initialisers.day01part1algorithm2 import Day01Part1Algorithm2
from adventofcode.services.initialisers.day01part2algorithm1 import Day01Part2Algorithm1


class InputDataService:
    def __init__(self, inputData: InputData):
        self.inputData = inputData

    def event_processor(self, ticknumber, str):
        newTick = Tick(input_data=self.inputData, tick_number=ticknumber, endstate=str)
        newTick.save()

    @transaction.atomic
    def initialise(self):
        match self.inputData.task.task_number:
            case 1:
                algo = Day01Part1Algorithm2(self.inputData.data)
            case 2:
                algo = Day01Part2Algorithm1(self.inputData.data)
            case _:
                raise ValueError(f"Unknown task number {self.inputData.task.task_number}")
        print("Initialising "+self.inputData.data)
        algo.build_ticks(self.event_processor)
        print("Initialised "+self.inputData.data)