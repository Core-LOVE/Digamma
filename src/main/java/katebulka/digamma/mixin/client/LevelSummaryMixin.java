package katebulka.digamma.mixin.client;

import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.LevelSummary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LevelSummary.class)
public abstract class LevelSummaryMixin {
    @ModifyArg(
            method = "createInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/chat/MutableComponent;append(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/MutableComponent;"
            ),
            index = 0
    )
    private Component replaceSpaceWithEmpty(Component component) {
        return (component == CommonComponents.SPACE) ? Component.empty() : component;
    }
}