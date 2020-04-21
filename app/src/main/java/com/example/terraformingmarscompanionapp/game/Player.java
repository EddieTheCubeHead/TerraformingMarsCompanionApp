package com.example.terraformingmarscompanionapp.game;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;

import java.util.ArrayList;

public class Player {

    //Pelaajan nimi ja peli
    private final Game game;
    private final String name;

    //Pelaajan yleistilanteesta tarvittavat tiedot
    private Integer cities = 0;
    private Integer greeneries = 0;
    private Integer colonies = 0;
    private Integer hand_size = 0; //Vaikka ei pidetä tietoa, mitä kortteja kädessä, korttien määrä on helppo pitää ylhäällä milestoneja varten

    //Erityyppisille korteille säilytyspaikkoja
    private Card corporation;
    private final ArrayList<Card> green_cards = new ArrayList<>();
    private final ArrayList<Card> red_cards = new ArrayList<>();
    private final ArrayList<ActionCard> action_cards = new ArrayList<>();
    private final ArrayList<Card> blue_cards = new ArrayList<>();
    private final ArrayList<Card> preludes = new ArrayList<>();
    private final ArrayList<ResourceCard> resource_holders = new ArrayList<>();

    //Korttien lisääminen vastaavaan säilytyspaikkaan
    public void setCorporation(Card card) {corporation=card;}
    public void addGreen(Card card) {green_cards.add(card);}
    public void addRed(Card card) {red_cards.add(card);}
    public void addAction(ActionCard card) {action_cards.add(card);}
    public void addBlue(Card card) {blue_cards.add(card);}
    public void addPrelude(Card card) {preludes.add(card);}
    public void addResourceHolder(ResourceCard card) {resource_holders.add(card);}

    //Parametrien muuttaminen
    public void addCity() {cities++;}
    public void addGreenery() {greeneries++;}
    public void addColony() {colonies++;}

    //Getterit tarvittaville muuttujille
    public String getName() {return name;}

    public Integer getCities() {return cities;}
    public Integer getGreeneries() {return greeneries;}
    public Integer getColonies() {return colonies;}
    public Integer getHandSize() {return hand_size;}

    public Card getCorporation() {return corporation;}
    public ArrayList<Card> getGreens() {return green_cards;}
    public ArrayList<Card> getReds() {return red_cards;}
    public ArrayList<ActionCard> getActions() {return action_cards;}
    public ArrayList<Card> getEffects() {return blue_cards;}
    public ArrayList<ResourceCard> getResourceHolders() {return resource_holders;}

    //Käsikoon asettaminen
    public Boolean changeHandSize(Integer change_amount) {
        if (hand_size + change_amount < 0) {
            return false;
        }
        hand_size += change_amount;
        return true;
    }

    //Resetoi toimintojen käyttötilanne sukupolven lopuksi
    void resetActions() {
        for (Card card : blue_cards) {
            if (card instanceof ActionCard) {
                card.resetActionUsed();
            }
        }
    }

    /* Seuraavana luokassa on valtava määrä datan säilömiseen tarvittavia muuttujia, sekä
     * niiden getterietä ja settereitä. Olen koittanut lajitella muuttujat melko selkeisiin
     * kategorioihin. */

    /***********************************************************************************
     Resurssit
     ************************************************************************************/

    //Raha
    private Integer money = 0;
    private Integer money_production = 0;
    public Integer getMoney() {return money;}
    public boolean changeMoney(Integer change_amount) {
        if (money + change_amount < 0) {
            return false;
        }
        money += change_amount;
        return true;
    }
    /* Jotkin kortit vievät mahdollisimman paljon resurssia, takeResource() -funktiot vievät annetun
     * määrän mutta jättävät määrän nollaan ja toimivat vaikkei annettua määrä voisi viedä.
     */
    public void takeMoney(Integer amount) {
        money -= amount;
        if (money < 0) {
            money = 0;
        }
    }


    public Integer getMoneyProduction() {return money_production;}
    public void changeMoneyProduction(Integer change_amount) {
        money_production += change_amount;
        game.update_manager.onMoneyProductionRaised(this, change_amount);
    }

