"use client";
import styled from "styled-components";
import {familjenGrotesk} from "@app/styles/fonts";

//props prefix with $ to ensure transient props, not passed down to DOM
export const PanelWrapper = styled.div<{ $shadowed?: boolean }>`
  background-color: #fff;
  display: inline-block;
  border-radius: 13px;
  padding: 10px;
  box-shadow: ${props => props.$shadowed ? "5px 5px 15px 0px rgba(0,0,0,0.75)" : "none"};
`;
export const InnerPanelWrapper = styled.div`
  background-color: #e3e3e3;
  border-radius: 5px;
  padding: 10px;
  display: inline-block;
`;
export const PanelTitle = styled.h2`
    ${familjenGrotesk.style};
  font-size: 1.5em;
  margin: 0;
`;
export const PanelSubTitle = styled.h3`
  font-size: 1.2em;
  margin: 0;
`;
