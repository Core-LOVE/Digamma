package katebulka.digamma.registry;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import katebulka.digamma.DigammaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

public class ModBiomes {
    public static ResourceKey<Biome> addBiome(String id,
                                float[] temperature,
                                float[] humidity,
                                float[] continentalness,
                                float[] erosion,
                                float[] depth,
                                float[] weirdness,
                                float offset
    ) {
        ResourceKey<Biome> BIOME = ResourceKey.create(
                Registries.BIOME, ResourceLocation.fromNamespaceAndPath("omphalos", id));

        BiomePlacement.addOverworld(BIOME,
                Climate.parameters(
                        Climate.Parameter.span(temperature[0], temperature[1]),
                        Climate.Parameter.span(humidity[0], humidity[1]),
                        Climate.Parameter.span(continentalness[0], continentalness[1]),
                        Climate.Parameter.span(erosion[0], erosion[1]),
                        Climate.Parameter.span(depth[0], depth[1]),
                        Climate.Parameter.span(weirdness[0], weirdness[1]),
                        offset)
        );

        return BIOME;
    }

    public static ResourceKey<Biome> addSubBiome(ResourceKey<Biome> og_biome, String id, Criterion criterion) {
        ResourceKey<Biome> BIOME = ResourceKey.create(
                Registries.BIOME, ResourceLocation.fromNamespaceAndPath("omphalos", id));

        BiomePlacement.addSubOverworld(og_biome, BIOME, criterion);

        return BIOME;
    }

    public static ResourceKey<Biome> replaceBiome(ResourceKey<Biome> og_biome, String id, double proportion) {
        ResourceKey<Biome> BIOME = ResourceKey.create(
                Registries.BIOME, ResourceLocation.fromNamespaceAndPath("omphalos", id));

        BiomePlacement.replaceOverworld(og_biome, BIOME, proportion);

        return BIOME;
    }
//    private static void addSurfaceRule(ResourceKey<Biome> biome,) {
//
//    }
//
//    private static void initializeSurfaceRules() {
//        addSurfaceRule("boggy_grounds");
//    }

    public static void initialize() {
        // EXAMPLE
//        addBiome("EXAMPLE",
//                new float[]{-1.0F, 1.0F}, // temp (cold-hot)
//                new float[]{-1.0F, 1.0F}, // humidity (dry-wet)
//                new float[]{-1.0F, 1.0F}, // continentalness (deep oceanic-land)
//                new float[]{-1.0F, 1.0F}, // erosion (mountain-plains)
//                new float[]{-1.0F, 1.0F}, // depth (sky-caves) (0 is sea y)
//                new float[]{-1.0F, 1.0F}, // weird (???)
//                0.0F // offset
//        );

        float stone_caves_border = 0.64F;
        float stone_caves_rarity = 0.4F;
        float stone_caves_level = 0.15F;

        addBiome("granite_caves",
                new float[]{0.5F, 1.5F}, // temp (cold-hot)
                new float[]{-1.0F, 0.4F}, // humidity (dry-wet)
                new float[]{-0.2F, 1.0F}, // continentalness (deep oceanic-land)
                new float[]{-1.0F, 0.5F}, // erosion (mountain-plains)
                new float[]{stone_caves_level, stone_caves_border}, // depth (sky-caves) (0 is sea y)
                new float[]{-1.0F, 1.0F}, // weird (???)
                stone_caves_rarity // offset
        );

        addBiome("marble_caves",
                new float[]{0.0F, 0.9F}, // temp (cold-hot)
                new float[]{0.0F, 1.0F}, // humidity (dry-wet)
                new float[]{-0.5F, 1.0F}, // continentalness (deep oceanic-land)
                new float[]{-0.8F, 0.8F}, // erosion (mountain-plains)
                new float[]{stone_caves_level, stone_caves_border}, // depth (sky-caves) (0 is sea y)
                new float[]{-1.0F, 1.0F}, // weird (???)
                stone_caves_rarity // offset
        );

        addBiome("bluestone_caves",
                new float[]{-1.0F, 0.0F}, // temp (cold-hot)
                new float[]{-0.5F, 0.8F}, // humidity (dry-wet)
                new float[]{-0.5F, 1.0F}, // continentalness (deep oceanic-land)
                new float[]{-1.0F, 0.4F}, // erosion (mountain-plains)
                new float[]{stone_caves_level, stone_caves_border}, // depth (sky-caves) (0 is sea y)
                new float[]{-1.0F, 1.0F}, // weird (???)
                stone_caves_rarity // offset
        );

        addBiome("tuff_caves",
                new float[]{-0.5F, 1.0F}, // temp (cold-hot)
                new float[]{-0.5F, 0.8F}, // humidity (dry-wet)
                new float[]{-0.8F, 0.8F}, // continentalness (deep oceanic-land)
                new float[]{-0.8F, 0.8F}, // erosion (mountain-plains)
                new float[]{0.5F, 1.1F}, // depth (sky-caves) (0 is sea y)
                new float[]{-1.0F, 1.0F}, // weird (???)
                stone_caves_rarity // offset
        );

        // OTHER CAVE BIOMES
        addBiome("boggy_grounds",
                new float[]{-0.5F, 0.9F}, // temp (cold-hot)
                new float[]{-0.6F, 1.0F}, // humidity (dry-wet)
                new float[]{-0.2F, 1.0F}, // continentalness (deep oceanic-land)
                new float[]{-0.4F, 0.5F}, // erosion (mountain-plains)
                new float[]{0.15F, 0.45F}, // depth (sky-caves) (0 is sea y)
                new float[]{-0.3F, 0.4F}, // weird (???)
                0.5F // offset
        );


        ResourceKey<Biome> topsoil = addBiome("topsoil",
                new float[]{0.22F, 0.9F}, // temp (cold-hot)
                new float[]{0.25F, 0.8F}, // humidity (dry-wet)
                new float[]{0.0F, 0.9F}, // continentalness (deep oceanic-land)
                new float[]{-0.5F, 0.5F}, // erosion (mountain-plains)
                new float[]{0.05F, 0.25F}, // depth (sky-caves) (0 is sea y)
                new float[]{-0.5F, 0.5F}, // weird (???)
                0.2F // offset
        );

        addSubBiome(topsoil, "subsoil", CriterionBuilder.ratioMax(RatioTargets.EDGE, 0.25F));
//        addSubBiome(topsoil, "uproots", CriterionBuilder.deviationMin(BiomeParameterTargets.HUMIDITY, 0.75F));

        replaceBiome(topsoil, "uproots", 0.1d);

        addSubBiome(Biomes.WINDSWEPT_FOREST, "mountain_edge", CriterionBuilder.ratioMax(RatioTargets.EDGE, 0.25F));
        replaceBiome(Biomes.FOREST, "rainforest", 0.1d);
        replaceBiome(Biomes.SAVANNA, "shrubland", 0.1d);

        addSubBiome(Biomes.SNOWY_PLAINS, "snowy_forest", CriterionBuilder.ratioMax(RatioTargets.EDGE, 0.32F));
//        initializeSurfaceRules();
    }
}
