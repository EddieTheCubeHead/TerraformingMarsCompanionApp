package com.example.terraformingmarscompanionapp.game;

import java.io.Serializable;

/**
 * A dataclass for the expansions and houserules of the game (both TBA)
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class GameModifiers implements Serializable {
    private Boolean corporate_era = false;
    private Boolean prelude = false;
    private Boolean colonies = false;
    private Boolean venus = false;
    private Boolean turmoil = false;
    private Boolean extra_corporations = false;
    private Boolean world_government_terraforming = false;
    private Boolean must_max_venus = false;
    private Boolean turmoil_terraforming_revision = false;

    // Corporate era
    /**
     * @return {@link Boolean} whether the corporate era expansion is in use
     */
    public Boolean getCorporateEra() {
        return corporate_era;
    }

    /**
     * @param value {@link Boolean} whether the corporate era expansion is in use
     */
    public void setCorporateEra(Boolean value) {
        corporate_era = value;
    }


    // Prelude
    /**
     * @return {@link Boolean} whether the prelude expansion is in use
     */
    public Boolean getPrelude() {
        return prelude;
    }

    /**
     * @param value {@link Boolean} whether the prelude expansion is in use
     */
    public void setPrelude(Boolean value) {
        prelude = value;
    }


    // Colonies
    /**
     * @return {@link Boolean} whether the colonies expansion is in use
     */
    public Boolean getColonies() {
        return colonies;
    }

    /**
     * @param value {@link Boolean} whether the colonies expansion is in use
     */
    public void setColonies(Boolean value) {
        colonies = value;
    }


    // Venus next
    /**
     * @return {@link Boolean} whether the venus next expansion is in use
     */
    public Boolean getVenus() {
        return venus;
    }

    /**
     * @param value {@link Boolean} whether the venus next expansion is in use
     */
    public void setVenus(Boolean value) {
        venus = value;
    }


    // Turmoil
    /**
     * @return {@link Boolean} whether the turmoil expansion is in use
     */
    public Boolean getTurmoil() {
        return turmoil;
    }

    /**
     * @param value {@link Boolean} whether the turmoil expansion is in use
     */
    public void setTurmoil(Boolean value) {
        turmoil = value;
    }


    // Extra Corporations houserule
    /**
     * @return {@link Boolean} whether the houserule to use all compatible corporations is in use
     */
    public Boolean getExtraCorporations() {
        return extra_corporations;
    }

    /**
     * @param value {@link Boolean} whether the houserule to use all compatible corporations is in use
     */
    public void setExtraCorporations(Boolean value) {
        extra_corporations = value;
    }


    // Solar Phase houserule
    /**
     * @return {@link Boolean} whether the solar phase from venus next is in use
     */
    public Boolean getWorldGovernmentTerraformin() {
        return world_government_terraforming;
    }

    /**
     * @param value {@link Boolean} whether the solar phase from venus next is in use
     */
    public void setWorldGovernmentTerraformin(Boolean value) {
        world_government_terraforming = value;
    }


    // Must max venus houserule
    /**
     * @return {@link Boolean} whether the houserule requiring venus slider to be maxed for the game to end is in use
     */
    public Boolean getMustMaxVenus() {
        return must_max_venus;
    }

    /**
     * @param value {@link Boolean} whether the houserule requiring venus slider to be maxed for the game to end is in use
     */
    public void setMustMaxVenus(Boolean value) {
        must_max_venus = value;
    }


    // Turmoil terraforming revision houserule
    /**
     * @return {@link Boolean} whether the terraforming revision from the turmoil expansion is in use
     */
    public Boolean getTurmoilTerraformingRevision() {
        return turmoil_terraforming_revision;
    }

    /**
     * @param value {@link Boolean} whether the terraforming revision from the turmoil expansion is in use
     */
    public void setTurmoilTerraformingRevision(Boolean value) {
        turmoil_terraforming_revision = value;
    }
}
