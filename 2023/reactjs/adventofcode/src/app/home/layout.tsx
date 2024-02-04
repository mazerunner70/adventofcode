import { inter, mountainsOfChristmas, familjenGrotesk } from "@app/styles/fonts";

import styles from "./styles.module.css";
export default function HomeLayout(props: {
  children: React.ReactNode;
  detail: React.ReactNode;
}) {
  return (
    <html lang="en">
      <head>
        <title>Advent of Code 2023</title>
        <meta name="description" content="Advent of Code 2023" />
      </head>
      <body className={mountainsOfChristmas.className}>
        <header />
        {props.children}
        <div className={styles.box}>
          <div className={styles.right}>{props.detail}</div>
        </div>
      </body>
    </html>
  );
}
