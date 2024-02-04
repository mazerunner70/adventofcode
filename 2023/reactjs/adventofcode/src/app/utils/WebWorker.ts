


export default class WebWorker {
    constructor(worker: { toString: () => void; }) {
        const code = worker.toString();
        const blob = new Blob(['('+code+')()']);
        return new Worker(URL.createObjectURL(blob));
    }
}

export function asWorker(f) {
    const code = f.toString();
    console.log("code", code);
    const blob = new Blob(['('+code+')()']);
    return new Worker(URL.createObjectURL(blob));
}
