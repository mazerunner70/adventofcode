import AdventDay from "@app/components/aoc/sidepanel/adventday";
import { IConfig } from "@app/context/Advent.context";
import Panel from "@app/components/base/panel";
import styled from "styled-components";

const Divider = styled.hr`
  border: 0;
  height: 1px;

  background-image: linear-gradient(
    to right,
    rgba(0, 0, 0, 0),
    rgba(0, 0, 0, 0.75),
    rgba(0, 0, 0, 0)
  );
`;
const ADPanel = styled.div`
  width: fit-content;
  display: inline-block;
`;
export default function AdventDays({ data }: { data: IConfig[] }) {
  return (
    <Panel title={"Tasks List"} shadowed={true}>
      {data?.map((dayConfig: IConfig) => (
        <div>
          <Divider />
          <AdventDay key={dayConfig.dayno} dayConfig={dayConfig} />
        </div>
      ))}
    </Panel>
  );
}
