'use client'
import styles from './styles.module.css'

const handleClick = async () => {
    console.log("here")
}

export default function Page() {
    return (
            <button className={styles['glow-on-hover']} type="button" onClick={handleClick}>INITIALISE</button>
    )

}