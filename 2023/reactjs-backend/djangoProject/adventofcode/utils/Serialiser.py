import json

# from adventofcode.services.initialisers.day01part1algorithm1 import UIActions, TickState, TickResult, LineSearchState, Action


def to_json(obj, classlist, enumlist):
    def as_default(classlist, enumlist):
        return lambda obj: to_json_camel2(obj, classlist, enumlist)
    return json.dumps(obj, default=as_default(classlist, enumlist))

# use isinstance to check if class is in classlist, convert to camel case
def to_json_camel2(obj, classlist, enumlist):
    # classlist = [UIActions, TickState, TickResult, LineSearchState, Action]
    res = [isinstance(obj, clazz) for clazz in classlist]
    if any(res):
        return convert_keys(obj.__dict__)
    res = [isinstance(obj, enum) for enum in enumlist]
    if any(res):
        return obj.value

    raise TypeError(f"Object of type '{obj.__class__.__name__}' is not JSON serializable")
# def to_json_camel(obj):
#     def to_json_camel(obj):
#         if isinstance(obj, UIActions):
#             return convert_keys(obj.__dict__)
#         if isinstance(obj, TickState):
#             return convert_keys(obj.__dict__)
#         if isinstance(obj, TickResult):
#             return convert_keys(obj.__dict__)
#         if isinstance(obj, LineSearchState):
#             return convert_keys(obj.__dict__)
#         if isinstance(obj, Action):
#             return obj.value
#         raise TypeError(f"Object of type '{obj.__class__.__name__}' is not JSON serializable")
def snake_to_camel(snake_str):
    components = snake_str.split('_')
    return components[0] + ''.join(x.title() for x in components[1:])
def convert_keys(obj):
    if isinstance(obj, dict):
        return {snake_to_camel(k): convert_keys(v) for k, v in obj.items()}
    if isinstance(obj, list):
        return [convert_keys(i) for i in obj]
    return obj
