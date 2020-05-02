package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ViralEnhancers extends Card implements EffectCard {
    public ViralEnhancers(Game game) {
        super(Type.BLUE);
        name = "Viral enhancers";
        price = 9;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {

        return super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        //TODO Kasvien Mikrobien ja eläinten suojaus
    }
}
