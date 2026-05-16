package katebulka.digamma.mixin.client;

import net.minecraft.world.level.storage.LevelVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LevelVersion.class)
public abstract class LevelVersionMixin {
    @Overwrite
    public String minecraftVersionName() {
        return "β2";
    }
}
