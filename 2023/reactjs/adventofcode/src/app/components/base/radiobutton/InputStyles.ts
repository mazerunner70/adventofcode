import styled from "styled-components";
import { BrandColor } from "../../styled-components/GlobalStyles";

export const Legend = styled.legend`
  font-weight: 600;
  font-size: 1rem;
  color: ${BrandColor.DARK_PURPLE};
  font-family:
    StabilGrotesk,
    -apple-system,
    BlinkMacSystemFont,
    "Segoe UI",
    Roboto,
    Oxygen-Sans,
    Ubuntu,
    Cantarell,
    "Helvetica Neue",
    sans-serif;
`;

export const StyledRadio = styled.input`
  appearance: none;
  margin: 0;
  width: 20px;
  height: 20px;
  border: 2px solid ${BrandColor.DARK_PURPLE};
  border-radius: 50%;
  transition: all 0.1s ease-in-out;

  &::after {
    content: "";
    display: block;
    border-radius: 50%;
    width: 12px;
    height: 12px;
    margin: 2px;
  }

  &:checked::after {
    background-color: ${BrandColor.DARK_PURPLE};
  }

  &:hover::after {
    background-color: ${BrandColor.DARK_PURPLE_FADED};
  }

  &:focus {
    outline: 2px solid ${BrandColor.YELLOW};
  }

  &:disabled {
    cursor: not-allowed;
    border-color: ${BrandColor.DARK_PURPLE_FADED};
    background-color: ${BrandColor.PURPLE};

    &::after {
      background-color: ${BrandColor.PURPLE};
    }

    &:checked::after {
      background-color: ${BrandColor.DARK_PURPLE_FADED};
    }
  }
`;

export const StyledLabel = styled.label<{ disabled?: boolean }>`
  font-weight: 600;
  font-size: 1rem;
  color: ${BrandColor.DARK_PURPLE};
  cursor: default;

  ${({ disabled }) =>
    disabled &&
    `
    color: ${BrandColor.DARK_PURPLE_FADED};
    cursor: not-allowed;
    &::after {
      margin-left: 8px;
      width: 12px;
      height: 15px;
      display: inline-block;
      content: " ";
      -webkit-mask: url("/lock.svg") no-repeat 50% 50%;
      -webkit-mask-size: cover;
      background-color: ${BrandColor.DARK_PURPLE};
    }
  `}
`;
