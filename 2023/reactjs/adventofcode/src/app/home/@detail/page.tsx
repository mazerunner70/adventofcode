'use client'

import styles from './styles.module.css'

const handleClick = async () => {
    console.log("here1")
}
export default function Page() {
    return (

            <button className={styles['glow-on-hover']} type="button" onClick={handleClick}>INITIALISE</button>

    )

}