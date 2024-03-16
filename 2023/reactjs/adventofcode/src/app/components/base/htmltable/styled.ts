import styled from "styled-components";

export const TableContainer = styled.div`
  position: absolute;
  display: inline-block;

  padding: 0;
`;

export const Table = styled.table`
  border-collapse: collapse;
  width: 100%;
  background-color: #ffffff;
`;

export const TableHeader = styled.thead`
  background-color: #f2f2f2;
  font-weight: bold;
`;

export const TableHeaderCell = styled.th`
  padding: 10px;
  text-align: left;
`;

export const TableBody = styled.tbody`
  background-color: #ffffff;
`;

export const TableRow = styled.tr`
  border-bottom: 1px solid #f2f2f2;
`;

export const TableCell = styled.td`
  padding: 10px;
  text-align: left;
`;

export const TableFooter = styled.tfoot`
  background-color: #f2f2f2;
  font-weight: bold;
`;

export const TableFooterCell = styled.td`
  padding: 10px;
  text-align: left;
`;

export const TableCaption = styled.caption`
  font-weight: bold;
  font-size: 1.5em;
  color: #000000;
  padding: 10px;
`;

export const TableHeaderCellCentered = styled(TableHeaderCell)`
  text-align: center;
`;

export const TableCellCentered = styled(TableCell)`
  text-align: center;
`;

export const TableFooterCellCentered = styled(TableFooterCell)`
  text-align: center;
`;

export const TableHeaderCellRight = styled(TableHeaderCell)`
  text-align: right;
`;

export const TableCellRight = styled(TableCell)`
  text-align: right;
`;

export const TableFooterCellRight = styled(TableFooterCell)`
  text-align: right;
`;

export const TableHeaderCellRightAligned = styled(TableHeaderCell)`
  text-align: right;
`;

export const TableCellRightAligned = styled(TableCell)`
  text-align: right;
`;

export const TableFooterCellRightAligned = styled(TableFooterCell)`
  text-align: right;
`;

export const TableHeaderCellLeft = styled(TableHeaderCell)`
  text-align: left;
`;

export const TableCellLeft = styled(TableCell)`
  text-align: left;
`;

export const TableFooterCellLeft = styled(TableFooterCell)`
  text-align: left;
`;

export const TableHeaderCellCenteredRight = styled(TableHeaderCell)`
  text-align: center;
`;

export const TableCellCenteredRight = styled(TableCell)`
  text-align: center;
`;

export const TableFooterCellCenteredRight = styled(TableFooterCell)`
  text-align: center;
`;

export const TableHeaderCellCenteredLeft = styled(TableHeaderCell)`
  text-align: center;
`;

export const TableCellCenteredLeft = styled(TableCell)`
  text-align: center;
`;

export const TableFooterCellCenteredLeft = styled(TableFooterCell)`
  text-align: center;
`;

export const TableHeaderCellRightCentered = styled(TableHeaderCell)`
  text-align: right;
`;

export const TableCellRightCentered = styled(TableCell)`
  text-align: right;
`;