    //Teräs
    private Integer steel = 0;
    private Integer steel_production = 0;
    public Integer getSteel() {return steel;}
    public boolean changeSteel(Integer change_amount) {
        if (steel + change_amount < 0) {
            return false;
        }
        steel += change_amount;
        return true;
    }
    public void takeSteel(Integer amount) {
        steel -= amount;
        if (steel < 0) {
            steel = 0;
        }
    }

    public Integer getSteelProduction() {return steel_production;}
    public boolean changeSteelProduction(Integer change_amount) {
        if (steel_production + change_amount < 0) {
            return false;
        }
        steel_production += change_amount;
        game.update_manager.onSteelProductionRaised(this, change_amount);
        return true;
    }

    //Titaani
    private Integer titanium = 0;
    private Integer titanium_production = 0;
    public Integer getTitanium() {return titanium;}
    public boolean changeTitanium(Integer change_amount) {
        if (titanium + change_amount < 0) {
            return false;
        }
        titanium += change_amount;
        return true;
    }
    public void takeTitanium(Integer amount) {
        titanium -= amount;
        if (titanium < 0) {
            titanium = 0;
        }
    }

    public Integer getTitaniumProduction() {return titanium_production;}
    public boolean changeTitaniumProduction(Integer change_amount) {
        if (titanium_production + change_amount < 0) {
            return false;
        }
        titanium_production += change_amount;
        game.update_manager.onTitaniumProductionRaised(this, change_amount);
        return true;
    }

    //Kasvit
    private Integer plants = 0;
    private Integer plants_production = 0;
    public Integer getPlants() {return plants;}
    public boolean changePlants(Integer change_amount) {
        if (plants + change_amount < 0) {
            return false;
        }
        plants += change_amount;
        return true;
    }
    public void takePlants(Integer amount) {
        plants -= amount;
        if (plants < 0) {
            plants = 0;
        }
    }

    public Integer getPlantsProduction() {return plants_production;}
    public boolean changePlantsProduction(Integer change_amount) {
        if (plants_production + change_amount < 0) {
            return false;
        }
        plants_production += change_amount;
        game.update_manager.onPlantsProductionRaised(this, change_amount);
        return true;
    }
    public void takePlantsProduction(Integer amount) {
        plants_production -= amount;
        if (plants_production < 0) {
            plants_production = 0;
        }
    }

    //Energia
    private Integer energy = 0;
    private Integer energy_production = 0;
    public Integer getEnergy() {return energy;}
    public boolean changeEnergy(Integer change_amount) {
        if (energy + change_amount < 0) {
            return false;
        }
        energy += change_amount;
        return true;
    }

    public Integer getEnergyProduction() {return energy_production;}
    public boolean changeEnergyProduction(Integer change_amount) {
        if (energy_production + change_amount < 0) {
            return false;
        }
        energy_production += change_amount;
        game.update_manager.onEnergyProductionRaised(this, change_amount);
        return true;
    }
    public void takeEnergyProduction(Integer amount) {
        energy_production -= amount;
        if (energy_production < 0) {
            energy_production = 0;
        }
    }

    //Lämpö
    private Integer heat = 0;
    private Integer heat_production = 0;
    public Integer getHeat() {return heat;}
    public boolean changeHeat(Integer change_amount) {
        if (heat + change_amount < 0) {
            return false;
        }
        heat += change_amount;
        return true;
    }
    public Integer getHeatProduction() {return heat_production;}
    public boolean changeHeatProduction(Integer change_amount) {
        if (heat_production + change_amount < 0) {
            return false;
        }
        heat_production += change_amount;
        game.update_manager.onHeatProductionRaised(this, change_amount);
        return true;
    }
    public void takeHeatProduction(Integer amount) {
        heat_production -= amount;
        if (heat_production < 0) {
            heat_production = 0;
        }
    }

    //TR
    private Integer terraforming_rating = 20;
    public Integer getTerraformingRating() {return terraforming_rating;}
    public void changeTerraformingRating(Integer change_amount) {
        terraforming_rating += change_amount;
        raised_tr_this_generation = true;
    }

