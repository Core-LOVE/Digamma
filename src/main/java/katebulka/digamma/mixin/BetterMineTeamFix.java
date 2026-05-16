package katebulka.digamma.mixin;

import com.i113w.better_mine_team.client.gui.GuiEventHandler;
import com.i113w.better_mine_team.common.config.BMTConfig;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = GuiEventHandler.class,
        remap = false
)
public abstract class BetterMineTeamFix {
    @Inject(method = "onScreenInit", at = @At("HEAD"), cancellable = true, remap = false)
    private void onScreenInit(ScreenEvent.Init.Post event, CallbackInfo ci) {
        ModConfigSpec spec = BMTConfig.CONFIG;

        if (spec == null || !spec.isLoaded()) {
            ci.cancel();
        }
    }
}
