package com.example.terraformingmarscompanionapp.ui.main;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import java.util.ArrayList;


/**
 * Kortin visuaaliset tiedot sisältävä luokka.
 */

public class CardView {
    private String card_name = null;
    private Integer requirement_image_resource = null;
    private Integer tag_image_resource_1 = null;
    private Integer tag_image_resource_2 = null;
    private Integer tag_image_resource_3 = null;
    private Integer tag_image_resource_4 = null;

    public CardView(Card card)
    {
        card_name = card.getName();
        requirement_image_resource = card.getRequirementInt();
        ArrayList<Integer> tags = card.getTagIntegers();
        try {
            tag_image_resource_1 = tags.get(0);
            tag_image_resource_2 = tags.get(1);
            tag_image_resource_3 = tags.get(2);
            tag_image_resource_4 = tags.get(3);
        } catch (IndexOutOfBoundsException e) {}
    }

    //set ei päivitä korttiuita. päivittäminen tapahtuu recyclerviewadapter publishresults()
    /*
    public void setRequirement  (int resource)  {requirement_image_resource = resource;}
    public void setTag1         (int resource)  {tag_image_resource_1 = resource;}
    public void setTag2         (int resource)  {tag_image_resource_2 = resource;}
    public void setTag3         (int resource)  {tag_image_resource_3 = resource;}
    public void setTag4         (int resource)  {tag_image_resource_4 = resource;}
     */
    public int getTag1() {return tag_image_resource_1;}
    public int getTag2() {return tag_image_resource_2;}
    public int getTag3() {return tag_image_resource_3;}
    public int getTag4() {return tag_image_resource_4;}

    public String getCardName() {return card_name;}
    public void setCardName(String card_name) {this.card_name = card_name;}
}