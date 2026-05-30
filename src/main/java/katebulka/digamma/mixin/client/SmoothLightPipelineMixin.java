package katebulka.digamma.mixin.client;

import net.caffeinemc.mods.sodium.client.model.light.data.LightDataAccess;
import net.caffeinemc.mods.sodium.client.model.light.data.QuadLightData;
import net.caffeinemc.mods.sodium.client.model.light.flat.FlatLightPipeline;
import net.caffeinemc.mods.sodium.client.model.light.smooth.SmoothLightPipeline;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.buffers.ChunkVertexConsumer;
import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(
        value = SmoothLightPipeline.class,
        remap = false,
        priority = 1500
)
public abstract class SmoothLightPipelineMixin {
    @Shadow
    private LightDataAccess lightCache;

    @Unique
    private final QuadLightData flatLightData = new QuadLightData();

    @Unique
    private static final float blend = 0.6f;

    @Inject(method = "calculate", at = @At("HEAD"), remap = false)
    private void onCalculateHead(ModelQuadView quad, BlockPos pos, QuadLightData out,
                                 Direction cullFace, Direction lightFace,
                                 boolean shade, boolean enhanced, CallbackInfo ci) {
        calculateFlat(quad, pos, flatLightData, cullFace, lightFace, shade, enhanced);
    }

    @Inject(method = "calculate", at = @At("RETURN"), remap = false)
    private void onCalculateReturn(ModelQuadView quad, BlockPos pos, QuadLightData out,
                                   Direction cullFace, Direction lightFace,
                                   boolean shade, boolean enhanced, CallbackInfo ci) {
        for (int i = 0; i < 4; i++) {
            int smoothLight = out.lm[i];
            int flatLight   = flatLightData.lm[i];
            int smoothSky = (smoothLight >> 16) & 0xFF;
            int smoothBlock = smoothLight & 0xFF;
            int flatSky = (flatLight >> 16) & 0xFF;
            int flatBlock = flatLight & 0xFF;

            int blendedSky = (int) (smoothSky * blend + flatSky * (1 - blend));
            int blendedBlock = (int) (smoothBlock * blend + flatBlock * (1 - blend));
            out.lm[i] = (blendedSky << 16) | blendedBlock;

            out.br[i] = out.br[i] * blend + flatLightData.br[i] * (1 - blend);
        }
    }

    @Unique
    private void calculateFlat(ModelQuadView quad, BlockPos pos, QuadLightData out,
                               Direction cullFace, Direction lightFace,
                               boolean shade, boolean enhanced) {
        int lightmap;
        float shadeFactor;

        if (cullFace != null) {
            lightmap = getOffsetLightmap(pos, cullFace);
            shadeFactor = lightCache.getLevel().getShade(lightFace, shade);
        } else {
            int flags = quad.getFlags();
            boolean fullBright = ((flags & 2) != 0) && LightDataAccess.unpackFC(lightCache.get(pos));
            if ((flags & 4) == 0 && !fullBright) {
                lightmap = LightDataAccess.getEmissiveLightmap(lightCache.get(pos));
                shadeFactor = enhanced ?
                        PlatformBlockAccess.getInstance().getNormalVectorShade(quad, lightCache.getLevel(), shade) :
                        lightCache.getLevel().getShade(lightFace, shade);
            } else {
                lightmap = getOffsetLightmap(pos, lightFace);
                shadeFactor = lightCache.getLevel().getShade(lightFace, shade);
            }
        }

        Arrays.fill(out.lm, lightmap);
        Arrays.fill(out.br, shadeFactor);
    }

    @Unique
    private int getOffsetLightmap(BlockPos pos, Direction face) {
        int word = lightCache.get(pos);
        if (LightDataAccess.unpackEM(word)) {
            return 15728880;
        }

        int adjWord = lightCache.get(pos, face);
        return LightTexture.pack(
                Math.max(LightDataAccess.unpackBL(adjWord), LightDataAccess.unpackLU(word)),
                LightDataAccess.unpackSL(adjWord)
        );
    }
}
