package katebulka.digamma.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.client.render.entity.layers.AngelItemLayer;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(
        value = AngelItemLayer.class,
        remap = false
)
public abstract class AngelItemLayerMixin {
    @Overwrite
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, WeepingAngel livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}
