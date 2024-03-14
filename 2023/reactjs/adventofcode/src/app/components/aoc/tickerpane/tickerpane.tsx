import React, { ReactNode, useEffect, useState } from "react";
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
import { TickerStateContext } from "@app/contexts/contexts";
import { useTicker } from "@app/components/aoc/taskpane/useticker";

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
  currentTick: number;
  totalTicks: number;
}

export default function TickerPane({
  inputData,
  children,
}: { inputData: IInputData } & { children?: ReactNode }) {
  const [tickProps, setTickProps] = useState<TickProps>({
    inputData: {
      id: 0,
      inputType: InputDataType.test,
      data: "",
      state: InputDataState.notInitialised,
    },
    currentTick: 0,
    totalTicks: 0,
  });
  const [speedState, setSpeedState] = useState<number>(0);

  useEffect(() => {
    if (inputData) {
      initialiseInputData(inputData.id).then((res) => {
        console.log("initialiseInputData", res);
        if (res) {
          setTickProps({
            inputData: inputData,
            currentTick: 0,
            totalTicks: res.data.buildTicks.inputData.tickCount,
          });
        }
      });
    }
  }, [inputData]);

  function onSpeedChange(speed: number): void {
    console.log("speed", speedState);
    if (tickProps.totalTicks > 0) {
      setSpeedState(speed);
    }
  }
  const currTick = useTicker(
    (prevTick) => {
      return Math.min(
        Math.max(0, prevTick + speedState),
        tickProps.totalTicks - 1,
      );
    },
    speedState !== 0 ? 1000 : null,
    0,
  );

  useEffect(() => {
    setTickProps({ ...tickProps, currentTick: currTick });
  }, [currTick]);

  return (
    <>
      <RowContainer>
        <PaddingContainer>
          <VCRControls speedState={speedState} onSpeedChange={onSpeedChange} />
        </PaddingContainer>
        <PaddingRightContainer>
          <ProgressPanel
            speedState={speedState}
            progressData={{
              currentTick: currTick,
              totalTicks: tickProps.totalTicks,
            }}
          />
        </PaddingRightContainer>
      </RowContainer>
      <RowContainer>
        <TickerStateContext.Provider value={tickProps}>
          <PaddingContainer>{children}</PaddingContainer>
        </TickerStateContext.Provider>
      </RowContainer>
    </>
  );
}
