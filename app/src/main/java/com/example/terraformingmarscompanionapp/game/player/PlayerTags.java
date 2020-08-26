package com.example.terraformingmarscompanionapp.game.player;

import com.example.terraformingmarscompanionapp.game.GameController;

import java.io.Serializable;

/**
 * A dataclass to host the list of tags associated with a player. Associated with {@link Player} via
 * composition.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class PlayerTags implements Serializable {

    private Integer building_tags = 0;
    private Integer space_tags = 0;
    private Integer science_tags = 0;
    private Integer plant_tags = 0;
    private Integer microbe_tags = 0;
    private Integer animal_tags = 0;
    private Integer energy_tags = 0;
    private Integer jovian_tags = 0;
    private Integer earth_tags = 0;
    private Integer city_tags = 0;
    private Integer event_tags = 0;
    private Integer venus_tags = 0;
    private Integer joker_tags = 0;
    private Integer null_tags = 0;
    private Integer unique_tags = 0;


    /**
     * @return {@link Integer} the amount of building tags the player has
     */
    public Integer getBuildingTags() {
        return building_tags;
    }

    /**
     * Add a building tag to the player's tags
     */
    public void addBuildingTag() {
        if (building_tags == 0) {
            addUniqueTag();
        }
        building_tags++;
    }


    /**
     * @return {@link Integer} the amount of building tags the player has
     */
    public Integer getSpaceTags() {
        return space_tags;
    }

    /**
     * Add a space tag to the player's tags
     */
    public void addSpaceTag() {
        if (space_tags == 0) {
            addUniqueTag();
        }
        space_tags++;
    }


    /**
     * @return {@link Integer} the amount of science tags the player has
     */
    public Integer getScienceTags() {
        return science_tags;
    }

    /**
     * Add a science tag to the player's tags
     */
    public void addScienceTag() {
        if (science_tags == 0) {
            addUniqueTag();
        }
        science_tags++;
    }


    /**
     * @return {@link Integer} the amount of plant tags the player has
     */
    public Integer getPlantTags() {
        return plant_tags;
    }

    /**
     * Add a plant tag to the player's tags
     */
    public void addPlantTag() {
        if (plant_tags == 0) {
            addUniqueTag();
        }
        plant_tags++;
    }


    /**
     * @return {@link Integer} the amount of microbe tags the player has
     */
    public Integer getMicrobeTags() {
        return  microbe_tags;
    }

    /**
     * Add a microbe tag to the player's tags
     */
    public void addMicrobeTag() {
        if (microbe_tags == 0) {
            addUniqueTag();
        }
        microbe_tags++;
    }


    /**
     * @return {@link Integer} the amount of animal tags the player has
     */
    public Integer getAnimalTags() {
        return animal_tags;
    }

    /**
     * Add an animal tag to the player's tags
     */
    public void addAnimalTag() {
        if (animal_tags == 0) {
            addUniqueTag();
        }
        animal_tags++;
    }


    /**
     * @return {@link Integer} the amount of energy tags the player has
     */
    public Integer getEnergyTags() {
        return energy_tags;
    }

    /**
     * Add an energy tag to the player's tags
     */
    public void addEnergyTag() {
        if (energy_tags == 0) {
            addUniqueTag();
        }
        energy_tags++;
    }


    /**
     * @return {@link Integer} the amount of jovian tags the player has
     */
    public Integer getJovianTags() {
        return jovian_tags;
    }

    /**
     * Add a jovian tag to the player's tags
     */
    public void addJovianTag() {
        if (jovian_tags == 0) {
            addUniqueTag();
        }
        jovian_tags++;
    }


    /**
     * @return {@link Integer} the amount of earth tags the player has
     */
    public Integer getEarthTags() {
        return earth_tags;
    }

    /**
     * Add an earth tag to the player's tags
     */
    public void addEarthTag() {
        if (earth_tags == 0) {
            addUniqueTag();
        }
        earth_tags++;
    }


    /**
     * @return {@link Integer} the amount of city tags the player has
     */
    public Integer getCityTags() {
        return city_tags;
    }

    /**
     * Add a city tag to the player's tags
     */
    public void addCityTag() {
        if (city_tags == 0) {
            addUniqueTag();
        }
        city_tags++;
    }


    /**
     * @return {@link Integer} the amount of event tags the player has
     */
    public Integer getEventTags() {
        return event_tags;
    }

    /**
     * Add an event tag to the player's tags
     */
    public void addEventTag() {
        event_tags++;
    }


    /**
     * @return {@link Integer} the amount of venus tags the player has
     */
    public Integer getVenusTags() {
        return venus_tags;
    }

    /**
     * Add a venus tag to the player's tags
     */
    public void addVenusTag() {
        if (venus_tags == 0) {
            addUniqueTag();
        }
        venus_tags++;
    }


    /**
     * @return {@link Integer} the amount of joker tags the player has
     */
    public Integer getJokerTags() {
        return joker_tags;
    }

    /**
     * Add a joker tag to the player's tags
     */
    public void addJokerTag() {
        if (joker_tags == 0) {
            addUniqueTag();
        }
        joker_tags++;
    }


    /**
     * @return {@link Integer} the amount of cards without tags the player has
     */
    public Integer getNullTags() {
        return null_tags;
    }

    /**
     * Add a tagless card to the player
     */
    public void addNullTag() {
        null_tags++;
    }


    /**
     * @return {@link Integer} the amount of different unique tags a player has
     */
    public Integer getUniqueTags() {
        return unique_tags;
    }

    /**
     * Called from within the class whenever a new unique tag is added to the player
     */
    private void addUniqueTag() {
        unique_tags++;
        GameController.getGame().update_manager.onNewUniqueTag(GameController.getCurrentPlayer());
    }
}