    //Voittopisteet
    private Integer victory_points = 0;
    public Integer getVictoryPoints() {return victory_points;}
    public void changeVictoryPoints(Integer change_amount) {victory_points += change_amount;}

    /***********************************************************************************
     Tagit
     ************************************************************************************/

    private Integer building_tags = 0;
    public Integer getBuildingTags() {return building_tags;}
    public void addBuildingTag() {
        if (building_tags == 0) {
            addUniqueTag();
        }
        building_tags++;
    }

    private Integer space_tags = 0;
    public Integer getSpaceTags() {return space_tags;}
    public void addSpaceTag() {
        if (space_tags == 0) {
            addUniqueTag();
        }
        space_tags++;
    }

    private Integer science_tags = 0;
    public Integer getScienceTags() {return science_tags;}
    public void addScienceTag() {
        if (science_tags == 0) {
            addUniqueTag();
        }
        science_tags++;
    }

    private Integer plant_tags = 0;
    public Integer getPlantTags() {return plant_tags;}
    public void addPlantTag() {
        if (plant_tags == 0) {
            addUniqueTag();
        }
        plant_tags++;
    }

    private Integer microbe_tags = 0;
    public Integer getMicrobeTags() {return  microbe_tags;}
    public void addMicrobeTag() {
        if (microbe_tags == 0) {
            addUniqueTag();
        }
        microbe_tags++;
    }

    private Integer animal_tags = 0;
    public Integer getAnimalTags() {return animal_tags;}
    public void addAnimalTag() {
        if (animal_tags == 0) {
            addUniqueTag();
        }
        animal_tags++;
    }

    private Integer energy_tags = 0;
    public Integer getEnergyTags() {return energy_tags;}
    public void addEnergyTag() {
        if (energy_tags == 0) {
            addUniqueTag();
        }
        energy_tags++;
    }

    private Integer jovian_tags = 0;
    public Integer getJovianTags() {return jovian_tags;}
    public void addJovianTag() {
        if (jovian_tags == 0) {
            addUniqueTag();
        }
        jovian_tags++;
    }

    private Integer earth_tags = 0;
    public Integer getEarthTags() {return earth_tags;}
    public void addEarthTag() {
        if (earth_tags == 0) {
            addUniqueTag();
        }
        earth_tags++;
    }

    private Integer city_tags = 0;
    public Integer getCityTags() {return city_tags;}
    public void addCityTag() {
        if (city_tags == 0) {
            addUniqueTag();
        }
        city_tags++;
    }

    private Integer event_tags = 0;
    public Integer getEventTags() {return event_tags;}
    public void addEventTag() {
        event_tags++;
    }

    private Integer venus_tags = 0;
    public Integer getVenusTags() {return venus_tags;}
    public void addVenusTag() {
        if (venus_tags == 0) {
            addUniqueTag();
        }
        venus_tags++;
    }

    private Integer joker_tags = 0;
    public Integer getJokerTags() {return joker_tags;}
    public void addJokerTag() {
        if (joker_tags == 0) {
            addUniqueTag();
        }
        joker_tags++;
    }

    private Integer null_tags = 0;
    public Integer getNullTags() {return null_tags;}
    public void addNullTag() {null_tags++;}

    private Integer unique_tags = 0;
    public Integer getUniqueTags() {return unique_tags;}
    private void addUniqueTag() {
        unique_tags++;
        game.update_manager.onNewUniqueTag(this);
    }


    /***********************************************************************************
    Passiiviset vaikutukset
    ************************************************************************************/
    private Integer base_tr_requirement_discount = 0;
    public Integer getBaseTrRequirementDiscount() {return base_tr_requirement_discount;}
    public void changeBaseTrRequirementDiscount(Integer change_amount) {base_tr_requirement_discount += change_amount;}

    private Integer card_buy_cost_modifier = 0;
    public Integer getCardBuyCostModifier() {return card_buy_cost_modifier;}
    public void changeCardBuyCostModifier(Integer change_amount) {card_buy_cost_modifier += change_amount;}

    private Integer earth_tag_discount = 0;
    public Integer getEarthTagDiscount() {return earth_tag_discount;}
    public void changeEarthTagDiscount(Integer change_amount) {earth_tag_discount += change_amount;}

