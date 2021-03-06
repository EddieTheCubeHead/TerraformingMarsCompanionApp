package com.example.terraformingmarscompanionapp.game;

import android.util.Log;

import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.cards.basegame.awards.*;
import com.example.terraformingmarscompanionapp.cards.basegame.cards.*;
import com.example.terraformingmarscompanionapp.cards.basegame.corporations.*;
import com.example.terraformingmarscompanionapp.cards.basegame.milestones.*;
import com.example.terraformingmarscompanionapp.cards.basegame.utilityCards.*;
import com.example.terraformingmarscompanionapp.cards.basegame.standardProjects.*;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.AcquiredCompany;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.AdvancedAlloys;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.AiCentral;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.AntiGravityTechnology;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.AsteroidMiningConsortium;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.BribedCommittee;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.BuildingIndustries;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.BusinessNetwork;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.CallistoPenalMines;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.CaretakerContract;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Cartel;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.CeosFavoriteProject;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.CommercialDistrict;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.DevelopmentCenter;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.EarthCatapult;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.EarthOffice;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.ElectroCatapult;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.EnergyTapping;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.FuelFactory;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.GeneRepair;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.GreatEscarpmentConsortium;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Hackers;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.HiredRaiders;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.IndenturnedWorkers;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.InterstellarColonyShip;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.InventorsGuild;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.InvestionContest;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.InvestmentLoan;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.IoMiningIndustries;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.LagrangeObservatory;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.LandClaim;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.LightningHarvest;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MarsUniverse;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MassConverter;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MediaArchives;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MediaGroup;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MedicalLab;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Mine;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MineralDeposit;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MiningArea;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.MirandaResort;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.PhysicsComplex;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.PowerInfrastructure;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.PowerSupplyConsortium;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.ProtectedHabitats;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.QuantumExtractor;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RadSuits;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Research;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RestrictedArea;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RoboticWorkforce;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Sabotage;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Satellites;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.SecurityFleet;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.SpaceElevator;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.SpaceStation;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Sponsors;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.StandardTechnology;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Tardigrades;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.TechnologyDemostration;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.TerraformingGanymede;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.TitaniumMine;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.TollStation;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.TransNeptuneProbe;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.TropicalResort;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.VestaShipyard;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.ViralEnhancers;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.Virus;
import com.example.terraformingmarscompanionapp.cards.corporate_era.corporations.SaturnSystems;
import com.example.terraformingmarscompanionapp.cards.corporate_era.corporations.Teractor;
import com.example.terraformingmarscompanionapp.game.tileSystem.GameMap;

import java.util.HashMap;

