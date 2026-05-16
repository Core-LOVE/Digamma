package katebulka.digamma.mixin.client;

import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import moe.prwk.emiffect.EMIffectPlugin;
import moe.prwk.emiffect.recipes.MobEffectInfo;
import moe.prwk.emiffect.util.MobEffectEmiStack;
import moe.prwk.emiffect.util.resources.ExtraAppenderLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(EMIffectPlugin.class)
public abstract class EMIffectPluginMixin {
    @Shadow
    public static EmiRecipeCategory CATEGORY;

    @Overwrite
    public void register(@NotNull EmiRegistry registry) {
        registry.addCategory(CATEGORY);
        List<ExtraAppenderLoader.ExtraAppender> appenders = ExtraAppenderLoader.getAppenders();

        for(MobEffect effect : BuiltInRegistries.MOB_EFFECT) {
            MobEffectEmiStack stack = new MobEffectEmiStack(effect);
            MobEffectInfo recipe = new MobEffectInfo(effect, stack);
            recipe.addFromAppenders(appenders);
            registry.addRecipe(recipe);
            registry.addEmiStack(stack);
        }
    }
}
