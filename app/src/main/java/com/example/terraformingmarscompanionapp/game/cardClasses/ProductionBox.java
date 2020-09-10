package com.example.terraformingmarscompanionapp.game.cardClasses;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.RoboticWorkforcePlayEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.ui.gameMainElements.dialogues.ChoiceDialog;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A dataclass representing the production box of cards. Only needed so robotic workforce functions
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public final class ProductionBox implements Serializable {

    private Boolean has_target = false;

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
     * A simple method to set the card as requiring a target. Needed for playing cards with robotic
     * workforce.
     */
    public void requireTarget(){
        has_target = true;
    }

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
        requireTarget();
    }

    /**
     * @param value {@link Integer} amount of steel production stolen from another player when playing the card
     */
    public void setStealSteelProduction(Integer value) {
        steal_steel_production = value;
        requireTarget();
    }

    /**
     * @param value {@link Integer} amount of titanium production stolen from another player when playing the card
     */
    public void setStealTitaniumProduction(Integer value) {
        steal_titanium_production = value;
        requireTarget();
    }

    /**
     * @param value {@link Integer} amount of plants production stolen from another player when playing the card
     */
    public void setStealPlantsProduction(Integer value) {
        steal_plants_production = value;
        requireTarget();
    }

    /**
     * @param value {@link Integer} amount of energy production stolen from another player when playing the card
     */
    public void setStealEnergyProduction(Integer value) {
        steal_energy_production = value;
        requireTarget();
    }

    /**
     * @param value {@link Integer} amount of heat production stolen from another player when playing the card
     */
    public void setStealHeatProduction(Integer value) {
        steal_heat_production = value;
        requireTarget();
    }

    /**
     * A method to play the box with production values stored in the box
     *
     * @param player {@link Player} that is playing the card
     * @param target_index {@link Integer} representing the target for production stealing. See {@link GameController#getPlayer(Integer)}
     */
    public void playProductionBox(Player player, Integer target_index) throws InvalidResourcesException {
        player.getResources().setMoneyProduction(player.getResources().getMoneyProduction() + money_production);
        player.getResources().setSteelProduction(player.getResources().getSteelProduction() + steel_production);
        player.getResources().setTitaniumProduction(player.getResources().getTitaniumProduction() + titanium_production);
        player.getResources().setPlantsProduction(player.getResources().getPlantsProduction() + plants_production);
        player.getResources().setEnergyProduction(player.getResources().getEnergyProduction() + energy_production);
        player.getResources().setHeatProduction(player.getResources().getHeatProduction() + heat_production);

        try {
            if (target_index != 0) {
                Player target_player = GameController.getPlayer(target_index);

                target_player.getResources().setMoneyProduction(target_player.getResources().getMoneyProduction() - steal_money_production);
                target_player.getResources().setSteelProduction(target_player.getResources().getSteelProduction() - steal_steel_production);
                target_player.getResources().setTitaniumProduction(target_player.getResources().getTitaniumProduction() - steal_titanium_production);
                target_player.getResources().setPlantsProduction(target_player.getResources().getPlantsProduction() - steal_plants_production);
                target_player.getResources().setEnergyProduction(target_player.getResources().getEnergyProduction() - steal_energy_production);
                target_player.getResources().setHeatProduction(target_player.getResources().getHeatProduction() - steal_heat_production);
            }
        } catch (Exception ignored) {}
    }

    /**
     * A method for playing the card's production box again with the card {@link com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RoboticWorkforce}.
     *
     * @param player {@link Player} playing the card
     * @param card {@link Card} this production box belongs to
     */
    public void playWithRoboticWorkforce(Player player, Card card){
        if (has_target) {
            ArrayList<String> player_names = new ArrayList<>();
            for(Player game_player : GameController.getPlayers()){
                player_names.add(game_player.getName());
            }
            EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target:", player_names, card, ChoiceDialog.UseCase.ROBOTIC_WORKFORCE));
        } else {
            EventScheduler.addEvent(new RoboticWorkforcePlayEvent(card, player, 0));
        }
    }
}
