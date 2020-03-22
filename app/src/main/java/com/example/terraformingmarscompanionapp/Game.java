package com.example.terraformingmarscompanionapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private HashMap<String, Card> deck;
    private HashMap<String, Card> preludes = new HashMap<>();
    private HashMap<String, Card> corporations = new HashMap<>();
    private Integer global_temperature;
    public Integer getGlobalTemperature() {return global_temperature;}
    private Integer global_oxygen;
    public Integer getGlobalOxygen() {return global_oxygen;}
    private Integer oceans_placed;
    public Integer getOceansPlaced() {return oceans_placed;}
    private Integer venus_terraform;
    public Integer getVenusTerraform() {return venus_terraform;}

    public Game(int player_count, boolean hellas_elysium, boolean corporate_era, boolean prelude, boolean colonies, boolean venus, boolean turmoil) {


        for (int i = 0; i < player_count; i++) {
            players.add(new Player(this));
        }

        GameConstructor constructor = new GameConstructor();
        deck = constructor.createDeck(corporate_era, prelude, colonies, venus, turmoil);

        if (prelude) {
            preludes = constructor.createPreludes();
        }

        global_temperature = -30;
        global_oxygen = 0;
        oceans_placed = 0;

        //TODO finish constructor
    }

    public boolean placeOcean(Player placing_player) {
        if (oceans_placed >= 9) {
            return false;
        }
        //TODO Lisää UpdateManager.ocean_placed
        //TODO Lisää pelaajan manipulointi (TR yms.)
        placing_player.changeTerraformingRating(1);
        oceans_placed++;
        return true;
    }

    public boolean raiseTemperature(Player raising_player) {
        if (global_temperature >= 8) {
            return false;
        }
        //TODO tarkista onko lämmön nostolle triggeriä, lisää tarvittaessa update manager
        //TODO Lisää pelaajan manipulointi (TR yms.)
        raising_player.changeTerraformingRating(1);
        global_temperature += 2;
        return true;
    }

    public boolean raiseOxygen(Player raising_player) {
        if (global_oxygen >= 14) {
            return false;
        }
        //TODO tarkista onko hapen nostolle triggeriä, lisää tarvittaessa update manager
        //TODO Lisää pelaajan manipulointi (TR yms.)
        raising_player.changeTerraformingRating(1);
        global_oxygen++;
        return true;
    }

    public boolean raiseVenus(Player raising_player) {
        if (venus_terraform >= 30) {
            return false;
        }
        //TODO tarkista onko venuksen nostolle triggeriä, lisää tarvittaessa update manager
        //TODO Lisää pelaajan manipulointi (TR yms.)
        raising_player.changeTerraformingRating(1);
        venus_terraform += 2;
        return true;
    }

    public boolean placeCity(Player placing_player, Integer type) {
        /*Luokat:
        0: tavallinen
        1: pääkaupunki
        2: noctis
        3: vulkaaninen
        4: phobos space haven
         */
        //TODO tämä
        return true;
    }

    public HashMap<String, Integer> checkCardCost(Card card, Player player) {
        HashMap<String, Integer> required_resources = new HashMap<>();
        Integer actual_price = card.getPrice();
        Integer money_amount = 0;
        Integer steel_amount = 0;
        Integer titanium_amount = 0;
        Integer heat_amount = 0;
        Integer plants_amount = 0;
        Integer needed_money;
        HashMap<String, Integer> card_tags = card.getTags();

        //If -tarkistukset tagialennuksille
        if (!card_tags.containsKey("Standard project")) {
            actual_price -= player.getCardDiscount();
        }

        if (card_tags.containsKey("Building")) {
            actual_price -= player.getBuildingTagDiscount();
        }

        if (card_tags.containsKey("Space")) {
            actual_price -= player.getSpaceTagDiscount();
        }

        if (card_tags.containsKey("Earth")) {
            actual_price -= player.getEarthTagDiscount();
        }

        if (card_tags.containsKey("Science")) {
            actual_price -= player.getScienceTagDiscount();
        }

        if (card_tags.containsKey("Energy")) {
            actual_price -= player.getEnergyTagDiscount();
        }

        if (card_tags.containsKey("Venus")) {
            actual_price -= player.getVenusTagDiscount();
        }


        //Mikäli raaka raha ei riitä, ahne algoritmi tarkistamaan voiko korvata muilla resursseilla
        if (actual_price < player.getMoney()) {
            money_amount = player.getMoney();
            needed_money = actual_price - money_amount;

            if (card_tags.containsKey("Space")) {
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

            if (card_tags.containsKey("Building")) {
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

    public Boolean checkCardRequirements(Card card, Player player) {
        //TODO lisää kaikki vaatimukset tähän
        return true;
    }
}
