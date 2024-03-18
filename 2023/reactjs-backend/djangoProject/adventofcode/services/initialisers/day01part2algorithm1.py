from dataclasses import dataclass, replace
from enum import Enum, IntEnum
from typing import Optional


@dataclass(frozen=True)
class LineSearchState:
    linenumber: int
    line: str
    search_index: int
    found_left_index: int
    found_left_length: int
    found_left_digit: Optional[str]
    found_right_index: int
    found_right_length: int
    found_right_digit: Optional[str]
    value_found: Optional[str]


class EventType(Enum):
    SearchingFromLeft = 1
    FoundLeftIndex = 2
    SearchingFromRight = 3
    FoundRightIndex = 4
    ValueCalculated = 5


@dataclass(frozen=True)
class Event:
    type: EventType
    param: str

    def __repr__(self):
        return f"{self.type.value}-{self.param}"


class SubStringTests:
    @staticmethod
    def is_digit(s: str, i: int) -> bool:
        # print(s, i)
        ss = s[i]
        return ss.isdigit()

    @staticmethod
    def start_index_in_stringlist(s: str, i: int, stringlist: list[str]) -> int:
        index = -1
        for j, str in enumerate(stringlist):
            if s[i:i + len(str)] == str:
                index = j
                break
        return index

    @staticmethod
    def end_index_in_stringlist(s: str, i: int, stringlist: list[str]) -> int:
        index = -1
        for j, str in enumerate(stringlist):
            if s[max(0, i - len(str) + 1):i + 1] == str:
                index = j
                break
        return index


class ListAlgorithm:
    @staticmethod
    def find_first(predicate, list) -> int:
        for index, item in enumerate(list):
            if predicate(item):
                return index
        return -1


class ScanDirection(IntEnum):
    Left = -1
    Right = 1


class Day01Part2Algorithm1:
    digit_strings = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]

    def __init__(self, data: str):
        self.data = data
        self.lines = self.data.split("\n")

    def is_value_calculated(self, lss: LineSearchState) -> bool:
        return lss.value_found is not None

    def is_right_index_found(self, lss: LineSearchState) -> bool:
        return lss.found_right_index != -1

    def is_searching_from_right(self, lss: LineSearchState) -> bool:
        return lss.found_left_index != -1 and lss.found_right_index == -1 and lss.search_index != -1

    def is_left_index_found(self, lss: LineSearchState) -> bool:
        return lss.found_left_index != -1

    def is_searching_from_left(self, lss: LineSearchState) -> bool:
        return lss.search_index != -1 and lss.found_left_index == -1

    def is_search_started(self, lss: LineSearchState) -> bool:
        return lss.search_index != -1

    def build_tick_event(self, lss: LineSearchState) -> Event:
        if self.is_value_calculated(lss):
            return Event(EventType.ValueCalculated, str(lss.value_found))
        if self.is_right_index_found(lss):
            return Event(EventType.ValueCalculated, lss.found_left_digit + lss.found_right_digit)
        if self.is_searching_from_right(lss):
            if SubStringTests.is_digit(lss.line, lss.search_index):
                return Event(EventType.FoundRightIndex, str(lss.search_index)+",1,"+str(lss.line[lss.search_index]))
            index = SubStringTests.end_index_in_stringlist(lss.line, lss.search_index, self.digit_strings)
            if index != -1:
                return Event(EventType.FoundRightIndex,
                             str(lss.search_index) + "," + str(len(self.digit_strings[index])) + "," + str(index))
            return Event(EventType.SearchingFromRight, str(lss.search_index - 1))
        if self.is_left_index_found(lss):
            return Event(EventType.SearchingFromRight, str(len(lss.line) - 1))
        if self.is_searching_from_left(lss):
            if SubStringTests.is_digit(lss.line, lss.search_index):
                return Event(EventType.FoundLeftIndex, str(lss.search_index)+",1,"+str(lss.line[lss.search_index]))
            index = SubStringTests.start_index_in_stringlist(lss.line, lss.search_index, self.digit_strings)
            if index != -1:
                return Event(EventType.FoundLeftIndex,
                             str(lss.search_index) + "," + str(len(self.digit_strings[index])) + "," + str(index))
            return Event(EventType.SearchingFromLeft, str(lss.search_index + 1))
        if not self.is_search_started(lss):
            return Event(EventType.SearchingFromLeft, "0")
        raise ValueError(f"Unknown state {lss}")

    def update_lss_with_event(self, lss: LineSearchState, event: Event) -> LineSearchState:
        if event.type == EventType.ValueCalculated:
            return replace(lss, value_found=event.param)
        if event.type == EventType.FoundRightIndex:
            s = event.param.split(",")
            return replace(lss, found_right_index=int(s[0]), found_right_length=int(s[1]), found_right_digit=s[2])
        if event.type == EventType.SearchingFromRight:
            return replace(lss, search_index=int(event.param))
        if event.type == EventType.FoundLeftIndex:
            s = event.param.split(",")
            return replace(lss, found_left_index=int(s[0]), found_left_length=int(s[1]), found_left_digit=s[2],
                           search_index=-1)
        if event.type == EventType.SearchingFromLeft:
            return replace(lss, search_index=int(event.param))
        raise ValueError(f"Unknown event type {event.type}")

    def build_line_ticks(self, seed_lss) -> list[Event]:
        lss = seed_lss
        while not self.is_value_calculated(lss):
            event = self.build_tick_event(lss)
            lss = self.update_lss_with_event(lss, event)
            yield event

    def build_ticks(self, event_processor):
        ticknumber = 1
        for i, line in enumerate(self.lines):
            lss = LineSearchState(i, line, -1, -1, 0, None, -1, 0, None, None)
            for event in self.build_line_ticks(lss):
                event_processor(ticknumber, event.__repr__())
                ticknumber += 1


def event_processor(ticknumber: int, event_string: str):
    print(ticknumber, event_string)
    # pass


if __name__ == "__main__":
    # data="1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet"
    data = "two1nine\neightwothree\nabcone2threexyz"
    # read in input.txt to string
    # with open('test/input.txt', 'r') as file:
    #     data = file.read()
    day01part1algorithm2 = Day01Part2Algorithm1(data)
    day01part1algorithm2.build_ticks(event_processor)
