package katebulka.digamma.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.client.render.entity.WeepingAngelRenderer;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(
        value = WeepingAngelRenderer.class,
        remap = false,
        priority = 1500
)
public abstract class WeepingAngelRendererMixin {
    @Redirect(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;")
    )
    private Item replaceItem(
            ItemStack itemStack, WeepingAngel pEntity, float pEntityYaw, float pPartialTicks,
            PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        return Items.CALCITE;
    }
}
