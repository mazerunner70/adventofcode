import { ReactNode } from "react";
import {
  InnerPanelWrapper,
  PanelSubTitle,
  PanelTitle,
  PanelWrapper,
} from "./styled";

export default function Panel({
  children,
  title,
  subheading,
  shadowed,
}: {
  children: ReactNode;
  title?: string;
  subheading?: string;
  shadowed?: boolean;
}) {
  return (
    <PanelWrapper shadowed={shadowed}>
      <InnerPanelWrapper>
        {title && <PanelTitle>{title}</PanelTitle>}
        {subheading && <PanelSubTitle>{subheading}</PanelSubTitle>}
        {children}
      </InnerPanelWrapper>
    </PanelWrapper>
  );
}
