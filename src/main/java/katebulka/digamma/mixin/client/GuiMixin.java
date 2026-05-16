package katebulka.digamma.mixin.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow
    public Minecraft minecraft;

    @Shadow
    public boolean isExperienceBarVisible() {
        return this.minecraft.player.jumpableVehicle() == null && this.minecraft.gameMode.hasExperience();
    }

    @Shadow
    public Font getFont() {
        return this.minecraft.font;
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void noExperienceLevel(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        int i = this.minecraft.player.totalExperience;
        if (this.isExperienceBarVisible() && i > 0) {
//            this.minecraft.getProfiler().push("expLevel");
            String string = String.format(
                    Component.translatable("deathScreen.score.value").getString(),
                    i
            );

            int j = (guiGraphics.guiWidth() - this.getFont().width(string)) / 2;
            int k = guiGraphics.guiHeight() - 31;
            guiGraphics.drawString(this.getFont(), string, j + 1, k, 0, false);
            guiGraphics.drawString(this.getFont(), string, j - 1, k, 0, false);
            guiGraphics.drawString(this.getFont(), string, j, k + 1, 0, false);
            guiGraphics.drawString(this.getFont(), string, j, k - 1, 0, false);
            guiGraphics.drawString(this.getFont(), string, j, k, 12632256, false);
            this.minecraft.getProfiler().pop();
        }

        ci.cancel();
    }
}
