package com.example.terraformingmarscompanionapp.ui.main;

/**
 * Korttipohjainen cardviewn ohjaaja, jossa on get-setit.
 */


//saa editoida tai uudelleennimetä jos haluaa
//ei yhtään set in stone implementaatio
//vois ehkä tehdä osana card-luokkaa.
public class CardView {
    private String card_name;


    //jos on jotain muuta joka on kaikilla korteilla niin tähän
    //ehkä tyyppi?
    public CardView(String card_name, int card_number){
        this.card_name = card_name;
    }

    public void setTag1() {}
    public void setTag2() {}
    public void setTag3() {}

    public void setPrice()
    {

    }
}
