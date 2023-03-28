interface GameState {
  grids: Grid[];
  currPlayer: string | null;
  winner: string | null;
  msg: string;
  hasSetGodCards: number;
}

interface Grid {
  text: string;
  playable: boolean;
  x: number;
  y: number;
}
  
export type { GameState, Grid}