package katebulka.digamma.mixin.client;

import com.evandev.advancement_enhancement.gui.EnhancedAdvancementTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = EnhancedAdvancementTab.class
)
public abstract class EnhancedAdvancementTabMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        EnhancedAdvancementTab self = (EnhancedAdvancementTab)(Object)this;

        self.bgWidth = 256;
        self.bgHeight = 256;
    }
}
