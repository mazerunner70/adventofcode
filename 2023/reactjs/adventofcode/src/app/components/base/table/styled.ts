import styled from "styled-components";

export const Table = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  font-family: "Roboto Mono", monospace;
  font-size: 14px;
  font-weight: 400;
  color: #000000;
  background-color: #ffffff;
  border-radius: 5px;
  overflow: hidden;
`;
export const TableHeader = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 40px;
  background-color: #f2f2f2;
`;
export const TableHeaderCell = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  height: 100%;
  padding: 0 10px;
  font-size: 14px;
  font-weight: 700;
  color: #000000;
  text-align: left;
`;
export const TableBody = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;
export const TableRow = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 40px;
  background-color: #ffffff;
  border-bottom: 1px solid #f2f2f2;
`;
export const TableCell = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  height: 100%;
  padding: 0 10px;
  font-size: 14px;
  font-weight: 400;
  color: #000000;
  text-align: left;
`;
