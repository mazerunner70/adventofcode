import json

class HardwareProfile:
    def __init__(self, vm_size):
        self.vm_size = vm_size
        self.some_other_thing = 42
        self.a = 'a'

def snake_to_camel(s):
    a = s.split('_')
    a[0] = a[0].lower()
    if len(a) > 1:
        a[1:] = [u.title() for u in a[1:]]
    return ''.join(a)

def serialise(obj):
    return {snake_to_camel(k): v for k, v in obj.__dict__.items()}

if __name__ == '__main__':
    hp = HardwareProfile('Large')
    print(json.dumps(serialise(hp), indent=4, default=serialise))