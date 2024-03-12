import {
  RowProps,
  TableRenderer,
} from "@app/components/renderers/table/TableRenderer";
import {
  Highlight,
  StringHighlighter,
} from "@app/components/tasks/day01/util/StringHighlighter";

const highlightCellRenderer = (rowProps: RowProps, columnNumber: number) => {
  const rules: Highlight[] = [];
  if (rowProps.params[0] !== "-1") {
    rules.push({
      index: parseInt(rowProps.params[0]),
      length: 1,
      textColour: "red",
    });
  }
  if (rowProps.params[1] !== "-1") {
    rules.push({
      index: parseInt(rowProps.params[1]),
      length: 1,
      textColour: "blue",
    });
  }
  if (rowProps.params[2] !== "-1") {
    rules.push({
      index: parseInt(rowProps.params[2]),
      length: 1,
      textColour: "blue",
    });
  }
  return (
    <StringHighlighter
      highlights={rules}
      text={rowProps.columns[columnNumber]}
    />
  );
};
const cellRenderer = (rowProps: RowProps, columnNumber: number) => {
  switch (columnNumber) {
    case 0:
      return highlightCellRenderer(rowProps, columnNumber);
    default:
      return <div>{rowProps.columns[columnNumber]}</div>;
  }
};
const columns = [
  { name: "Column 1", renderer: cellRenderer },
  { name: "Column 2", renderer: cellRenderer },
];
const rows = [
  { columns: ["This is a cell", "100"], params: ["-1", "-1", "-1"] },
  { columns: ["This is also a cell", "43"], params: ["2", "-1", "-1"] },
];
export const Day01p1 = () => {
  return (
    <div>
      <TableRenderer columns={columns} rows={rows} />
    </div>
  );
};
