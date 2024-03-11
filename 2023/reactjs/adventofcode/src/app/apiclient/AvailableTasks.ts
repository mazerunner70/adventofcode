export interface ITaskProps {
  day: number;
  part: number;
  testDataUsed: boolean;
  taskId: number;
}
// Map of tasks by day, part and testDataused
export type TaskMap = Map<number, Map<number, Map<boolean, ITaskProps>>>;

export const fetchAvailableTasks = async () => {
  const data = await fetch("http://localhost:8000/aoc", {
    method: "POST",
    body: JSON.stringify({
      query:
        "query {\n" +
        "  allAdventDays {\n" +
        "    id\n" +
        "    dayNumber\n" +
        "    description\n" +
        "    tasks {\n" +
        "      taskId\n" +
        "      taskNumber\n" +
        "      description\n" +
        "      inputData {\n" +
        "        inputType\n" +
        "      }\n" +
        "    }\n" +
        "  }\n" +
        "}",
    }),
    headers: {
      "Content-Type": "application/json",
    },
  }).then((res) => res.json());
  const taskMap: TaskMap = new Map();
  const dayList = data.data.allAdventDays;
  for (const d of dayList) {
    const dayMap = new Map<number, Map<boolean, ITaskProps>>();
    taskMap.set(d.dayNumber, dayMap);
    for (const p of d.tasks) {
      const partMap = new Map<boolean, ITaskProps>();
      dayMap.set(p.taskNumber, partMap);
      const inputData = p.inputData;
      for (const inputDatum of inputData) {
        partMap.set(inputDatum.inputType === "TEST", {
          day: d.dayNumber,
          part: p.taskNumber,
          testDataUsed: inputDatum.inputType === "TEST",
          taskId: p.taskId,
        });
      }
    }
  }
  return taskMap;
};
