package katebulka.digamma.mixin;

import com.naterbobber.darkerdepths.entities.VoidSoulKnightEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(
        value = VoidSoulKnightEntity.class
)
public abstract class VoidSoulKnightEntityMixin extends Monster {
    protected VoidSoulKnightEntityMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public void die(DamageSource damageSource) {
        if (!this.isRemoved() && !this.dead) {
            Entity entity = damageSource.getEntity();
            LivingEntity livingEntity = this.getKillCredit();
            if (this.deathScore >= 0 && livingEntity != null) {
                livingEntity.awardKillScore(this, this.deathScore, damageSource);
            }

            if (this.isSleeping()) {
                this.stopSleeping();
            }

//            if (!this.level().isClientSide && this.hasCustomName()) {
//                LOGGER.info("Named entity {} died: {}", this, this.getCombatTracker().getDeathMessage().getString());
//            }

            this.dead = true;
            this.getCombatTracker().recheckStatus();
            Level var5 = this.level();
            if (var5 instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)var5;
                if (entity == null || entity.killedEntity(serverLevel, this)) {
                    this.gameEvent(GameEvent.ENTITY_DIE);
                    this.dropAllDeathLoot(serverLevel, damageSource);
                    this.createWitherRose(livingEntity);
                }

                this.level().broadcastEntityEvent(this, (byte)3);
            }

            this.setPose(Pose.DYING);
        }
    }
}
