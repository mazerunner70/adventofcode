export default () => {
  onmessage = (e) => {
    console.log("Message received from main script", e);
    fetch(`http://localhost:8080/assets/day01/input.txt`).then((resp) => {
      resp.text();
      postMessage(5);
    });
    //const workerResult = `Result: ${e.data}`;
    console.log("Posting message back to main script");
    postMessage(2);
  };
};
