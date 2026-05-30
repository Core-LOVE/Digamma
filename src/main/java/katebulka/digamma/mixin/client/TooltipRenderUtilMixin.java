package katebulka.digamma.mixin.client;

import com.iafenvoy.tooltipsreforged.BuiltinTooltips;
import com.iafenvoy.tooltipsreforged.api.TooltipsReforgeEntrypoint;
import com.iafenvoy.tooltipsreforged.config.TooltipReforgedConfig;
import com.iafenvoy.tooltipsreforged.render.TooltipProviders;
import com.iafenvoy.tooltipsreforged.render.TooltipsRenderHelper;
import com.iafenvoy.tooltipsreforged.util.ExtendedTextVisitor;
import com.iafenvoy.tooltipsreforged.util.TextUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTextTooltip;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2ic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(
        value = TooltipRenderUtil.class,
        remap = true
)
public abstract class TooltipRenderUtilMixin {
    @Unique private static final ResourceLocation DEFAULT_BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/tooltip/background.png");
    @Unique private static final ResourceLocation DEFAULT_FRAME_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/tooltip/frame.png");

    @Unique
    private static ResourceLocation getBackgroundTexture(@Nullable ResourceLocation texture) {
        return texture != null && !texture.getPath().isEmpty() ? texture.withPath((name) -> "textures/gui/sprites/tooltip/" + name + "_background.png") : DEFAULT_BACKGROUND_TEXTURE;
    }

    @Unique
    private static ResourceLocation getFrameTexture(@Nullable ResourceLocation texture) {
        return texture != null && !texture.getPath().isEmpty() ? texture.withPath((name) -> "textures/gui/sprites/tooltip/" + name + "_frame.png") : DEFAULT_FRAME_TEXTURE;
    }

    @Unique
    private static void render(GuiGraphics context, int x, int y, int width, int height, int z) {
        ResourceLocation texture = null;

        int i = x - 4;
        int j = y - 4;
        int k = width + 8;
        int l = height + 8;

        context.pose().pushPose();
        int bgColor = (Integer)TooltipReforgedConfig.INSTANCE.misc.backgroundColor.getValue();

        if ((Boolean)TooltipReforgedConfig.INSTANCE.misc.useImageBackground.getValue()) {
            context.pose().translate(0.0F, 0.0F, (float)z);
            TooltipsRenderHelper.drawNineSlicedTexture(context, getBackgroundTexture(texture), i - 9, j - 9, k + 18, l + 18, 10, 100, 100);
        } else {
            context.fill(i, j - 1, i + k, j, z, bgColor);
            context.fill(i, j + l, i + k, j + l + 1, z, bgColor);
            context.fill(i, j, i + k, j + l, z, (Integer)TooltipReforgedConfig.INSTANCE.misc.backgroundColor.getValue());
            context.fillGradient(i - 1, j, i, j + l, z, bgColor, bgColor);
            context.fillGradient(i + k, j, i + k + 1, j + l, z, bgColor, bgColor);
        }

        context.pose().popPose();
    }

    @Inject(
            method = "renderTooltipBackground",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onRenderInject(GuiGraphics graphics, int x, int y, int width, int height, int z, CallbackInfo callback)
    {
//        render(graphics, x, y, width, height, z);
        callback.cancel();
    }
}
