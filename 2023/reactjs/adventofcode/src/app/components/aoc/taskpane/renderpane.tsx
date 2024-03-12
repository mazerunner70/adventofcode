import { familjenGrotesk } from "@app/styles/fonts";
import styled from "styled-components";
import Render from "@app/components/tasks/day01/p1-old/render";
import { LineSearchState } from "@app/components/tasks/day01/p1/stateengine";

export interface ITaskProps {
  data: string;
  tick: LineSearchState;
  linesCompleted: LineSearchState[];
}

const TaskPanel = styled.div`
  ${familjenGrotesk.style};
  width: 100%;
  height: 380px;
  overflow-y: scroll;
  position: relative;
  //border: 1px solid black;
`;

export default function RenderPane({ taskProps }: { taskProps: ITaskProps }) {
  console.log("Renderpane", taskProps);
  return (
    <TaskPanel>
      <Render
        data={taskProps.data}
        tickOutcome={taskProps.tick}
        linesCompleted={taskProps.linesCompleted}
      />
    </TaskPanel>
  );
}