    private Integer building_tag_discount = 0;
    public Integer getBuildingTagDiscount() {return building_tag_discount;}
    public void changeBuildingTagDiscount(Integer change_amount) {building_tag_discount += change_amount;}

    private Integer science_tag_discount = 0;
    public Integer getScienceTagDiscount() {return science_tag_discount;}
    public void changeScienceTagDiscount(Integer change_amount) {science_tag_discount += change_amount;}

    private Integer energy_tag_discount = 0;
    public Integer getEnergyTagDiscount() {return energy_tag_discount;}
    public void changeEnergyTagDiscount(Integer change_amount) {energy_tag_discount += change_amount;}

    private Integer space_tag_discount = 0;
    public Integer getSpaceTagDiscount() {return space_tag_discount;}
    public void changeSpaceTagDiscount(Integer change_amount) {space_tag_discount += change_amount;}

    private Integer venus_tag_discount = 0;
    public Integer getVenusTagDiscount() {return venus_tag_discount;}
    public void changeVenusTagDiscount(Integer change_amount) {venus_tag_discount += change_amount;}

    private Integer titanium_value_modifier = 0;
    public Integer getTitaniumValueModifier() {return titanium_value_modifier;}
    public void changeTitaniumValueModifier(Integer change_amount) {titanium_value_modifier += change_amount;}

    private Integer steel_value_modifier = 0;
    public Integer getSteelValueModifier() {return steel_value_modifier;}
    public void changeSteelValueModifier(Integer change_amount) {steel_value_modifier += change_amount;}

    private Integer ocean_adjacency_bonus_modifier = 0;
    public Integer getOceanAdjacencyBonusModifier() {return ocean_adjacency_bonus_modifier;}
    public void changeOceanAdjacencyBonusModifier(Integer change_amount) {ocean_adjacency_bonus_modifier += change_amount;}

    private Integer venus_tr_requirement_discount = 0;
    public Integer getVenusTrRequirementDiscount() {return venus_tr_requirement_discount;}
    public void changeVenusTrRequirementDiscount(Integer change_amount) {venus_tr_requirement_discount += change_amount;}

    private Integer greenery_plant_cost_modifier = 0;
    public Integer getGreeneryPlantCostModifier() {return greenery_plant_cost_modifier;}
    public void changeGreeneryPlantCostModifier(Integer change_amount) {greenery_plant_cost_modifier += change_amount;}

    private Boolean can_raise_colony_track = false;
    public Boolean getCanRaiseColonyTrack() {return can_raise_colony_track;}
    public void setCanRaiseColonyTrack(Boolean value) {can_raise_colony_track = value;}

    private Boolean organics_protected = false;
    public Boolean getOrganicsProtected() {return organics_protected;}
    public void setOrganicsProtected(Boolean value) {organics_protected = value;}

    private Integer trade_discount = 0;
    public Integer getTradeDiscount() {return trade_discount;}
    public void changeTradeDiscount(Integer change_amount) {trade_discount += change_amount;}

    private Integer card_discount = 0;
    public Integer getCardDiscount() {return card_discount;}
    public void changeCardDiscount(Integer change_amount) {card_discount += change_amount;}

    private Integer bonus_influence = 0;
    public Integer getBonusInfluence() {return bonus_influence;}
    public void changeBonusInfluence(Integer change_amount) {bonus_influence += change_amount;}

    private Boolean heat_is_money = false;
    public Boolean getHeatIsMoney() {return heat_is_money;}
    public void setHeatIsMoney(Boolean value) {heat_is_money = value;}

    private Boolean special_design_effect = false;
    public Boolean getSpecialDesignEffect() {return  special_design_effect;}
    public void setSpecialDesignEffect(Boolean value) {special_design_effect = value;}

    private Boolean raised_tr_this_generation = false;
    public Boolean getRaisedTrThisGeneration() {return raised_tr_this_generation;}
    public void setRaisedTrThisGeneration(Boolean value) {raised_tr_this_generation = value;}

    /***********************************************************************************
     Constructor
     ************************************************************************************/

    public Player(Game super_game, String player_name) {
        game = super_game;
        name = player_name;
    }
}
