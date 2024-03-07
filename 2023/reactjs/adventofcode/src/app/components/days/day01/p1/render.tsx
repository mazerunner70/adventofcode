import { pairwise } from "@app/utils/ArrayOps";
import {
  FoundLetter,
  RespTable,
  RespTableBody,
  RespTableHeaderText,
  SearchLetter,
  TableCell,
  TableHeaderCell,
  TableRow,
  WordGrid,
} from "./styled";

import { useEffect, useRef, useState } from "react";
import gsap from "gsap";
import {
  emptySearchState,
  LineSearchState,
  SearchEventType,
} from "@app/components/days/day01/p1/stateengine";
import { tick } from "@app/components/days/day01/p1/ticker";

const emptyState: LineSearchState = emptySearchState(-1);

function RenderLine({
  line,
  tickOutcome,
}: {
  line: string;
  tickOutcome: LineSearchState;
}): JSX.Element {
  const tableCellRef = useRef(null);
  const foundValueRef = useRef(null);

  if (
    tickOutcome.event_type === SearchEventType.SearchingFromRight &&
    tickOutcome.search_index === line.length - 1
  ) {
    const scaleTween = gsap.to(tableCellRef.current, {
      scale: 1.1,
      repeat: 3,
      x: 5,
      yoyo: true,
      paused: true,
      color: "yellow",
    });
    scaleTween.play();
    //console.log("scaleTween1")
  }
  if (tickOutcome.event_type === SearchEventType.ValueCalculated) {
    const scaleTween = gsap.to(foundValueRef.current, {
      scale: 1.1,
      repeat: 3,
      x: 5,
      yoyo: true,
      paused: true,
      color: "yellow",
    });
    scaleTween.play();
    //console.log("scaleTween2")
  }

  function compareNumbers(a, b) {
    return a - b;
  }
  const indexes: number[] = [
    0,
    tickOutcome.search_index,
    tickOutcome.found_left_index,
    tickOutcome.found_right_index,
    line.length,
  ]
    .filter((v) => v != -1)
    .sort(compareNumbers);
  //console.log(">", indexes)
  const slices: string[] = Array.from(pairwise(indexes)).map((s, i) =>
    line.slice(s[0], s[1]),
  );
  //console.log(">>", slices)
  return (
    <TableRow>
      <TableCell ref={tableCellRef}>
        {slices.map((slice, i) => {
          const headSlice = slice[0];
          const tailSlice = slice.slice(1);
          return i === 0 ? (
            <span key={i}>{slice}</span>
          ) : (
            <span key={i}>
              {indexes[i] === tickOutcome.found_left_index ||
              indexes[i] === tickOutcome.found_right_index ? (
                <FoundLetter>{headSlice}</FoundLetter>
              ) : indexes[i] === tickOutcome.search_index ? (
                <SearchLetter>{headSlice}</SearchLetter>
              ) : (
                <span>{headSlice}</span>
              )}
              <span>{tailSlice}</span>
            </span>
          );
        })}
      </TableCell>
      <TableCell ref={foundValueRef}>{tickOutcome.value_found}</TableCell>
    </TableRow>
  );
}

export default function Render({
  data,
  tickOutcome,
  linesCompleted,
}: {
  data: string;
  tickOutcome: LineSearchState;
  linesCompleted: LineSearchState[];
}): JSX.Element {
  console.log("Render", tickOutcome, linesCompleted);
  const foundValueRef = useRef(null);

  if (tickOutcome.event_type === SearchEventType.ValueCalculated) {
    const scaleTween = gsap.to(foundValueRef.current, {
      scale: 1.1,
      repeat: 3,
      x: 5,
      yoyo: true,
      paused: true,
      color: "yellow",
    });
    scaleTween.play();
    console.log("scaleTween2");
  }
  const lines = data.split("\n");
  //console.log("entered Render Day01", lines, data, tickState, uiActions);
  return (
    <div>
      <div>
        <WordGrid>
          <RespTable>
            <RespTableBody>
              <TableRow>
                <TableCell>Total</TableCell>
                <TableCell ref={foundValueRef}>{tickOutcome.total}</TableCell>
              </TableRow>

              <RespTableHeaderText>
                <TableHeaderCell>Word</TableHeaderCell>
                <TableHeaderCell>Found</TableHeaderCell>
              </RespTableHeaderText>

              {lines.map((line, i) => {
                return (
                  tickOutcome && (
                    <RenderLine
                      key={i}
                      line={line}
                      tickOutcome={
                        tickOutcome.line_number === i
                          ? tickOutcome
                          : tickOutcome.line_number > i
                            ? linesCompleted[i]
                            : emptyState
                      }
                    />
                  )
                );
              })}
            </RespTableBody>
          </RespTable>
        </WordGrid>
      </div>
    </div>
  );
}
