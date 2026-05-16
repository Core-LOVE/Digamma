package katebulka.digamma.registry;

import katebulka.digamma.DigammaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    private static TagKey<Block> register(String name) {
        return TagKey.create(Registries.BLOCK, DigammaMod.id(name));
    }

    public static final TagKey<Block> REPLACEABLE = register("replaceable");
}
