'use client'

import { useContext } from 'react';
import styles from './styles.module.css'
import { AdventContext } from '@app/context/Advent.context';
import DetailHeader from "@app/components/detailheader/DetailHeader";

const handleClick = async () => {
    console.log("here1")
}
export default function Page() {

    const { state, dispatch } = useContext(AdventContext);

    return (
        <div>
            <div>
                {state.selected && <DetailHeader config={state.selected}/>}
            </div>
            <button className={styles['glow-on-hover']} type="button" onClick={handleClick}>INITIALISE</button>
        </div>

    )

}