package katebulka.digamma.mixin.client;

import com.blamejared.ambientenvironment.AmbientEnvironmentCommon;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

@Mixin(
        value = AmbientEnvironmentCommon.class,
        remap = false
)
public abstract class AmbientEnvironmentCommonMixin {
    @Unique
    private static final List<Integer> ANOTHER_OCTAVES = IntStream.rangeClosed(0, 2).boxed().toList();

    @Unique
    private static final PerlinSimplexNoise ANOTHER_NOISE =
            new PerlinSimplexNoise(new XoroshiroRandomSource("YET_ANOTHER_COLOR_RANDOM".hashCode()), ANOTHER_OCTAVES);

    @Shadow
    public static double curve(double start, double end, double amount) {
        throw new AssertionError();
    }

    @Shadow
    public static int blend(int color1, int color2, float ratio) {
        throw new AssertionError();
    }

    @Shadow
    public static double remap(double value, double currentLow, double currentHigh, double newLow, double newHigh) {
        throw new AssertionError();
    }

    @Unique
    private static final float hue_blend = 1.0f / 360.0f;

    @Overwrite
    private static int modifyColour(PerlinSimplexNoise generator, ColorResolver resolver, Biome biome, double x, double z, double scale, double darkness) {
        int base = resolver.getColor(biome, x, z);
        double value = generator.getValue(x / scale, z / scale, false);
        value = curve((double)0.0F, (double)1.0F, remap(value, (double)-3.0F, (double)3.0F, (double)0.0F, (double)1.0F)) * darkness;

        double noiseval = remap(ANOTHER_NOISE.getValue(x / 32.0, z / 32.0, false),
                -3, 3, 0, 1);
        float hue = (float) ((value + noiseval) * 360.0f) % 360.0f;

        int blending = Color.HSBtoRGB(hue * hue_blend, 1.0f, (float) darkness);

        return blend(base, blending, (float)value);
    }
}
