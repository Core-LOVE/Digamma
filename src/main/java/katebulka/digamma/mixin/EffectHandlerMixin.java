package katebulka.digamma.mixin;

import com.feliscape.gladius.content.event.EffectHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = EffectHandler.class,
        remap = false
)
public abstract class EffectHandlerMixin {
    @Inject(method = "onEffectRemoved", at = @At("HEAD"), cancellable = true)
    private static void onEffectRemovedInject(MobEffectEvent.Remove event, CallbackInfo ci) {
        if (event.getEntity().level().isClientSide) {
            ci.cancel();
        }
    }
}
