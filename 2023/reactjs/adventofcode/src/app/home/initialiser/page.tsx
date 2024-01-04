
import styles from './styles.module.css'

export default function Page(handleClick) {
    return (
        <div>
            <button className={styles['glow-on-hover']} type="button" onClick={handleClick}>INITIALISE</button>
        </div>
    )

}