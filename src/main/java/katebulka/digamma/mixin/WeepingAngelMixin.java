package katebulka.digamma.mixin;

import katebulka.digamma.mixin.accessor.AbstractWeepingAngelAccessor;
import mc.craig.software.angels.common.entity.angel.AbstractWeepingAngel;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(
        value = WeepingAngel.class,
        remap = false,
        priority = 1500
)
public abstract class WeepingAngelMixin extends AbstractWeepingAngel {
    public WeepingAngelMixin(Level worldIn, EntityType<? extends Monster> entityType) {
        super(worldIn, entityType);
    }

    @Overwrite
    protected void tickDeath() {
        Level level = this.level();
        ++this.deathTime;
        if (this.deathTime == 20 && !level.isClientSide()) {
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    public void setVariant(AngelVariant angelVariant) {
        this.getEntityData().set(AbstractWeepingAngelAccessor.getVariant(), "normal");
    }

    public void setDrops(boolean drops) {
        this.getEntityData().set(AbstractWeepingAngelAccessor.getShouldDropLoot(), false);
    }
}
