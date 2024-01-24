import { InputHTMLAttributes } from "react";

export interface IOption {
  label: string;
  name?: string;
  disabled?: boolean;
}

export interface IInputGroup {
  label: string;
  options: IOption[];
  hasFullWidth?: boolean;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export interface InputElementProps
  extends InputHTMLAttributes<HTMLInputElement> {
  label: string;
  id: string;
  error?: boolean;
  disabled?: boolean;
}
