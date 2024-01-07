'use client'

import styles from './styles.module.css'
import { useState, useContext } from 'react';
import Initialiser from "@app/components/initialiser";
import Advent from '@app/components/advent';
import { InitialisedContext } from "@app/context/Initialised.context";
import GlowOnHoverButton
    from "../../../../../../../../../../../../home/william/Documents/personal/projects/adventofcode/2023/reactjs/adventofcode/src/app/components/glowbutton/styled";

export default function Page() {

    const { state, dispatch } = useContext(InitialisedContext);

    const handleClick = async () => {
        dispatch({ type: "SET_INITIALISED", payload: { initialised: true } });
    }

    return (
        <div className={styles.main}>
            <div className={styles.header}>

            </div>
            <section className={styles.section}>
                <span className={styles.h1}>Advent of Code 2023</span>
                { !state.initialised &&
                    <GlowOnHoverButton type="button" onClick={handleClick}>INITIALISE</GlowOnHoverButton>
                }
            </section>
        </div>
    )

}