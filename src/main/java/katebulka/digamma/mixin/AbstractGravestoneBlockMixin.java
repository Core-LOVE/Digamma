package katebulka.digamma.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pneumono.gravestones.block.AbstractGravestoneBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AbstractGravestoneBlock.class)
public abstract class AbstractGravestoneBlockMixin {
    @Shadow
    public static EnumProperty<Direction> FACING;

    @Unique
    private static final VoxelShape NORTH_SHAPE = Block.box(0, 0, 0, 16, 16, 8);

    @Unique
    private static final VoxelShape SOUTH_SHAPE = Block.box(0, 0, 8, 16, 16, 16);

    @Unique
    private static final VoxelShape EAST_SHAPE = Block.box(8, 0, 0, 16, 16, 16);

    @Unique
    private static final VoxelShape WEST_SHAPE = Block.box(0, 0, 0, 8, 16, 16);

    @Overwrite
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
        VoxelShape var10000;

        switch ((Direction)state.getValue(FACING)) {
            case EAST -> var10000 = WEST_SHAPE;
            case SOUTH -> var10000 = NORTH_SHAPE;
            case WEST -> var10000 = EAST_SHAPE;
            default -> var10000 = SOUTH_SHAPE;
        }

        return var10000;
    }
}
