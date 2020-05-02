package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AdvancedAlloys extends Card {
    public AdvancedAlloys(Game game) {
        super(Type.BLUE);
        name = "Advanced alloys";
        price = 9;
        tags.add(Tag.SCIENCE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeSteelValueModifier(1);
        player.changeTitaniumValueModifier(1);
        return super.onPlay(player);
    }

}