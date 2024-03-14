// "use client"
//
// import {useContext, useEffect, useRef, useState} from "react"
// import {Conntainer, ProgressBarContainer, ProgressBar, ProgressBarText, TitleDiv} from "./styled"
// import gsap from "gsap"
// import GlowButton from "@app/components/glowbutton/GlowButton";
// import {AdventContext, IAdventEnv} from "@app/contexts/Advent.contexts";
//
// export default function AdventDayEnv({dayEnvConfig, envName, partName, dayno}:{dayEnvConfig: IAdventEnv, envName: string, partName: string, dayno: string}) {
//     const spinnerRef = useRef(null)
//     const [progress, setProgress] = useState(0)
//     useEffect(() => {
//         gsap.to(spinnerRef.current, {
//                  x: `${progress}%`,
//                  duration: 20,
//         })
//     }, [])
//
//     const { state, dispatch } = useContext(AdventContext);
//
//     const handleClick = async () => {
//         dispatch({ type: "SET_SELECTED", payload: { dayno, partName, envName, envConfig:dayEnvConfig } });
//     }
//
//
//     return (
//         <>
//             <Conntainer>
//                 <TitleDiv>{envName}</TitleDiv>
//                 <ProgressBarContainer>
//                     <ProgressBar ref={spinnerRef}>
//                         <ProgressBarText>Upload</ProgressBarText>
//                     </ProgressBar>
//                 </ProgressBarContainer>
//                 <GlowButton onClick={handleClick} props={undefined}>View</GlowButton>
//             </Conntainer>
//         </>
//     )
// }
//
// //look here for progress bar details
// //https://alvarotrigo.com/blog/progress-bar-css/
//
//
//
