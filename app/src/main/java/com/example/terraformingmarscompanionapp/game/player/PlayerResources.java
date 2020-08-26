package com.example.terraformingmarscompanionapp.game.player;

import android.util.Log;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.GameResourceType;

import java.io.Serializable;

/**
 * A dataclass to host the resources and productions associated with a player. Associated with
 * {@link Player} via composition.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class PlayerResources implements Serializable {

    // Storing the player for triggering Manutech and UNMI effects
    private Player player;

    // Raw resources
    private Integer money = 0;
    private Integer steel = 0;
    private Integer titanium = 0;
    private Integer plants = 0;
    private Integer energy = 0;
    private Integer heat = 0;

    // Productions
    private Integer money_production = 0;
    private Integer steel_production = 0;
    private Integer titanium_production = 0;
    private Integer plants_production = 0;
    private Integer energy_production = 0;
    private Integer heat_production = 0;

    private Integer terraforming_rating = 20;

    PlayerResources(Player player) {
        this.player = player;
    }

    /**
     * A function to add player's productions to their resources at the end of a generation
     */
    public void addProduction() {

        money += money_production;
        money += terraforming_rating;

        steel += steel_production;

        titanium += titanium_production;

        plants += plants_production;

        heat += energy;
        heat += heat_production;

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
    public void setMoney(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.MEGACREDIT, -amount);
        }
        money = amount;
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
    public void setMoneyProduction(Integer amount) throws InvalidResourcesException {

        if (amount < -5) {
            throw new InvalidResourcesException(GameResourceType.MEGACREDIT_PRODUCTION, -(amount+5));
        }

        // Triggering the unique effect of Manutech corporation
        if (amount > money_production && player.getModifiers().getResourcesFromRaisingProduction()) {
            money += (amount - money_production);
        }
        money_production = amount;
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
    public void setSteel(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.STEEL, -amount);
        }
        steel = amount;
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
    public void setSteelProduction(Integer amount) throws InvalidResourcesException {

        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.STEEL_PRODUCTION, -amount);
        }

        // Triggering the unique effect of Manutech corporation
        if (amount > steel && player.getModifiers().getResourcesFromRaisingProduction()) {
            steel += (amount - steel_production);
        }
        steel_production = amount;
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
    public void setTitanium(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.TITANIUM, -amount);
        }

        titanium = amount;
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
    public void setTitaniumProduction(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.TITANIUM_PRODUCTION, -amount);
        }

        // Triggering the unique effect of Manutech corporation
        if (amount > titanium_production && player.getModifiers().getResourcesFromRaisingProduction()) {
            titanium += (amount - titanium_production);
        }
        titanium_production = amount;
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
    public void setPlants(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.PLANT, -amount);
        }

        plants = amount;
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
    public void setPlantsProduction(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.PLANT_PRODUCTION, -amount);
        }

        // Triggering the unique effect of Manutech corporation
        if (amount > plants_production && player.getModifiers().getResourcesFromRaisingProduction()) {
            plants += (amount - plants_production);
        }
        plants_production = amount;
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
    public void setEnergy(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.ENERGY, -amount);
        }

        energy = amount;
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
    public void setEnergyProduction(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.ENERGY_PRODUCTION, -amount);
        }

        // Triggering the unique effect of Manutech corporation
        if (amount > energy_production && player.getModifiers().getResourcesFromRaisingProduction()) {
            energy += (amount - energy_production);
        }
        energy_production = amount;
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
    public void setHeat(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.HEAT, -amount);
        }

        heat = amount;
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
    public void setHeatProduction(Integer amount) throws InvalidResourcesException {
        if (amount < 0) {
            throw new InvalidResourcesException(GameResourceType.HEAT_PRODUCTION, -amount);
        }

        // Triggering the unique effect of Manutech corporation
        if (amount > heat_production && player.getModifiers().getResourcesFromRaisingProduction()) {
            heat += (amount - heat_production);
        }
        heat_production = amount;
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
        Log.i("PlayerResources", "Setting " + player.getName() + "'s terraforming rating to " + amount + ". Used to be " + terraforming_rating);
        // For UNMI special action
        if (amount > terraforming_rating) {
            player.getModifiers().setRaisedTrThisGeneration(true);
        }
        terraforming_rating = amount > 0 ? amount : 0; // Should never happen, but just in case
    }
}
