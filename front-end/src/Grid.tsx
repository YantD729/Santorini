import React from 'react';
import { Grid } from './game';

interface Props {
  grid: Grid;
}

class BoardGrid extends React.Component<Props> {
  render(): React.ReactNode {
    const playable = this.props.grid.playable ? 'playable' : '';
    return (
      // <div className={`grid ${playable}`}>{this.props.grid.text}</div>
      <div className={`grid ${playable}`}>{this.props.grid.text}</div>
    )
  }
}

export default BoardGrid;