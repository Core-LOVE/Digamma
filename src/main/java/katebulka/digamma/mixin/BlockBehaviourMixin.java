package katebulka.digamma.mixin;

import katebulka.digamma.registry.ModBlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviourMixin {
    @Inject(method = "canBeReplaced", at = @At("RETURN"), cancellable = true)
    public void canBeReplacedInjection(BlockState blockState, BlockPlaceContext blockPlaceContext, CallbackInfoReturnable cir) {
        if (blockState.is(ModBlockTags.REPLACEABLE)) {
            Item item = blockPlaceContext.getItemInHand().getItem();

            if (item instanceof BlockItem && item == blockState.getBlock().asItem()) {
                return;
            } else {
                cir.setReturnValue(true);
            }
        }
    }
}
