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
        <div className={styles.main}>
            <div className={styles.header}>

            </div>
            <section className={styles.section}>
                <h1 className={styles.h1}>Advent of Code 2023</h1>
            </section>
        </div>
    )

}