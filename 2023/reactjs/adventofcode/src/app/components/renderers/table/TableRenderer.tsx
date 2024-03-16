import {
  RespTable,
  RespTableBody,
  RespTableCaption,
  RespTableHeader,
  RespTableHeaderText,
  TableCell,
  TableHeaderCell,
  TableRow,
  WordGrid,
} from "@app/components/renderers/table/styled";

export interface RowProps {
  columns: string[];
  params: string[];
}

export interface ColumnProps {
  name: string;
  renderer: (rowProps: RowProps, columnNumber: number) => JSX.Element;
}

export interface TableProps {
  columns: ColumnProps[];
  rows: RowProps[];
}

export function TableRenderer({ columns, rows }: TableProps): JSX.Element {
  // console.log("TableRenderer", columns, rows);
  return (
    <WordGrid>
      <RespTable>
        <RespTableCaption>Table</RespTableCaption>
        <RespTableHeader>
          <RespTableHeaderText>
            {columns.map((column, i) => (
              <TableHeaderCell key={i}>{column.name}</TableHeaderCell>
            ))}
          </RespTableHeaderText>
        </RespTableHeader>
        <RespTableBody>
          {rows.map((row, i) => (
            <TableRow key={i}>
              {columns.map((column, j) => (
                <TableCell key={j}>{column.renderer(row, j)}</TableCell>
              ))}
            </TableRow>
          ))}
        </RespTableBody>
      </RespTable>
    </WordGrid>
  );
}
