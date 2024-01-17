import AdventDayPart from "@app/components/adventdaypart/AdventDayPart";
import { IConfig } from "@app/context/Advent.context";
import styled from "styled-components";

const ADPanel = styled.div`
  width: fit-content;
  display: inline-block;
`;

export default function AdventDay({ dayConfig }: { dayConfig: IConfig }) {
  return (
    <ADPanel>
      <h1>Day {dayConfig.dayno}</h1>
    </ADPanel>
  );
}
