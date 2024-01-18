import React from "react";
import Select from "react-select";
export interface Entry {
  value: string;
  label: string;
}
export interface ArrayObjectSelectState {
  selectedEntry: Entry | null;
}

export default function DropDown({
  options,
  selected,
  onChange,
}: {
  options: Entry[];
  selected: Entry | null;
  onChange: (state: ArrayObjectSelectState) => void;
}) {
  return (
    <Select
      // If you don't need a state you can remove the two following lines value & onChange
      value={selected}
      onChange={(option: Entry | null) => {
        onChange({ selectedEntry: option });
      }}
      getOptionLabel={(entry: Entry) => entry.label}
      getOptionValue={(entry: Entry) => entry.value}
      options={options}
      isClearable={true}
      backspaceRemovesValue={true}
    />
  );
}
