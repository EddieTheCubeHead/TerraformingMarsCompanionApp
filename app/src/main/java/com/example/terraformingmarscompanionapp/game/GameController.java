package com.example.terraformingmarscompanionapp.game;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Olio jonka pitäisi sisältää kaikki UI-luokkien kutsuma joka liittyy pelilogiikkaan.
 * -Hallinnoi vuoroja, sukupolvia.
 */


public class GameController
{
    //dequessa vuorojärjestys. ensimmäinen jäsen on aina nykyinen
    //queue sisältää kaikki jotka eivät ole foldannu
    static GameController instance = null;

    private Game game;
    private Deque<Player> queue_full = new LinkedList<>(); //double ended queue
    private Deque<Player> queue = new LinkedList<>();
    private Player current_player;
    private Player current_starter;

    public Game getGame() { return game; }
    public Player getCurrentPlayer()  { return current_player; }
    public Player getCurrentStarter() { return current_starter; }

    //Player player = new Player(game, "Testipelaaja");

    private GameController(Game game){
        this.game = game;

        ArrayList<Player> players = game.getPlayers();
        if (players == null || players.size() == 0)
            new Exception().printStackTrace();

        queue_full.addAll(players);
        queue.addAll(queue_full);

        current_player = queue.getFirst();
        current_starter = current_player;
        //oletuksena:
        // ensimmäisen sukupolven aloittaja on laittanut nimensä ekana.
        // tästä jatketaan nimien laittamisjärjestyksessä.
        //voi muuttaa vapaasti.
    }

    public static GameController makeInstance(Game game)
    {
        if (instance != null)
        {
            System.out.println("GAMECONTROLLER ALREADY INSTANCIATED BEFORE");
            new Exception().printStackTrace();
            System.exit(-1);
        }
        instance = new GameController(game);
        return instance;
    }

    public static GameController getInstance()
    {
        if (instance == null)
        {
            System.out.println("GAMECONTROLLER NOT INSTANTIATED AT GETINSTANCE");
            new Exception().printStackTrace();
            System.exit(-1);
        }
        return instance;
    }

    //vuorojen hallitseminen
    private Boolean folding = false;
    public void setPlayerIsFolding(Boolean currentIsFolding) { folding = currentIsFolding; }
    public void foldOnTurnEnd() { folding = true; }

    public void endTurn()
    {
        beforeTurnEnd();

        //vuoron vaihto
        if (folding)
            queue.removeFirst();
        else
            queue.addLast(queue.removeFirst());
        setPlayerIsFolding(false);

        //kun kaikki on foldannu
        if (queue.size() == 0)
            endGeneration();

        current_player = queue.getFirst();

        atTurnStart();
    }

    private void beforeTurnEnd()
    {
        //TODO kaikki vuoron lopussa vuoron lopettavalle current_playerille tapahtuva
    }

    private void atTurnStart()
    {
        //TODO kaikki vuoron alussa vuoron aloittavalle current_playerille tapahtuva
    }

    private void endGeneration()
    {
        game.onGenerationEnd();

        //epäfoldaus
        queue.clear();
        queue.addAll(queue_full);

        //seuraavaan aloittajaan vaihto
        while(current_starter != queue.getFirst())
            queue.addLast(queue.removeFirst());
        queue.addLast(queue.removeFirst());

        current_starter = queue.getFirst();
    }

    //TODO tokenien sijoittaminen

    private void doStuff()
    {

    }
}
