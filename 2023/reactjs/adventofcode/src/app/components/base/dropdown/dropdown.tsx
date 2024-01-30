import React from "react";
import Select from "react-select";
import { BrandColor } from "../../styled-components/GlobalStyles";
export interface Entry {
  value: string;
  label: string;
}
export interface ArrayObjectSelectState {
  selectedEntry: string | null;
}

export default function DropDown({
  options,
  selected,
  onChange,
}: {
  options: Entry[];
  selected: Entry | null;
  onChange: (string | null) => void;
}) {
  const newTheme = (theme: any) => {
    return {
      ...theme,
      colors: {
        ...theme.colors,
        primary25: "var(--color-primary-25)",
        primary: "var(--color-primary)",
      },
    };
  }
  const newTheme2 = (theme: any) => {
    return {
      ...theme,
      colors: {
        ...theme.colors,
        primary25: BrandColor.YELLOW,
        primary: BrandColor.DARK_PURPLE,
      },
    };
  }
  return (
    <Select
      // If you don't need a state you can remove the two following lines value & onChange
      value={selected}
      onChange={(option: Entry | null) => {
        onChange(option?.value);
      }}
      getOptionLabel={(entry: Entry) => entry.label}
      getOptionValue={(entry: Entry) => entry.value}
      options={options}
      isClearable={true}
      backspaceRemovesValue={true}
        theme={newTheme2}
    />
  );
}
