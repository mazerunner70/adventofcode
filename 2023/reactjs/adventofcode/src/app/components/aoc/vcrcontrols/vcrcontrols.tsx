import Image from 'next/image';
//https://www.iconarchive.com/search?q=vcr+buttons&page=4
import playButton from '../../../../../public/assets/vcr/Play-Button-icon.png';
import pauseButton from '../../../../../public/assets/vcr/Pause-Button-icon.png';
import stopButton from '../../../../../public/assets/vcr/Stop-Button-icon.png';
import recordButton from '../../../../../public/assets/vcr/Record-Button-icon.png';
import ejectButton from '../../../../../public/assets/vcr/Eject-Button-icon.png';
import fastForwardButton from '../../../../../public/assets/vcr/Fast-Forward-Button-icon.png';
import rewindButton from '../../../../../public/assets/vcr/Fast-Reverse-Button-icon.png';
import styled from "styled-components";
import React from 'react';
import Panel from '@app/components/base/panel';

export const controlType= {
    PLAY: {name: 'play', icon: playButton, isActive: (speed: number) => speed == 1},
    PAUSE: {name: 'pause', icon: pauseButton, isActive: (speed: number) => false},
    STOP: {name: 'stop', icon: stopButton, isActive: (speed: number) => speed == 0},
    RECORD: {name: 'record', icon: recordButton, isActive: (speed: number) => speed == 1},
    EJECT: {name: 'eject', icon: ejectButton, isActive: (speed: number) => speed == 1},
    FAST_FORWARD: {name: 'fast-forward', icon: fastForwardButton, isActive: (speed: number) => speed > 1},
    REWIND: {name: 'rewind', icon: rewindButton, isActive: (speed: number) => speed < 0},
}
export const VCRBUttonContainer = styled.div`
    background-color: transparent;
    display: inline-block;
    position: relative;
    width: 64px;
    height: 64px ;
`;
export const VCRBacking = styled.div<{ $active?: boolean }>`
    left: 0px;
    top: 0px;
    background-color: ${props => props.$active ? "green" : "transparent"};
    position: absolute;
    margin: 12% 6% 6% 12%;
    width: 82%;
    height: 82%;
`;
export const VCRButton = styled.button`
    background-color: transparent;
    z-index: 1;
    padding: 0;
    margin: 0;
    cursor: pointer;
    &:focus {
        outline: none;
    }
    width: 100%;
    height: 100%;    
    left: 0px;
    top: 0px;
    position: absolute;
    border: solid 2px transparent;
`;

export const Button = ({ type, onClick, speed }) => {
    return (
        <VCRBUttonContainer>
        <VCRBacking $active={type.isActive(speed)}/>
        <VCRButton onClick={onClick}>
            <Image src={type.icon} alt={type.name} width={64} height={64}/>
        </VCRButton>
        </VCRBUttonContainer>
    )
}

export interface IVCRControlsProps {
    speedState: number;
    onSpeedChange: (speed: number) => void;
}

export const VCRControls = ({speedState, onSpeedChange}: IVCRControlsProps) => {
    //const [speedState, setSpeedState] = React.useState<number>(0);

    const rewindHandler = () => {
        if (speedState>=0)
            onSpeedChange(-1)
        else
            onSpeedChange(speedState * 2)
    }
    const fastForwardHandler = () => {
        if (speedState<=0)
            onSpeedChange(1)
        else
            onSpeedChange(speedState * 2)
    }
    const stopHandler = () => {
        onSpeedChange(0)
    }
    const playHandler = () => {
        onSpeedChange(1)
    }
    const pauseHandler = () => {
        onSpeedChange(0)
    }
    console.log("speed: ", speedState)
    return (
        <Panel shadowed={true}>
            <Button type={controlType.REWIND} onClick={rewindHandler} speed={speedState}/>
            <Button type={controlType.STOP} onClick={stopHandler} speed={speedState} />
            <Button type={controlType.PLAY} onClick={playHandler} speed={speedState} />
            <Button type={controlType.PAUSE} onClick={pauseHandler} speed={speedState} />
            <Button type={controlType.FAST_FORWARD} onClick={fastForwardHandler} speed={speedState} />
        </Panel>
    )
}