/**
 * A Class responsible for creating all the different decks in the game. Takes the expansions and
 * modifiers of the game as input in constructor and has methods that can be called to get different
 * parts of the deck
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
class DeckConstructor {
    private final HashMap<String, Card> deck;
    private final HashMap<String, Card> preludes;
    private final HashMap<String, Card> corporations;
    private Game game;
    private Boolean corporate_era;
    private Boolean prelude;
    private Boolean colonies;
    private Boolean venus;
    private Boolean turmoil;
    private Boolean extra_corporations;
    private GameMap map;

    /**
     * Constructor
     *
     * @param game {@link Game} the deck constructor is associated with
     * @param map {@link GameMap} the map of the game
     */
    DeckConstructor(Game game, GameMap map) {
        deck = new HashMap<>();
        preludes = new HashMap<>();
        corporations = new HashMap<>();
        this.game = game;
        this.corporate_era = game.modifiers.getCorporateEra();
        this.prelude = game.modifiers.getPrelude();
        this.colonies = game.modifiers.getColonies();
        this.venus = game.modifiers.getVenus();
        this.turmoil = game.modifiers.getTurmoil();
        this.extra_corporations = game.modifiers.getExtraCorporations();
        this.map = map;
    }

    /**
     * A method to create the project card deck of the game.
     *
     * @return {@link HashMap} of {@link String} and {@link Card} representing the deck
     */
    HashMap<String, Card> createDeck() {

        // Basegame:
        Card adaptation_technology = new AdaptationTechnology(game);
        deck.put(adaptation_technology.getName(), adaptation_technology);

        Card adapted_lichen = new AdaptedLichen(game);
        deck.put(adapted_lichen.getName(), adapted_lichen);

        Card advanced_ecosystems = new AdvancedEcosystems(game);
        deck.put(advanced_ecosystems.getName(), advanced_ecosystems);

        Card aerobraked_ammonia_asteroid = new AerobrakedAmmoniaAsteroid(game);
        deck.put(aerobraked_ammonia_asteroid.getName(), aerobraked_ammonia_asteroid);

        Card algae = new Algae(game);
        deck.put(algae.getName(), algae);

        Card ants = new Ants(game);
        deck.put(ants.getName(), ants);

        Card aquifer_pumping = new AquiferPumping(game);
        deck.put(aquifer_pumping.getName(), aquifer_pumping);

        Card archaebacteria = new Archaebacteria(game);
        deck.put(archaebacteria.getName(), archaebacteria);

        Card arctic_algae = new ArcticAlgae(game);
        deck.put(arctic_algae.getName(), arctic_algae);

        Card artificial_lake = new ArtificialLake(game);
        deck.put(artificial_lake.getName(), artificial_lake);

        Card artificial_photosynthesis = new ArtificialPhotosynthesis(game);
        deck.put(artificial_photosynthesis.getName(), artificial_photosynthesis);

        Card asteroid = new Asteroid(game);
        deck.put(asteroid.getName(), asteroid);

        Card asteroid_mining = new AsteroidMining(game);
        deck.put(asteroid_mining.getName(), asteroid_mining);

        Card beam_from_a_thorium_asteroid = new BeamFromAThoriumAsteroid(game);
        deck.put(beam_from_a_thorium_asteroid.getName(), beam_from_a_thorium_asteroid);

        Card big_asteroid = new BigAsteroid(game);
        deck.put(big_asteroid.getName(), big_asteroid);

        Card biomass_combustion = new BiomassCombustion(game);
        deck.put(biomass_combustion.getName(), biomass_combustion);

        Card birds = new Birds(game);
        deck.put(birds.getName(), birds);

        Card black_polar_dust = new BlackPolarDust(game);
        deck.put(black_polar_dust.getName(), black_polar_dust);

        Card breathing_filters = new BreathingFilters(game);
        deck.put(breathing_filters.getName(), breathing_filters);

        Card bushes = new Bushes(game);
        deck.put(bushes.getName(), bushes);

        Card capital = new Capital(game);
        deck.put(capital.getName(), capital);

        Card carbonate_processing = new CarbonateProcessing(game);
        deck.put(carbonate_processing.getName(), carbonate_processing);

        Card cloud_seeding = new CloudSeeding(game);
        deck.put(cloud_seeding.getName(), cloud_seeding);

        Card colonizer_training_camp = new ColonizerTrainingCamp(game);
        deck.put(colonizer_training_camp.getName(), colonizer_training_camp);

        Card comet = new Comet(game);
        deck.put(comet.getName(), comet);

        Card convoy_from_europe = new ConvoyFromEurope(game);
        deck.put(convoy_from_europe.getName(), convoy_from_europe);

        Card cupola_city = new CupolaCity(game);
        deck.put(cupola_city.getName(), cupola_city);

        Card decomposers = new Decomposers(game);
        deck.put(decomposers.getName(), decomposers);

        Card deep_well_heating = new DeepWellHeating(game);
        deck.put(deep_well_heating.getName(), deep_well_heating);

        Card deimos_down = new DeimosDown(game);
        deck.put(deimos_down.getName(), deimos_down);

        Card designed_microorganisms = new DesignedMicroorganisms(game);
        deck.put(designed_microorganisms.getName(), designed_microorganisms);

        Card domed_crater = new DomedCrater(game);
        deck.put(domed_crater.getName(), domed_crater);

        Card dust_seals = new DustSeals(game);
        deck.put(dust_seals.getName(), dust_seals);

        Card ecological_zone = new EcologicalZone(game);
        deck.put(ecological_zone.getName(), ecological_zone);

        Card energy_saving = new EnergySaving(game);
        deck.put(energy_saving.getName(), energy_saving);

        Card eon_chasma_national_park = new EonChasmaNationalPark(game);
        deck.put(eon_chasma_national_park.getName(), eon_chasma_national_park);

        Card equatorial_magnetizer = new EquatorialMagnetizer(game);
        deck.put(equatorial_magnetizer.getName(), equatorial_magnetizer);

        Card extreme_cold_fungus = new ExtremeColdFungus(game);
        deck.put(extreme_cold_fungus.getName(), extreme_cold_fungus);

        Card farming = new Farming(game);
        deck.put(farming.getName(), farming);

        Card fish = new Fish(game);
        deck.put(fish.getName(), fish);

        Card flooding = new Flooding(game);
        deck.put(flooding.getName(), flooding);

        Card food_factory = new FoodFactory(game);
        deck.put(food_factory.getName(), food_factory);

        Card fueled_generators = new FueledGenerators(game);
        deck.put(fueled_generators.getName(), fueled_generators);

        Card fusion_power = new FusionPower(game);
        deck.put(fusion_power.getName(), fusion_power);

        Card ganymede_colony = new GanymedeColony(game);
        deck.put(ganymede_colony.getName(), ganymede_colony);

        Card geothermal_power = new GeothermalPower(game);
        deck.put(geothermal_power.getName(), geothermal_power);

        Card GHG_factories = new GHGFactories(game);
        deck.put(GHG_factories.getName(), GHG_factories);

        Card GHG_producing_bacteria = new GHGProducingBacteria(game);
        deck.put(GHG_producing_bacteria.getName(), GHG_producing_bacteria);

        Card giant_ice_asteroid = new GiantIceAsteroid(game);
        deck.put(giant_ice_asteroid.getName(), giant_ice_asteroid);

        Card giant_space_mirror = new GiantSpaceMirror(game);
        deck.put(giant_space_mirror.getName(), giant_space_mirror);

        Card grass = new Grass(game);
        deck.put(grass.getName(), grass);

        Card great_dam = new GreatDam(game);
        deck.put(great_dam.getName(), great_dam);

        Card greenhouses = new Greenhouses(game);
        deck.put(greenhouses.getName(), greenhouses);

        Card heather = new Heather(game);
        deck.put(heather.getName(), heather);

        Card heat_trappers = new HeatTrappers(game);
        deck.put(heat_trappers.getName(), heat_trappers);

        Card herbivores = new Herbivores(game);
        deck.put(herbivores.getName(), herbivores);

        Card ice_asteroid = new IceAsteroid(game);
        deck.put(ice_asteroid.getName(), ice_asteroid);

        Card ice_cap_melting = new IceCapMelting(game);
        deck.put(ice_cap_melting.getName(), ice_cap_melting);

        Card immigrant_city = new ImmigrantCity(game);
        deck.put(immigrant_city.getName(), immigrant_city);

        Card immigration_shuttles = new ImmigrationShuttles(game);
        deck.put(immigration_shuttles.getName(), immigration_shuttles);

        Card imported_GHG = new ImportedGHG(game);
        deck.put(imported_GHG.getName(), imported_GHG);

        Card imported_hydrogen = new ImportedHydrogen(game);
        deck.put(imported_hydrogen.getName(), imported_hydrogen);

        Card imported_nitrogen = new ImportedNitrogen(game);
        deck.put(imported_nitrogen.getName(), imported_nitrogen);

        Card import_of_advanced_GHG = new ImportOfAdvancedGHG(game);
        deck.put(import_of_advanced_GHG.getName(), import_of_advanced_GHG);

        Card industrial_microbes = new IndustrialMicrobes(game);
        deck.put(industrial_microbes.getName(), industrial_microbes);

        Card insects = new Insects(game);
        deck.put(insects.getName(), insects);

        Card insulation = new Insulation(game);
        deck.put(insulation.getName(), insulation);

        Card ironworks = new Ironworks(game);
        deck.put(ironworks.getName(), ironworks);

        Card kelp_farming = new KelpFarming(game);
        deck.put(kelp_farming.getName(), kelp_farming);

        Card lake_marineris = new LakeMarineris(game);
        deck.put(lake_marineris.getName(), lake_marineris);

        Card large_convoy = new LargeConvoy(game);
        deck.put(large_convoy.getName(), large_convoy);

        Card lava_flows = new LavaFlows(game);
        deck.put(lava_flows.getName(), lava_flows);

        Card lichen = new Lichen(game);
        deck.put(lichen.getName(), lichen);

        Card livestock = new Livestock(game);
        deck.put(livestock.getName(), livestock);

        Card local_heat_trapping = new LocalHeatTrapping(game);
        deck.put(local_heat_trapping.getName(), local_heat_trapping);

        Card lunar_beam = new LunarBeam(game);
        deck.put(lunar_beam.getName(), lunar_beam);

        Card magnetic_dome_field = new MagneticDomeField(game);
        deck.put(magnetic_dome_field.getName(), magnetic_dome_field);

        Card magnetic_field_generators = new MagneticFieldGenerators(game);
        deck.put(magnetic_field_generators.getName(), magnetic_field_generators);

        Card mangrove = new Mangrove(game);
        deck.put(mangrove.getName(), mangrove);

        Card martian_rails = new MartianRails(game);
        deck.put(martian_rails.getName(), martian_rails);

        Card methane_from_titan = new MethaneFromTitan(game);
        deck.put(methane_from_titan.getName(), methane_from_titan);

        Card micro_mills = new MicroMills(game);
        deck.put(micro_mills.getName(), micro_mills);

        Card mining_expedition = new MiningExpedition(game);
        deck.put(mining_expedition.getName(), mining_expedition);

        Card mining_rights = new MiningRights(game);
        deck.put(mining_rights.getName(), mining_rights);

        Card mohole_area = new MoholeArea(game);
        deck.put(mohole_area.getName(), mohole_area);

        Card moss = new Moss(game);
        deck.put(moss.getName(), moss);

        Card natural_preserve = new NaturalPreserve(game);
        deck.put(natural_preserve.getName(), natural_preserve);

        Card nitrite_reducing_bacteria = new NitriteReducingBacteria(game);
        deck.put(nitrite_reducing_bacteria.getName(), nitrite_reducing_bacteria);

        Card nitrogen_rich_asteroid = new NitrogenRichAsteroid(game);
        deck.put(nitrogen_rich_asteroid.getName(), nitrogen_rich_asteroid);

        Card nitrophilic_moss = new NitrophilicMoss(game);
        deck.put(nitrophilic_moss.getName(), nitrophilic_moss);

        Card noctis_city = new NoctisCity(game);
        deck.put(noctis_city.getName(), noctis_city);

        Card noctis_farming = new NoctisFarming(game);
        deck.put(noctis_farming.getName(), noctis_farming);

        Card nuclear_power = new NuclearPower(game);
        deck.put(nuclear_power.getName(), nuclear_power);

        Card nuclear_zone = new NuclearZone(game);
        deck.put(nuclear_zone.getName(), nuclear_zone);

        Card open_city = new OpenCity(game);
        deck.put(open_city.getName(), open_city);

        Card optimal_aerobraking = new OptimalAerobraking(game);
        deck.put(optimal_aerobraking.getName(), optimal_aerobraking);

        Card ore_processor = new OreProcessor(game);
        deck.put(ore_processor.getName(), ore_processor);

        Card permafrost_extraction = new PermafrostExtraction(game);
        deck.put(permafrost_extraction.getName(), permafrost_extraction);

        Card peroxide_power = new PeroxidePower(game);
        deck.put(peroxide_power.getName(), peroxide_power);

        Card pets = new Pets(game);
        deck.put(pets.getName(), pets);

        Card phobos_space_haven = new PhobosSpaceHaven(game);
        deck.put(phobos_space_haven.getName(), phobos_space_haven);

        Card plantation = new Plantation(game);
        deck.put(plantation.getName(), plantation);

        Card power_grid = new PowerGrid(game);
        deck.put(power_grid.getName(), power_grid);

        Card power_plant = new PowerPlant(game);
        deck.put(power_plant.getName(), power_plant);

        Card predators = new Predators(game);
        deck.put(predators.getName(), predators);

        Card protected_valley = new ProtectedValley(game);
        deck.put(protected_valley.getName(), protected_valley);

        Card rad_chem_factory = new RadChemFactory(game);
        deck.put(rad_chem_factory.getName(), rad_chem_factory);

        Card regolith_eaters = new RegolithEaters(game);
        deck.put(regolith_eaters.getName(), regolith_eaters);

        Card release_of_inert_gases = new ReleaseOfInertGases(game);
        deck.put(release_of_inert_gases.getName(), release_of_inert_gases);

        Card research_outpost = new ResearchOutpost(game);
        deck.put(research_outpost.getName(), research_outpost);

        Card rover_construction = new RoverConstruction(game);
        deck.put(rover_construction.getName(), rover_construction);

        Card search_for_life = new SearchForLife(game);
        deck.put(search_for_life.getName(), search_for_life);

        Card shuttles = new Shuttles(game);
        deck.put(shuttles.getName(), shuttles);

        Card small_animals = new SmallAnimals(game);
        deck.put(small_animals.getName(), small_animals);

        Card soil_factory = new SoilFactory(game);
        deck.put(soil_factory.getName(), soil_factory);

        Card solar_power = new SolarPower(game);
        deck.put(solar_power.getName(), solar_power);

        Card solar_wind_power = new SolarWindPower(game);
        deck.put(solar_wind_power.getName(), solar_wind_power);

        Card soletta = new Soletta(game);
        deck.put(soletta.getName(), soletta);

        Card space_mirrors = new SpaceMirrors(game);
        deck.put(space_mirrors.getName(), space_mirrors);

        Card special_design = new SpecialDesign(game);
        deck.put(special_design.getName(), special_design);

        Card steelworks = new Steelworks(game);
        deck.put(steelworks.getName(), steelworks);

        Card strip_mine = new StripMine(game);
        deck.put(strip_mine.getName(), strip_mine);

        Card subterranean_reservoir = new SubterraneanReservoir(game);
        deck.put(subterranean_reservoir.getName(), subterranean_reservoir);

        Card symbiotic_fungus = new SymbioticFungus(game);
        deck.put(symbiotic_fungus.getName(), symbiotic_fungus);

        Card tectonic_stress_power = new TectonicStressPower(game);
        deck.put(tectonic_stress_power.getName(), tectonic_stress_power);

        Card towing_a_comet = new TowingAComet(game);
        deck.put(towing_a_comet.getName(), towing_a_comet);

        Card trees = new Trees(game);
        deck.put(trees.getName(), trees);

        Card tundra_farming = new TundraFarming(game);
        deck.put(tundra_farming.getName(), tundra_farming);

        Card underground_city = new UndergroundCity(game);
        deck.put(underground_city.getName(), underground_city);

        Card underground_detonation = new UndergroundDetonation(game);
        deck.put(underground_detonation.getName(), underground_detonation);

        Card urbanized_area = new UrbanizedArea(game);
        deck.put(urbanized_area.getName(), urbanized_area);

        Card water_import_from_europe = new WaterImportFromEurope(game);
        deck.put(water_import_from_europe.getName(), water_import_from_europe);

        Card water_splitting_plant = new WaterSplittingPlant(game);
        deck.put(water_splitting_plant.getName(), water_splitting_plant);

        Card wave_power = new WavePower(game);
        deck.put(wave_power.getName(), wave_power);

        Card windmills = new Windmills(game);
        deck.put(windmills.getName(), windmills);

        Card worms = new Worms(game);
        deck.put(worms.getName(), worms);

        Card zeppelins = new Zeppelins(game);
        deck.put(zeppelins.getName(), zeppelins);

        // Standard projects
        Card standard_aquifer = new StandardAquifer(game);
        deck.put(standard_aquifer.getName(), standard_aquifer);

        Card standard_asteroid = new StandardAsteroid(game);
        deck.put(standard_asteroid.getName(), standard_asteroid);

        Card standard_city = new StandardCity(game);
        deck.put(standard_city.getName(), standard_city);

        Card standard_greenery = new StandardGreenery(game);
        deck.put(standard_greenery.getName(), standard_greenery);

        Card standard_power_plant = new StandardPowerPlant(game);
        deck.put(standard_power_plant.getName(), standard_power_plant);

        Card standard_sell_patents = new StandardSellPatents(game);
        deck.put(standard_sell_patents.getName(), standard_sell_patents);


        // Utility cards
        Card build_greenery = new BuildGreenery(game);
        deck.put(build_greenery.getName(), build_greenery);

        Card raise_temperature = new RaiseTemperature(game);
        deck.put(raise_temperature.getName(), raise_temperature);

        Card round_start_draw = new RoundStartDraw(game);
        deck.put(round_start_draw.getName(), round_start_draw);

        Card aquifer_pumping_ghost = new AquiferPumpingGhost(game);
        deck.put(aquifer_pumping_ghost.getName(), aquifer_pumping_ghost);

        Card water_import_from_europe_ghost = new WaterImportFromEuropeGhost(game);
        deck.put(water_import_from_europe_ghost.getName(), water_import_from_europe_ghost);

        // Corporate era
        if (corporate_era) {
            Card acquired_company = new AcquiredCompany(game);
            deck.put(acquired_company.getName(), acquired_company);

            Card advanced_alloys = new AdvancedAlloys(game);
            deck.put(advanced_alloys.getName(), advanced_alloys);

            Card ai_centeral = new AiCentral(game);
            deck.put(ai_centeral.getName(), ai_centeral);

            Card anti_gravity_technology = new AntiGravityTechnology(game);
            deck.put(anti_gravity_technology.getName(), anti_gravity_technology);

            Card asteroid_mining_consortium = new AsteroidMiningConsortium(game);
            deck.put(asteroid_mining_consortium.getName(), asteroid_mining_consortium);

            Card bribed_committee = new BribedCommittee(game);
            deck.put(bribed_committee.getName(), bribed_committee);

            Card building_indrustries = new BuildingIndustries(game);
            deck.put(building_indrustries.getName(), building_indrustries);

            Card business_network = new BusinessNetwork(game);
            deck.put(business_network.getName(), business_network);

            Card callisto_penal_mines = new CallistoPenalMines(game);
            deck.put(callisto_penal_mines.getName(), callisto_penal_mines);

            Card caretaker_contract = new CaretakerContract(game);
            deck.put(caretaker_contract.getName(), caretaker_contract);

            Card cartel = new Cartel(game);
            deck.put(cartel.getName(), cartel);

            Card ceos_favorite_project = new CeosFavoriteProject(game);
            deck.put(ceos_favorite_project.getName(), ceos_favorite_project);

            Card commercial_district = new CommercialDistrict(game);
            deck.put(commercial_district.getName(), commercial_district);

            Card developement_center = new DevelopmentCenter(game);
            deck.put(developement_center.getName(), developement_center);

            Card earth_catapult = new EarthCatapult(game);
            deck.put(earth_catapult.getName(), earth_catapult);

            Card earth_office = new EarthOffice(game);
            deck.put(earth_office.getName(), earth_office);

            Card electro_catapult = new ElectroCatapult(game);
            deck.put(electro_catapult.getName(), electro_catapult);

            Card energy_tapping = new EnergyTapping(game);
            deck.put(energy_tapping.getName(), energy_tapping);

            Card fuel_factory = new FuelFactory(game);
            deck.put(fuel_factory.getName(), fuel_factory);

            Card gene_repair = new GeneRepair(game);
            deck.put(gene_repair.getName(), gene_repair);

            Card great_escarpment_consortium = new GreatEscarpmentConsortium(game);
            deck.put(great_escarpment_consortium.getName(), great_escarpment_consortium);

            Card hackers = new Hackers(game);
            deck.put(hackers.getName(), hackers);

            Card hired_raiders = new HiredRaiders(game);
            deck.put(hired_raiders.getName(), hired_raiders);

            Card indenturned_workers = new IndenturnedWorkers(game);
            deck.put(indenturned_workers.getName(), indenturned_workers);

            Card intersteller_colony_ship = new InterstellarColonyShip(game);
            deck.put(intersteller_colony_ship.getName(), intersteller_colony_ship);

            Card inventors_guild = new InventorsGuild(game);
            deck.put(inventors_guild.getName(), inventors_guild);

            Card investion_contest = new InvestionContest(game);
            deck.put(investion_contest.getName(), investion_contest);

            Card investment_loan = new InvestmentLoan(game);
            deck.put(investment_loan.getName(), investment_loan);

            Card io_mining_industries = new IoMiningIndustries(game);
            deck.put(io_mining_industries.getName(), io_mining_industries);

            Card lagranga_observatory = new LagrangeObservatory(game);
            deck.put(lagranga_observatory.getName(), lagranga_observatory);

            Card land_claim = new LandClaim(game);
            deck.put(land_claim.getName(), land_claim);

            Card lighgtning_harvest = new LightningHarvest(game);
            deck.put(lighgtning_harvest.getName(), lighgtning_harvest);

            Card mars_universe = new MarsUniverse(game);
            deck.put(mars_universe.getName(), mars_universe);

            Card mass_conventer = new MassConverter(game);
            deck.put(mass_conventer.getName(), mass_conventer);

            Card media_archives = new MediaArchives(game);
            deck.put(media_archives.getName(), media_archives);

            Card media_group = new MediaGroup(game);
            deck.put(media_group.getName(), media_group);

            Card medical_lab = new MedicalLab(game);
            deck.put(medical_lab.getName(), medical_lab);

            Card mine = new Mine(game);
            deck.put(mine.getName(), mine);

            Card mineral_deposit = new MineralDeposit(game);
            deck.put(mineral_deposit.getName(), mineral_deposit);

            Card mining_area = new MiningArea(game);
            deck.put(mining_area.getName(), mining_area);

            Card miranda_resort = new MirandaResort(game);
            deck.put(miranda_resort.getName(), miranda_resort);

            Card physics_complex = new PhysicsComplex(game);
            deck.put(physics_complex.getName(), physics_complex);

            Card power_infastructure = new PowerInfrastructure(game);
            deck.put(power_infastructure.getName(), power_infastructure);

            Card power_supply_consortium = new PowerSupplyConsortium(game);
            deck.put(power_supply_consortium.getName(), power_supply_consortium);

            Card protected_habitats = new ProtectedHabitats(game);
            deck.put(protected_habitats.getName(), protected_habitats);

            Card quantum_extractor = new QuantumExtractor(game);
            deck.put(quantum_extractor.getName(), quantum_extractor);

            Card rad_suits = new RadSuits(game);
            deck.put(rad_suits.getName(), rad_suits);

            Card research = new Research(game);
            deck.put(research.getName(), research);

            Card restricted_area = new RestrictedArea(game);
            deck.put(restricted_area.getName(), restricted_area);

            Card robotic_workforce = new RoboticWorkforce(game);
            deck.put(robotic_workforce.getName(), robotic_workforce);

            Card sabotage = new Sabotage(game);
            deck.put(sabotage.getName(), sabotage);

            Card satellites = new Satellites(game);
            deck.put(satellites.getName(), satellites);

            Card security_fleet = new SecurityFleet(game);
            deck.put(security_fleet.getName(), security_fleet);

            Card space_elevator = new SpaceElevator(game);
            deck.put(space_elevator.getName(), space_elevator);

            Card space_station = new SpaceStation(game);
            deck.put(space_station.getName(), space_station);

            Card sponsors = new Sponsors(game);
            deck.put(sponsors.getName(), sponsors);

            Card standard_technology = new StandardTechnology(game);
            deck.put(standard_technology.getName(), standard_technology);

            Card tardigrades = new Tardigrades(game);
            deck.put(tardigrades.getName(), tardigrades);

            Card technology_demostration = new TechnologyDemostration(game);
            deck.put(technology_demostration.getName(), technology_demostration);

            Card terraforming_ganymede = new TerraformingGanymede(game);
            deck.put(terraforming_ganymede.getName(), terraforming_ganymede);

            Card titanium_mine = new TitaniumMine(game);
            deck.put(titanium_mine.getName(), titanium_mine);

            Card toll_station = new TollStation(game);
            deck.put(toll_station.getName(), toll_station);

            Card trans_neptune_probe = new TransNeptuneProbe(game);
            deck.put(trans_neptune_probe.getName(), trans_neptune_probe);

            Card tropical_resort = new TropicalResort(game);
            deck.put(tropical_resort.getName(), tropical_resort);

            Card vesta_shipyard = new VestaShipyard(game);
            deck.put(vesta_shipyard.getName(), vesta_shipyard);

            Card viral_enhancers = new ViralEnhancers(game);
            deck.put(viral_enhancers.getName(), viral_enhancers);

            Card virus = new Virus(game);
            deck.put(virus.getName(), virus);

            Card saturn_stystems = new SaturnSystems(game);
            corporations.put(saturn_stystems.getName(), saturn_stystems);

            Card teractor = new Teractor(game);
            corporations.put(teractor.getName(), teractor);
        }

        // Adding prelude cards
        if (prelude) {
            //TODO add prelude
        }

        // Adding colonies cards:
        if (colonies) {
            //TODO add colonies
        }

        // Adding venus cards:
        if (venus) {
            //TODO add venus
        }

        // Adding turmoil cards:
        if (turmoil) {
            //TODO add Turmoil
        }

        return deck;
    }

    /**
     * A method to create the prelude deck if the prelude expansion is in use
     *
     * @return {@link HashMap} of {@link String} and {@link Card} representing the prelude deck
     */
    HashMap<String, Card> createPreludes() {
        if (!prelude) {
            return null;
        }
        HashMap<String, Card> preludes = new HashMap<>();
        //TODO add preludes
        return preludes;
    }

    /**
     * A method to create the corporation deck of the game
     *
     * @return {@link HashMap} of {@link String} and {@link Card} representing the corporate deck
     */
    HashMap<String, Card> createCorporations() {

        HashMap<String, Card> corporations = new HashMap<>();

        Card beginner_corporation = new BeginnerCorporation(game);
        corporations.put(beginner_corporation.getName(), beginner_corporation);

        Card credicor = new Credicor(game);
        corporations.put(credicor.getName(), credicor);

        Card ecoline = new Ecoline(game);
        corporations.put(ecoline.getName(), ecoline);

        Card helion = new Helion(game);
        corporations.put(helion.getName(), helion);

        Card interplanetary_cinematics = new InterplanetaryCinematics(game);
        corporations.put(interplanetary_cinematics.getName(), interplanetary_cinematics);

        Card inventrix = new Inventrix(game);
        corporations.put(inventrix.getName(), inventrix);

        Card mining_guild = new MiningGuild(game);
        corporations.put(mining_guild.getName(), mining_guild);

        Card phobo_log = new PhoboLog(game);
        corporations.put(phobo_log.getName(), phobo_log);

        Card tharsis_republic = new TharsisRepublic(game);
        corporations.put(tharsis_republic.getName(), tharsis_republic);

        Card thorgate = new Thorgate(game);
        corporations.put(thorgate.getName(), thorgate);

        Card united_nations_mars_initiative = new UnitedNationsMarsInitiative(game);
        corporations.put(united_nations_mars_initiative.getName(), united_nations_mars_initiative);

        // Adding corporate era corporations:
        if (corporate_era || extra_corporations) {
            Card saturn_systems = new SaturnSystems(game);
            corporations.put(saturn_systems.getName(), saturn_systems);

            Card teractor = new Teractor(game);
            corporations.put(teractor.getName(), teractor);
        }

        // Adding prelude corporations:
        if (prelude) {
            //TODO add prelude corps
        }

        // Adding colonies corporations:
        if (colonies) {
            //TODO add colonies corps
        }

        // Adding venus next corporations:
        if (venus) {
            //TODO add venus corps
        }

        // Adding turmoil corporations:
        if (turmoil) {
            //TODO add turmoil corps
        }

        return corporations;
    }

    /**
     * A method to create the hidden ghost and utility card deck
     *
     * @return {@link HashMap} of {@link String} and {@link Card} representing the ghost deck of the game
     */
    HashMap<String, Card> createGhosts() {
        HashMap<String, Card> ghosts = new HashMap<>();

        Card tharsis_republic_second_effect = new TharsisRepublicGhost(game);
        ghosts.put(tharsis_republic_second_effect.getName(), tharsis_republic_second_effect);

        return ghosts;
    }

    /**
     * A method to create the awards of the game
     *
     * @return {@link HashMap} of {@link String} and {@link Card} representing the awards of the game
     */
    HashMap<String, Award> createAwards() {
        HashMap<String, Award> deck = new HashMap<>();

        switch (map) {
            case THARSIS:
                Award banker = new Banker(game);
                deck.put(banker.getName(), banker);

                Award landlord = new Landlord(game);
                deck.put(landlord.getName(), landlord);

                Award miner = new Miner(game);
                deck.put(miner.getName(), miner);

                Award scientist = new Scientist(game);
                deck.put(scientist.getName(), scientist);

                Award thermalist = new Thermalist(game);
                deck.put(thermalist.getName(), thermalist);
                break;
            case HELLAS:
                break;
            case ELYSIUM:
                break;
            default:
                Log.i("DeckConstructor", "Invalid map value");
        }

        return deck;
    }

    /**
     * A method to create the milestones of the game
     *
     * @return {@link HashMap} of {@link String} and {@link Card} representing the milestones of the game
     */
    HashMap<String, Card> createMilestones() {
        HashMap<String, Card> deck = new HashMap<>();

        switch (map) {
            case THARSIS:
                Card builder = new Builder(game);
                deck.put(builder.getName(), builder);

                Card gardener = new Gardener(game);
                deck.put(gardener.getName(), gardener);

                Card mayor = new Mayor(game);
                deck.put(mayor.getName(), mayor);

                Card planner = new Planner(game);
                deck.put(planner.getName(), planner);

                Card terraformer = new Terraformer(game);
                deck.put(terraformer.getName(), terraformer);
                break;
            case HELLAS:
                break;
            case ELYSIUM:
                break;
            default:
                Log.i("DeckConstructor", "Invalid map value");
        }
        return deck;
    }
}
