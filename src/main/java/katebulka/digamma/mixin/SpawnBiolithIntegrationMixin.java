package katebulka.digamma.mixin;

import com.ninni.spawn.compat.biolith.BiolithIntegration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(
        value = BiolithIntegration.class,
        remap = false
)
public abstract class SpawnBiolithIntegrationMixin {
    @Overwrite
    public static void register() {

    }
}
