'use client';
import styled from 'styled-components';

const WordGrid = styled.div`
    font: 1.5rem "Courier New", monospace, bold;
`;
const SearchLetter = styled.span`
    font: 1.5rem "Courier New", monospace, bold;
    color: red;
`;
const FoundLetter = styled.span`
    font: 1.5rem "Courier New", monospace, bold;
    color: #002bff;
`;


// table in css
const RespTable = styled.div`
    display: table;
`
const RespTableCaption = styled.div`
    display: table-caption;
    text-align: center;
    font-weight: bold;
    font-size: 1.5em;
`
const RespTableHeader = styled.div`
    display: table-header-group;
    background-color: gray;
    font-weight: bold;
    font-size: 1.5em;
`
const TableHeaderCell = styled.div`
    display: table-cell;
    border: 1px solid black;
    padding: 5px;
    text-align: center;
`
const RespTableBody = styled.div`
    display: table-row-group;
`
const TableRow = styled.div`
    display: table-row;
`
const TableCell = styled.div`
    display: table-cell;
`
const TableFooter = styled.div`
    display: table-footer-group;
    background-color: gray;
    font-weight: bold;
    font-size: 1.5em;
    color: white;
`
const TableFooterCell = styled.div`
    display: table-cell;
    border: 1px solid black;
    padding: 5px;
    text-align: center;
`




export{WordGrid, SearchLetter, FoundLetter, RespTable, RespTableCaption, RespTableHeader, TableHeaderCell, RespTableBody, TableRow, TableCell, TableFooter, TableFooterCell}