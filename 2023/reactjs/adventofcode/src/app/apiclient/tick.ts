import {
  IInputData
} from "@app/apiclient/inputdata";


export interface IEndState {
  uiActions: {
    action: number,
    param: string
  },
  tickState: {
    linesSearchstate: [{
      searchIndex: number,
      foundLeftIndex: number,
      foundRightIndex: number,
      valueFound: number | null    }]
  }
}

export interface ITick {
  id: number;
  tickNumber: number;
  endstate: IEndState
}

const getCamelCaseFrom = (a) => {
  return Object.fromEntries(Object.entries(a).map(s => {
    if (Array.isArray(s[1])) s[1] = s[1].map(x => getCamelCaseFrom(x));
    s[0] = s[0].split('_').map((e, i) => i === 0 ? e : e.slice(0, 1).toUpperCase() + e.slice(1)).join('');
    return s
  }))
}

export const fetchTicksByInputDataAndTickNumberRange = async (inputData:IInputData, startTick: number, endTick: number):Promise<ITick> => {
  return fetch(
    "http://localhost:8000/aoc",
    {
      method: "POST",
      body: JSON.stringify({
        query: 'query {\n' +
          '  ticksInRange(inputDataId: 1, startTick: 1, endTick: 1) {\n' +
          '    id\n' +
          '    tickNumber\n' +
          '    endstate\n' +
          '  }\n' +
          '}',
      }),
      headers: {
        "Content-Type": "application/json",
      },
    }
  ).then((res) => res.json())
    .then((res) => res.data.ticksInRange[0])
    .then(res=> { return {id:res.id, tickNumber : res.tickNumber, endstate : JSON.parse(res.endstate)}});
}