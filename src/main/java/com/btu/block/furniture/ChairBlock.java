// com/btu/block/furniture/ChairBlock.java

package com.btu.block.furniture;

import com.btu.entity.SeatEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
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

public class ChairBlock extends HorizontalFacingBlock {

    // Hitbox basée sur votre modèle
    protected static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1, 10, 1, 15, 11, 14), // Assise
            Block.createCuboidShape(1, 0, 1, 3, 10, 3),    // Pieds...
            Block.createCuboidShape(13, 0, 1, 15, 10, 3),
            Block.createCuboidShape(1, 0, 13, 3, 10, 15),
            Block.createCuboidShape(13, 0, 13, 15, 10, 15),
            Block.createCuboidShape(1, 10, 14, 15, 26, 15) // Dossier
    );

    public ChairBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    // 🟢 FIX CRASH: Implémentation correcte du MapCodec requis.
    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return createCodec(ChairBlock::new);
    }

    // --- Gestion de la Rotation ---

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    // --- Gestion de la Hitbox et du Rendu ---

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    // --- Fonctionnalité S'ASSEOIR ---

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // Logique pour s'asseoir
        if (world.isClient() || player.isSneaking() || !player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
            return ActionResult.PASS;
        }

        // Vérifie si un siège (SeatEntity) n'est pas déjà présent
        if (world.getOtherEntities(null, new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.5, pos.getZ() + 1.0)).isEmpty()) {

            // Crée l'entité siège invisible
            SeatEntity seat = new SeatEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            world.spawnEntity(seat);

            // Fait monter le joueur dessus (s'asseoir)
            player.startRiding(seat);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}