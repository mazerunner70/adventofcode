import type { Preview } from "@storybook/react";
import { Familjen_Grotesk} from "next/font/google";

const Familjen_GroteskFont = Familjen_Grotesk({
  weight: '400',
  subsets: ['latin'],
  variable: '--font-OpenSans'
});

const preview: Preview = {
  decorators: [
    Story => (
        <main className={Familjen_GroteskFont.className}>
          <Story />
        </main>
    )
  ],
  parameters: {
    actions: { argTypesRegex: "^on[A-Z].*" },
    controls: {
      matchers: {
        color: /(background|color)$/i,
        date: /Date$/i,
      },
    },
  },
};

export default preview;
