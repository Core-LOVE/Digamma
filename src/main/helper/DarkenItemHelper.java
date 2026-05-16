package katebulka.digamma.helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;

public class DarkenItemHelper {
    private static final Minecraft client = Minecraft.getInstance();

    private static final class DarkVertexConsumer implements VertexConsumer {
        private VertexConsumer parent;

        void setParent(VertexConsumer parent) {
            this.parent = parent;
        }

        @Override
        public VertexConsumer addVertex(float x, float y, float z) {
            parent.addVertex(x, y, z);
            return this;
        }

        @Override
        public VertexConsumer setColor(int r, int g, int b, int a) {
            parent.setColor(0, 0, 0, a);
            return this;
        }

        @Override
        public VertexConsumer setColor(int color) {
            int alpha = (color >> 24) & 0xFF;
            parent.setColor(0, 0, 0, alpha);
            return this;
        }

        @Override
        public VertexConsumer setColor(float r, float g, float b, float a) {
            parent.setColor(0, 0, 0, a);
            return this;
        }

        @Override
        public VertexConsumer setUv(float u, float v) {
            parent.setUv(u, v);
            return this;
        }

        @Override
        public VertexConsumer setUv1(int u, int v) {
            parent.setUv1(u, v);
            return this;
        }

        @Override
        public VertexConsumer setUv2(int u, int v) {
            parent.setUv2(u, v);
            return this;
        }

        @Override
        public VertexConsumer setNormal(float x, float y, float z) {
            parent.setNormal(x, y, z);
            return this;
        }

        @Override
        public void putBulkData(PoseStack.Pose pose, BakedQuad quad, float[] brightness, float red, float green, float blue, float alpha, int[] lightmap, int packedOverlay, boolean readAlpha) {
            parent.putBulkData(pose, quad, brightness, red, green, blue, alpha, lightmap, packedOverlay, readAlpha);
        }

        @Override
        public VertexConsumer setWhiteAlpha(int alpha) {
            parent.setWhiteAlpha(alpha);
            return this;
        }

        @Override
        public VertexConsumer setLight(int packedLight) {
            parent.setLight(packedLight);
            return this;
        }

        @Override
        public VertexConsumer setOverlay(int packedOverlay) {
            parent.setOverlay(packedOverlay);
            return this;
        }
    }

    public static final class BufferSource implements MultiBufferSource {
        private final DarkVertexConsumer consumer = new DarkVertexConsumer();
        private MultiBufferSource parent;

        public void setParent(MultiBufferSource parent) {
            this.parent = parent;
        }

        @Override
        public VertexConsumer getBuffer(RenderType type) {
            consumer.setParent(parent.getBuffer(type));
            return consumer;
        }
    }

    public static void render(ItemStack stack, PoseStack pose, MultiBufferSource buffer, int light, int overlay) {
        if (stack.isEmpty()) return;

        ItemRenderer ir = client.getItemRenderer();
        BakedModel model = ir.getModel(stack, null, null, 0);

        model = ClientHooks.handleCameraTransforms(pose, model, ItemDisplayContext.GUI, false);
        pose.translate(-0.5F, -0.5F, -0.5F);

        for (BakedModel passModel : model.getRenderPasses(stack, true)) {
            for (RenderType type : passModel.getRenderTypes(stack, true)) {
                VertexConsumer parent = buffer.getBuffer(type);

                VertexConsumer consumer = new DarkVertexConsumer();

                ir.renderModelLists(passModel, stack, light, overlay, pose, consumer);
            }
        }
    }
}
