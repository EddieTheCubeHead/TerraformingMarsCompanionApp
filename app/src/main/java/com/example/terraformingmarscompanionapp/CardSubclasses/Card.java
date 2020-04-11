package com.example.terraformingmarscompanionapp.CardSubclasses;

import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;
import com.example.terraformingmarscompanionapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Card {
    protected Game owner_game;
    protected String name = "ABSTRACT_BASE_CARD";
    protected Integer price = 0;
    protected Integer victory_points = 0;
    protected String type; //green, red, blue, corporation, prelude. Käytetään super.onPlayssa
    protected Boolean action_used = false;
    protected Player owner_player = null; //Omistava pelaaja, null jos pelaamaton
    protected ArrayList<String> tags;
    protected HashMap<String, Integer> requirements;

    public Card(String card_type) {
        type = card_type;
    }

    public void onPlay (Player player) {
        owner_player = player;

        boolean is_event = (type.equals("red"));

        //Lisätään pelaajalle tägit ja aktivoidaan sopivat triggerit updateManagerissa
        for (String tag : tags)
        {
            switch (tag)
            {
                case "building":
                    if (!is_event) {
                        player.addBuildingTag();
                    }
                    break;

                case "space":
                    if (!is_event) {
                        player.addSpaceTag();
                    } else {
                        owner_game.update_manager.onSpaceEvent(player);
                    }
                    break;

                case "earth":
                    if (!is_event) {
                        player.addEarthTag();
                    }
                    owner_game.update_manager.onEarthTag(player);
                    break;

                case "city":
                    if (!is_event) {
                        player.addCityTag();
                    }
                    break;

                case "plant":
                    if (!is_event) {
                        player.addPlantTag();
                    }
                    owner_game.update_manager.onPlantTag(player);
                    break;

                case "microbe":
                    if (!is_event) {
                        player.addMicrobeTag();
                    }
                    owner_game.update_manager.onMicrobeTag(player);
                    break;

                case "science":
                    if (!is_event) {
                        player.addScienceTag();
                    }
                    owner_game.update_manager.onScienceTag(player);
                    break;

                case "energy":
                    if (!is_event) {
                        player.addEnergyTag();
                    }
                    break;

                case "jovian":
                    if (!is_event) {
                        player.addJovianTag();
                    }
                    owner_game.update_manager.onJovianTag(player);
                    break;

                case "venus":
                    if (!is_event) {
                        player.addVenusTag();
                    }
                    break;

                case "animal":
                    if (!is_event) {
                        player.addAnimalTag();
                    }
                    owner_game.update_manager.onAnimalTag(player);
                    break;

                case "joker":
                    //Jokereita ei ole event-korteissa, joten checkin voi jättää pois
                    player.addJokerTag();
                    break;

                case "event":
                    player.addEventTag();
                    owner_game.update_manager.onEventPlayed(player);
            }
        }


        if (tags.size() == 0) {
            player.addNullTag();
        }

        switch (type) {
            case "green":
                player.addGreen(this);
            case "red":
                player.addRed(this);
            case "blue":
                player.addBlue(this);
            case "corporation":
                player.setCorporation(this);
            case "prelude":
                player.addPrelude(this);
        }

        if (this instanceof ActionCard) {
            player.addAction((ActionCard)this);
        }
    }

    public void onGameEnd() {owner_player.changeVictoryPoints(victory_points);}
    public final String getName() {return name;}
    public final Integer getPrice() {return price;}
    public final ArrayList<String> getTags() {return tags;}
    public final HashMap<String, Integer> getRequirements() {return requirements;}
    public final void resetActionUsed() {action_used = false;}

    public final ArrayList<Integer> getTagIntegers() {
        ArrayList<Integer> tag_integers = new ArrayList<>();

        for (String tag : tags)
        {
            //TODO Aleksanteri: luvut kuntoon
            //toimii myös R.drawable.joku
            //tällä hetkellä placeholderikoni
            switch (tag)
            {
            case "building":    tag_integers.add(R.drawable.ic_ph); break;
            case "space":       tag_integers.add(R.drawable.ic_ph); break;
            case "earth":       tag_integers.add(R.drawable.ic_ph); break;
            case "city":        tag_integers.add(R.drawable.ic_ph); break;
            case "plant":       tag_integers.add(R.drawable.ic_ph); break;
            case "microbe":     tag_integers.add(R.drawable.ic_ph); break;
            case "science":     tag_integers.add(R.drawable.ic_ph); break;
            case "energy":      tag_integers.add(R.drawable.ic_ph); break;
            case "jovian":      tag_integers.add(R.drawable.ic_ph); break;
            case "venus":       tag_integers.add(R.drawable.ic_ph); break;
            case "animal":      tag_integers.add(R.drawable.ic_ph); break;
            case "event":       tag_integers.add(R.drawable.ic_ph); break;
            case "joker":       tag_integers.add(R.drawable.ic_ph); break;
            }
        }
        return tag_integers;
    }

    public final Integer getRequirementInt()
    {
        return R.drawable.ic_ph;
    }
}