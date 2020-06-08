package com.example.terraformingmarscompanionapp.game.player;

import com.example.terraformingmarscompanionapp.game.GameController;

/**
 * A dataclass to host the resources and productions associated with a player. Associated with
 * {@link Player} via composition.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class PlayerResources {

    // Raw resources
    private Integer money;
    private Integer steel;
    private Integer titanium;
    private Integer plants;
    private Integer energy;
    private Integer heat;

    // Productions
    private Integer money_production;
    private Integer steel_production;
    private Integer titanium_production;
    private Integer plants_production;
    private Integer energy_production;
    private Integer heat_production;

    private Integer terraforming_rating;

    public void addProduction() {

        money += money_production;
        money += terraforming_rating;

        steel += steel_production;

        titanium += titanium_production;

        plants += plants_production;

        heat += energy;
        heat += energy_production;

        energy = energy_production;
    }

    // Getters and setters

    // Money
    /**
     * @return {@link Integer} the amount of money a {@link Player} has
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * @param amount {@link Integer} the amount of money a {@link Player} has
     */
    public void setMoney(Integer amount) {
        money = amount > 0 ? amount : 0;
    }

    /**
     * @return {@link Integer} the amount of money production a {@link Player} has
     */
    public Integer getMoneyProduction() {
        return money_production;
    }

    /**
     * @param amount {@link Integer} the amount of money production a {@link Player} has
     */
    public void setMoneyProduction(Integer amount) {
        money_production = amount > -5 ? amount : -5;
    }


    // Steel
    /**
     * @return {@link Integer} the amount of steel a {@link Player} has
     */
    public Integer getSteel() {
        return steel;
    }

    /**
     * @param amount {@link Integer} the amount of steel a {@link Player} has
     */
    public void setSteel(Integer amount) {
        steel = amount > 0 ? amount : 0;
    }

    /**
     * @return {@link Integer} the amount of steel production a {@link Player} has
     */
    public Integer getSteelProduction() {
        return steel_production;
    }

    /**
     * @param amount {@link Integer} the amount of steel production a {@link Player} has
     */
    public void setSteelProduction(Integer amount) {
        steel_production = amount > 0 ? amount : 0;
    }


    // Titanium
    /**
     * @return {@link Integer} the amount of titanium a {@link Player} has
     */
    public Integer getTitanium() {
        return titanium;
    }

    /**
     * @param amount {@link Integer} the amount of titanium a {@link Player} has
     */
    public void setTitanium(Integer amount) {
        titanium = amount > 0 ? amount : 0;
    }

    /**
     * @return {@link Integer} the amount of titanium production a {@link Player} has
     */
    public Integer getTitaniumProduction() {
        return titanium_production;
    }

    /**
     * @param amount {@link Integer} the amount of titanium production a {@link Player} has
     */
    public void setTitaniumProduction(Integer amount) {
        titanium_production = amount > 0 ? amount : 0;
    }


    // Plants
    /**
     * @return {@link Integer} the amount of plants a {@link Player} has
     */
    public Integer getPlants() {
        return plants;
    }

    /**
     * @param amount {@link Integer} the amount of plants a {@link Player} has
     */
    public void setPlants(Integer amount) {
        plants = amount > 0 ? amount : 0;
    }

    /**
     * @return {@link Integer} the amount of plants production a {@link Player} has
     */
    public Integer getPlantsProduction() {
        return plants_production;
    }

    /**
     * @param amount {@link Integer} the amount of plants productopm a {@link Player} has
     */
    public void setPlantsProduction(Integer amount) {
        plants_production = amount > 0 ? amount : 0;
    }


    // Energy
    /**
     * @return {@link Integer} the amount of energy a {@link Player} has
     */
    public Integer getEnergy() {
        return energy;
    }

    /**
     * @param amount {@link Integer} the amount of energy a {@link Player} has
     */
    public void setEnergy(Integer amount) {
        energy = amount > 0 ? amount : 0;
    }

    /**
     * @return {@link Integer} the amount of energy production a {@link Player} has
     */
    public Integer getEnergyProduction() {
        return energy_production;
    }

    /**
     * @param amount {@link Integer} the amount of energy production a {@link Player} has
     */
    public void setEnergyProduction(Integer amount) {
        energy_production = amount > 0 ? amount : 0;
    }


    // Heat
    /**
     * @return {@link Integer} the amount of heat a {@link Player} has
     */
    public Integer getHeat() {
        return heat;
    }

    /**
     * @param amount {@link Integer} the amount of heat a {@link Player} has
     */
    public void setHeat(Integer amount) {
        heat = amount > 0 ? amount : 0;
    }

    /**
     * @return {@link Integer} the amount of heat production a {@link Player} has
     */
    public Integer getHeatProduction() {
        return heat_production;
    }

    /**
     * @param amount {@link Integer} the amount of heat production a {@link Player} has
     */
    public void setHeatProduction(Integer amount) {
        heat_production = amount > 0 ? amount : 0;
    }


    // Terraforming rating
    /**
     * @return {@link Integer} the amount of terraforming rating a {@link Player} has
     */
    public Integer getTerraformingRating() {
        return terraforming_rating;
    }

    /**
     * @param amount {@link Integer} the amount of terraforming rating a {@link Player} has
     */
    public void setTerraformingRating(Integer amount) {
        terraforming_rating = amount > 0 ? amount : 0; // Should never happen, but just in case
    }
}
