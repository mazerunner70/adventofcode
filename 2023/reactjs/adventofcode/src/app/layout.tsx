
import {InitialisedContextProvider} from "@app/context/Initialised.context";

export default function RootLayout({
                                       children,
                                   }: {
    children: React.ReactNode
}) {
    return (
        <html lang="en">
            <body>
                <InitialisedContextProvider>
                    {children}
                </InitialisedContextProvider>
            </body>
            </html>
    )

}