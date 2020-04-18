package com.example.terraformingmarscompanionapp.cardSubclasses;

import android.util.Log;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.CardRequirements;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;

import java.util.ArrayList;

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

    //Enum kortin tyyppiin
    protected enum Type {
        GREEN,
        RED,
        BLUE,
        CORPORATION,
        PRELUDE,
        STANDARD_PROJECT,
        OTHER,
        GHOST
    }

    //Käytetään serverin kanssa pelatessa ilmoittamaan serverille tarvittavat lisätiedot yhdessä
    //sendWaitInformation(Boolean action) -funktion kanssa
    protected Integer wait_tile_event = 0;
    protected Integer wait_metadata = 0;
    protected Integer wait_resource_event = 0;
    protected Integer wait_action_tile_event = 0;
    protected Integer wait_action_metadata = 0;
    protected Integer wait_action_resource_event = 0;

    public Card(Type card_type) {
        type = card_type;
    }

    public void onPlay (Player player) {
        owner_player = player;

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


        if (tags.size() == 0 && type != Type.GHOST ) {
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
            case OTHER:
                break;
            default:
                Log.i("Card","Type error in card " + getName());
        }

        if (this instanceof ActionCard) {
            player.addAction((ActionCard)this);
        }
    }

    /* Serveri-implementaatiossa on siirrettävä jotenkin kortin pelaamiseen liittyvät päätökset.
     * Onneksi kaikki nämä päätökset ovat kuvattavissa yhdellä kokonaisluvulla. Tarvittaessa
     * kortti voi override:aa tämän funktion, jotta sen pelaaminen muilla pelissä olevilla onnistuu
     * ilman kortin sisällä tehtävää päätöstä. Samoin jos kortissa on manuaalisesti toteutettava
     * resurssi- tai tiilitapahtuma kyseinen tapahtuma saadaan serveriltä eikä UI:sta. playWithMetadata
     * ottaa arraylistin TileEventteja ja ResourceEventtejä näiden kuvastamiseen.
     */
    public void playWithMetadata(Player player, Integer data, ArrayList<TileEventPacket> tile_events, ArrayList<ResourceEventPacket> resource_events) {
        Log.i("Card", "Play with metadata called from unsupported card: " + this.name);
        onPlay(player);
    }

    //Lähettää serverille ilmoituksen, että serverin täytyy odottaa lisää paketteja
    public final void updateWaitInformation(CardEventPacket event) {
        if (event.getIsAction()) {
            event.setMetadata(wait_action_metadata);
            event.setResourceEvents(wait_action_resource_event);
            event.setTileEvents(wait_action_tile_event);
        } else {
            event.setMetadata(wait_metadata);
            event.setResourceEvents(wait_resource_event);
            event.setTileEvents(wait_tile_event);
        }
    }

    public void onGameEnd() {owner_player.changeVictoryPoints(victory_points);}
    public final String getName() {return name;}
    public final Player getOwenr() {return owner_player;}
    public final Integer getPrice() {return price;}
    public final ArrayList<Tag> getTags() {return tags;}
    public final CardRequirements getRequirements() {return requirements;}
    public final void resetActionUsed() {action_used = false;}

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

    public final Integer getRequirementInt()
    {
        return R.drawable.ic_ph;
    }
}
