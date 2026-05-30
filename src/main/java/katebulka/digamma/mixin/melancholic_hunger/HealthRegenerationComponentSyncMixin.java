package katebulka.digamma.mixin.melancholic_hunger;

import antigers.melancholic_hunger.components.HealthRegenerationComponent;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = HealthRegenerationComponent.class,
        remap = false
)
public abstract class HealthRegenerationComponentSyncMixin {
    @Shadow
    @Final
    private net.minecraft.world.entity.player.Player player;

    @Inject(method = "sync", at = @At("HEAD"), cancellable = true, remap = false)
    private void onSync(CallbackInfo ci) {
        if (player instanceof ServerPlayer sp) {
            sp.syncData(antigers.melancholic_hunger.components.PlayerComponents.HEALTH_REGENERATION);
            ci.cancel();
        } else {
            ci.cancel();
        }
    }
}
