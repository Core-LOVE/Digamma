package katebulka.digamma.mixin;

import com.feliscape.artistry.content.block.TallCandleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(
        value = TallCandleBlock.class,
        remap = false
)
public abstract class TallCandleMixin {
    @Overwrite
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Block self = (Block) (Object) this;
        BlockState below = level.getBlockState(pos.below());
        if (below.is(self)) {
            return true;
        }
        return Block.canSupportCenter(level, pos.below(), Direction.UP);
    }
}
