import AdventDay from "@app/components/aoc/sidepanel/adventday";
import { IConfig } from "@app/context/Advent.context";
import Panel from "@app/components/base/panel";
import styled from "styled-components";
import { H1 } from "@app/components/base/text/textwrappers";
import {Legend} from "@app/components/base/radiobutton/InputStyles";
import DropDown, {
  Entry,
  ArrayObjectSelectState,
} from "@app/components/base/dropdown/dropdown";
import React, { useEffect } from "react";
import RadioButtonGroup from "@app/components/base/radiobutton/RadioButtonGroup";
import { familjenGrotesk} from "@app/styles/fonts";

const HorizontalDivider = styled.hr`
  border: 0;
  height: 1px;
  background-image: linear-gradient(
    to right,
    rgba(0, 0, 0, 0),
    rgba(0, 0, 0, 0.75),
    rgba(0, 0, 0, 0)
  );
`;
const ADPanel = styled.div`
  width: fit-content;
  display: flex;
  flex-direction: column;
`;
export default function AdventDays({ data }: { data: IConfig[] }) {
  const toEntry: (day: IConfig) => Entry = (day) => ({
    label: day.dayno.toString(),
    value: day.dayno.toString(),
  });
  const dayOptions = data.map(toEntry);
  const [dayNumberState, setDayNumberState] =
    React.useState<ArrayObjectSelectState>({
      selectedEntry: dayOptions[0],
    });

  const parts = [
    {
      label: "1",
      name: "Parts",
    },
    {
      label: "2",
      name: "Parts",
    },
  ];
  const testDataOnOff = [
    {
      label: "On",
      name: "OnOff",
    },
    {
      label: "Off",
      name: "OnOff",
    },
  ];

  console.log(familjenGrotesk)

  const [partState, setPartState] = React.useState<string>(parts[0].label);
  function partRadioGroupHandler(event: React.ChangeEvent<HTMLInputElement>) {
    setPartState(event.target.value);
  }

  useEffect(() => {
    console.log(dayNumberState, partState);
  }, [dayNumberState, partState]);

  return (
    <Panel title={"Tasks List"} shadowed={true}>
      <div>
        <HorizontalDivider />
        <ADPanel>
          <Legend>Day</Legend>
          <DropDown
            options={dayOptions}
            selected={dayNumberState.selectedEntry}
            onChange={setDayNumberState}
          />
        </ADPanel>
        <HorizontalDivider />
        <ADPanel>
          <RadioButtonGroup
              label="Part"
              options={parts}
              onChange={partRadioGroupHandler}
          />
        </ADPanel>
        <HorizontalDivider />
        <ADPanel>
          <RadioButtonGroup
              label="Test Data"
              options={testDataOnOff}
              onChange={partRadioGroupHandler}
          />
        </ADPanel>
      </div>
    </Panel>
  );
}
