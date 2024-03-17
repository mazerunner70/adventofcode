import styled from "styled-components";
import React, { memo } from "react";

export interface Highlight {
  index: number;
  length: number;
  textColour: string;
}

const SpanHighlighter = styled.span<{ $textColour: string }>`
  color: ${(props) => props.$textColour};
`;

// create component that takes in a string and an array of index and length and highlighting scheme
export const StringHighlighter = memo(function StringHighlighter({
  highlights,
  text,
}: {
  highlights: Highlight[];
  text: string;
}): JSX.Element {
  let index = 0;
  console.log(">", text, highlights);
  highlights.sort((a, b) => a.index - b.index);
  return (
    <div>
      {highlights.map((highlight, i) => {
        const frag = (
          <span key={i}>
            {highlight.index > index && text.slice(index, highlight.index)}
            <SpanHighlighter $textColour={highlight.textColour}>
              {text.slice(
                Math.max(highlight.index, index),
                highlight.index + highlight.length,
              )}
            </SpanHighlighter>
          </span>
        );
        index = highlight.index + highlight.length;
        return frag;
      })}
      {text.slice(index)}
    </div>
  );
});
