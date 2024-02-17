from django.db import models

# Create your models here.
class AdventDay(models.Model):
    day_number = models.PositiveSmallIntegerField(unique=True, help_text="Day number (1-24)", db_index=True)
    title = models.CharField(max_length=255, help_text="Title of the Advent Day")
    description = models.TextField(help_text="Description of the Advent Day")

    class Meta:
        ordering = ['day_number']

    def __str__(self):
        return f"Day {self.day_number}: {self.title}"

class Task(models.Model):
    task_id = models.AutoField(primary_key=True)
    TASK_CHOICES = (
        (1, 'Task 1'),
        (2, 'Task 2'),
    )
    advent_day = models.ForeignKey(AdventDay, on_delete=models.CASCADE, related_name="tasks")
    task_number = models.PositiveSmallIntegerField(choices=TASK_CHOICES, help_text="Task number (1 or 2)")
    description = models.TextField(help_text="Description of the task")

    class Meta:
        unique_together = ('advent_day', 'task_number')
        ordering = ['advent_day__day_number', 'task_number']

    def __str__(self):
        return f"{self.advent_day.title} - {self.get_task_number_display()} (ID: {self.task_id})"

class InputData(models.Model):
    INPUT_TYPES = (
        ('test', 'Test'),
        ('full', 'Full'),
    )
    STATE_CHOICES = (
        ('not_initialised', 'Not Initialised'),
        ('initialising', 'Initialising'),
        ('initialised', 'Initialised'),
    )
    task = models.ForeignKey(Task, on_delete=models.CASCADE, related_name="input_data")
    input_type = models.CharField(max_length=4, choices=INPUT_TYPES, help_text="Type of input data (test or full)")
    data = models.TextField(help_text="The input data for the task")
    state = models.CharField(max_length=15, choices=STATE_CHOICES, default='not_initialised', help_text="State of the input data")

    class Meta:
        unique_together = ('task', 'input_type')

    def __str__(self):
        return f"{self.task} - {self.get_input_type_display()} Input"

class Tick(models.Model):
    input_data = models.ForeignKey(InputData, on_delete=models.CASCADE, related_name="ticks")
    tick_number = models.PositiveIntegerField(help_text="Sequential number of the tick for the input data")
    endstate = models.TextField(help_text="The state and actions from this tick", default='{}')

    class Meta:
        unique_together = ('input_data', 'tick_number')
        ordering = ['input_data__task__advent_day__day_number', 'input_data__task__task_number', 'tick_number']

    def __str__(self):
        return f"Tick {self.tick_number} for {self.input_data}"
