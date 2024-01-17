"use client";

import styles from "./styles.module.css";

import { InitialisedContext } from "@app/context/Initialised.context";
import GlowOnHoverButton from "@app/components/glowbutton/styled";
import { useContext } from "react";

export default function Page() {
  const { state, dispatch } = useContext(InitialisedContext);

  const handleClick = async () => {
    dispatch({ type: "SET_INITIALISED", payload: { initialised: true } });
  };

  return (
    <div className={styles.main}>
      <div className={styles.header}></div>
      <section className={styles.section}>
        <span className={styles.h1}>Advent of Code 2023</span>
        {!state.initialised && (
          <GlowOnHoverButton type="button" onClick={handleClick}>
            Click to begin...
          </GlowOnHoverButton>
        )}
      </section>
    </div>
  );
}
