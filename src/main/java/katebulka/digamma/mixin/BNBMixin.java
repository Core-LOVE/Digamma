package katebulka.digamma.mixin;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.mcreator.builds_n_barrens.BuildsNBarrensMod;
import net.mcreator.builds_n_barrens.init.BuildsNBarrensModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

@Mixin(BuildsNBarrensModBiomes.class)
public abstract class BNBMixin {
    @Overwrite
    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {

    }
}
