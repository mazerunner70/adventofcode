import { ITickState } from "@app/apiclient/tick";
import { familjenGrotesk } from "@app/styles/fonts";
import styled from "styled-components";
import Render
  from "@app/components/days/day01/p1/render";



export interface ITaskProps {
  data: string,
  tick: ITickState
}

const TaskPanel = styled.div`
  ${familjenGrotesk.style};
  width: 70%;
  height: 300px;
  position: relative;
`;

export default function Task( {taskProps} : {taskProps:ITaskProps} ) {
  console.log("Task", taskProps)
  return (
    <TaskPanel>
      <Render data={taskProps.data} tickOutcome={taskProps.tick.tickOutcome} />
    </TaskPanel>
  )

}