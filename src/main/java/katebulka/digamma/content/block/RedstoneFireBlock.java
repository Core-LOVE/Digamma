package katebulka.digamma.content.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.crystalnest.prometheus.api.Fire;
import it.crystalnest.prometheus.api.FireManager;
import it.crystalnest.prometheus.api.block.CustomFireBlock;
import it.crystalnest.prometheus.api.type.FireTyped;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RedstoneFireBlock extends CustomFireBlock {
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    public RedstoneFireBlock(ResourceLocation fireType, TagKey<Block> base, Properties properties) {
        super(fireType, base, properties.lightLevel((state) -> 15 - state.getValue(POWER)));
        this.registerDefaultState(this.defaultBlockState().setValue(POWER, 0));
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        BlockPos below = pos.below();
        return this.canSurvive(level.getBlockState(below)) && level.getBlockState(below).isFaceSturdy(level, below, Direction.UP);
    }

    @Override
    public boolean canSurvive(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWER);
    }

    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource rand) {
        super.tick(state, level, pos, rand);
        if (!level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) return;

        int power = state.getValue(POWER);
        int age = state.getValue(AGE);

        if (power != 0) {
            if (age != 0) {
                level.setBlock(pos, state.setValue(AGE, 0), 3);
            }

            return;
        }

        if (age < 15 && rand.nextInt(2) == 0) {
            level.setBlock(pos, state.setValue(AGE, age + 1), 3);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        super.neighborChanged(state, level, pos, neighborBlock, fromPos, moving);
        if (!level.isClientSide) {
            int pow = level.getBestNeighborSignal(pos);
            level.setBlock(pos, state.setValue(POWER, Mth.clamp(pow, 0, 15)), 2);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(POWER,
                context.getLevel().getBestNeighborSignal(context.getClickedPos()));
    }
}
