# Generated by Django 5.0.2 on 2024-02-17 14:52

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('adventofcode', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='inputdata',
            name='state',
            field=models.CharField(choices=[('not_initialised', 'Not Initialised'), ('initialising', 'Initialising'), ('initialised', 'Initialised')], default='not_initialised', help_text='State of the input data', max_length=15),
        ),
    ]