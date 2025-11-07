package com.btu.block.furniture;

import com.btu.entity.SeatEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ChairBlock extends HorizontalFacingBlock {

    protected static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1, 0, 1, 15, 11, 15)
    );

    public ChairBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return createCodec(ChairBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction playerFacing = ctx.getHorizontalPlayerFacing().getOpposite();
        if (!ctx.getWorld().getBlockState(ctx.getBlockPos().down()).isSideSolidFullSquare(ctx.getWorld(), ctx.getBlockPos().down(), Direction.UP)) {
            return this.getDefaultState().with(FACING, Direction.NORTH);
        }
        return this.getDefaultState().with(FACING, playerFacing);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    // Dans com/btu/block/furniture/ChairBlock.java

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient() || player.isSneaking() || !player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
            return ActionResult.PASS;
        }

        if (world.getOtherEntities(null, new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.5, pos.getZ() + 1.0)).isEmpty()) {
            Direction chairFacing = state.get(Properties.HORIZONTAL_FACING);
            float yaw = chairFacing.asRotation();
            SeatEntity seat = new SeatEntity(world, pos.getX()+0.5, pos.getY()-1.3, pos.getZ()+0.5);

            seat.setYaw(yaw);
            seat.setHeadYaw(yaw);
            world.spawnEntity(seat);

            player.startRiding(seat);
            player.setYaw(yaw);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}