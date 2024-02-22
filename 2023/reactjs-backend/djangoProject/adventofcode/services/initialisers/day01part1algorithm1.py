import json
from enum import Enum
from typing import List, Optional, Union
import copy

class Action(Enum):
    NoAction = 0
    TotalFound = 1
    ValueFoundForLine = 2
    DigitFoundForRight = 3
    SearchingFromRight = 4
    DigitFoundForLeft = 5
    SearchingFromLeft = 6
    StartingSearch = 7


class LineSearchState:
    def __init__(self, search_index: int, found_left_index: int, found_right_index: int,
                 value_found: Optional[int] = None):
        self.search_index = search_index
        self.found_left_index = found_left_index
        self.found_right_index = found_right_index
        self.value_found = value_found


class UIActions:
    def __init__(self, action: Action, param: str):
        self.action = action
        self.param = param


class TickState:
    def __init__(self, lines_searchstate: List[LineSearchState], total: int = None, completed: bool = False):
        self.lines_searchstate = lines_searchstate
        self.total = total
        self.completed = completed


class TickResult:
    def __init__(self, ui_actions: UIActions, tick_state: TickState):
        self.ui_actions = ui_actions
        self.tick_state = tick_state
    @staticmethod
    def to_json(obj):
        if isinstance(obj, UIActions):
            return obj.__dict__
        if isinstance(obj, TickState):
            return obj.__dict__
        if isinstance(obj, TickResult):
            return obj.__dict__
        if isinstance(obj, LineSearchState):
            return obj.__dict__
        if isinstance(obj, Action):
            return obj.value
        raise TypeError(f"Object of type '{obj.__class__.__name__}' is not JSON serializable")
    def to_json_camel(obj):
        if isinstance(obj, UIActions):
            return convert_keys(obj.__dict__)
        if isinstance(obj, TickState):
            return convert_keys(obj.__dict__)
        if isinstance(obj, TickResult):
            return convert_keys(obj.__dict__)
        if isinstance(obj, LineSearchState):
            return convert_keys(obj.__dict__)
        if isinstance(obj, Action):
            return obj.value
        raise TypeError(f"Object of type '{obj.__class__.__name__}' is not JSON serializable")
def snake_to_camel(snake_str):
    components = snake_str.split('_')
    return components[0] + ''.join(x.title() for x in components[1:])
def convert_keys(obj):
    if isinstance(obj, dict):
        return {snake_to_camel(k): convert_keys(v) for k, v in obj.items()}
    if isinstance(obj, list):
        return [convert_keys(i) for i in obj]
    return obj

def ui_actions_found_all_lines(tickstate: TickState) -> bool:
    return all(lss.value_found is not None for lss in tickstate.lines_searchstate)


def ui_actions_find_first_line_that(predicate, lines_searchstate: List[LineSearchState]) -> int:
    for index, lss in enumerate(lines_searchstate):
        if predicate(lss):
            return index
    return -1


def notp(predicate):
    return lambda lss: not predicate(lss)


def andp(predicates):
    return lambda lss: all(pred(lss) for pred in predicates)


def has_not_found_value(lss: LineSearchState) -> bool:
    return lss.value_found is None


def has_found_left_index(lss: LineSearchState) -> bool:
    return lss.found_left_index != -1


def has_found_right_index(lss: LineSearchState) -> bool:
    return lss.found_right_index != -1


def has_started_searching(lss: LineSearchState) -> bool:
    return lss.search_index != -1


def has_not_started_searching(lss: LineSearchState) -> bool:
    return lss.search_index == -1


def has_started_searching_from_left(lss: LineSearchState) -> bool:
    return andp([
        has_not_found_value,
        notp(has_found_left_index),
        has_started_searching
    ])(lss)


def has_started_searching_from_right(lss: LineSearchState) -> bool:
    return andp([
        has_not_found_value,
        has_found_left_index,
        notp(has_found_right_index),
        has_started_searching
    ])(lss)


def is_ready_to_find_value(lss: LineSearchState) -> bool:
    return andp([
        has_not_found_value,
        has_found_left_index,
        has_found_right_index
    ])(lss)


def is_digit(s: str) -> bool:
    return s.isdigit()


