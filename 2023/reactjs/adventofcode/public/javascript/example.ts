
onmessage = (e) => {
  console.log("Message received from main script");
  const workerResult = `Result: ${e.data}`;
  console.log("Posting message back to main script");
  postMessage(5);
};