package katebulka.digamma.mixin.client;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(
        targets = "dev.emi.emi.screen.EmiScreenManager$SidebarPanel",
        remap = false
)
public class SidebarPanelMixin {
//    @Inject(method = "render", at = @At("HEAD"))
//    private void getSidebarType(EmiDrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
//        EmiScreenManager.SidebarPanel self = (EmiScreenManager.SidebarPanel)(Object)this;
//
//        SidebarType type = self.getType();
//        HiderHelper.setCurrentSidebarType(type);
//    }
//
//    @Inject(method = "render", at = @At("RETURN"))
//    private void clearSidebarType(CallbackInfo ci) {
//        HiderHelper.clear();
//    }
}
