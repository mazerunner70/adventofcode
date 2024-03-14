import { useRef } from "react";
import gsap from "gsap";

export enum AnimationType {
  YOYO = "yoyo",
}

export function useAnimation(
  animationType: AnimationType,
  activateWhen: () => boolean,
) {
  const ref = useRef(null);
  if (activateWhen() && ref.current) {
    const scaleTween = gsap.to(ref.current, {
      scale: 1.1,
      repeat: 1,
      x: 5,
      yoyo: true,
      paused: true,
      color: "yellow",
    });
    scaleTween.play();
  }
  return ref;
}
