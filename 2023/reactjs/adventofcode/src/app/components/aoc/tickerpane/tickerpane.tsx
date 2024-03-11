import React, { useEffect, useState } from "react";
import { initialiseInputData } from "@app/apiclient/inputdata";
import {
  fetchTicksByInputDataAndTickNumberRange,
  ITicksState,
} from "@app/apiclient/tick";
import { VCRControls } from "@app/components/aoc/vcrcontrols/vcrcontrols";
import ProgressPanel from "@app/components/aoc/progresspanel/progresspanel";
import {
  PaddingContainer,
  PaddingRightContainer,
  RowContainer,
} from "@app/components/aoc/taskpane/styled";

export enum InputDataState {
  initialising = "initialising",
  notInitialised = "not initialised",
  initialised = "initialised",
}

export enum InputDataType {
  test = "Test",
  full = "Full",
}

export interface IInputData {
  id: number;
  inputType: InputDataType;
  data: string;
  state: InputDataState;
}
export interface TickProps {
  inputData: IInputData;
  ticksState: ITicksState;
  totalTicks: number;
  currentTick: number;
}

export default function TickerPane({ inputData }: { inputData: IInputData }) {
  const [tickProps, setTickProps] = useState<TickProps>({
    inputData: {
      id: 0,
      inputType: InputDataType.test,
      data: "",
      state: InputDataState.notInitialised,
    },
    ticksState: {
      ticks: [],
    },
    totalTicks: 0,
    currentTick: 0,
  });
  const [speedState, setSpeedState] = useState<number>(0);

  useEffect(() => {
    if (inputData) {
      initialiseInputData(inputData.id)
        .then((res) => {
          console.log("initialiseInputData", res);
          if (res) {
            return fetchTicksByInputDataAndTickNumberRange(
              inputData,
              1,
              res.data.buildTicks.inputData.tickCount,
            );
          }
        })
        .then((res) => {
          console.log("fetchTicksByInputDataAndTickNumberRange", res);
          if (res) {
            setTickProps({
              inputData: inputData,
              ticksState: { ticks: res.ticks },
              currentTick: 1,
              totalTicks: res.ticks.length,
            });
          }
        });
    }
  }, [inputData]);

  function onSpeedChange(speed: number): void {
    if (tickProps.ticksState.ticks.length > 0) {
      setSpeedState(speed);
    }
  }

  return (
    <RowContainer>
      <PaddingContainer>
        <VCRControls speedState={speedState} onSpeedChange={onSpeedChange} />
      </PaddingContainer>
      <PaddingRightContainer>
        <ProgressPanel
          speedState={speedState}
          progressData={{
            currentTick: tickProps.currentTick,
            totalTicks: tickProps.totalTicks,
          }}
        />
      </PaddingRightContainer>
    </RowContainer>
  );
}
