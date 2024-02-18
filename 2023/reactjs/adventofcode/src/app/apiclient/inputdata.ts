

export enum InputDataState {
  initialising = "initialising",
  notInitialised = "not initialised",
  initialised = "initialised",
}

export enum InputDataType {
  test = "Test",
  full = "Full",
}

export interface IInputData {
  id: number;
  inputType: InputDataType;
  data: string;
  state: InputDataState;
}
export const fetchInputDataByTaskIdAndType = async (taskId: number, testDataUsed: boolean) : Promise<IInputData > => {
  return fetch(
    "http://localhost:8000/aoc",
    {
      method: "POST",
      body: JSON.stringify({
        query: 'query {\n' +
          '  inputdataByTaskIdAndType(taskId: ' + taskId + ', inputType: "' + (testDataUsed?"test":"full") + '") {\n' +
          '    id\n' +
          '    data\n' +
          '    inputType\n' +
          '    state\n' +
          '  }\n' +
          '}',
      }),
      headers: {
        "Content-Type": "application/json",
      },
    }
  ).then((res) => res.json()).then((res) => res.data.inputdataByTaskIdAndType);
}


/**
 * initialiseInputData
 * @param taskId
 * @param testDataUsed
 *
 * @returns Promise of JSON
 *
 * Notes request
 *
 */

export interface IInitialiseInputDataResponse {
  data: {
    buildTicks: {
      inputData: {
        id: string;
        state: string;
        tickCount: number;
      };
      success: boolean;
    }
  }
}

export const initialiseInputData = async (inputDataId: number):Promise<IInitialiseInputDataResponse> => {
  console.log("initialiseInputData", inputDataId);
  return fetch(
    "http://localhost:8000/aoc",
    {
      method: "POST",
      body: JSON.stringify({
        query: 'mutation {\n' +
          '  buildTicks(id: "' + inputDataId + '") {\n' +
          '    inputData {\n' +
          '      id\n' +
          '      state\n' +
          '      tickCount\n' +
          '    }\n' +
          '    success\n' +
          '  }\n' +
          '}',
      }),
      headers: {
        "Content-Type": "application/json",
      },
    }
  ).then(res => res.json());
}