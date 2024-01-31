import styled from "styled-components";
import { InputElementProps } from "./InputInterface";
import { StyledLabel, StyledRadio } from "./InputStyles";

const Wrapper = styled.div`
  display: flex;
  gap: 0.5rem;
  align-items: center;
`;

const RadioButton = ({ label, id, disabled, checked, ...rest }: InputElementProps) => (
  <Wrapper>
    <StyledRadio id={id} type="radio" disabled={disabled} checked={checked} {...rest} />
    <StyledLabel htmlFor={id} disabled={disabled}>
      {label}
    </StyledLabel>
  </Wrapper>
);

export default RadioButton;
