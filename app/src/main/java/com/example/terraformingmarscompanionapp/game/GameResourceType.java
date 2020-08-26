package com.example.terraformingmarscompanionapp.game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An enum for easing the handling of resource types in string related context, for example exceptions.
 * Contains a method to check if the resource requires plural form.
 */
public enum GameResourceType {
    MEGACREDIT,
    STEEL,
    TITANIUM,
    PLANT,
    ENERGY,
    HEAT,
    MEGACREDIT_PRODUCTION,
    STEEL_PRODUCTION,
    TITANIUM_PRODUCTION,
    PLANT_PRODUCTION,
    ENERGY_PRODUCTION,
    HEAT_PRODUCTION;

    @Override
    public String toString() {
        switch (this) {
            case MEGACREDIT:
                return "megacredit";
            case STEEL:
                return "steel";
            case TITANIUM:
                return "titanium";
            case PLANT:
                return "plant";
            case ENERGY:
                return "energy";
            case HEAT:
                return "heat";
            case MEGACREDIT_PRODUCTION:
                return "megacredit production";
            case STEEL_PRODUCTION:
                return "steel production";
            case TITANIUM_PRODUCTION:
                return "titanium production";
            case PLANT_PRODUCTION:
                return "plant production";
            case ENERGY_PRODUCTION:
                return "energy production";
            case HEAT_PRODUCTION:
                return "heat production";
            default:
                return this.name();
        }
    }

    // All resource types that require -s suffix in plural context
    private static final ArrayList<GameResourceType> PLURAL_TYPES = new ArrayList<>(
            Arrays.asList(GameResourceType.MEGACREDIT, GameResourceType.PLANT));

    /**
     * @return {@link Boolean} whether a resource requires -s suffix in plural context
     */
    public Boolean requiresPlural() {
        return PLURAL_TYPES.contains(this);
    }
}
