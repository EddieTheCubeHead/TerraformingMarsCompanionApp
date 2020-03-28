package com.example.terraformingmarscompanionapp.ui.main;
import android.widget.TextView;

import com.example.terraformingmarscompanionapp.Card;
import java.util.ArrayList;


/**
 * Kortin visuaaliset tiedot sisältävä luokka.
 */
//TODO luokan sisällyttäminen cardiin kun tiedetään että toimii.
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

    public String getCardName() {return card_name;}
    public Integer getRequirement() {return requirement_image_resource;}
    public int getTag1() {return tag_image_resource_1;}
    public int getTag2() {return tag_image_resource_2;}
    public int getTag3() {return tag_image_resource_3;}
    public int getTag4() {return tag_image_resource_4;}
}