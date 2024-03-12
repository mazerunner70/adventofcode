import AdventDay from "@app/components/aoc/taskselection/adventday";
import { IConfig } from "@app/contexts/Advent.context";
import Panel from "@app/components/base/panel";
import styled from "styled-components";
import { H1 } from "@app/components/base/text/textwrappers";
import { Legend } from "@app/components/base/radiobutton/InputStyles";
import DropDown, {
  Entry,
  ArrayObjectSelectState,
} from "@app/components/base/dropdown/dropdown";
import React, { useEffect } from "react";
import RadioButtonGroup from "@app/components/base/radiobutton/RadioButtonGroup";
import { familjenGrotesk } from "@app/styles/fonts";

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

export interface ISelectionConfig {
  day: number;
  part: number;
  testData: boolean;
  onDayChange: (day: number | null) => void;
  onPartChange: (part: number) => void;
  onTestDataChange: (testData: boolean) => void;
}

export default function TaskSelection({
  daylist,
  selectionConfig,
}: {
  daylist: number[];
  selectionConfig: ISelectionConfig;
}) {
  const toEntry: (dayno: number) => Entry = (day) => ({
    label: day.toString(),
    value: day.toString(),
  });
  const dayOptions = daylist.map(toEntry);

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

  // function partRadioGroupHandler(event: React.ChangeEvent<HTMLInputElement>) {
  //   setPartState(event.target.value);
  // }

  console.log(
    selectionConfig.day,
    selectionConfig.part,
    selectionConfig.testData,
  );
  return (
    <Panel title={"Task Selection"} shadowed={true}>
      <div>
        <HorizontalDivider />
        <ADPanel>
          <Legend>Day</Legend>
          <DropDown
            options={dayOptions}
            selected={toEntry(selectionConfig.day)}
            onChange={(daynoString: string | null) =>
              selectionConfig.onDayChange(
                daynoString ? parseInt(daynoString) : null,
              )
            }
          />
        </ADPanel>
        <HorizontalDivider />
        <ADPanel>
          <RadioButtonGroup
            label="Part"
            options={parts}
            selected={selectionConfig.part.toString()}
            onChange={(evt: React.ChangeEvent<HTMLInputElement>) =>
              selectionConfig.onPartChange(parseInt(evt.target.value))
            }
          />
        </ADPanel>
        <HorizontalDivider />
        <ADPanel>
          <RadioButtonGroup
            label="Test Data"
            options={testDataOnOff}
            selected={selectionConfig.testData ? "On" : "Off"}
            onChange={(evt: React.ChangeEvent<HTMLInputElement>) =>
              selectionConfig.onTestDataChange(evt.target.value == "On")
            }
          />
        </ADPanel>
      </div>
    </Panel>
  );
}
