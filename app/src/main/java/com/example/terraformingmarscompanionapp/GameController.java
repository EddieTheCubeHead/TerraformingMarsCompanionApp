package com.example.terraformingmarscompanionapp;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Kommunikoi gamen kanssa. UI-luokkien kutsuma siihen vaikuttava luokka.
 * Hallinnoi myös aktiivista pelaajaa.
 */


public class GameController
{
    //dequessa vuorojärjestys. ensimmäinen jäsen on aina nykyinen
    private Deque<Player> queue; //double-ended queue
    private Player current_player;
    private Player current_starter;

    //Player player = new Player(game, "Testipelaaja");

    private Game game;
    GameController(Game game){
        this.game = game;

        ArrayList<Player> players = game.getPlayers();
        if (players == null || players.size() == 0)
            new Exception().printStackTrace();

        queue = new LinkedList<>();
        queue.addAll(players);

        current_player = queue.getFirst();
        current_starter = current_player;
        //oletuksena se, että ensimmäisen sukupolven aloittaja on laittanut nimensä ekana.
        //voi muuttaa vapaasti.
    }

    public void endTurn()
    {
        beforeTurnEnd();

        //vuoron vaihto
        queue.addLast(queue.removeFirst());
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

    public void endGeneration()
    {
        game.onGenerationEnd();

        while(current_starter != queue.getFirst())
            queue.addLast(queue.removeFirst());

        queue.addLast(queue.removeFirst());
        current_starter = queue.getFirst();
    }

    public Player getCurrentPlayer()  { return current_player; }
    public Player getCurrentStarter() { return current_starter; }

}
