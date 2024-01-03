import styles from './styles.module.css'

export default function Page() {
    return (
        <div className={styles.box}>
            <div className={styles.left}><h1>Hello, left</h1></div>
            <div className={styles.right}><h1>Hello, right</h1></div>
        </div>
    )

}