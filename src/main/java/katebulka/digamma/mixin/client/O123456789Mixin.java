package katebulka.digamma.mixin.client;

import meow.binary.o123456789.O123456789;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
    value = O123456789.class,
    priority = 6000
)
public abstract class O123456789Mixin {
    @Inject(method = "drawOutline", at = @At("HEAD"), cancellable = true)
    public static void drawOutline(Font font, FormattedCharSequence text, float x, float y, int color, boolean dropShadow,
               Matrix4f matrix, MultiBufferSource buffer, Font.DisplayMode displayMode, int backgroundColor, int packedLightCoords,
               CallbackInfoReturnable<Integer> cir) {
        ResourceLocation[] rl = new ResourceLocation[1];
        text.accept(((i, style, j) -> {
            rl[0] = style.getFont();
            return false;
        }));
        if (rl[0] == null || !(rl[0].equals(O123456789.FONT) || rl[0].equals(O123456789.FONT_TINY))) {
            return;
        }

        int alpha = 128;

        if (dropShadow) {
            alpha = (alpha >> 24) & 0xFF;
            int red   = (color >> 16) & 0xFF;
            int green = (color >> 8)  & 0xFF;
            int blue  = color         & 0xFF;

            float darkenFactor = 0.22f;

            red   = (int) (red   * darkenFactor) & 0xFF;
            green = (int) (green * darkenFactor) & 0xFF;
            blue  = (int) (blue  * darkenFactor) & 0xFF;

            int shadowColor = (alpha << 24) | (red << 16) | (green << 8) | blue;

            Matrix4f matrix4f = new Matrix4f(matrix);
            matrix4f.translate(0,0,0.1f);
            font.drawInBatch8xOutline(text, x, y, color, shadowColor, matrix4f, buffer, packedLightCoords);

            cir.setReturnValue(font.width(text)+1);
        }
    }
}
