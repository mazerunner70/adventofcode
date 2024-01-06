
import {InitialisedContextProvider} from "@app/context/Initialised.context";
import StyledComponentsRegistry from '@app/lib/registry'
export default function RootLayout({
                                       children,
                                   }: {
    children: React.ReactNode
}) {
    return (
        <html lang="en">
            <body>
                <InitialisedContextProvider>
                    <StyledComponentsRegistry>
                        {children}
                    </StyledComponentsRegistry>
                </InitialisedContextProvider>
            </body>
        </html>
    )

}