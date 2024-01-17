import { inter } from "@app/fonts";

import styles from "./styles.module.css";
export default function HomeLayout(props: {
  children: React.ReactNode;
  summary: React.ReactNode;
  detail: React.ReactNode;
}) {
  return (
    <html lang="en">
      <head>
        <title>Advent of Code 2020</title>
        <meta name="description" content="Advent of Code 2020" />
      </head>
      <body className={inter.className}>
        <header />
        {props.children}
        <div className={styles.box}>
          <div className={styles.left}>{props.summary}</div>
          <div className={styles.right}>{props.detail}</div>
        </div>
      </body>
    </html>
  );
}
