package katebulka.digamma.registry;

import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.prometheus.api.Fire;
import it.crystalnest.prometheus.api.FireManager;
import it.crystalnest.prometheus.api.FireRegistrar;
import it.crystalnest.prometheus.api.block.CustomCampfireBlock;
import it.crystalnest.prometheus.api.block.CustomFireBlock;
import it.crystalnest.prometheus.api.block.CustomTorchBlock;
import it.crystalnest.prometheus.api.block.CustomWallTorchBlock;
import katebulka.digamma.DigammaClient;
import katebulka.digamma.DigammaMod;
import katebulka.digamma.content.block.RedstoneFireBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.material.MapColor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ModFires {
    @FunctionalInterface
    private interface FireInterface {
        void make(Fire.Builder a);
    }

    private static ResourceLocation registerFire(String name, MapColor color, FireInterface construct) {
        ResourceLocation id = DigammaMod.id(name);

        Fire.Builder builder = FireManager.fireBuilder(id)
                .setDefaultComponents();

        construct.make(builder);

        FireManager.registerFire(builder.build());

        TagKey<Block> tag = TagKey.create(Registries.BLOCK, id.withPath(id.getPath() + "_fire_base_blocks"));

        FireRegistrar.registerParticle(id);
        FireRegistrar.registerFireSource(id, tag, color, CustomFireBlock::new);

        return id;
    }

    private static ResourceLocation registerRedstoneFire(String name, MapColor color, FireInterface construct) {
        ResourceLocation id = DigammaMod.id(name);

        Fire.Builder builder = FireManager.fireBuilder(id)
                .setDefaultComponents();

        construct.make(builder);

        FireManager.registerFire(builder.build());

        TagKey<Block> tag = TagKey.create(Registries.BLOCK, id.withPath(id.getPath() + "_fire_base_blocks"));

        FireRegistrar.registerParticle(id);
        FireRegistrar.registerFireSource(id, tag, color, RedstoneFireBlock::new);
        FireRegistrar.registerFireCharge(id);

        return id;
    }

    public static void initialize() {
        registerFire("copper", MapColor.COLOR_GREEN, (builder) -> {
            builder.setDamage(0.5F);
        });

        FireRegistrar.registerDefaultFireComponents(
                registerFire("brimstone", MapColor.COLOR_BLUE, (builder) -> {
                    builder.setDamage(3.0F);
                }),

                Fire.Component.CAMPFIRE_BLOCK, Fire.Component.CAMPFIRE_ITEM,
                Fire.Component.TORCH_BLOCK, Fire.Component.TORCH_ITEM,
                Fire.Component.LANTERN_BLOCK, Fire.Component.LANTERN_ITEM
        );

        FireRegistrar.registerDefaultFireComponents(
                registerFire("cupid", MapColor.COLOR_PINK, (builder) -> {
                    builder.setDamage(0.0F);
                }),

                Fire.Component.CAMPFIRE_BLOCK, Fire.Component.CAMPFIRE_ITEM,
                Fire.Component.TORCH_BLOCK, Fire.Component.TORCH_ITEM,
                Fire.Component.LANTERN_BLOCK, Fire.Component.LANTERN_ITEM
        );

        FireRegistrar.registerDefaultFireComponents(
                registerRedstoneFire("redstone", MapColor.COLOR_RED, (builder) -> {
                    builder.setDamage(1.5F);
                }),

                Fire.Component.CAMPFIRE_BLOCK, Fire.Component.CAMPFIRE_ITEM
        );
    }
}
