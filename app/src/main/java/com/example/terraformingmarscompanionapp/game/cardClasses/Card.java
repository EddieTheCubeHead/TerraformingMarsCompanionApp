package com.example.terraformingmarscompanionapp.game.cardClasses;

import android.content.Context;
import android.util.Log;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cards.basegame.corporations.BeginnerCorporation;
import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.CardRequirements;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardEventPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The main parent class of all cards. Most actions in the game are represented as a card. The process
 * of playing a card is split into three methods, {@link #initializePlayEvents(Player)}, {@link #onPlayServerHook(Player, Integer)} and
 * {@link #playWithMetadata(Player, Integer)}.
 * <p></p>
 * {@link #initializePlayEvents(Player)} is called when starting the playing process and it
 * queues events into the static {@link EventScheduler} -class. The events then call {@link #onPlayServerHook(Player, Integer)}
 * which in turn calls {@link #playWithMetadata(Player, Integer)}.
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.2
 */
public abstract class Card implements Serializable {
    protected static Game game;
    protected String name = "ABSTRACT_BASE_CARD";
    protected Integer price = 0;
    protected Integer victory_points = 0;
    private Type type;
    protected Boolean action_used = false;
    protected transient Player owner_player = null; //null if unowned
    protected String player_name = null; //used in serialization
    protected ArrayList<Tag> tags = new ArrayList<>();
    protected CardRequirements requirements = new CardRequirements();
    protected ProductionBox production_box = new ProductionBox();

    // Lists of tag collections for certain operations
    private final static ArrayList<Type> OWNABLES = new ArrayList<>(Arrays.asList(Type.RED, Type.GREEN, Type.BLUE, Type.CORPORATION, Type.GHOST, Type.MILESTONE));
    private final static ArrayList<Type> TAG_HOLDERS = new ArrayList<>(Arrays.asList(Type.GREEN, Type.BLUE, Type.CORPORATION));
    public final static ArrayList<Type> MAIN_DECK = new ArrayList<>(Arrays.asList(Type.RED, Type.GREEN, Type.BLUE));

    /**
     * Default constructor. Should be overriden in every concrete childclass and set at least the
     * name and the price of the card.
     *
     * @param card_type {@link Type} of the card
     */
    public Card(Type card_type) {
        type = card_type;
    }

    public static void SetGame(Game game) {
        Card.game = game;
    }


    /**
     * The first method to be called in the playing process. Queues using an action and playing the
     * card into {@link EventScheduler}. Can be overriden if the card needs custom event queueing.
     * <p></p>
     * When overriding make sure to add a call for {@link PlayCardEvent} or some form of metadata
     * gathering event and a call for {@link EventScheduler#playNextEvent(Context)}. Never call
     * super.onPlay when overriding
     *
     * @param player {@link Player} playing the card. Instance of {@link Player}
     */
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
    }

    /**
     * A method mainly used for sending data to the server during the playing process of the card.
     * Might need to be rewritten in some more complex cases to allow for more intricate metadata
     * operations like chaining two decisions together. Unlike {@link #initializePlayEvents(Player)}, this should always
     * call {@code super.playWithMetadata} when overriding.
     *
     * @param player {@link Player} playing the card. Instance of {@link Player}
     * @param data {@link Integer} the metadata associated with the play event
     */
    public void onPlayServerHook(Player player, Integer data) throws InvalidResourcesException {
        if (game.getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getName(), player.getName(), data));
        }
        playWithMetadata(player, data);
    }

    /**
     * The last public method called when playing a card. In almost all cases should be called from
     * {@link #onPlayServerHook(Player, Integer)}. The most often overriden method out of the three in the playing
     * process. Everything that happens on all clients when playing through a server should be
     * declared in a card specific overriden version. As with {@link #onPlayServerHook(Player, Integer)}, should
     * always call {@code super.playWithMetadata} when overriden.
     *
     * @param player {@link Player} playing the card. Instance of {@link Player}
     * @param data {@link Integer} the metadata associated with the play event
     */
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.playProductionBox(player, data);
        finishPlay(player);
    }

    /**
     * Method for finishing the play process. Contains the shared logic. Should always be called
     * from the superclass' {@link #playWithMetadata(Player, Integer)}.
     *
     * @param player {@link Player} playing the card. Instance of {@link Player}
     */
    protected final void finishPlay (Player player) {
        Log.i("Card", String.format("Finishing playing card '%s'", name));
        if (OWNABLES.contains(type) && !(this instanceof BeginnerCorporation)) {
            owner_player = player;
            player_name = player.getName();
        }

        if (MAIN_DECK.contains(type)) {
            player.getModifiers().setNextCardDiscount(0);
        }

        boolean is_event = (type == Type.RED);

        // Adding tags for players and calling triggers from UpdateManager
        for (Tag tag : tags)
        {
            switch (tag)
            {
                case BUILDING:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addBuildingTag();
                    }
                    break;

                case SPACE:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addSpaceTag();
                    } else {
                        game.update_manager.onSpaceEvent(player);
                    }
                    break;

                case EARTH:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addEarthTag();
                    }
                    game.update_manager.onEarthTag(player);
                    break;

                case CITY:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addCityTag();
                    }
                    break;

                case PLANT:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addPlantTag();
                    }
                    game.update_manager.onPlantTag(player);
                    break;

                case MICROBE:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addMicrobeTag();
                    }
                    game.update_manager.onMicrobeTag(player);
                    break;

                case SCIENCE:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addScienceTag();
                    }
                    game.update_manager.onScienceTag(player);
                    break;

                case ENERGY:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addEnergyTag();
                    }
                    break;

                case JOVIAN:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addJovianTag();
                    }
                    game.update_manager.onJovianTag(player);
                    break;

                case VENUS:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addVenusTag();
                    }
                    break;

                case ANIMAL:
                    if (!is_event && OWNABLES.contains(type)) {
                        player.getTags().addAnimalTag();
                    }
                    game.update_manager.onAnimalTag(player);
                    break;

                case JOKER:
                    // event-cards don't have jokers, so check can be left off
                    player.getTags().addJokerTag();
                    break;

                case EVENT:
                    player.getTags().addEventTag();
                    game.update_manager.onEventPlayed(player);
                    break;
                default:
                    System.out.println("Tag error in card " + getName());
            }
        }


        if (tags.size() == 0 && TAG_HOLDERS.contains(type)) {
            player.getTags().addNullTag();
        }

        switch (type) {
            case GREEN:
                player.addGreen();
                break;
            case RED:
                player.addRed();
                break;
            case BLUE:
                player.addBlue();
                break;
            case CORPORATION:
                player.setCorporation(this);
                break;
            case PRELUDE:
            case GHOST:
            case AWARD:
            case MILESTONE:
            case OTHER:
                break;
            case STANDARD_PROJECT:
                game.update_manager.onStandardProjectPayment(player);
                break;
            default:
                Log.i("Card","Type error in card " + getName());
        }

        EventScheduler.playNextEvent(GameController.getContext());
    }

    /**
     * A method for counting points when the game ends.
     */
    public void onGameEnd() {
        if (owner_player == null) {
            return;
        }
        owner_player.setVictoryPoints(owner_player.getVictoryPoints() + victory_points);
    }

    /**
     * @return {@link String} the name of the card
     */
    public final String getName() {
        return name;
    }

    /**
     * @return {@link Player} the owner player
     */
    public final Player getOwner() {
        return owner_player;
    }

    /**
     * @return {@link Integer} the price of the card
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @return {@link Type} of the card
     */
    public final Type getType() {
        return type;
    }

    /**
     * @return {@link Boolean} whether the action of the card is used this generation
     */
    public final Boolean getActionUsed() {
        return action_used;
    }

    /**
     * @return {@link ArrayList} of {@link Tag} enums that represent the tags of the card
     */
    public final ArrayList<Tag> getTags() {return tags;}

    /**
     * @return {@link CardRequirements} the requirements for playing the card.
     */
    public final CardRequirements getRequirements() {return requirements;}

    /**
     * Used to reset the action of the card if the card is an instance of {@link ActionCard}.
     * Implemented here because this way at the end of a generation the game can just run this for
     * all cards, no need for casting.
     */
    public final void resetActionUsed() {action_used = false;}

    // For playing robotit workforce. Possible to override in case metadata is needed
    // (or dynamic checking of production)

    // God do I hate robotic workforce
    /**
     * Method representing the "production box" part of the card. Only here because robotic workforce
     * needs this. Runs {@link ProductionBox#playProductionBox(Player, Integer)} on the {@link ProductionBox}
     * of the card.
     */
    public void playProductionBox() throws InvalidResourcesException {
        //TODO playing robotic workforce
        production_box.playProductionBox(owner_player, 0);
    }

    /**
     * @return {@link ProductionBox} of the card
     */
    public final ProductionBox getProductionBox() {
        return production_box;
    }

    /**
     * Method for getting the tags of the card as drawables
     *
     * @return {@link ArrayList} of {@link Integer}, that represent drawables of card tags.
     */
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

    /**
     * Method to get the resources on the card as text for UI uses
     *
     * @return {@link String} representation of the resources on the card
     */
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

    /**
     * Providing a way to avoid serializing card owning players
     *
     * @param in_stream {@link ObjectInputStream} used in the serialization process
     * @throws IOException default exception thrown by deserialization
     * @throws ClassNotFoundException default exception thrown by deserialization
     */
    private void readObject(ObjectInputStream in_stream) throws IOException, ClassNotFoundException {

        in_stream.defaultReadObject();

        if (player_name != null) {
            owner_player = GameController.getPlayer(player_name);
            Log.i("Card", "Deserialized an owned card '" + name + "', owner: " + player_name);
        } else {
            owner_player = null;
        }
    }

    // Future-proofing for the tactician -achievement in Hellas -board
    public final Boolean getHasRequirement() {
        return requirements.getDrawableRequrement() != 0;
    }

    // TBA with graphics for requirements. Stud for now
    public final Integer getRequirementInt()
    {
        return R.drawable.ic_ph;
    }

    // Spinners call toString by default
    @Override public String toString() {
        return getName();
    }
}
