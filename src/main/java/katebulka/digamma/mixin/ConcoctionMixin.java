package katebulka.digamma.mixin;

import net.mcreator.concoction.init.ConcoctionModDataComponents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(
        value = ConcoctionModDataComponents.class,
        remap = false
)
public abstract class ConcoctionMixin {
    @SubscribeEvent
    @Overwrite
    public static void modifyComponents(ModifyDefaultComponentsEvent event) {

    }
}
