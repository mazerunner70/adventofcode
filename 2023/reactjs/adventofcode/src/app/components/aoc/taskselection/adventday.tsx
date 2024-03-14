import AdventDayPart from "@app/components/adventdaypart/AdventDayPart";
import { IConfig } from "@app/contexts/Advent.context";
import styled from "styled-components";
import { Legend } from "../../../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/base/radiobutton/InputStyles";

const ADPanel = styled.div`
  width: fit-content;
  display: inline-block;
`;
const Heading = styled(Legend)``;

export default function AdventDay({ dayConfig }: { dayConfig: IConfig }) {
  return (
    <ADPanel>
      <Heading>Day {dayConfig.dayno}</Heading>
    </ADPanel>
  );
}
