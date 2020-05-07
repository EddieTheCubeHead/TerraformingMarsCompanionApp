package com.example.terraformingmarscompanionapp.cardSubclasses;

import android.util.Log;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cards.basegame.corporations.BeginnerCorporation;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RoboticWorkforce;
import com.example.terraformingmarscompanionapp.game.CardRequirements;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The meat of the app. Most things are cards. The basic pipeline of playing a card goes:
 *
 * - call ChildCard.onPlay(Player) ->
 * - Child card knows if it requires metadata and either
 *     1: (no metadata) calls onPlayServerHook(player, 0)
 *     2: (requires metadata) calls an UI to get metadata. The UI calls onPlayServerHook(player, data)
 * - onPlayServerHook checks if the game is playd with a server and sends info to server if needed
 * - onPlayServerHook calls this.playActionWithMetadata(player, data)
 * - this.playActionWithMetadata calls super.playActionWithMetadata
 * - super.playActionWithMetadata calls super.finishPlay()
 */
public abstract class Card {
    protected Game owner_game;
    protected String name = "ABSTRACT_BASE_CARD";
    protected Integer price = 0;
    protected Integer victory_points = 0;
    private Type type;
    protected Boolean action_used = false;
    protected Player owner_player = null; //null if unowned
    protected ArrayList<Tag> tags = new ArrayList<>();
    protected CardRequirements requirements = new CardRequirements();
    protected ProductionBox production_box = new ProductionBox();
    private final static ArrayList<Type> OWNABLES = new ArrayList<>(Arrays.asList(Type.RED, Type.GREEN, Type.BLUE, Type.CORPORATION, Type.GHOST, Type.MILESTONE));
    private final static ArrayList<Type> TAG_HOLDERS = new ArrayList<>(Arrays.asList(Type.GREEN, Type.BLUE, Type.CORPORATION));
    protected Boolean wait_for_server = false;
    private Boolean override_play_action_call = false;
    public final void overridePlayActionCall() {override_play_action_call = true;}

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


    public void onPlay(Player player) {
        onPlayServerHook(player, 0);
    }

    public void onPlayServerHook(Player player, Integer data) {
        if (GameController.getInstance().getGame().getServerMultiplayer() && !(this instanceof RoboticWorkforce)) {
            GameActions.sendCardEvent(new CardEventPacket(this.getName(), player.getName(), data));
        }
        playWithMetadata(player, data);
    }

    public void playWithMetadata(Player player, Integer data) {
        production_box.playProductionBox(player, data);
        finishPlay(player);

        if (override_play_action_call) {
            override_play_action_call = false;
            return;
        }

        if (wait_for_server && owner_game.getServerMultiplayer() && GameController.getInstance().getCurrentPlayer() != GameController.getInstance().getSelfPlayer()) {
            return;
        }
        if (type == Type.CORPORATION) {
            System.out.println("Calling use action from card row 84");
            GameController.getInstance().useAction();
        }

        if (type != Type.GHOST) {
            System.out.println("Calling use action from card row 86");
            GameController.getInstance().useAction();
        }
    }

    protected final void finishPlay (Player player) {
        if (OWNABLES.contains(type) && !(this instanceof BeginnerCorporation)) {
            owner_player = player;
        }

        boolean is_event = (type == Type.RED);

        // Adding tags for players and calling triggers from UpdateManager
        for (Tag tag : tags)
        {
            switch (tag)
            {
                case BUILDING:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addBuildingTag();
                    }
                    break;

                case SPACE:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addSpaceTag();
                    } else {
                        owner_game.update_manager.onSpaceEvent(player);
                    }
                    break;

                case EARTH:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addEarthTag();
                    }
                    owner_game.update_manager.onEarthTag(player);
                    break;

                case CITY:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addCityTag();
                    }
                    break;

                case PLANT:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addPlantTag();
                    }
                    owner_game.update_manager.onPlantTag(player);
                    break;

                case MICROBE:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addMicrobeTag();
                    }
                    owner_game.update_manager.onMicrobeTag(player);
                    break;

                case SCIENCE:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addScienceTag();
                    }
                    owner_game.update_manager.onScienceTag(player);
                    break;

                case ENERGY:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addEnergyTag();
                    }
                    break;

                case JOVIAN:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addJovianTag();
                    }
                    owner_game.update_manager.onJovianTag(player);
                    break;

                case VENUS:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addVenusTag();
                    }
                    break;

                case ANIMAL:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.addAnimalTag();
                    }
                    owner_game.update_manager.onAnimalTag(player);
                    break;

                case JOKER:
                    //event-cards don't have jokers, so check can be left off
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


        if (tags.size() == 0 && TAG_HOLDERS.contains(type)) {
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

    // For playing robotit workforce. Possible to overwrite in case metadata is needed
    // (or dynamic checking of production)

    // God do I hate robotic workforce -Eetu
    public void playProductionBox() {
        production_box.playProductionBox(owner_player, 0);
    }
    public final ProductionBox getProductionBox() {
        return production_box;
    }

    // Getting graphics for tags
    public final ArrayList<Integer> getTagIntegers() {
        ArrayList<Integer> tag_integers = new ArrayList<>();

        for (Tag tag : tags)
        {
            switch (tag)
            {
                case BUILDING:    tag_integers.add(R.drawable.building_round); break;
                case SPACE:       tag_integers.add(R.drawable.space_round); break;
                case EARTH:       tag_integers.add(R.drawable.earth); break;
                case CITY:        tag_integers.add(R.drawable.ic_ph); break;
                case PLANT:       tag_integers.add(R.drawable.plant_round); break;
                case MICROBE:     tag_integers.add(R.drawable.ic_ph); break;
                case SCIENCE:     tag_integers.add(R.drawable.science_round); break;
                case ENERGY:      tag_integers.add(R.drawable.electricity_round); break;
                case JOVIAN:      tag_integers.add(R.drawable.jovian); break;
                case VENUS:       tag_integers.add(R.drawable.ic_ph); break;
                case ANIMAL:      tag_integers.add(R.drawable.animal_round); break;
                case EVENT:       tag_integers.add(R.drawable.event); break;
                case JOKER:       tag_integers.add(R.drawable.ic_ph); break;
            }
        }
        return tag_integers;
    }

    // Getting the resources on the card represented as text
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

    // Future-proofing for the tactician -achievement in Hellas -board
    public final Boolean getHasRequirement() {
        return requirements.getDrawableRequrement() != 0;
    }

    public final Integer getRequirementInt()
    {
        return R.drawable.ic_ph;
    }

    // Spinners call toString by default
    @Override public String toString() {
        return getName();
    }
}
