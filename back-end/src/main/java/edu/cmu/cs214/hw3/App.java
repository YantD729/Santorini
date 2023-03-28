package edu.cmu.cs214.hw3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.cmu.cs214.hw3.godCards.Demeter;
import edu.cmu.cs214.hw3.godCards.Minotaur;
import edu.cmu.cs214.hw3.godCards.Pan;
import fi.iki.elonen.NanoHTTPD;


public class App extends NanoHTTPD {

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    private Game game;
    private Map<Player, Game> cardsMap;
    private boolean firstPlayerSetCard;
    private boolean secondPlayerSetCard;
    private int hasSetGodCards;

    /**
     * Start the server at :8080 port.
     * @throws IOException
     */
    public App() throws IOException {
        super(8080);

        Player A = new Player("A");
        Player B = new Player("B");
        this.game = new OriginalGame(A, B);
        cardsMap = new HashMap<Player, Game>(2);
        cardsMap.put(A, this.game);
        cardsMap.put(B, this.game);
        firstPlayerSetCard = false;
        secondPlayerSetCard = false;
        hasSetGodCards = 0;

        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning!\n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String, String> params = session.getParms();
        if (uri.equals("/newgame")) {
            Player A = new Player("A");
            Player B = new Player("B");
            this.game = new OriginalGame(A, B);
            cardsMap = new HashMap<Player, Game>(2);
            cardsMap.put(A, this.game);
            cardsMap.put(B, this.game);
            firstPlayerSetCard = false;
            secondPlayerSetCard = false;
            hasSetGodCards = 0;
        } else if (uri.equals("/pan")) {
            cardsMap.put(this.game.getCurrPlayer(), new Pan(this.game));
            this.game.setNextCurrPlayer(this.game.getCurrPlayer());
            if (this.game.getCurrPlayer().getPlayerID().equals("A")) 
                firstPlayerSetCard = true;
            else secondPlayerSetCard = true;
        } else if (uri.equals("/minotaur")) {
            cardsMap.put(this.game.getCurrPlayer(), new Minotaur(this.game));
            this.game.setNextCurrPlayer(this.game.getCurrPlayer());
            if (this.game.getCurrPlayer().getPlayerID().equals("A")) 
                firstPlayerSetCard = true;
            else secondPlayerSetCard = true;
        } else if (uri.equals("/demeter")) {
            cardsMap.put(this.game.getCurrPlayer(), new Demeter(this.game));
            this.game.setNextCurrPlayer(this.game.getCurrPlayer());
            if (this.game.getCurrPlayer().getPlayerID().equals("A")) 
                firstPlayerSetCard = true;
            else secondPlayerSetCard = true;
        } else if (uri.equals("/pass")) {
            this.game.setNextCurrPlayer(this.game.getCurrPlayer());
            if (this.game.getCurrPlayer().getPlayerID().equals("A")) 
                firstPlayerSetCard = true;
            else secondPlayerSetCard = true;
        } else if (uri.equals("/undo")) {
            this.game = this.game.undo();
        } else if (uri.equals("/play")) {
            this.game = cardsMap.get(this.game.getCurrPlayer()).play(Integer.parseInt(params.get("x")), Integer.parseInt(params.get("y")));
        }
        
        // Extract the view-specific data from the game and apply it to the template.
        hasSetGodCards = (firstPlayerSetCard && secondPlayerSetCard) ? 1 : 0;
        GameState gameplay = null;
        gameplay = GameState.forGame(this.game, hasSetGodCards);
        return newFixedLengthResponse(gameplay.toString());
    }

    public static class Test {
        public String getText() {
            return "Hello World!";
        }
    }
}