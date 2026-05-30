package katebulka.digamma.mixin.client;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import com.nozz.it.client.render.TypingOverlay;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = TypingOverlay.class,
        remap = false
)
public abstract class TypingOverlayMixin {
    @Inject(
            method = "render",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private static void renderInject(GuiGraphics context, float tickDelta, CallbackInfo info) {
        if (BetterF1.state != HUDState.ALL_VISIBLE) {
            info.cancel();
        }
    }
}
