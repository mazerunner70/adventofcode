

import styles from './styles.module.css'

export default function HomeLayout(props: {
    children: React.ReactNode
    summary : React.ReactNode
    detail: React.ReactNode
}) {
    return (
        <html lang="en">
            <body>
                {props.children}
                <div className={styles.box}>
                    <div className={styles.left}>
                        {props.summary}
                    </div>
                    <div className={styles.right}>
                        {props.detail}
                    </div>
                </div>
            </body>
        </html>
    )
}