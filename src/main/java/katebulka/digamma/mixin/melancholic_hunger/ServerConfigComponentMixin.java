package katebulka.digamma.mixin.melancholic_hunger;

import antigers.melancholic_hunger.components.ServerConfigComponent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = ServerConfigComponent.class,
        remap = false
)
public abstract class ServerConfigComponentMixin {
    @Inject(method = "syncAllPlayersExceptOf", at = @At("HEAD"), cancellable = true)
    private static void onSyncAllPlayersExceptOf(int ignoredPlayerId, CallbackInfo ci) {
        if (ServerLifecycleHooks.getCurrentServer() == null) {
            ci.cancel();
        }
    }
}
