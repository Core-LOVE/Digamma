package katebulka.digamma.mixin.client;

import dev.emi.emi.api.stack.ItemEmiStack;
import org.spongepowered.asm.mixin.Mixin;
//import tfar.dei.DEIConfig;
//import tfar.dei.client.DiscoveredItems;

@Mixin(
        value = ItemEmiStack.class,
        remap = false
)
public class ItemEmiStackMixin {
//    @Shadow
//    private static Minecraft client;
//
//    @Unique
//    private static final DarkenItemHelper.BufferSource buffer = new DarkenItemHelper.BufferSource();
//
//    @Unique
//    private static boolean isDecorated(ItemStack stack) {
//        if (stack.hasFoil() || stack.isEnchanted()) {
//            return true;
//        }
//
//        return false;
//    }
//
//    @Unique
//    private static ItemStack getNormalStack(ItemStack stack) {
//        if (isDecorated(stack)) {
//            ItemStack new_stack = new ItemStack(
//                    stack.getItem(),
//                    stack.getCount()
//            );
//
//            new_stack.remove(DataComponents.ENCHANTMENTS);
//            new_stack.remove(DataComponents.STORED_ENCHANTMENTS);
//            new_stack.remove(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
//
//            return new_stack;
//        }
//
//        return stack;
//    }
//
//    @Inject(method = "renderForBatch", at = @At("HEAD"), cancellable = true)
//    private void renderForBatchDark(MultiBufferSource vcp, GuiGraphics draw, int x, int y, int z, float delta, CallbackInfo ci) {
//        ItemEmiStack self = (ItemEmiStack)(Object)this;
//        ItemStack stack = self.getItemStack();
//
//        if (HiderHelper.canDarken(stack)) {
//            return;
//        }
//
//        ci.cancel();
//
//        ItemRenderer ir = client.getItemRenderer();
//        BakedModel model = ir.getModel(stack, null, null, 0);
//
//        PoseStack pose = draw.pose();
//
//        pose.pushPose();
//        pose.translate(x, y, 100.0f + z + (model.isGui3d() ? 50 : 0));
//        pose.translate(8.0, 8.0, 0.0);
//        pose.scale(16.0f, -16.0f, 16.0f);
//
//        DarkenItemHelper.render(stack, pose, vcp, 0, OverlayTexture.NO_OVERLAY);
//
//        pose.popPose();
//    }
//
//    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
//    private void renderDark(GuiGraphics draw, int x, int y, float delta, int flags, CallbackInfo ci) {
//        ItemEmiStack self = (ItemEmiStack)(Object)this;
//        ItemStack stack = self.getItemStack();
//
//        if (HiderHelper.canDarken(stack)) {
//            return;
//        }
//
//        stack = getNormalStack(stack);
//        ci.cancel();
//
//        ItemRenderer ir = Minecraft.getInstance().getItemRenderer();
//        BakedModel model = ir.getModel(stack, null, null, 0);
//
//        buffer.setParent(draw.bufferSource());
//
//        PoseStack pose = draw.pose();
//        pose.pushPose();
//        pose.translate(x, y, 100.0f + (model.isGui3d() ? 50 : 0));
//        pose.translate(8.0, 8.0, 0.0);
//        pose.scale(16.0f, -16.0f, 16.0f);
//
//        ir.render(stack, ItemDisplayContext.GUI, false, pose, buffer,
//                LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, model);
//
//        pose.popPose();
//
//        EmiDrawContext context = EmiDrawContext.wrap(draw);
//
//        if ((flags & EmiIngredient.RENDER_ICON) != 0) {
//            draw.renderItemDecorations(client.font, stack, x, y, "");
//        }
//
//        if ((flags & EmiIngredient.RENDER_AMOUNT) != 0) {
//            String count = self.getAmount() != 1 ? String.valueOf(self.getAmount()) : "";
//            EmiRenderHelper.renderAmount(context, x, y, EmiPort.literal(count));
//        }
//
//        if ((flags & EmiIngredient.RENDER_REMAINDER) != 0) {
//            EmiRender.renderRemainderIcon(self, context.raw(), x, y);
//        }
//    }
}
