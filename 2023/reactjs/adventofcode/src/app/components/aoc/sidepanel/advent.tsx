import AdventDay from "@app/components/adventday";
import { IAdvent, IConfig } from "@app/context/Advent.context";
import Panel from "@app/components/base/panel";

export default function Advent({ data }: { data: IConfig[] }) {
  return (
    <Panel title={"Tasks List"} shadowed={true}>
      {data?.map((dayConfig: IConfig) => (
        <AdventDay key={dayConfig.dayno} dayConfig={dayConfig} />
      ))}
    </Panel>
  );
}
