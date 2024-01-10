import { Heading1, Heading2 } from "./styled";


export function H1({children}:{children: any}) {
    return (
        <Heading1>{children}</Heading1>
    )
}

export function H2({children}:{children: any}) {
    return (
        <Heading2>{children}</Heading2>
    )
}