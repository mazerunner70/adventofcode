import React from 'react';
import { Box, Slide, Divider, IconButton, AppBar } from '@mui/material';
import { List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import {Inbox, Drafts} from '@mui/icons-material';
// const useStyles = makeStyles(theme => ({
//   root: {
//     width: 150,
//     maxWidth: 400,
//     backgroundColor: theme.palette.background.paper,
//   },
// }));

type Props = { open: boolean };

export default function MakeshiftDrawer( props : Props) {
  const [selectedIndex, setSelectedIndex] = React.useState(1);

  function handleListItemClick(event: React.MouseEvent<HTMLDivElement, MouseEvent>, index: React.SetStateAction<number>) {
    setSelectedIndex(index);
  }

  return (
    <Box sx={{
        width: 150,
        maxWidth: 400,
        backgroundColor: 'background.paper',
    }}>
    <Slide direction="right" in={props.open} mountOnEnter unmountOnExit>
      <div >
        <List component="nav" aria-label="main mailbox folders">
          <ListItem
            button
            selected={selectedIndex === 0}
            onClick={event => handleListItemClick(event, 0)}
          >
            <ListItemIcon>
              <Inbox />
            </ListItemIcon>
            <ListItemText primary="Inbox" />
          </ListItem>
          <ListItem
            button
            selected={selectedIndex === 1}
            onClick={event => handleListItemClick(event, 1)}
          >
            <ListItemIcon>
              <Drafts />
            </ListItemIcon>
            <ListItemText primary="Drafts" />
          </ListItem>
        </List>
        <Divider />
        <List component="nav" aria-label="secondary mailbox folder">
          <ListItem
            button
            selected={selectedIndex === 2}
            onClick={event => handleListItemClick(event, 2)}
          >
            <ListItemText primary="Trash" />
          </ListItem>
          <ListItem
            button
            selected={selectedIndex === 3}
            onClick={event => handleListItemClick(event, 3)}
          >
            <ListItemText primary="Spam" />
          </ListItem>
        </List>
      </div>
    </Slide>
    </Box>
  );
}