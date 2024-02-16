import graphene
from graphene_django import DjangoObjectType

from adventofcode.models import AdventDay, Task, InputData, Tick


class AdventDayType(DjangoObjectType):
    class Meta:
        model = AdventDay

class TaskType(DjangoObjectType):
    class Meta:
        model = Task

class InputDataType(DjangoObjectType):
    class Meta:
        model = InputData

class TickType(DjangoObjectType):
    class Meta:
        model = Tick

class Query(graphene.ObjectType):
    all_advent_days = graphene.List(AdventDayType)
    task_by_id = graphene.Field(TaskType, task_id=graphene.Int())
    input_data_by_task = graphene.List(InputDataType, task_id=graphene.Int())
    ticks_by_input_data = graphene.List(TickType, input_data_id=graphene.Int())

    def resolve_all_advent_days(self, info, **kwargs):
        return AdventDay.objects.all()

    def resolve_task_by_id(self, info, task_id):
        return Task.objects.get(pk=task_id)

    def resolve_input_data_by_task(self, info, task_id):
        return InputData.objects.filter(task_id=task_id)

    def resolve_ticks_by_input_data(self, info, input_data_id):
        return Tick.objects.filter(input_data_id=input_data_id)

aocschema = graphene.Schema(query=Query)