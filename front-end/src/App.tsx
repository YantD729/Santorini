import React from 'react';
import './App.css'; // import the css file to enable your styles.
import { GameState, Grid } from './game';
import BoardGrid from './Grid';


/**
 * Define the type of the props field for a React component
 */
interface Props { }

/**
 * Using generics to specify the type of props and state.
 * props and state is a special field in a React component.
 * React will keep track of the value of props and state.
 * Any time there's a change to their values, React will
 * automatically update (not fully re-render) the HTML needed.
 * 
 * props and state are similar in the sense that they manage
 * the data of this component. A change to their values will
 * cause the view (HTML) to change accordingly.
 * 
 * Usually, props is passed and changed by the parent component;
 * state is the internal value of the component and managed by
 * the component itself.
 */
class App extends React.Component<Props, GameState> {
  private initialized: boolean = false;

  /**
   * @param props has type Props
   */
  constructor(props: Props) {
    super(props)
    /**
     * state has type GameState as specified in the class inheritance.
     */
    // this.state = { grids: [], winner: null, currPlayer: null, gameWin: false, msg: '' }
    this.state = { grids: [], currPlayer: null, winner: null, msg: '', hasSetGodCards: 0}
  }

  /**
   * Use arrow function, i.e., () => {} to create an async function,
   * otherwise, 'this' would become undefined in runtime. This is
   * just an issue of Javascript.
   */
  newGame = async () => {
    const response = await fetch('/newgame');
    const json = await response.json();
    this.setState({
      grids: json.cells,
      currPlayer: json.currPlayer,
      winner: json.winner,
      msg: json.message,
      hasSetGodCards: json.hasSetGodCards
    });
    console.log(json);
    console.log(this.state.grids)
  }

  /**
   * play will generate an anonymous function that the component
   * can bind with.
   * @param x 
   * @param y 
   * @returns 
   */
  play(x: number, y: number): React.MouseEventHandler {
    return async (e) => {
      // prevent the default behavior on clicking a link; otherwise, it will jump to a new page.
      e.preventDefault();
      const response = await fetch(`/play?x=${x}&y=${y}`)
      const json = await response.json();
      this.setState({
        grids: json.cells,
        currPlayer: json.currPlayer,
        winner: json.winner,
        msg: json.message,
        hasSetGodCards: json.hasSetGodCards
      });
      console.log(json);
      console.log(this.state.grids)
    }
  }

  undo = async () => {
    const response = await fetch('/undo');
    const json = await response.json();
    this.setState(json);
  }

  demeter = async () => {
    const response = await fetch('/demeter');
    const json = await response.json();
    this.setState(json);
  }

  minotaur = async () => {
    const response = await fetch('/minotaur');
    const json = await response.json();
    this.setState(json);
  }

  pan = async () => {
    const response = await fetch('/pan');
    const json = await response.json();
    this.setState(json);
  }

  pass = async () => {
    const response = await fetch('/pass');
    const json = await response.json();
    this.setState(json);
  }

  creategrid(grid: Grid, index: number): React.ReactNode {
    if (grid.playable)
      /**
       * key is used for React when given a list of items. It
       * helps React to keep track of the list items and decide
       * which list item need to be updated.
       * @see https://reactjs.org/docs/lists-and-keys.html#keys
       */
      return (
        <div key={index}>
          <a href='/' onClick={this.play(grid.x, grid.y)}>
            <BoardGrid grid={grid}></BoardGrid>
          </a>
        </div>
      )
    else
      return (
        <div key={index}><BoardGrid grid={grid}></BoardGrid></div>
      )
  }

  // Just an Instruction part on the top
  createInstruction(): React.ReactNode {
    if (this.state.winner !== null && this.state.winner != "null") {
      return `Player ${this.state.winner} wins!`
    } else if (this.state.currPlayer !== null){
      return `It is Player${this.state.currPlayer}'s turn.
              ${this.state.msg}`

    }
  }

  /**
   * This function will call after the HTML is rendered.
   * We update the initial state by creating a new game.
   * @see https://reactjs.org/docs/react-component.html#componentdidmount
   */
  componentDidMount(): void {
    /**
     * setState in DidMount() will cause it to render twice which may cause
     * this function to be invoked twice. Use initialized to avoid that.
     */
    if (!this.initialized) {
      this.newGame();
      this.initialized = true;
    }
  }

  /**
   * The only method you must define in a React.Component subclass.
   * @returns the React element via JSX.
   * @see https://reactjs.org/docs/react-component.html
   */
  render(): React.ReactNode {
    /**
     * We use JSX to define the template. An advantage of JSX is that you
     * can treat HTML elements as code.
     * @see https://reactjs.org/docs/introducing-jsx.html
     */
    if (this.state.hasSetGodCards == 0) {
      return (
        <div>
        <div id="board">
          <button className="btn btn-dark btn-lg download-button" type="button" onClick={this.demeter}>Demeter</button>
          <button className="btn btn-dark btn-lg download-button" type="button" onClick={this.minotaur}>Minotaur</button>
          <button className="btn btn-dark btn-lg download-button" type="button" onClick={this.pan}>Pan</button>
          <button className="btn btn-dark btn-lg download-button" type="button" onClick={this.pass}>Pass</button>
        </div>
      </div>
      );
    } else if (this.state.hasSetGodCards == 1) {
      return (
        <div>
          <div id="instructions">{this.createInstruction()}</div>
          <div id="board">
            {this.state.grids.map((grid, i) => this.creategrid(grid, i))}
          </div>
          <section id="bottombar">
            <button className="btn btn-dark btn-lg download-button" type="button" onClick={this.newGame}>New Game</button>
            <button className="btn btn-light btn-lg download-button" type="button" onClick={this.undo}>Undo</button>
          </section>
        </div>
      );
    }
    
  }
}
// { hasSetPlayers && <div id=“board”>...</div> }

export default App;