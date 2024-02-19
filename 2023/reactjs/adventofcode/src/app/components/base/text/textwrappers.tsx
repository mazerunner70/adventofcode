import { Heading1, Heading2, Heading3, Heading4 } from "./styled";


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
export function H3({children}:{children: any}) {
    return (
      <Heading3>{children}</Heading3>
    )
}
export function H4({children}:{children: any}) {
    return (
      <Heading4>{children}</Heading4>
    )
}