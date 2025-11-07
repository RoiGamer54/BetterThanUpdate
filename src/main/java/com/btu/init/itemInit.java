package com.btu.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.registry.Registry;

import com.btu.BetterThanUpdate;

public class ItemInit {

    // Item registeration method
    public static final Item EXAMPLE_ITEM = registerItem("example_item", new Item(new Item.Settings()));

    // Block registeration method
    public static final BlockItem Example_BlockItem = registerItem("example_block_item", new BlockItem(BlockInit.EXAMPLE_BLOCK, new Item.Settings()));
    public static final BlockItem OakChair_BlockItem = registerItem("oak_chair_block_item", new BlockItem(BlockInit.OAK_CHAIR_BLOCK, new Item.Settings()));

    public static <T extends Item> T registerItem(String name, T item) {
        BetterThanUpdate.LOGGER.info("Registering item: " + name);
        return Registry.register(Registries.ITEM, BetterThanUpdate.id(name), item);
    }

    public static void load() {}
}
