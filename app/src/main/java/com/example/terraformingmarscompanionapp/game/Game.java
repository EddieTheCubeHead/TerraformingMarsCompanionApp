package com.example.terraformingmarscompanionapp.game;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game implements Serializable {

    public final UpdateManager update_manager;
    private final ArrayList<Player> players = new ArrayList<>();
    private final HashMap<String, Card> deck;

    private HashMap<String, Card> preludes = new HashMap<>();
    private final HashMap<String, Card> corporations = new HashMap<>();
    public final TileHandler tile_handler;

    public ArrayList<Player> getPlayers() {return  players;}
    public HashMap<String, Card> getDeck() {return deck;}
    public HashMap<String, Card> getPreludes() {return preludes;}
    public HashMap<String, Card> getCorporations() {return  corporations;}
    ArrayList<Card> getDeckAsList() {return new ArrayList<>(deck.values());}
    HashMap<String, EffectCard> getEffectCards() {
        HashMap<String, EffectCard> effect_cards = new HashMap<>();
        for (Map.Entry<String, Card> entry : deck.entrySet()) {
            if (entry.getValue() instanceof ActionCard) {
                effect_cards.put(entry.getKey(), (EffectCard)entry.getValue());
            }
        }
        return effect_cards;
    }

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

    public Game(
                    ArrayList<String> player_names,
                    boolean hellas_elysium,
                    boolean corporate_era,
                    boolean prelude,
                    boolean colonies,
                    boolean venus,
                    boolean turmoil,
                    boolean extra_corporations,
                    Integer map
                )
    {

        for (String player_name : player_names) {
            players.add(new Player(this, player_name));
        }

        GameConstructor constructor = new GameConstructor();
        deck = constructor.createDeck(this, corporate_era, prelude, colonies, venus, turmoil);

        if (prelude) {
            preludes = constructor.createPreludes();
        }

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

    private HashMap<String, Integer> checkCardCost(Card card, Player player) {
        HashMap<String, Integer> required_resources = new HashMap<>();
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


        //Mikäli raaka raha ei riitä, ahne algoritmi tarkistamaan voiko korvata muilla resursseilla
        if (actual_price < player.getMoney()) {
            money_amount = player.getMoney();
            needed_money = actual_price - money_amount;

            if (card_tags.contains("Space")) {
                if (player.getTitanium() * (3 + player.getTitaniumValueModifier()) >= needed_money) {
                    titanium_amount = needed_money / (3 + player.getTitaniumValueModifier());
                    money_amount = actual_price - titanium_amount * (3 + player.getTitaniumValueModifier());
                    required_resources.put("money", money_amount);
                    required_resources.put("titanium", titanium_amount);
                    return required_resources;
                } else {
                    titanium_amount = player.getTitanium();
                    needed_money -= titanium_amount * (3 + player.getTitaniumValueModifier());
                }
            }

            if (card_tags.contains("Building")) {
                if (player.getSteel() * (2 + player.getSteelValueModifier()) >= needed_money) {
                    steel_amount = needed_money / (2 + player.getSteelValueModifier());
                    money_amount = actual_price - (steel_amount * (2 + player.getSteelValueModifier()
                                                   + titanium_amount * (3 + player.getTitaniumValueModifier())));
                    required_resources.put("money", money_amount);
                    required_resources.put("titanium", titanium_amount);
                    required_resources.put("steel", steel_amount);
                    return required_resources;
                } else {
                    steel_amount = player.getTitanium();
                    needed_money -= steel_amount * (2 + player.getSteelValueModifier());
                }
            }

            //TODO lisää psychrophiles check tähän jahka kortti implementoitu

            //TODO Lisää floater = venus tag rahaa jahka kortti implementoitu

            if (player.getHeatIsMoney()) {
                if (player.getHeat() >= needed_money) {
                    required_resources.put("money", money_amount);
                    required_resources.put("titanium", titanium_amount);
                    required_resources.put("steel", steel_amount);
                    required_resources.put("heat", heat_amount);
                    return required_resources;
                } else {
                    return null;
                }
            }


        } else {
            required_resources.put("money", actual_price);
        }
        return required_resources;
    }

    private Boolean checkCardRequirements(Card card, Player player) {
        //Erittäin tylsä switch-case hirviö. Palauttaa true jos kaikki vaatimukset täytetty, muuten false.

        Integer value;
        Integer min_tr_value;
        Integer max_tr_value;
        Integer min_venus_value;
        Integer max_venus_value;
        for (Map.Entry<String, Integer> entry : card.getRequirements().entrySet()) {

            //Voi olla muutoksia korteista, tämä hoitaa nämä
            value = entry.getValue();
            min_tr_value = entry.getValue() - player.getBaseTrRequirementDiscount();
            max_tr_value = entry.getValue() + player.getBaseTrRequirementDiscount();
            min_venus_value = entry.getValue() - player.getVenusTrRequirementDiscount();
            max_venus_value = entry.getValue() + player.getVenusTrRequirementDiscount();

            Integer unused_jokers = player.getJokerTags();

            switch (entry.getKey()) {
                case "min_oceans":
                    if (oceans_placed < min_tr_value) {
                        return false;
                    } break;

                case "min_plants":
                    if (player.getPlants() < value) {
                        return false;
                    } break;

                case "min_energy_production":
                    if (player.getEnergyProduction() < value) {
                        return false;
                    } break;

                case "min_oxygen":
                    if (global_oxygen < min_tr_value) {
                        return false;
                    } break;

                case "min_science_tags":
                    if (player.getScienceTags() + unused_jokers < value) {
                        return false;
                    }
                    if (player.getScienceTags() < value) {
                        unused_jokers -= (value - player.getScienceTags());
                    } break;

                case "min_jovian_tags":
                    if (player.getJovianTags() + unused_jokers < value) {
                        return false;
                    }
                    if (player.getJovianTags() < value) {
                        unused_jokers -= (value - player.getJovianTags());
                    } break;

                case "min_steel_production":
                    if (player.getSteelProduction() < value) {
                        return false;
                    } break;

                case "min_global_cities":
                    if ((cities_in_space + cities_on_mars) < value) {
                        return false;
                    } break;

                case "min_personal_cities":
                    if (player.getCities() < value) {
                        return false;
                    } break;

                case "min_venus_tr":
                    if (venus_terraform < min_venus_value) {
                        return false;
                    } break;

                case "min_temperature":
                    if (global_temperature < min_tr_value) {
                        return false;
                    } break;

                case "min_plant_production":
                    if (player.getPlantsProduction() < value) {
                        return false;
                    } break;

                case "min_plant_tags":
                    if (player.getPlantTags() + unused_jokers < value) {
                        return false;
                    } if (player.getPlantTags() < value) {
                    unused_jokers -= (value - player.getPlantTags());
                    } break;

                case "min_microbe_tags":
                    if (player.getMicrobeTags() + unused_jokers < value) {
                        return false;
                    } if (player.getMicrobeTags() < value) {
                    unused_jokers -= (value - player.getMicrobeTags());
                    } break;

                case "min_animal_tags":
                    if (player.getAnimalTags() + unused_jokers < value) {
                        return false;
                    } if (player.getAnimalTags() < value) {
                    unused_jokers -= (value - player.getAnimalTags());
                    } break;

                case "min_earth_tags":
                    if (player.getEarthTags() + unused_jokers < value) {
                        return false;
                    } if (player.getEarthTags()  < value) {
                    unused_jokers -= (value - player.getEarthTags());
                    } break;

                case "min_energy_tags":
                    if (player.getEnergyTags() + unused_jokers < value) {
                        return false;
                    } if (player.getEnergyTags() < value) {
                    unused_jokers -= (value - player.getEnergyTags());
                    } break;

                case "min_floaters":
                    int floaters = 0;
                    for (ResourceCard resource_card : player.getResourceHolders()) {
                        if (resource_card.getResourceType() == 4) {
                            floaters += resource_card.getResourceAmount();
                        }
                    }
                    if (floaters < value) {
                        return false;
                    } break;

                case "min_personal_colonies":
                    if (player.getColonies() < value) {
                        return false;
                    } break;

                case "min_titanium_production":
                    if (player.getTitaniumProduction() < value) {
                        return false;
                    } break;

                case "min_personal_greeneries":
                    if (player.getGreeneries() < value) {
                        return false;
                    } break;

                case "min_heat_production":
                    if (player.getHeatProduction() < value) {
                        return false;
                    } break;

                case "min_tr":
                    if (player.getTerraformingRating() < value) {
                        return false;
                    } break;

                case "min_venus_tags":
                    if (player.getVenusTags() + unused_jokers < value) {
                        return false;
                    } if (player.getVenusTags() < value) {
                    unused_jokers -= (value - player.getVenusTags());
                    }  break;

                case "max_temperature":
                    if (global_temperature > max_tr_value) {
                        return false;
                    } break;

                case "max_oceans":
                    if (oceans_placed > max_tr_value) {
                        return false;
                    } break;

                case "max_personal_colonies":
                    if (player.getColonies() > value) {
                        return false;
                    } break;

                case "max_venus_tr":
                    if (venus_terraform > max_venus_value) {
                        return false;
                    } break;
            }
        }
        return true;
    }


    public void playCard(Card card, Player player) {
        HashMap<String, Integer> resources_to_use = checkCardCost(card, player);
        if (resources_to_use == null | !checkCardRequirements(card, player)) {
            return;
        }

        //TODO UI kysy haluaako pelaaja muuttaa resurssien määrää

        for (Map.Entry<String, Integer> entry : resources_to_use.entrySet()) {
            switch (entry.getKey()) {
                case "money":
                    player.changeMoney(-entry.getValue());
                case "steel":
                    player.changeSteel(-entry.getValue());
                case "titanium":
                    player.changeTitanium(-entry.getValue());
                case "heat":
                    player.changeHeat(-entry.getValue());
            }
        }

        card.onPlay(player);
    }

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
        }
        //TODO turmoil-hommat tähän
    }

    private void endGame() {

    }
}
