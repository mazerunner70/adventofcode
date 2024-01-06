import {useState} from "react";
import {useAppDispatch, useAppSelector} from "../../app/hooks";
import {selectAdventConfig} from "../../features/advent/adventSlice";
import {fetchConfigAsync} from "../../features/advent/adventSlice";



export default function AdventOfCode() {
    const advent = useAppSelector(selectAdventConfig)
    const dispatch = useAppDispatch()
    const [adventConfig, setAdventConfig] = useState([])

    const dayCount = useAppSelector(state => state.advent.days?.length)
    console.log("advent", advent)
    console.log("dayCount", dayCount)
    return  (
        <section>
            Day Count: {dayCount}
            <button onClick={() => dispatch(fetchConfigAsync())}>Fetch Config</button>
        </section>
    )

}