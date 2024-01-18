import styled from "styled-components";
import { Legend } from "./InputStyles";
import { IInputGroup, IOption } from "./InputInterface";
import RadioButton from "./RadioButton";

// https://medium.com/@christinaroise/how-to-create-a-reusable-custom-radiobutton-in-react-with-typescript-3ae7fc704e09

const Fieldset = styled.fieldset`
  border: none;
`;

const Wrapper = styled.div`
  padding: 0.5rem;
  display: grid;
  gap: 1rem;
`;

const RadioButtonGroup = ({ label, options, onChange }: IInputGroup) => {
  function renderOptions() {
    return options.map(({ label, name, disabled }: IOption, index) => {
      const shortenedOptionLabel = label.replace(/\s+/g, "");
      const optionId = `radio-option-${shortenedOptionLabel}`;

      return (
        <RadioButton
          value={label}
          label={label}
          key={optionId}
          id={optionId}
          name={name}
          disabled={disabled}
          defaultChecked={index === 0}
          onChange={onChange}
        />
      );
    });
  }
  return (
    <Fieldset>
      <Legend>{label}</Legend>
      <Wrapper>{renderOptions()}</Wrapper>
    </Fieldset>
  );
};
export default RadioButtonGroup;
