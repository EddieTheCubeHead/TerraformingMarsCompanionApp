package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

//Dataluokka korttien tuotantolaatikoille
public final class ProductionBox {
    private Boolean has_steal = false;
    private void hasSteal() {has_steal = true;}

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

    public void setMoneyProduction(Integer value) {money_production = value;}
    public void setSteelProduction(Integer value) {steel_production = value;}
    public void setTitaniumProduction(Integer value) {titanium_production = value;}
    public void setPlantsProduction(Integer value) {plants_production = value;}
    public void setEnergyProduction(Integer value) {energy_production = value;}
    public void setHeatProduction(Integer value) {heat_production = value;}

    public void setStealMoneyProduction(Integer value) {steal_money_production = value; hasSteal();}
    public void setStealSteelProduction(Integer value) {steal_steel_production = value; hasSteal();}
    public void setStealTitaniumProduction(Integer value) {steal_titanium_production = value; hasSteal();}
    public void setStealPlantsProduction(Integer value) {steal_plants_production = value; hasSteal();}
    public void setStealEnergyProduction(Integer value) {steal_energy_production = value; hasSteal();}
    public void setStealHeatProduction(Integer value) {steal_heat_production = value; hasSteal();}

    public void playProductionBox(Player player, Integer target_index) {
        player.changeMoneyProduction(money_production);
        player.changeSteelProduction(steel_production);
        player.changeTitaniumProduction(titanium_production);
        player.changeEnergyProduction(energy_production);
        player.changePlantsProduction(plants_production);
        player.changeHeatProduction(heat_production);

        if (target_index != 0) {
            Player target_player = GameController.getInstance().getPlayer(target_index);
            target_player.changeMoneyProduction(-steal_money_production);
            target_player.takeSteelProduction(steal_steel_production);
            target_player.takeTitaniumProduction(steal_titanium_production);
            target_player.takePlantsProduction(steal_plants_production);
            target_player.takeEnergyProduction(steal_energy_production);
            target_player.takeHeatProduction(steal_heat_production);
        }
    }
}
