package com.example.terraformingmarscompanionapp.game;

import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Serializable {

    //Suuremmat logiikkaa ohjaavat luokat
    public final UpdateManager update_manager;
    public final TileHandler tile_handler;

    //Erityyppiset pakat
    private final HashMap<String, Card> deck;
    private HashMap<String, Card> preludes = new HashMap<>();
    private final HashMap<String, Card> corporations;

    //Simppeli ArrayList pelaajia. Pelaajien tarkempi hallinta ylempänä GameController -luokassa
    private final ArrayList<Player> players = new ArrayList<>();

    //Pelataanko peli serverin välityksellä vai ei
    private Boolean server_multiplayer = false;
    public Boolean getServerMultiplayer() {return server_multiplayer;}

    /* Serveripelissä siirrosta lähetetään serverille aina vähintään CardEventPacketin (voi kuvata myös toimintaa)
     * lisäksi packet kertoo mitä kaikkea muuta lähetetään perässä. Nämä mahdolliset perässälähetettävät toiminnot
     * säilytetään vuoron ajan listoissa. CardEventPacket ja ResourceEventPacket luodaan tarvittaessa erikseen.
     */
    private ArrayList<ResourceEventPacket> resource_events = new ArrayList<>();
    private ArrayList<TileEventPacket> tile_events = new ArrayList<>();

    public void addResourceEvent(ResourceEventPacket event) {resource_events.add(event);}
    public void addTileEvent(TileEventPacket event) {tile_events.add(event);}

    //Getterit pelaajille ja pakoille, plus pakalle listana
    public ArrayList<Player> getPlayers() {return  players;}
    public HashMap<String, Card> getDeck() {return deck;}
    public HashMap<String, Card> getPreludes() {return preludes;}
    public HashMap<String, Card> getCorporations() {return corporations;}
    ArrayList<Card> getDeckAsList() {return new ArrayList<>(deck.values());}

    //EffectCard -rajapinnan korttien haku pakasta UpdateManageria varten
    HashMap<String, EffectCard> getEffectCards() {
        HashMap<String, EffectCard> effect_cards = new HashMap<>();
        for (Map.Entry<String, Card> entry : deck.entrySet()) {
            if (entry.getValue() instanceof EffectCard) {
                effect_cards.put(entry.getKey(), (EffectCard)entry.getValue());
            }
        }

        for (Map.Entry<String, Card> entry : corporations.entrySet()) {
            if (entry.getValue() instanceof EffectCard) {
                effect_cards.put(entry.getKey(), (EffectCard)entry.getValue());
            }
        }

        return effect_cards;
    }

    //Getteri pelaajalle nimen perusteella
    public Player getPlayer(String player_name) {
        for (Player player : players) {
            if (player.getName().equals(player_name)) {
                return player;
            }
        }
        return null;
    }

    //Pelin tilan parametrit, getterit ja setterit
    private Integer global_temperature;
    public Integer getGlobalTemperature() {return global_temperature;}
    private Integer global_oxygen;
    public Integer getGlobalOxygen() {return global_oxygen;}
    private final Integer oceans_placed;
    Integer getOceansPlaced() {return oceans_placed;}
    private Integer venus_terraform;
    public Integer getVenusTerraform() {return venus_terraform;}
    private Integer cities_on_mars;
    public Integer getCitiesOnMars() {return cities_on_mars;}
    void addCityOnMars() {cities_on_mars++;}
    private Integer cities_in_space;
    public Integer getCitiesInSpace() {return cities_in_space;}
    void addCityInSpace() {cities_in_space++;}

    //Rakentaja
    public Game(
                    ArrayList<String> player_names,
                    boolean hellas_elysium,
                    boolean corporate_era,
                    boolean prelude,
                    boolean colonies,
                    boolean venus,
                    boolean turmoil,
                    boolean extra_corporations,
                    boolean server_multiplayer,
                    Integer map
                )
    {

        for (String player_name : player_names) {
            players.add(new Player(this, player_name));
        }

        GameConstructor constructor = new GameConstructor();
        deck = constructor.createDeck(this, corporate_era, prelude, colonies, venus, turmoil);
        corporations = constructor.createCorporations(this, corporate_era, prelude, colonies, venus, turmoil);

        if (prelude) {
            preludes = constructor.createPreludes();
        }

        this.server_multiplayer = server_multiplayer;

        tile_handler = new TileHandler(this, map, venus);
        update_manager =  new UpdateManager(this, corporate_era, prelude, colonies, venus, turmoil);

        global_temperature = -30;
        global_oxygen = 0;
        oceans_placed = 0;
        cities_in_space = 0;
        cities_on_mars = 0;
        venus_terraform = 0;

        //TODO viimeistele constructor
    }

    //Terraformaus-sliderien manipulaatio
    public boolean raiseTemperature(Player raising_player) {
        if (global_temperature >= 8) {
            return false;
        }
        raising_player.changeTerraformingRating(1);
        global_temperature += 2;
        return true;
    }

    public boolean raiseOxygen(Player raising_player) {
        if (global_oxygen >= 14) {
            return false;
        }
        raising_player.changeTerraformingRating(1);
        global_oxygen++;
        return true;
    }

    public boolean raiseVenus(Player raising_player) {
        if (venus_terraform >= 30) {
            return false;
        }
        raising_player.changeTerraformingRating(1);
        venus_terraform += 2;
        return true;
    }


    //Koko kortin pelaaminen yhdessä funktiossa. Ottaa Card- ja Player -oliot
    public void playCard(Card card, Player player) {
        Log.i("Game", "Playing card");
        CardCostPacket resources_to_use = checkCardCost(card, player);
        if (resources_to_use == null | !checkCardRequirements(card, player)) {
            Log.i("Game", "Requirements or cost check invalid");
            return;
        }
        Log.i("Game", "Requirements and cost checks valid");

        Log.i("Game", String.format("Cost packet aquired, money: %d, steel: %d, titanium: %d heat: %d.", resources_to_use.getMoney(), resources_to_use.getSteel(), resources_to_use.getTitanium(), resources_to_use.getHeat()));

        //TODO UI kysy haluaako pelaaja muuttaa resurssien määrää

        player.changeMoney(-resources_to_use.getMoney());
        player.changeSteel(-resources_to_use.getSteel());
        player.changeTitanium(-resources_to_use.getTitanium());
        player.changeHeat(-resources_to_use.getHeat());

        //TODO pelaajan korttiresurssien vähentäminen kun kyseinen järjestelmä implementoitu.

        if (server_multiplayer) {
            CardEventPacket card_event = new CardEventPacket(card.getName(), player.getName(), false);

            Timer timer = new Timer();

            //Timer tarkistaa onko kaikki kortin määrittämät tapahtumat tallennettu ja lähettää paketit jos näin on
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (card_event.getResourceEventAmount() == resource_events.size() &&
                        card_event.getTileEventAmount() == tile_events.size()) {
                        GameActions.sendCardEvent(card_event);
                        GameActions.sendCardCost(resources_to_use);
                        if (tile_events.size() != 0) {
                            for (TileEventPacket tile_event : tile_events) {
                                GameActions.sendTileEvent(tile_event);
                            }
                            tile_events.clear();
                        }
                        if (resource_events.size() != 0) {
                            for (ResourceEventPacket resource_event : resource_events) {
                                GameActions.sendResourceEvent(resource_event);
                            }
                        }
                        timer.cancel();
                    }
                }
            }, 0, 1000);
        }
        Log.i("Game", "OnPlay called");
        card.onPlay(player);
    }

    //Kortin pelaamisen ensimmäinen vaihe. Palautta hinnan CardCost -oliona
    private CardCostPacket checkCardCost(Card card, Player player) {
        Log.i("Game", "Checking card cost");
        //Hyvin tylsä ja repetitiivinen funktio. Suosittelen minimoimaan.

        Integer actual_price = card.getPrice();
        Integer money_amount;
        Integer steel_amount = 0;
        Integer titanium_amount = 0;
        Integer heat_amount = 0;
        Integer plants_amount = 0;
        Integer floaters_amount = 0;
        Integer needed_money;
        ArrayList<String> card_tags = card.getTags();

        //If -tarkistukset tagialennuksille
        if (!card_tags.contains("Standard project")) {
            actual_price -= player.getCardDiscount();
        }

        if (card_tags.contains("Building")) {
            actual_price -= player.getBuildingTagDiscount();
        }

        if (card_tags.contains("Space")) {
            actual_price -= player.getSpaceTagDiscount();
        }

        if (card_tags.contains("Earth")) {
            actual_price -= player.getEarthTagDiscount();
        }

        if (card_tags.contains("Science")) {
            actual_price -= player.getScienceTagDiscount();
        }

        if (card_tags.contains("Energy")) {
            actual_price -= player.getEnergyTagDiscount();
        }

        if (card_tags.contains("Venus")) {
            actual_price -= player.getVenusTagDiscount();
        }

        if (actual_price < 0) {
            actual_price = 0;
        }

        Log.i("Game", "Actual price for card '" + card.getName() + "' played by player '" + player.getName() + "': " + actual_price);


        //Mikäli raaka raha ei riitä, ahne algoritmi tarkistamaan voiko korvata muilla resursseilla
        if (actual_price > player.getMoney()) {
            money_amount = player.getMoney();
            needed_money = actual_price - money_amount;

            /* Käytettävän teräksen ja titaanin tarkastus toimii seuraavasti:
             * Jos kortissa on käyttämiseen oikeuttava tägi, katsotaan onko pelaajan
             * ko. resurssin määrä tarpeeksi suuri maksamaan kortin, huomioiden mahdolliset korteista
             * saadut arvomuutokset resurssille. Sitten jakojäännöksen avulla poistetaan käytettyä rahaa
             * niin että resurssin arvoa ei mene hukkaan.
             *
             * Jos pelaajan resurssimäärä ei riitä, lisätään kaikki pelaajalta löytyvät resurssit muistiin
             * ja siirrytään tarkastamaan näiden kanssa seuraavan resurssin riittävyys. */
            if (card_tags.contains("space")) {
                Log.i("Game", "Player titanium amount: " + player.getTitanium());
                if (player.getTitanium() * (3 + player.getTitaniumValueModifier()) >= needed_money) {
                    titanium_amount = (needed_money + needed_money % (3 + player.getTitaniumValueModifier())) / (3 + player.getTitaniumValueModifier());
                    money_amount = actual_price - titanium_amount * (3 + player.getTitaniumValueModifier());

                    if (money_amount < 0) {
                        money_amount = 0;
                    }

                    return new CardCostPacket(money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                } else {
                    titanium_amount = player.getTitanium();
                    needed_money -= titanium_amount * (3 + player.getTitaniumValueModifier());
                }
            }

            if (card_tags.contains("building")) {
                Log.i("Game", "Player steel amount: " + player.getSteel());
                if (player.getSteel() * (2 + player.getSteelValueModifier()) >= needed_money) {
                    steel_amount = (needed_money + needed_money % (2 + player.getSteelValueModifier())) / (2 + player.getSteelValueModifier());
                    money_amount = actual_price - (steel_amount * (2 + player.getSteelValueModifier()
                                                   + titanium_amount * (3 + player.getTitaniumValueModifier())));
                    if (money_amount < 0) {
                        money_amount = 0;
                    }

                    return new CardCostPacket(money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                } else {
                    steel_amount = player.getSteel();
                    needed_money -= steel_amount * (2 + player.getSteelValueModifier());
                }
            }

            //TODO lisää psychrophiles check tähän jahka kortti implementoitu

            //TODO Lisää floater = venus tag rahaa jahka kortti implementoitu

            if (player.getHeatIsMoney()) {
                if (player.getHeat() >= needed_money) {
                    Log.i("Game", "Cost packet created");
                    heat_amount = needed_money;
                    return new CardCostPacket(money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                } else {
                    Log.i("Game", "Invalid funds");
                    return null;
                }
            }
            Log.i("Game", "Invalid funds");
            return null;

        } else {
            Log.i("Game", "Cost packet created");
            return new CardCostPacket(actual_price, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
        }
    }

    //Kortin pelaamisen toinen vaihe. Palauttaa totuusarvon, täyttääkö pelaajan nykytilanne kortin vaatimukset.
    private Boolean checkCardRequirements(Card card, Player player) {
        Log.i("Game", "Checking card requirements");

        //Erittäin tylsä if-hirviö. Palauttaa true jos kaikki vaatimukset täytetty, muuten false.
        //Suosittelen lämpimästi minimoimaan tämän.
        CardRequirements requirements = card.getRequirements();

        Integer base_discount = player.getBaseTrRequirementDiscount();
        Integer venus_discount = player.getVenusTrRequirementDiscount();

        Integer unused_jokers = player.getJokerTags();

        //Erillisinä portteina, jotta mahdollisuus kirjoittaa myöhemmin tarkempi palautteen anto
        if (requirements.getMinOceans() != null && oceans_placed < requirements.getMinOceans() - base_discount) {
            return false;
        }

        if (requirements.getMinEnergyProduction() != null && player.getEnergyProduction() < requirements.getMinEnergyProduction()) {
            return false;
        }

        if (requirements.getMinPlants() != null && player.getPlants() < requirements.getMinPlants()) {
            return false;
        }

        if (requirements.getMinOxygen() != null && global_oxygen < requirements.getMinOxygen() - base_discount) {
            return false;
        }

        if (requirements.getMinScienceTags() != null && player.getScienceTags() < requirements.getMinScienceTags()) {
            if (player.getScienceTags() + unused_jokers > requirements.getMinScienceTags()) {
                unused_jokers -= (requirements.getMinScienceTags() - player.getScienceTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinJovianTags() != null && player.getJovianTags() < requirements.getMinJovianTags()) {
            if (player.getJovianTags() + unused_jokers > requirements.getMinJovianTags()) {
                unused_jokers -= (requirements.getMinJovianTags() - player.getJovianTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinSteelProduction() != null && player.getSteelProduction() < requirements.getMinSteelProduction()) {
            return false;
        }

        if (requirements.getMinGlobalCities() != null && cities_in_space + cities_on_mars < requirements.getMinGlobalCities()) {
            return false;
        }

        if (requirements.getMinPersonalCities() != null && player.getCities() < requirements.getMinPersonalCities()) {
            return false;
        }

        if (requirements.getMinVenusTr() != null && venus_terraform < requirements.getMinVenusTr() - venus_discount) {
            return false;
        }

        if (requirements.getMinTemperature() != null && global_temperature < requirements.getMinTemperature() - venus_discount) {
            return false;
        }

        if (requirements.getMinPlantProduction() != null && player.getPlantsProduction() < requirements.getMinPlantProduction()) {
            return false;
        }

        if (requirements.getMinMicrobeTags() != null && player.getMicrobeTags() < requirements.getMinMicrobeTags()) {
            return false;
        }

        if (requirements.getMinAnimalTags() != null && player.getAnimalTags() < requirements.getMinAnimalTags()) {
            return false;
        }

        if (requirements.getMinEarthTags() != null && player.getEarthTags() < requirements.getMinEarthTags()) {
            return false;
        }

        if (requirements.getMinEnergyTags() != null && player.getEnergyTags() < requirements.getMinEnergyTags()) {
            return false;
        }

        if (requirements.getMinFloaters() != null) {
            int floaters = 0;
            for (ResourceCard resource_card : player.getResourceHolders()) {
                if (resource_card.getResourceType() == 4) {
                    floaters += resource_card.getResourceAmount();
                }
            }
            if (floaters < requirements.getMinFloaters()) {
                return false;
            }
        }

        if (requirements.getMinPersonalColonies() != null && player.getColonies() < requirements.getMinPersonalColonies()) {
            return false;
        }

        if (requirements.getMinTitaniumProduction() != null && player.getTitaniumProduction() < requirements.getMinTitaniumProduction()) {
            return false;
        }

        if (requirements.getMinPersonalGreeneries() != null && player.getGreeneries() < requirements.getMinPersonalGreeneries()) {
            return false;
        }

        if (requirements.getMinHeatProduction() != null && player.getHeatProduction() < requirements.getMinHeatProduction()) {
            return false;
        }

        if (requirements.getMinTr() != null && player.getTerraformingRating() < requirements.getMinTr()) {
            return false;
        }

        if (requirements.getMinVenusTags() != null && player.getVenusTags() < requirements.getMinVenusTags()) {
            return false;
        }

        if (requirements.getMinHeat() != null && player.getHeat() < requirements.getMinHeat()) {
            return false;
        }

        if (requirements.getMaxTemperature() != null && global_temperature > requirements.getMaxTemperature() + base_discount) {
            return false;
        }

        if (requirements.getMaxOceans() != null && oceans_placed > requirements.getMaxOceans() + base_discount) {
            return false;
        }

        if (requirements.getMaxOxygen() != null && global_oxygen > requirements.getMaxOxygen() + base_discount) {
            return false;
        }

        if (requirements.getMaxPersonalColonies() != null && player.getColonies() > requirements.getMaxPersonalColonies()) {
            return false;
        }

        return requirements.getMaxVenusTr() == null || venus_terraform <= requirements.getMaxVenusTr() + base_discount;
    }

    //Tätä tarvitaan erityisesti serveripohjaisessa moninpelissä, mutta myös parissa erityistapauksessa
    //paikallisissa peleissä.
    public void changeCardResources(Card card, Integer amount) {

        if (!(card instanceof ResourceCard)) {
            return;
        }
        ResourceCard resource_holder = (ResourceCard)card;

        resource_holder.changeResourceAmount(amount);
    }

    //Sukupolven päättäminen
    public void onGenerationEnd() {
        if (global_temperature >= 8 && global_oxygen >= 14 && oceans_placed >= 9) {
            endGame();
        }
        for (Player player : players) {
            player.changeMoney(player.getMoneyProduction() + player.getTerraformingRating());
            player.changeSteel(player.getSteelProduction());
            player.changeTitanium(player.getTitaniumProduction());
            player.changePlants(player.getPlantsProduction());
            player.changeHeat(player.getEnergy());
            player.changeEnergy(-player.getEnergy());
            player.changeEnergy(player.getEnergyProduction());
            player.changeHeat(player.getHeatProduction());
            player.setRaisedTrThisGeneration(false);
            player.resetActions();
        }
        //TODO turmoil-hommat tähän
    }

    private void endGame() {

    }
}