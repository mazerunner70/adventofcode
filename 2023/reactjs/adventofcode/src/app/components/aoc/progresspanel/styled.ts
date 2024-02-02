"use client";
import styled from "styled-components";

export const EvenDistributed = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: row;
  width: 100%;
`;

export const Heading = styled.div`
  font-family: "arial";
  font-size: 1rem;
  font-weight: 600;
  margin: 0.5rem 0;
`;

export const OutlinedBox = styled.span`
    border: 1px solid #000;
    padding: 0.5rem;
    border-radius: 0.5rem;
    margin: 0 0.5rem;
    `;

export const PulsingText = styled.span`
    animation: pulse 1s infinite;
    @keyframes pulse {
        0% {
        opacity: 0.5;
        }
        50% {
        opacity: 1;
        }
        100% {
        opacity: 0.5;
        }
    }
    `;