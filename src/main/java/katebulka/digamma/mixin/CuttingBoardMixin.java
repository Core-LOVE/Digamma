package katebulka.digamma.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;

@Mixin(
        value = CuttingBoardBlock.class
)
public abstract class CuttingBoardMixin {
    @Unique
    private static final VoxelShape SHAPE = Block.box(
            (double)0.0F, (double)0.0F, (double)0.0F,
            (double)16.0F, (double)12.0F, (double)16.0F
    );

    @Overwrite
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