class Day01Part1Algorithm1:
    def __init__(self, data:str):
        self.data = data
        self.lines = self.data.split("\n")

    # call the tick method repeatedly until the tick result action says 1
    def initialise(self, tickProcessor):
        lines_searchstate = [LineSearchState(-1, -1, -1) for _ in self.lines]
        tickstate = TickState(lines_searchstate)
        ticknumber = 1
        while True:
            tickresult = self.tick(tickstate, self.lines)
            #jsonString = json.dumps(tickresult, default=TickResult.to_json)
            jsonString = json.dumps(tickresult, default=TickResult.to_json_camel)
            tickProcessor(ticknumber, jsonString)
            ticknumber += 1
            # if (ticknumber > 100):
            #     return -1;
            if (ticknumber % 100 == 0):
                print(ticknumber)
            if tickresult.ui_actions.action == Action.TotalFound:
                return tickresult.tick_state.total
            tickstate = tickresult.tick_state

    def tick(self, tickstate: TickState, lines: List[str]) -> TickResult:
        if tickstate.completed:
            return TickResult(UIActions(Action.NoAction, ""), tickstate)
        newlines_searchstate = copy.deepcopy(tickstate.lines_searchstate)
        # Check if all lines have found their values
        if ui_actions_found_all_lines(tickstate):
            total = sum(lss.value_found or 0 for lss in tickstate.lines_searchstate)
            return TickResult(UIActions(Action.TotalFound, str(total)), TickState(tickstate.lines_searchstate, total=total, completed=True))

        # Find line ready to show value
        find_index = ui_actions_find_first_line_that(is_ready_to_find_value, tickstate.lines_searchstate)
        if find_index != -1:
            lss = tickstate.lines_searchstate[find_index]
            inputline = lines[find_index]
            value_found = int(inputline[lss.found_left_index]+inputline[lss.found_right_index])
            newlines_searchstate[find_index].value_found = value_found
            newlines_searchstate[find_index].search_index = -2
            total = sum(lss.value_found or 0 for lss in newlines_searchstate)
            return TickResult(UIActions(Action.ValueFoundForLine, str(find_index)),
                              TickState(newlines_searchstate, completed=False, total=total))

        # Searching from right
        find_index_right = ui_actions_find_first_line_that(has_started_searching_from_right, tickstate.lines_searchstate)
        if find_index_right != -1:
            lss = tickstate.lines_searchstate[find_index_right]
            inputline = lines[find_index_right]
            if is_digit(inputline[lss.search_index]):
                newlines_searchstate[find_index_right].found_right_index = lss.search_index
                newlines_searchstate[find_index_right].search_index = -1
                return TickResult(UIActions(Action.DigitFoundForRight, str(find_index_right)),
                                  TickState(newlines_searchstate, tickstate.total))
            else:
                newlines_searchstate[find_index_right].search_index -= 1
                return TickResult(UIActions(Action.SearchingFromRight, str(find_index_right)),
                                  TickState(newlines_searchstate, tickstate.total))

        # Searching from left
        find_index_left = ui_actions_find_first_line_that(has_started_searching_from_left, tickstate.lines_searchstate)
        if find_index_left != -1:
            lss = tickstate.lines_searchstate[find_index_left]
            inputline = lines[find_index_left]
            if is_digit(inputline[lss.search_index]):
                newlines_searchstate[find_index_left].found_left_index = lss.search_index
                newlines_searchstate[find_index_left].search_index = len(inputline) - 1
                return TickResult(UIActions(Action.DigitFoundForLeft, str(find_index_left)),
                                  TickState(newlines_searchstate, tickstate.total))
            else:
                newlines_searchstate[find_index_left].search_index += 1
                return TickResult(UIActions(Action.SearchingFromLeft, str(find_index_left)),
                                  TickState(newlines_searchstate, tickstate.total))

        # Starting search
        find_index_not_started = ui_actions_find_first_line_that(has_not_started_searching, tickstate.lines_searchstate)
        if find_index_not_started != -1:
            newlines_searchstate[find_index_not_started].search_index = 0
            return TickResult(UIActions(Action.StartingSearch, str(find_index_not_started)),
                              TickState(newlines_searchstate, tickstate.total))

        raise Exception("Should not reach here")


def tickProcessor(jsonstate, ticknumber):
    #print(ticknumber, jsonstate)
    pass

if __name__ == "__main__":
    # data="1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet"
    #read in input.txt to string
    with open('test/input.txt', 'r') as file:
        data = file.read()
    day01part1algorithm1 = Day01Part1Algorithm1(data)
    day01part1algorithm1.initialise(tickProcessor)
