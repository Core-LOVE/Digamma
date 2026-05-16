package katebulka.digamma.mixin;

import com.mrbysco.monstereggs.block.MonsterEggBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
        value = MonsterEggBlock.class,
        remap = false,
        priority = 2500
)
public abstract class MonsterEggBlockMixin {
    @Unique
    private static VoxelShape SHAPE;

    @Unique
    private static VoxelShape getShape() {
        if (SHAPE == null) {
            SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
        }

        return SHAPE;
    }

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true, remap = false)
    private void getShapeInjection(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context, CallbackInfoReturnable cir) {
        cir.setReturnValue(getShape());
    }
}
