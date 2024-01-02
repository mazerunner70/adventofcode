


export function fetchConfig() {
    return fetch("/assets/config.json").then((d) => {
        return d.json();
    })
    //     .then((data) => {
    //     console.log("config", data)
    //     return data;
    // })
}

export function fetchDayConfig(dayno = 1, filename) {
    return fetch(`/assets/day${dayno.toString().padStart(2, "0")}/${filename}`)
}
