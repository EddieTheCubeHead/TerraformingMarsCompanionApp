package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

/**
 * A dataclass representing the production box of cards. Only needed so robotic workforce functions
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public final class ProductionBox {

    /**
     * Stores required data for playing so calling {@link #playProductionBox(Player, Integer)} with
     * {@link com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RoboticWorkforce} gets
     * simplified.
     */
    private enum DATA_REQUIREMENT {
        NONE,
        TARGET,
        AMOUNT
    }

    private DATA_REQUIREMENT data_requirement = DATA_REQUIREMENT.NONE;

    /**
     * Set the data requirement to inform that a target for a stealing action is required
      */
    private void setStealData() {
        data_requirement = DATA_REQUIREMENT.TARGET;
    }

    /**
     * Set the data requirement to inform that an amount of production change is needed.
     */
    public void setAmountData() {
        data_requirement = DATA_REQUIREMENT.AMOUNT;
    }

    private Integer money_production = 0;
    private Integer steel_production = 0;
    private Integer titanium_production = 0;
    private Integer plants_production = 0;
    private Integer energy_production = 0;
    private Integer heat_production = 0;

    private Integer steal_money_production = 0;
    private Integer steal_steel_production = 0;
    private Integer steal_titanium_production = 0;
    private Integer steal_plants_production = 0;
    private Integer steal_energy_production = 0;
    private Integer steal_heat_production = 0;

    /**
     * @param value {@link Integer} amount of money production changed when playing the card
     */
    public void setMoneyProduction(Integer value) {
        money_production = value;
    }

    /**
     * @param value {@link Integer} amount of steel production changed when playing the card
     */
    public void setSteelProduction(Integer value) {
        steel_production = value;
    }

    /**
     * @param value {@link Integer} amount of titanium production changed when playing the card
     */
    public void setTitaniumProduction(Integer value) {
        titanium_production = value;
    }

    /**
     * @param value {@link Integer} amount of plants production changed when playing the card
     */
    public void setPlantsProduction(Integer value) {
        plants_production = value;
    }

    /**
     * @param value {@link Integer} amount of energy production changed when playing the card
     */
    public void setEnergyProduction(Integer value) {
        energy_production = value;
    }

    /**
     * @param value {@link Integer} amount of heat production changed when playing the card
     */
    public void setHeatProduction(Integer value) {
        heat_production = value;
    }


    /**
     * @param value {@link Integer} amount of money production stolen from another player when playing the card
     */
    public void setStealMoneyProduction(Integer value) {
        steal_money_production = value;
        setStealData();
    }

    /**
     * @param value {@link Integer} amount of steel production stolen from another player when playing the card
     */
    public void setStealSteelProduction(Integer value) {
        steal_steel_production = value;
        setStealData();
    }

    /**
     * @param value {@link Integer} amount of titanium production stolen from another player when playing the card
     */
    public void setStealTitaniumProduction(Integer value) {
        steal_titanium_production = value;
        setStealData();
    }

    /**
     * @param value {@link Integer} amount of plants production stolen from another player when playing the card
     */
    public void setStealPlantsProduction(Integer value) {
        steal_plants_production = value;
        setStealData();
    }

    /**
     * @param value {@link Integer} amount of energy production stolen from another player when playing the card
     */
    public void setStealEnergyProduction(Integer value) {
        steal_energy_production = value;
        setStealData();
    }

    /**
     * @param value {@link Integer} amount of heat production stolen from another player when playing the card
     */
    public void setStealHeatProduction(Integer value) {
        steal_heat_production = value;
        setStealData();
    }

    /**
     * A method to play the box with production values stored in the box
     *
     * @param player {@link Player} that is playing the card
     * @param target_index {@link Integer} representing the target for production stealing. See {@link GameController#getPlayer(Integer)}
     */
    public void playProductionBox(Player player, Integer target_index) {
        player.changeMoneyProduction(money_production);
        player.changeSteelProduction(steel_production);
        player.changeTitaniumProduction(titanium_production);
        player.changeEnergyProduction(energy_production);
        player.changePlantsProduction(plants_production);
        player.changeHeatProduction(heat_production);

        try {
            if (target_index != 0) {
                Player target_player = GameController.getPlayer(target_index);
                target_player.changeMoneyProduction(-steal_money_production);
                target_player.takeSteelProduction(steal_steel_production);
                target_player.takeTitaniumProduction(steal_titanium_production);
                target_player.takePlantsProduction(steal_plants_production);
                target_player.takeEnergyProduction(steal_energy_production);
                target_player.takeHeatProduction(steal_heat_production);
            }
        } catch (Exception ignored) {}
    }
}
