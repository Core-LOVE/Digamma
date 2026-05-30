package katebulka.digamma.mixin;

import com.feliscape.gladius.content.entity.projectile.Firebrand;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(
        value = Firebrand.class,
        remap = false
)
public abstract class FirebrandFix {
    @Redirect(
            method = "onHitBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/network/PacketDistributor;sendToAllPlayers(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;[Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V"
            )
    )
    private void redirectHitBlock(CustomPacketPayload payload, CustomPacketPayload... others) {
        if (!((ThrowableItemProjectile) (Object) this).level().isClientSide()) {
            PacketDistributor.sendToAllPlayers(payload, others);
        }
    }

    @Redirect(
            method = "onHitEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/network/PacketDistributor;sendToAllPlayers(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;[Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V"
            )
    )
    private void redirectHitEntity(CustomPacketPayload payload, CustomPacketPayload... others) {
        if (!((ThrowableItemProjectile) (Object) this).level().isClientSide()) {
            PacketDistributor.sendToAllPlayers(payload, others);
        }
    }
}
