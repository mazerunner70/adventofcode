import { Inter, Mountains_of_Christmas, Roboto_Mono, Familjen_Grotesk } from "next/font/google";

//https://nextjs.org/docs/pages/api-reference/components/font
export const inter = Inter({
  subsets: ["latin"],
  display: "swap",
});

export const roboto_mono = Roboto_Mono({
  subsets: ["latin"],
  display: "swap",
});
export const mountainsOfChristmas = Mountains_of_Christmas({
  weight: "700",
  subsets: ["latin"],
  display: "swap",
});

export const familjenGrotesk = Familjen_Grotesk({
  weight: '600',
  subsets: ["latin"],
  display: "swap",
  preload: true,
  variable: '--font-grotesk'
});
