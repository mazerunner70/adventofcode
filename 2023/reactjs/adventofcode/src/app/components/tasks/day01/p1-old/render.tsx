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
} from "@app/components/tasks/day01/p1/stateengine";
import {
  Highlight,
  StringHighlighter,
} from "@app/components/tasks/day01/util/StringHighlighter";
import {
  AnimationType,
  useAnimation,
} from "@app/components/tasks/day01/util/useanimate";

const emptyState: LineSearchState = emptySearchState(-1);

function RenderLine({
  line,
  tickOutcome,
}: {
  line: string;
  tickOutcome: LineSearchState;
}): JSX.Element {
  const [highlightRules, setHighlightRules] = useState<Highlight[]>([]);
  useEffect(() => {
    const rules: Highlight[] = [];
    if (tickOutcome.search_index !== -1) {
      rules.push({
        index: tickOutcome.search_index,
        length: 1,
        textColour: "red",
      });
    }
    if (tickOutcome.found_left_index !== -1) {
      rules.push({
        index: tickOutcome.found_left_index,
        length: 1,
        textColour: "blue",
      });
    }
    if (tickOutcome.found_right_index !== -1) {
      rules.push({
        index: tickOutcome.found_right_index,
        length: 1,
        textColour: "blue",
      });
    }
    setHighlightRules(rules);
  }, [tickOutcome]);
  const tableCellRef = useAnimation(AnimationType.YOYO, () => {
    return (
      tickOutcome.event_type === SearchEventType.SearchingFromLeft &&
      tickOutcome.search_index === 0
    );
  });
  const foundValueRef = useAnimation(AnimationType.YOYO, () => {
    return tickOutcome.event_type === SearchEventType.ValueCalculated;
  });
  return (
    <TableRow>
      <TableCell ref={tableCellRef}>
        <StringHighlighter text={line} highlights={highlightRules} />
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
                <TableCell>{tickOutcome.total}</TableCell>
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
