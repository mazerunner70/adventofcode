import { useEffect, useRef } from 'react';


// https://www.dhiwise.com/post/understanding-the-useinterval-hook-in-react
export default function useInterval(callback: ()=>void, delay: number|null) {
    const savedCallback = useRef(()=>{})

    // Remember the latest callback.
    useEffect(() => {
        savedCallback.current = callback;
    }, [callback]);

    // Set up the interval.
    useEffect(() => {
        function tick() {
            savedCallback.current();
        }
        if (delay !== null) {
            const id = setInterval(tick, delay);
            return () => clearInterval(id);
        }
    }, [delay]);
}