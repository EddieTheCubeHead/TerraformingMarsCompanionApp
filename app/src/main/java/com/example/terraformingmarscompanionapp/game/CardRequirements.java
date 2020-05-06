package com.example.terraformingmarscompanionapp.game;

//Dataluokka kortin vaatimusten säilömiseen ja siirtämiseen kompaktisti.
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

    //Milestoneja varten
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

    //Erikoistapaus Ecolinea varten
    private Boolean plants_for_greenery = false;

    private Integer max_temperature;
    private Integer max_oceans;
    private Integer max_oxygen;
    private Integer max_personal_colonies;
    private Integer max_venus_tr;
    private Integer max_milestones_claimed;
    private Integer max_awards_claimed;

    Integer getMinOceans() {return min_oceans;}
    public void setMinOceans(Integer value) {min_oceans = value;}

    Integer getMinPlants() {return min_plants;}
    public void setMinPlants(Integer value) {min_plants = value;}

    Integer getMinEnergyProduction() {return min_energy_production;}
    public void setMinEnergyProduction(Integer value) {min_energy_production = value;}

    Integer getMinOxygen() {return min_oxygen;}
    public void setMinOxygen(Integer value) {min_oxygen = value;}

    Integer getMinScienceTags() {return min_science_tags;}
    public void setMinScienceTags(Integer value) {min_science_tags = value;}

    Integer getMinJovianTags() {return min_jovian_tags;}
    public void setMinJovianTags(Integer value) {min_jovian_tags = value;}

    Integer getMinSteelProduction() {return min_steel_production;}
    public void setMinSteelProduction(Integer value) {min_steel_production = value;}

    Integer getMinGlobalCities() {return min_global_cities;}
    public void setMinGlobalCities(Integer value) {min_global_cities = value;}

    Integer getMinPersonalCities() {return min_personal_cities;}
    public void setMinPersonalCities(Integer value) {min_personal_cities = value;}

    Integer getMinVenusTr() {return min_venus_tr;}
    public void setMinVenusTr(Integer value) {min_venus_tr = value;}

    Integer getMinTemperature() {return min_temperature;}
    public void setMinTemperature(Integer value) {min_temperature = value;}

    Integer getMinPlantProduction() {return min_plant_production;}
    public void setMinPlantProduction(Integer value) {min_plant_production = value;}

    public Integer getMinPlantTags() {return min_plant_tags;}
    public void setMinPlantTags(Integer value) {min_plant_tags = value;}

    Integer getMinMicrobeTags() {return min_microbe_tags;}
    public void setMinMicrobeTags(Integer value) {min_microbe_tags = value;}

    Integer getMinAnimalTags() {return min_animal_tags;}
    public void setMinAnimalTags(Integer value) {min_animal_tags = value;}

    Integer getMinEarthTags() {return min_earth_tags;}
    public void setMinEarthTags(Integer value) {min_earth_tags = value;}

    Integer getMinEnergyTags() {return min_energy_tags;}
    public void setMinEnergyTags(Integer value) {min_energy_tags = value;}

    Integer getMinFloaters() {return min_floaters;}
    public void setMinFloaters(Integer value) {min_floaters = value;}

    Integer getMinPersonalColonies() {return min_personal_colonies;}
    public void setMinPersonalColonies(Integer value) {min_personal_colonies = value;}

    Integer getMinTitaniumProduction() {return min_titanium_production;}
    public void setMinTitaniumProduction(Integer value) {min_titanium_production = value;}

    Integer getMinPersonalGreeneries() {return min_personal_greeneries;}
    public void setMinPersonalGreeneries(Integer value) {min_personal_greeneries = value;}

    Integer getMinHeatProduction() {return min_heat_production;}
    public void setMinHeatProduction(Integer value) {min_heat_production = value;}

    Integer getMinTr() {return min_tr;}
    public void setMinTr(Integer value) {min_tr = value;}

    Integer getMinVenusTags() {return min_venus_tags;}
    public void setMinVenusTags(Integer value) {min_venus_tags = value;}

    Integer getMinHeat() {return min_heat;}
    public void setMinHeat(Integer value) {min_heat = value;}

    Integer getMinHighestProduction() {return min_highest_production;}
    public void setMinHighestProduction(Integer value) {min_highest_production = value;}

    Integer getMinOrganicTags() {return min_organic_tags;}
    public void setMinOrganicTags(Integer value) {min_organic_tags = value;}

    Integer getMinCardsInHand() {return min_cards_in_hand;}
    public void setMinCardsInHand(Integer value) {min_cards_in_hand = value;}

    Integer getMinCardsOnTable() {return min_cards_on_table;}
    public void setMinCardsOnTable(Integer value) {min_cards_on_table = value;}

    Integer getMinEventsPlayed() {return min_events_played;}
    public void setMinEventsPlayed(Integer value) {min_events_played = value;}

    Integer getMinMoneyProduction() {return min_money_production;}
    public void setMinMoneyProduction(Integer value) {min_money_production = value;}

    Integer getMinUniqueTags() {return min_unique_tags;}
    public void setMinUniqueTags(Integer value) {min_unique_tags = value;}

    Integer getMinRequirementCards() {return min_requirement_cards;}
    public void setMinRequirementCards(Integer value) {min_requirement_cards = value;}

    Integer getMinPolarTiles() {return min_polar_tiles;}
    public void setMinPolarTiles(Integer value) {min_polar_tiles = value;}

    Integer getMinBuildingTags() {return min_building_tags;}
    public void setMinBuildingTags(Integer value) {min_building_tags = value;}


    Boolean getPlantsForGreenery() {return plants_for_greenery;}
    public void setPlantsForGreenery(Boolean value) {plants_for_greenery = value;}


    Integer getMaxTemperature() {return max_temperature;}
    public void setMaxTemperature(Integer value) {max_temperature = value;}

    Integer getMaxOceans() {return max_oceans;}
    public void setMaxOceans(Integer value) {max_oceans = value;}

    Integer getMaxOxygen() {return max_oxygen;}
    public void setMaxOxygen(Integer value) {max_oxygen = value;}

    Integer getMaxPersonalColonies() {return max_personal_colonies;}
    public void setMaxPersonalColonies(Integer value) {max_personal_colonies = value;}

    Integer getMaxMilestonesClaimed() {return max_milestones_claimed;}
    public void setMaxMilestonesClaimed(Integer value) {max_milestones_claimed = value;}

    Integer getMaxAwardsClaimed() {return max_awards_claimed;}
    public void setMaxAwardsClaimed(Integer value) {max_awards_claimed = value;}

    Integer getMaxVenusTr() {return max_venus_tr;}
    public void setMaxVenusTr(Integer value) {max_venus_tr = value;}

    public Integer getDrawableRequrement() {
        //TODO siivous tänne jahka tiedetään mitä UI tarvitsee tästä ulos
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
