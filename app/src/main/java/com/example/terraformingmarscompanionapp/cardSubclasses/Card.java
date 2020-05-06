package com.example.terraformingmarscompanionapp.cardSubclasses;

import android.util.Log;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RoboticWorkforce;
import com.example.terraformingmarscompanionapp.game.CardRequirements;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Card {
    protected Game owner_game;
    protected String name = "ABSTRACT_BASE_CARD";
    protected Integer price = 0;
    protected Integer victory_points = 0;
    private Type type;
    protected Boolean action_used = false;
    protected Player owner_player = null; //Omistava pelaaja, null jos pelaamaton
    protected ArrayList<Tag> tags = new ArrayList<>();
    protected CardRequirements requirements = new CardRequirements();
    protected ProductionBox production_box = new ProductionBox();
    public static ArrayList<Type> ownables = new ArrayList<>(Arrays.asList(Type.RED, Type.GREEN, Type.BLUE, Type.CORPORATION, Type.GHOST));
    public static ArrayList<Type> tag_holders = new ArrayList<>(Arrays.asList(Type.GREEN, Type.BLUE, Type.CORPORATION));
    protected Boolean wait_for_server = false;

    //Enum kortin tyyppiin
    public enum Type {
        GREEN,
        RED,
        BLUE,
        CORPORATION,
        PRELUDE,
        STANDARD_PROJECT,
        OTHER,
        MILESTONE,
        AWARD,
        GHOST
    }

    public Card(Type card_type, Game game) {
        type = card_type;
        owner_game = game;
    }

    /* Kortin pelaamisen datavirta: peli kutsuu kortin onPlay
     * jos kortin onPlay tulee isäluokasta, kortti kutsuu playWithMetadataa arvolla 0
     * onPlay voidaan uudelleenkirjoittaa kutsumaan jotakin metadatan määrittävää UI:ta
     *
     */
    public void onPlay(Player player) {
        playServerConnection(player, 0);
    }

    public void playServerConnection(Player player, Integer data) {
        if (GameController.getInstance().getGame().getServerMultiplayer() && !(this instanceof RoboticWorkforce)) {
            GameActions.sendCardEvent(new CardEventPacket(this.getName(), player.getName(), 0));
        }
        playWithMetadata(player, data);
    }

    public void playWithMetadata(Player player, Integer data) {
        production_box.playProductionBox(player, data);
        finishPlay(player);
        if (wait_for_server && owner_game.getServerMultiplayer() && GameController.getInstance().getCurrentPlayer() != GameController.getInstance().getSelfPlayer()) {
            return;
        }
        if (type == Type.CORPORATION) {
            GameController.getInstance().useAction();
        }

        if (type != Type.GHOST) {
            GameController.getInstance().useAction();
        }
    }

    protected final void finishPlay (Player player) {
        if (ownables.contains(type)) {
            owner_player = player;
        }

        boolean is_event = (type == Type.RED);

        //Lisätään pelaajalle tägit ja aktivoidaan sopivat triggerit updateManagerissa
        for (Tag tag : tags)
        {
            switch (tag)
            {
                case BUILDING:
                    if (!is_event) {
                        player.addBuildingTag();
                    }
                    break;

                case SPACE:
                    if (!is_event) {
                        player.addSpaceTag();
                    } else {
                        owner_game.update_manager.onSpaceEvent(player);
                    }
                    break;

                case EARTH:
                    if (!is_event) {
                        player.addEarthTag();
                    }
                    owner_game.update_manager.onEarthTag(player);
                    break;

                case CITY:
                    if (!is_event) {
                        player.addCityTag();
                    }
                    break;

                case PLANT:
                    if (!is_event) {
                        player.addPlantTag();
                    }
                    owner_game.update_manager.onPlantTag(player);
                    break;

                case MICROBE:
                    if (!is_event) {
                        player.addMicrobeTag();
                    }
                    owner_game.update_manager.onMicrobeTag(player);
                    break;

                case SCIENCE:
                    if (!is_event) {
                        player.addScienceTag();
                    }
                    owner_game.update_manager.onScienceTag(player);
                    break;

                case ENERGY:
                    if (!is_event) {
                        player.addEnergyTag();
                    }
                    break;

                case JOVIAN:
                    if (!is_event) {
                        player.addJovianTag();
                    }
                    owner_game.update_manager.onJovianTag(player);
                    break;

                case VENUS:
                    if (!is_event) {
                        player.addVenusTag();
                    }
                    break;

                case ANIMAL:
                    if (!is_event) {
                        player.addAnimalTag();
                    }
                    owner_game.update_manager.onAnimalTag(player);
                    break;

                case JOKER:
                    //Jokereita ei ole event-korteissa, joten checkin voi jättää pois
                    player.addJokerTag();
                    break;

                case EVENT:
                    player.addEventTag();
                    owner_game.update_manager.onEventPlayed(player);
                    break;
                default:
                    System.out.println("Tag error in card " + getName());
            }
        }


        if (tags.size() == 0 && tag_holders.contains(type)) {
            player.addNullTag();
        }

        switch (type) {
            case GREEN:
                player.addGreen(this);
                break;
            case RED:
                player.addRed(this);
                break;
            case BLUE:
                player.addBlue(this);
                break;
            case CORPORATION:
                player.setCorporation(this);
                break;
            case PRELUDE:
                player.addPrelude(this);
                break;
            case GHOST:
            case STANDARD_PROJECT:
            case AWARD:
            case MILESTONE:
            case OTHER:
                break;
            default:
                Log.i("Card","Type error in card " + getName());
        }

        if (this instanceof ActionCard) {
            player.addAction((ActionCard)this);
        }
    }

    public void onGameEnd() {owner_player.changeVictoryPoints(victory_points);}
    public final String getName() {return name;}
    public final Player getOwner() {return owner_player;}
    public Integer getPrice() {return price;}
    public final Type getType() {return type;}
    public final ArrayList<Tag> getTags() {return tags;}
    public final CardRequirements getRequirements() {return requirements;}
    public final void resetActionUsed() {action_used = false;}

    // Robotic workforcen pelaamista varten. Mahdollista overwrite:aa jos tarvitaan metadataa
    // tai dynaamista tuotannon tarkistamista
    public void playProductionBox() {
        production_box.playProductionBox(owner_player, 0);
    }
    public final ProductionBox getProductionBox() {
        return production_box;
    }

    public final ArrayList<Integer> getTagIntegers() {
        ArrayList<Integer> tag_integers = new ArrayList<>();

        for (Tag tag : tags)
        {
            //TODO Aleksanteri: luvut kuntoon
            //toimii myös R.drawable.joku
            //tällä hetkellä placeholderikoni
            switch (tag)
            {
                case BUILDING:    tag_integers.add(R.drawable.ic_ph); break;
                case SPACE:       tag_integers.add(R.drawable.ic_ph); break;
                case EARTH:       tag_integers.add(R.drawable.ic_ph); break;
                case CITY:        tag_integers.add(R.drawable.ic_ph); break;
                case PLANT:       tag_integers.add(R.drawable.ic_ph); break;
                case MICROBE:     tag_integers.add(R.drawable.ic_ph); break;
                case SCIENCE:     tag_integers.add(R.drawable.ic_ph); break;
                case ENERGY:      tag_integers.add(R.drawable.ic_ph); break;
                case JOVIAN:      tag_integers.add(R.drawable.ic_ph); break;
                case VENUS:       tag_integers.add(R.drawable.ic_ph); break;
                case ANIMAL:      tag_integers.add(R.drawable.ic_ph); break;
                case EVENT:       tag_integers.add(R.drawable.ic_ph); break;
                case JOKER:       tag_integers.add(R.drawable.ic_ph); break;
            }
        }
        return tag_integers;
    }

    public String getResourceText() {
        String resource_text = "";
        if (this instanceof ResourceCard && this.owner_player != null) {
            switch (((ResourceCard) this).getResourceType())
            {
                case PET:
                case ANIMAL:
                    resource_text = "Animals: ";
                    break;
                case FLOATER:
                    resource_text = "Floaters: ";
                    break;
                case MICROBE:
                    resource_text = "Microbes: ";
                    break;
                case SCIENCE:
                    resource_text = "Science resources: ";
                    break;
                default:
                    resource_text = "Resources: ";
                    break;
            }

            resource_text += ((ResourceCard) this).getResourceAmount().toString();
        }
        return resource_text;
    }

    //Tarkistaa onko kortilla olemassa vaatimuksia jotka lasketaan specialist (tms, en muista nimeaä) -saavutukseen
    public final Boolean getHasRequirement() {
        return requirements.getDrawableRequrement() != 0;
    }

    public final Integer getRequirementInt()
    {
        return R.drawable.ic_ph;
    }

    //tehty spinnereitä varten. kutsuu tostring defaulttina.
    @Override public String toString() {
        return getName();
    }
}
