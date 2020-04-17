package com.example.terraformingmarscompanionapp.webSocket.events;

//Luokka kortin pelaamisen vaatimien resurssien listaamiseen kompaktisti.
public class CardCostPacket {
    private Integer money;
    private Integer steel;
    private Integer titanium;
    private Integer heat;
    private Integer plant_resources;
    private Integer floater_resources;

    public Integer getMoney() {return money;}
    public Integer getSteel() {return steel;}
    public Integer getTitanium() {return titanium;}
    public Integer getHeat() {return heat;}
    public Integer getPlantResources() {return plant_resources;}
    public Integer getFloaterResources() {return floater_resources;}

    public CardCostPacket(Integer money,
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
