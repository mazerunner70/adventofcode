import React, { ReactNode, useEffect, useState } from "react";
import {
  IInputData,
  initialiseInputData,
  InputDataState,
  InputDataType,
} from "@app/apiclient/inputdata";

import { VCRControls } from "@app/components/aoc/vcrcontrols/vcrcontrols";
import ProgressPanel from "@app/components/aoc/progresspanel/progresspanel";
import { PaddingContainer } from "@app/components/aoc/taskpane/styled";
import { TickerStateContext } from "@app/contexts/contexts";
import { useTicker } from "@app/components/aoc/taskpane/useticker";
import styled from "styled-components";
import {
  Table,
  TableBody,
  TableCell,
  TableRow,
} from "@app/components/base/htmltable/styled";

export interface TickProps {
  inputData: IInputData;
  currentTick: number;
  totalTicks: number;
}

const Z1 = styled.div`
  z-index: 2;
`;

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
    <Table>
      <TableBody>
        <TableRow>
          <TableCell>
            <PaddingContainer>
              <VCRControls
                speedState={speedState}
                onSpeedChange={onSpeedChange}
              />
            </PaddingContainer>
          </TableCell>

          <TableCell>
            <PaddingContainer>
              <ProgressPanel
                speedState={speedState}
                progressData={{
                  currentTick: currTick,
                  totalTicks: tickProps.totalTicks,
                }}
              />
            </PaddingContainer>
          </TableCell>
        </TableRow>
        <TableRow>
          <TableCell>
            <TickerStateContext.Provider value={tickProps}>
              <PaddingContainer>
                <Z1>{children}</Z1>
              </PaddingContainer>
            </TickerStateContext.Provider>
          </TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
}
