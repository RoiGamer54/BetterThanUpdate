// com/btu/init/BlockInit.java

package com.btu.init;

import com.btu.BetterThanUpdate;
import com.btu.block.furniture.ChairBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
// Remove item-related imports
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BlockInit {
    public static final Block EXAMPLE_BLOCK = registerBlock("example_block", new Block(AbstractBlock.Settings.create().strength(1.5F, 6.0F)));

    // 💡 FIX: Just register the block. ItemInit will handle the item.
    public static final Block OAK_CHAIR_BLOCK = registerBlock("oak_chair_block", new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SPRUCE_CHAIR_BLOCK = registerBlock("spruce_chair_block", new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block BIRCH_CHAIR_BLOCK = registerBlock("birch_chair_block", new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block CHERRY_CHAIR_BLOCK = registerBlock("cherry_chair_block", new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block DARK_OAK_CHAIR_BLOCK = registerBlock("dark_oak_chair_block", new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block JUNGLE_CHAIR_BLOCK = registerBlock("jungle_chair_block", new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block MANGROVE_CHAIR_BLOCK = registerBlock("mangrove_chair_block", new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block ACACIA_CHAIR_BLOCK = registerBlock("acacia_chair_block", new ChairBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));



    public static <T extends Block> T registerBlock(String name, T block) {
        // 💡 FIX: Moved logging here
        BetterThanUpdate.LOGGER.info("Registering block: " + name);
        return Registry.register(Registries.BLOCK, BetterThanUpdate.id(name), block);
    }

    // 💡 FIX: Delete both registerWithItem methods
    // public static <T extends Block> T registerWithItem(...) { ... }
    // public static <T extends Block> T registerWithItem(...) { ... }


    public static void load() {}
}