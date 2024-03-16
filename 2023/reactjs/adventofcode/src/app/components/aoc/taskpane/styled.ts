import styled from "styled-components";
import { familjenGrotesk } from "@app/styles/fonts";

export const TaskPanel = styled.div`
  ${familjenGrotesk.style};
`;

export const PaddingContainer = styled.div`
  padding: 10px;
`;
export const PaddingCentredContainer = styled.div`
  padding: 10px;
  margin: 0 auto;
  height: 100%;
`;
export const PaddingRightContainer = styled.div`
  position: absolute;
  padding: 10px;
  right: 0;
`;
export const RowContainer = styled.div`
  display: flex;
  flex-direction: row;
`;
export const ColumnContainer = styled.div`
  display: flex;
  flex-direction: column;
`;
