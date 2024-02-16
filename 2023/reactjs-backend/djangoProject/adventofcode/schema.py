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
    inputdata_by_task_id_and_type = graphene.Field(
        InputDataType,
        task_id=graphene.Int(required=True),
        input_type=graphene.String(required=True)
    )

    def resolve_all_advent_days(self, info, **kwargs):
        return AdventDay.objects.all()

    def resolve_task_by_id(self, info, task_id):
        return Task.objects.get(pk=task_id)

    def resolve_input_data_by_task(self, info, task_id):
        return InputData.objects.filter(task_id=task_id)

    def resolve_inputdata_by_task_id_and_type(self, info, task_id, input_type):
        try:
            return InputData.objects.get(task_id=task_id, input_type=input_type)
        except InputData.DoesNotExist:
            return None

    def resolve_ticks_by_input_data(self, info, input_data_id):
        return Tick.objects.filter(input_data_id=input_data_id)

aocschema = graphene.Schema(query=Query)