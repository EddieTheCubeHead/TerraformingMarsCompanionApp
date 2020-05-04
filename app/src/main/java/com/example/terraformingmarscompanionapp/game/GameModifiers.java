package com.example.terraformingmarscompanionapp.game;

//Dataluokka pelin boolean-muuttujille
public class GameModifiers {
    private Boolean corporate_era;
    private Boolean prelude;
    private Boolean colonies;
    private Boolean venus;
    private Boolean turmoil;
    private Boolean extra_corporations;
    private Boolean world_government_terraforming;
    private Boolean must_max_venus;
    private Boolean turmoil_terraforming_revision;

    public GameModifiers(boolean corporate_era,
                         boolean prelude,
                         boolean colonies,
                         boolean venus,
                         boolean turmoil,
                         boolean extra_corporations,
                         boolean world_government_terraforming,
                         boolean must_max_venus,
                         boolean turmoil_terraforming_revision) {
        this.corporate_era = corporate_era;
        this.prelude = prelude;
        this.colonies = colonies;
        this.venus = venus;
        this.turmoil = turmoil;
        this.extra_corporations = extra_corporations;
        this.world_government_terraforming = world_government_terraforming;
        this.must_max_venus = must_max_venus;
        this.turmoil_terraforming_revision = turmoil_terraforming_revision;
    }

    public Boolean getCorporateEra() {return corporate_era;}
    public Boolean getPrelude() {return prelude;}
    public Boolean getColonies() {return colonies;}
    public Boolean getVenus() {return venus;}
    public Boolean getTurmoil() {return turmoil;}
    public Boolean getExtraCorporations() {return extra_corporations;}
    public Boolean getWorldGovernmentTerraformin() {return world_government_terraforming;}
    public Boolean getMustMaxVenus() {return must_max_venus;}
    public Boolean getTurmoilTerraformingRevision() {return turmoil_terraforming_revision;}
}
