'use client'

import styles from './styles.module.css'
import { useState } from 'react';
import Initialiser from "@app/components/initialiser";
import Advent from '@app/components/advent';



export default function Page() {
    const [config , setConfig] = useState(null);
    const fetchConfig = async () => {
        const resp = await fetch("/assets/config.json");
        const config = await resp.json();
        setConfig(config);
    }

    console.log("here")
    console.log("gg", config)
    return (
        <div className={styles.box}>
            <div className={styles.left}>
                { config ? <h1><Advent data = { {config} }/></h1> : <Initialiser onClick={fetchConfig} /> }
            </div>
            <div className={styles.right}><h1>Hello, right</h1></div>
        </div>
    )

}