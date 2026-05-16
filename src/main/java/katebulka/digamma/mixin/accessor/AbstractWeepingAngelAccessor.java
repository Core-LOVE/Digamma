package katebulka.digamma.mixin.accessor;

import mc.craig.software.angels.common.entity.angel.AbstractWeepingAngel;
import net.minecraft.network.syncher.EntityDataAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(
        value = AbstractWeepingAngel.class,
        remap = false
)
public interface AbstractWeepingAngelAccessor {
    @Accessor("VARIANT")
    static EntityDataAccessor<String> getVariant() {
        throw new AssertionError();
    }

    @Accessor("SHOULD_DROP_LOOT")
    static EntityDataAccessor<Boolean> getShouldDropLoot() {
        throw new AssertionError();
    }
}
