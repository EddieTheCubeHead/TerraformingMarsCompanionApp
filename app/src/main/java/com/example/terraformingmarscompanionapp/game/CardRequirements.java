package com.example.terraformingmarscompanionapp.game;

import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.cardClasses.Milestone;

/**
 * A dataclass for housing the requirements for playing a card
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class CardRequirements {
    private Integer min_oceans;
    private Integer min_plants;
    private Integer min_energy_production;
    private Integer min_oxygen;
    private Integer min_science_tags;
    private Integer min_jovian_tags;
    private Integer min_steel_production;
    private Integer min_global_cities;
    private Integer min_personal_cities;
    private Integer min_venus_tr;
    private Integer min_temperature;
    private Integer min_plant_production;
    private Integer min_plant_tags;
    private Integer min_microbe_tags;
    private Integer min_animal_tags;
    private Integer min_earth_tags;
    private Integer min_energy_tags;
    private Integer min_floaters;
    private Integer min_personal_colonies;
    private Integer min_titanium_production;
    private Integer min_personal_greeneries;
    private Integer min_heat_production;
    private Integer min_tr;
    private Integer min_venus_tags;
    private Integer min_heat;

    // For milestones
    private Integer min_highest_production;
    private Integer min_organic_tags;
    private Integer min_cards_on_table;
    private Integer min_cards_in_hand;
    private Integer min_events_played;
    private Integer min_money_production;
    private Integer min_unique_tags;
    private Integer min_requirement_cards;
    private Integer min_polar_tiles;
    private Integer min_building_tags;

    // Special case for Ecoline
    private Boolean plants_for_greenery = false;

    private Integer max_temperature;
    private Integer max_oceans;
    private Integer max_oxygen;
    private Integer max_personal_colonies;
    private Integer max_venus_tr;
    private Integer max_milestones_claimed;
    private Integer max_awards_claimed;

    // Minimum oceans
    /**
     * @return {@link Integer} minimum number of oceans required to play the card
     */
    Integer getMinOceans() {
        return min_oceans;
    }

    /**
     * @param value {@link Integer} the minimum number of oceans required to play the card
     */
    public void setMinOceans(Integer value) {
        min_oceans = value;
    }


    // Minimum plant tags
    /**
     * @return {@link Integer} the minimum number of owned plant resources required to play the card
     */
    Integer getMinPlants() {
        return min_plants;
    }

    /**
     * @param value {@link Integer} the minimum number of owned plant resources required to play the card
     */
    public void setMinPlants(Integer value) {
        min_plants = value;
    }


    // Minimum energy production
    /**
     * @return {@link Integer} the minimum number of owned energy resources required to play the card
     */
    Integer getMinEnergyProduction() {
        return min_energy_production;
    }

    /**
     * @param value {@link Integer} the minimum number of energy resources required to play the card
     */
    public void setMinEnergyProduction(Integer value) {
        min_energy_production = value;
    }


    // Minimum global oxygen
    /**
     * @return {@link Integer} the minimum global oxygen level required to play the card
     */
    Integer getMinOxygen() {
        return min_oxygen;
    }

    /**
     * @param value {@link Integer} the minimum global oxygen level required to play the card
     */
    public void setMinOxygen(Integer value) {
        min_oxygen = value;
    }


    // Minimum science tags
    /**
     * @return {@link Integer} the minimum number of owned science tags required to play the card
     */
    Integer getMinScienceTags() {
        return min_science_tags;
    }

    /**
     * @param value {@link Integer} the minimum number of owned science tags required to play the card
     */
    public void setMinScienceTags(Integer value) {
        min_science_tags = value;
    }


    // Minimum jovian tags
    /**
     * @return {@link Integer} the minimum number of owned jovian tags required to play the card
     */
    Integer getMinJovianTags() {
        return min_jovian_tags;
    }

    /**
     * @param value {@link Integer} the minimum number of owned jovian tags required to play the card
     */
    public void setMinJovianTags(Integer value) {
        min_jovian_tags = value;
    }


    // Minimum steel production
    /**
     * @return {@link Integer} the minimum steel production required to play the card
     */
    Integer getMinSteelProduction() {
        return min_steel_production;
    }

    /**
     * @param value {@link Integer} the minimum steel production required to play the card
     */
    public void setMinSteelProduction(Integer value) {
        min_steel_production = value;
    }


    // Minimum owned cities
    /**
     * @return {@link Integer} the minimum number of all cities in play required to play the card
     */
    Integer getMinGlobalCities() {
        return min_global_cities;
    }

    /**
     * @param value {@link Integer} the minimum number of all cities in play required to play the card
     */
    public void setMinGlobalCities(Integer value) {
        min_global_cities = value;
    }


    // Minimum owned cities
    /**
     * @return {@link Integer} the minimum owned cities required to play the card
     */
    Integer getMinPersonalCities() {
        return min_personal_cities;
    }

    /**
     * @param value {@link Integer} the minimum owned cities required to play the card
     */
    public void setMinPersonalCities(Integer value) {
        min_personal_cities = value;
    }


    // Minimum global venus terraforming rating
    /**
     * @return {@link Integer} the minimum venus terraforming level reqruired to play the card
     */
    Integer getMinVenusTr() {
        return min_venus_tr;
    }

    /**
     * @param value {@link Integer} the minimum venus terraforming level required to play the card
     */
    public void setMinVenusTr(Integer value) {min_venus_tr = value;}


    // Minimum global temperature
    /**
     * @return {@link Integer} the minimum global temperature level required to play the card
     */
    Integer getMinTemperature() {
        return min_temperature;
    }

    /**
     * @param value {@link Integer} the minimum global temperature level required to play the card
     */
    public void setMinTemperature(Integer value) {
        min_temperature = value;
    }


    // Minimum plant production
    /**
     * @return {@link Integer} the minimum plant production required to play the card
     */
    Integer getMinPlantProduction() {
        return min_plant_production;
    }

    /**
     * @param value {@link Integer} the minimum plant production required to play the card
     */
    public void setMinPlantProduction(Integer value) {
        min_plant_production = value;
    }


    // Minimum plant tags
    /**
     * @return {@link Integer} the minimum owned plant tags required to play the card
     */
    public Integer getMinPlantTags() {
        return min_plant_tags;
    }

    /**
     * @param value {@link Integer} the minimum owned plant tags required to play the card
     */
    public void setMinPlantTags(Integer value) {
        min_plant_tags = value;
    }


    // Minimum microbe tags
    /**
     * @return {@link Integer} the minimum owned microbe tags required to play the card
     */
    Integer getMinMicrobeTags() {
        return min_microbe_tags;
    }

    /**
     * @param value {@link Integer} the minimum owned microbe tags required to play the card
     */
    public void setMinMicrobeTags(Integer value) {
        min_microbe_tags = value;
    }


    // Minimum animal tags
    /**
     * @return {@link Integer} the minimum owned animal tags required to play the card
     */
    Integer getMinAnimalTags() {
        return min_animal_tags;
    }

    /**
     * @param value {@link Integer} the minimum owned animal tags required to play the card
     */
    public void setMinAnimalTags(Integer value) {
        min_animal_tags = value;
    }


    // Minimum earth tags
    /**
     * @return {@link Integer} the minimum owned earth tags required to play the card
     */
    Integer getMinEarthTags() {
        return min_earth_tags;
    }

    /**
     * @param value {@link Integer} the minimum owned earth tags required to play the card
     */
    public void setMinEarthTags(Integer value) {
        min_earth_tags = value;
    }


    // Minimum energy tags
    /**
     * @return {@link Integer} the minimum owned energy tags required to play the card
     */
    Integer getMinEnergyTags() {
        return min_energy_tags;
    }

    /**
     * @param value {@link Integer} the minimum owned energy tags required to play the card
     */
    public void setMinEnergyTags(Integer value) {
        min_energy_tags = value;
    }


    // Minimum owned floaters
    /**
     * @return {@link Integer} the minimum amount of owned floaters required to play the card
     */
    Integer getMinFloaters() {
        return min_floaters;
    }

    /**
     * @param value {@link Integer} the minimum amount of owned floater required to play the card
     */
    public void setMinFloaters(Integer value) {min_floaters = value;}


    // Minimum owned colonies
    /**
     * @return {@link Integer} the minimum owned colonies required to play the card
     */
    Integer getMinPersonalColonies() {
        return min_personal_colonies;
    }

    /**
     * @param value {@link Integer} the minimum owned colonies required to play the card
     */
    public void setMinPersonalColonies(Integer value) {
        min_personal_colonies = value;
    }


    // Minimum titanium production
    /**
     * @return {@link Integer} the minimum titanium production requred to play the card
     */
    Integer getMinTitaniumProduction() {
        return min_titanium_production;
    }

    /**
     * @param value {@link Integer} the minimum titanium production required to play the card
     */
    public void setMinTitaniumProduction(Integer value) {
        min_titanium_production = value;
    }


    // Minimum owned greeneries
    /**
     * @return {@link Integer} the minimum owned greeneries required to play the card
     */
    Integer getMinPersonalGreeneries() {
        return min_personal_greeneries;
    }

    /**
     * @param value {@link Integer} the minimum owned greeneries required to play the card
     */
    public void setMinPersonalGreeneries(Integer value) {
        min_personal_greeneries = value;
    }


    // Minimum heat production
    /**
     * @return {@link Integer} the minimum heat production required to play the card
     */
    Integer getMinHeatProduction() {
        return min_heat_production;
    }

    /**
     * @param value {@link Integer} the minimum heat production required to play the card
     */
    public void setMinHeatProduction(Integer value) {
        min_heat_production = value;
    }


    // Minimum tr
    /**
     * @return {@link Integer} the minimum terraforming rating required to play the card
     */
    Integer getMinTr() {
        return min_tr;
    }

    /**
     * @param value {@link Integer} the minimum terraforming rating required to play the card
     */
    public void setMinTr(Integer value) {
        min_tr = value;
    }


    // Minimum venus tags
    /**
     * @return {@link Integer} the minimum owned venus tags required to play the card
     */
    Integer getMinVenusTags() {
        return min_venus_tags;
    }

    /**
     * @param value {@link Integer} the minimum owned venus tags required to play the card
     */
    public void setMinVenusTags(Integer value) {
        min_venus_tags = value;
    }


    // Minimum heat resources
    /**
     * @return {@link Integer} the minimum owned heat resources required to play the card
     */
    Integer getMinHeat() {
        return min_heat;
    }

    /**
     * @param value {@link Integer} the minimum owned heat resources required to play the card
     */
    public void setMinHeat(Integer value) {
        min_heat = value;
    }


    // Minimum money production
    /**
     * @return {@link Integer} the minimum amount of money production required to play the card
     */
    Integer getMinMoneyProduction() {
        return min_money_production;
    }

    /**
     * @param value {@link Integer} the minimum amount of money production required to play the card
     */
    public void setMinMoneyProduction(Integer value) {
        min_money_production = value;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       MILESTONES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Minimum cards in hand -- Planner
    /**
     * @return {@link Integer} the minimum number of cards player needs to have in hand to play the card
     */
    Integer getMinCardsInHand() {
        return min_cards_in_hand;
    }

    /**
     * @param value {@link Integer} the minimum number of cards player needs to have in hand to play the card
     */
    public void setMinCardsInHand(Integer value) {
        min_cards_in_hand = value;
    }


    // Minimum highest production -- Specialist
    /**
     * @return {@link Integer} the minimum value for the highest production required to play the card
     */
    Integer getMinHighestProduction() {
        return min_highest_production;
    }

    /**
     * @param value {@link Integer} the minimum value for the highest production required to play the card
     */
    public void setMinHighestProduction(Integer value) {
        min_highest_production = value;
    }


    // Minimum organic tags -- Ecologist
    /**
     * @return {@link Integer} the minimum owned organic tags (animal, microbe, plant) required to play the card
     */
    Integer getMinOrganicTags() {
        return min_organic_tags;
    }

    /**
     * @param value {@link Integer} the minimum owned organic tags (animal, microbe, plant) required to play the card
     */
    public void setMinOrganicTags(Integer value) {
        min_organic_tags = value;
    }


    // Minimum green and blue cards -- Tycoon
    /**
     * @return {@link Integer} the minimum owned green and blue cards required to play the card
     */
    Integer getMinCardsOnTable() {
        return min_cards_on_table;
    }

    /**
     * @param value {@link Integer} the minimum owned green and blue cards required to play the card
     */
    public void setMinCardsOnTable(Integer value) {
        min_cards_on_table = value;
    }


    // Minimum events played -- Legend
    /**
     * @return {@link Integer} the minimum number of event cards played by the player required to play the card
     */
    Integer getMinEventsPlayed() {
        return min_events_played;
    }

    /**
     * @param value {@link Integer} the minimum number of event cards played by the player required to play the card
     */
    public void setMinEventsPlayed(Integer value) {
        min_events_played = value;
    }


    // Minimum unique tags -- Diversifier
    /**
     * @return {@link Integer} the minimum amount of unique owned tags required to play the card
     */
    Integer getMinUniqueTags() {
        return min_unique_tags;
    }

    /**
     * @param value {@link Integer} the minimum amount of unique owned tags required to play the card
     */
    public void setMinUniqueTags(Integer value) {
        min_unique_tags = value;
    }


    // Minimum cards with requirements -- Tactician
    /**
     * @return {@link Integer} the minimum amount of cards with global requirements (shown on card) required to play the card
     */
    Integer getMinRequirementCards() {
        return min_requirement_cards;
    }

    /**
     * @param value {@link Integer} the minimum amount of cards with global requirements (shown on card) required to play the card
     */
    public void setMinRequirementCards(Integer value) {
        min_requirement_cards = value;
    }


    // Minimum tiles in polar zone -- Polar explorer
    /**
     * @return {@link Integer} the minimum amount of tiles in the polar zone (hellas map) required to play the card
     */
    Integer getMinPolarTiles() {
        return min_polar_tiles;
    }

    /**
     * @param value {@link Integer} the minimum amount of tiles in the polar zone (hellas map) required to play the card
     */
    public void setMinPolarTiles(Integer value) {
        min_polar_tiles = value;
    }


    // Minimum building tags -- Builder
    /**
     * @return {@link Integer} the minimum amount of owned building tags required to play the card
     */
    Integer getMinBuildingTags() {
        return min_building_tags;
    }

    /**
     * @param value {@link Integer} the minimum amount of owned building tags required to play the card
     */
    public void setMinBuildingTags(Integer value) {
        min_building_tags = value;
    }


    // Building a greenery
    /**
     * @return {@link Boolean} whether the player has to have enough resources to place a greenery
     */
    Boolean getPlantsForGreenery() {
        return plants_for_greenery;
    }

    /**
     * @param value {@link Boolean} whether the player has to have anough resources to place a greenery
     */
    public void setPlantsForGreenery(Boolean value) {
        plants_for_greenery = value;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      MAX VALUES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Maximum global temperature
    /**
     * @return {@link Integer} the maximum global temperature level allowed to play the card
     */
    Integer getMaxTemperature() {
        return max_temperature;
    }

    /**
     * @param value {@link Integer} the maximum global temperature level allowed to play the card
     */
    public void setMaxTemperature(Integer value) {
        max_temperature = value;
    }


    // Maximum oceans
    /**
     * @return {@link Integer} the maximum number of oceans allowed to play the card
     */
    Integer getMaxOceans() {
        return max_oceans;
    }

    /**
     * @param value {@link Integer} the maximum number of oceans allowed to play the card
     */
    public void setMaxOceans(Integer value) {
        max_oceans = value;
    }


    // Maximum global oxygen
    /**
     * @return {@link Integer} the maximum global oxygen level allowed to play the card
     */
    Integer getMaxOxygen() {
        return max_oxygen;
    }

    /**
     * @param value {@link Integer} the maximum global oxygen level allowed to play the card
     */
    public void setMaxOxygen(Integer value) {
        max_oxygen = value;
    }


    // Maximum owned colonies
    /**
     * @return {@link Integer} the maximum number of owned colonies allowed to play the card
     */
    Integer getMaxPersonalColonies() {
        return max_personal_colonies;
    }

    /**
     * @param value {@link Integer} the maximum number of owned colonies allowed to play the card
     */
    public void setMaxPersonalColonies(Integer value) {
        max_personal_colonies = value;
    }


    // Maximum global venus terraforming rating
    /**
     * @return {@link Integer} the maximum global venus terraforming level allowed to play the card
     */
    Integer getMaxVenusTr() {
        return max_venus_tr;
    }

    /**
     * @param value {@link Integer} the maximum global venus terraforming level allowed to play the card
     */
    public void setMaxVenusTr(Integer value) {
        max_venus_tr = value;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                  MILESTONES AND AWARDS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Maximum milestones
    /**
     * Used with {@link Milestone}
     *
     * @return {@link Integer} the maximum amount of milestones claimed in the game allowed to play the card
     */
    Integer getMaxMilestonesClaimed() {
        return max_milestones_claimed;
    }

    /**
     * Used with {@link Milestone}
     *
     * @param value {@link Integer} the maximum amount of milestones claimed in the game allowed to play the card
     */
    public void setMaxMilestonesClaimed(Integer value) {
        max_milestones_claimed = value;
    }


    // Maximum awards
    /**
     * Used with {@link Award}
     *
     * @return {@link Integer} the maximum amount of awards claimed in the game allowed to play the card
     */
    Integer getMaxAwardsClaimed() {
        return max_awards_claimed;
    }

    /**
     * Used with {@link Award}
     *
     * @param value {@link Integer} the maximum amount of awards claimed in the game allowed to play the card
     */
    public void setMaxAwardsClaimed(Integer value) {max_awards_claimed = value;}


    /**
     * Should return a drawable for the requirement. As drawing requirements is not currently implemented
     * this is currently just a placeholder.
     *
     * @return {@link Integer} representing the drawable corresponding with the requirements of the card
     */
    public Integer getDrawableRequrement() {
        // TODO cleanup once requirement displaying in UI is ready and I know what it needs to know
        if (min_oceans != null) {
            return 1;
        } else if (min_oxygen != null) {
            return 2;
        } else if (min_science_tags != null) {
            return 3;
        } else if (min_jovian_tags != null) {
            return 4;
        } else if (min_global_cities != null) {
            return 5;
        } else if (min_personal_greeneries != null) {
            return 6;
        } else if (min_personal_cities != null) {
            return 7;
        } else if (min_venus_tr != null) {
            return 8;
        } else if (min_temperature != null) {
            return 9;
        } else if (min_steel_production != null) {
            return 10;
        } else if (min_plant_tags != null) {
            return 11;
        } else if (min_microbe_tags != null) {
            return 12;
        } else if (min_animal_tags != null) {
            return 13;
        } else if (min_earth_tags != null) {
            return 14;
        } else if (min_energy_tags != null) {
            return 15;
        } else if (min_floaters != null) {
            return 16;
        } else if (min_personal_colonies != null) {
            return 17;
        } else if (min_titanium_production != null) {
            return 18;
        } else if (min_tr != null) {
            return 19;
        } else if (min_venus_tags != null) {
            return 20;
        } else if (max_oceans != null) {
            return 21;
        } else if (max_oxygen != null) {
            return 22;
        } else if (max_personal_colonies != null) {
            return 23;
        } else if (max_venus_tr != null) {
            return 24;
        } else if (max_temperature != null) {
            return 25;
        }
        return 0;
    }
}
