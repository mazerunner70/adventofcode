'use client';
import styled from 'styled-components';

const Conntainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;

    width: 100%;
`;

const ProgressBarContainer = styled.div`
    width: 80%;
    height: 2rem;
    border-radius: 2rem;
    position: relative;
    overflow: hidden;
    transition: all 0.5s;
    will-change: transform;
    box-shadow: 0 0 5px #e76f51;
`;

const ProgressBar = styled.div`
    position: absolute;
    height: 100%;
    width: 100%;
    content: "";
    background-color: #e76f51;
    top:0;
    bottom: 0;
    left: -100%;
    border-radius: inherit;
    display: flex;
    justify-content: center;
    align-items:center;
    color: white;
    font-family: sans-serif;
`;

const ProgressBarText = styled.div`
    display: none;
`;

const TitleDiv = styled.div`
    font-weight: bold;
    font-size: 1.5rem;
`;

export{Conntainer, ProgressBarContainer, ProgressBar, ProgressBarText, TitleDiv}