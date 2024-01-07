
import {InitialisedContextProvider} from "@app/context/Initialised.context";
import StyledComponentsRegistry from '@app/lib/registry'
import {AdventContextProvider} from "@app/context/Advent.context";
export default function RootLayout({
                                       children,
                                   }: {
    children: React.ReactNode
}) {
    return (
        <html lang="en">
            <body>

                    <StyledComponentsRegistry>
                        <InitialisedContextProvider>
                            <AdventContextProvider>
                                {children}
                            </AdventContextProvider>
                        </InitialisedContextProvider>
                    </StyledComponentsRegistry>

            </body>
        </html>
    )

}