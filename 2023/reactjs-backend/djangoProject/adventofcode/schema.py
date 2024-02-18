import graphene
from graphene import Int
from graphene_django import DjangoObjectType

from adventofcode.models import AdventDay, Task, InputData, Tick
from adventofcode.services.inputdataservice import InputDataService


class AdventDayType(DjangoObjectType):
    class Meta:
        model = AdventDay


class TaskType(DjangoObjectType):
    class Meta:
        model = Task


class InputDataType(DjangoObjectType):
    tick_count = Int(description="Count of ticks for the input data")

    class Meta:
        model = InputData

    def resolve_tick_count(self, info):
        # Return the count of related Tick instances
        return self.ticks.count()


class BuildTicksMutation(graphene.Mutation):
    class Arguments:
        id = graphene.ID(required=True)

    # The response fields of the mutation
    input_data = graphene.Field(InputDataType)
    tick_count = graphene.Field(InputDataType)
    success = graphene.Boolean()

    @classmethod
    def mutate(cls, root, info, id):
        try:
            input_data = InputData.objects.get(pk=id)
            if input_data.state != 'initialised':
                input_data.state = 'initialising'
                input_data.save()
                inputdataservice = InputDataService(input_data)
                inputdataservice.initialise()
                input_data.state = 'initialised'
                input_data.save()
                success = True
            else:
                success = False
            return BuildTicksMutation(input_data=input_data, success=success)
        except InputData.DoesNotExist:
            return BuildTicksMutation(input_data=None, success=False)


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
    ticks_in_range = graphene.List(
        TickType,
        input_data_id=graphene.Int(required=True),
        start_tick=graphene.Int(required=True),
        end_tick=graphene.Int(required=True)
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

    def resolve_ticks_in_range(self, info, input_data_id, start_tick, end_tick, **kwargs):
        # Fetch ticks for the specified InputData within the given range
        return Tick.objects.filter(
            input_data_id=input_data_id,
            tick_number__gte=start_tick,
            tick_number__lte=end_tick
        ).order_by('tick_number')

class MyMutations(graphene.ObjectType):
    build_ticks = BuildTicksMutation.Field()

aocschema = graphene.Schema(query=Query, mutation=MyMutations)
