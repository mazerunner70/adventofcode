
import GlowOnHoverButton from "./styled"
export default function GlowButton(
    {children, onClick, ...props }: { children: React.ReactNode, onClick: any, props: any }) {
    return (
        <GlowOnHoverButton onClick={onClick} {...props}>
            {children}
        </GlowOnHoverButton>
    )
}