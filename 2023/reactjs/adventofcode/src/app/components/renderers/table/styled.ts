"use client";
import styled from "styled-components";

export const WordGrid = styled.div`
  font:
    1.5rem "Courier New",
    monospace,
    bold;
`;

// table in css
export const RespTable = styled.div`
  display: table;
`;
export const RespTableCaption = styled.div`
  display: table-caption;
  text-align: center;
  font-weight: bold;
  font-size: 1em;
`;
export const RespTableHeader = styled.div`
  display: table-header-group;
  background-color: gray;
  font-weight: bold;
  font-size: 1em;
`;
export const RespTableHeaderText = styled.div`
  display: table-row;
  background-color: gray;
  font-weight: bold;
  font-size: 1em;
`;
export const TableHeaderCell = styled.div`
  display: table-cell;
  //border: 1px solid black;
  padding: 5px;
  text-align: center;
`;
export const RespTableBody = styled.div`
  display: table-row-group;
`;
export const TableRow = styled.div`
  display: table-row;
`;
export const TableCell = styled.div`
  display: table-cell;
  text-align: center;
  border: 1px solid black;
  padding: 5px;
`;
export const TableFooter = styled.div`
  display: table-footer-group;
  background-color: gray;
  font-weight: bold;
  font-size: 1.5em;
  color: white;
`;
export const TableFooterCell = styled.div`
  display: table-cell;
  border: 1px solid black;
  padding: 5px;
  text-align: center;
`;
