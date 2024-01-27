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
    PLAY: {name: 'play', icon: playButton},
    PAUSE: {name: 'pause', icon: pauseButton},
    STOP: {name: 'stop', icon: stopButton},
    RECORD: {name: 'record', icon: recordButton},
    EJECT: {name: 'eject', icon: ejectButton},
    FAST_FORWARD: {name: 'fast-forward', icon: fastForwardButton},
    REWIND: {name: 'rewind', icon: rewindButton},
}
export const VCRBUttonContainer = styled.div`
    background-color: transparent;
    display: inline-block;
    position: relative;
    width: 64px;
    height: 64px ;
`;
export const VCRBacking = styled.div`
    left: 0px;
    top: 0px;
    background-color: steelgray;
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

export const Button = ({ type, onClick }) => {
    return (
        <VCRBUttonContainer>
        <VCRBacking/>
        <VCRButton onClick={onClick}>
            <Image src={type.icon} alt={type.name} width={64} height={64}/>
        </VCRButton>
        </VCRBUttonContainer>
    )
}
export const VCRControls = () => {
    const [speedState, setSpeedState] = React.useState<number>(0);

    const rewindHandler = () => {
        if (speedState>=0)
            setSpeedState(-1)
        else
            setSpeedState(speedState * 2)
        console.log("rewind: ", speedState)
    }
    const fastForwardHandler = () => {
        if (speedState<=0)
            setSpeedState(1)
        else
            setSpeedState(speedState * 2)
        console.log("fast forward: ", speedState)
    }
    const stopHandler = () => {
        setSpeedState(0)
        console.log("stop: ", speedState)
    }
    const playHandler = () => {
        setSpeedState(1)
        console.log("play: ", speedState)
    }
    const pauseHandler = () => {
        setSpeedState(0)
        console.log("pause: ", speedState)
    }

    return (
        <Panel shadowed={true}>
            <Button type={controlType.REWIND} onClick={rewindHandler} />
            <Button type={controlType.STOP} onClick={stopHandler} />
            <Button type={controlType.PLAY} onClick={playHandler} />
            <Button type={controlType.PAUSE} onClick={pauseHandler} />
            <Button type={controlType.FAST_FORWARD} onClick={fastForwardHandler} />
        </Panel>
    )
}