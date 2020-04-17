package com.example.terraformingmarscompanionapp.game;

//Luokka kortin pelaamisen vaatimien resurssien listaamiseen kompaktisti.
public class CardCost {
    private Integer money;
    private Integer steel;
    private Integer titanium;
    private Integer heat;
    private Integer plant_resources;
    private Integer floater_resources;

    Integer getMoney() {return money;}
    Integer getSteel() {return steel;}
    Integer getTitanium() {return titanium;}
    Integer getHeat() {return heat;}
    Integer getPlantResources() {return plant_resources;}
    Integer getFloaterResources() {return floater_resources;}

    CardCost(Integer money,
             Integer steel,
             Integer titanium,
             Integer heat,
             Integer plant_resources,
             Integer floater_resources)
    {
        this.money = money;
        this.steel = steel;
        this.titanium = titanium;
        this.heat = heat;
        this.plant_resources = plant_resources;
        this.floater_resources = floater_resources;
    }
}
