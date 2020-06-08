package com.example.terraformingmarscompanionapp.game.player;

/**
 * A dataclass to host the modifiers associated with a player. Associated with{@link Player} via
 * composition.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class PlayerModifiers {

    // Discounts
    private Integer card_discount = 0;
    private Integer earth_tag_discount = 0;
    private Integer building_tag_discount = 0;
    private Integer science_tag_discount = 0;
    private Integer energy_tag_discount = 0;
    private Integer space_tag_discount = 0;
    private Integer venus_tag_discount = 0;
    private Integer next_card_discount = 0;

    // Resource values
    private Integer titanium_value_modifier = 0;
    private Integer steel_value_modifier = 0;

    // TR discounts
    private Integer base_tr_requirement_discount = 0;
    private Integer venus_tr_requirement_discount = 0;

    // Other numeric modifiers
    private Integer ocean_adjacency_bonus_modifier = 0;
    private Integer greenery_plant_cost_modifier = 0;
    private Integer trade_discount = 0;
    private Integer bonus_influence = 0;
    private Integer card_research_cost_modifier = 0;
    private Integer colony_track_modifier = 0;

    // Other boolean modifiers
    private Boolean special_design_effect = false;
    private Boolean organics_protected = false;
    private Boolean heat_is_money = false;
    private Boolean raised_tr_this_generation = false;

    /**
     * @return {@link Integer} the amount of discount a player gets when buying a card
     */
    public Integer getCardDiscount() {
        return card_discount;
    }

    /**
     * @param value {@link Integer} the amount of discount a player gets when buying a card
     */
    public void setCardDiscount(Integer value) {
        card_discount = value;
    }


    /**
     * @return {@link Integer} the amount of discount a player gets when buying a card with an earth tag
     */
    public Integer getEarthTagDiscount() {
        return earth_tag_discount;
    }

    /**
     * @param value {@link Integer} the amount of discount a player gets when buying a card with an earth tag
     */
    public void setEarthTagDiscount(Integer value) {
        earth_tag_discount = value;
    }


    /**
     * @return {@link Integer} the amount of discount a player gets when buying a card with a building tag
     */
    public Integer getBuildingTagDiscount() {
        return building_tag_discount;
    }

    /**
     * @param value {@link Integer} the amount of discount a player gets when buying a card with a building tag
     */
    public void setBuildingTagDiscount(Integer value) {
        building_tag_discount = value;
    }


    /**
     * @return {@link Integer} the amount of discount a player gets when buying a card with a science tag
     */
    public Integer getScienceTagDiscount() {
        return science_tag_discount;
    }

    /**
     * @param value {@link Integer} the amount of discount a player gets when buying a card with a science tag
     */
    public void setScienceTagDiscount(Integer value) {
        science_tag_discount = value;
    }


    /**
     * @return {@link Integer} the amount of discount a player gets when buying a card with an energy tag
     */
    public Integer getEnergyTagDiscount() {
        return energy_tag_discount;
    }

    /**
     * @param value {@link Integer} the amount of discount a player gets when buying a card with an energy tag
     */
    public void setEnergyTagDiscount(Integer value) {
        energy_tag_discount = value;
    }


    /**
     * @return {@link Integer} the amount of discount a player gets when buying a card with a space tag
     */
    public Integer getSpaceTagDiscount() {
        return space_tag_discount;
    }

    /**
     * @param value {@link Integer} the amount of discount a player gets when buying a card with a space tag
     */
    public void setSpaceTagDiscount(Integer value) {
        space_tag_discount = value;
    }


    /**
     * @return {@link Integer} the amount of discount a player gets when buying a card with a venus tag
     */
    public Integer getVenusTagDiscount() {
        return venus_tag_discount;
    }

    /**
     * @param value {@link Integer} the amount of discount a player gets when buying a card with a venus tag
     */
    public void setVenusTagDiscount(Integer value) {
        venus_tag_discount = value;
    }


    /**
     * @return {@link Integer} the amount of discount a player gets when buying their next card
     */
    public Integer getNextCardDiscount() {
        return next_card_discount;
    }

    /**
     * @param value {@link Integer} the amount of discount a player gets when buying their next card
     */
    public void setNextCardDiscount(Integer value) {
        next_card_discount = value;
    }


    /**
     * @return {@link Integer} the modifier to the value of titanium for the player
     */
    public Integer getTitaniumValueModifier() {
        return titanium_value_modifier;
    }

    /**
     * @param value {@link Integer} the modifier to the value of titanium for the player
     */
    public void setTitaniumValueModifier(Integer value) {
        titanium_value_modifier = value;
    }


    /**
     * @return {@link Integer} the modifier to the value of steel for the player
     */
    public Integer getSteelValueModifier() {
        return steel_value_modifier;
    }

    /**
     * @param value {@link Integer} the modifier to the value of steel for the player
     */
    public void setSteelValueModifier(Integer value) {
        steel_value_modifier = value;
    }


    /**
     * @return {@link Integer} the modifier to the required global tr parameters when playing a card
     */
    public Integer getBaseTrRequirementDiscount() {
        return base_tr_requirement_discount;
    }

    /**
     * @param value {@link Integer} the modifier to the required global tr paramaters when playing a card
     */
    public void setBaseTrRequirementDiscount(Integer value) {
        base_tr_requirement_discount = value;
    }


    /**
     * @return {@link Integer} the modifier to the required venus parameter when playing a card
     */
    public Integer getVenusTrRequirementDiscount() {
        return venus_tr_requirement_discount;
    }

    /**
     * @param value {@link Integer} the modifier to the required venus parameter when playing a card
     */
    public void getVenusTrRequirementDiscount(Integer value) {
        venus_tr_requirement_discount = value;
    }


    /**
     * @return {@link Integer} the modifier to the amount of money a player gets when placing a tile next to an ocean
     */
    public Integer getOceanAdjacencyBonusModifier() {
        return ocean_adjacency_bonus_modifier;
    }

    /**
     * @param value {@link Player} the modifier to the amount of money a player gets when placing a tile next to an ocean
     */
    public void setOceanAdjacencyBonusModifier(Integer value) {
        ocean_adjacency_bonus_modifier = value;
    }


    /**
     * @return {@link Integer} the modifier to the amount of plants needed to build a greenery
     */
    public Integer getGreeneryPlantCostModifier() {
        return greenery_plant_cost_modifier;
    }

    /**
     * @param value {@link Integer} the modifier to the amount of plants needed to build a greenery
     */
    public void setGreeneryPlantCostModifier(Integer value) {
        greenery_plant_cost_modifier = value;
    }

    /**
     * @return {@link Integer} the modifier to the amount of resources needed for trading
     */
    public Integer getTradeDiscount() {
        return trade_discount;
    }

    /**
     * @param value {@link Integer} the modifier to the amount of resources needed for trading
     */
    public void setTradeDiscount(Integer value) {
        trade_discount = value;
    }


    /**
     * @return {@link Integer} the modifier to the amount of influence a player has during turmoil phase
     */
    public Integer getBonusInfluence() {
        return bonus_influence;
    }

    /**
     * @param value {@link Integer} the modifier to the amount of influence a player has during turmoil phase
     */
    public void setBonusInfluence(Integer value) {
        bonus_influence = value;
    }


    /**
     * @return {@link Integer} the modifier to the amount of money a player needs to pay for drawing a card into hand during research phase
     */
    public Integer getCardResearchCostModifier() {
        return card_research_cost_modifier;
    }

    /**
     * @param value {@link Integer} the modifier to the amount of money a player needs to pay for drawing a card into hand during research phase
     */
    public void setCardResearchCostModifier(Integer value) {
        card_research_cost_modifier = value;
    }


    /**
     * @return {@link Integer} the amount of steps the player may choose to raise the colony track when trading, if they want to
     */
    public Integer getColonyTrackModifier() {
        return colony_track_modifier;
    }

    /**
     * @param value {@link Integer} the amount of steps the player may choose to raise the colony track when trading, if they want to
     */
    public void setColonyTrackModifier(Integer value) {
        colony_track_modifier = value;
    }


    /**
     * @return {@link Boolean} whether the card {@link com.example.terraformingmarscompanionapp.cards.basegame.cards.SpecialDesign} has been played to modify tr requirements
     */
    public Boolean getSpecialDesignEffect() {
        return special_design_effect;
    }

    /**
     * @param value {@link Boolean} whether the card {@link com.example.terraformingmarscompanionapp.cards.basegame.cards.SpecialDesign} has been played to modify tr requirements
     */
    public void setSpecialDesignEffect(Boolean value) {
        special_design_effect = false;
    }


    /**
     * @return {@link Boolean} whether the plants, animals and microbes of the player are protected
     */
    public Boolean getOrganicsProtected() {
        return organics_protected;
    }

    /**
     * @param value {@link Boolean} whether the plants, animals and microbes of the player are protected
     */
    public void setOrganicsProtected(Boolean value) {
        organics_protected = value;
    }


    /**
     * @return {@link Boolean} whether the player can use heat as money
     */
    public Boolean getHeatIsMoney() {
        return heat_is_money;
    }

    /**
     * @param value {@link Boolean} whether the player can use heat as money
     */
    public void setHeatIsMoney(Boolean value) {
        heat_is_money = value;
    }


    /**
     * @return {@link Boolean} whether the player has raised their terraforming rating this generation
     */
    public Boolean getRaisedTrThisGeneration() {
        return raised_tr_this_generation;
    }

    /**
     * @param value {@link Boolean} whether the player has raised their terraforming rating this generation
     */
    public void setRaisedTrThisGeneration(Boolean value) {
        raised_tr_this_generation = value;
    }
}
