package be.artex.item;

import be.artex.UselessOres;
import be.artex.item.itemTypes.Custom;
import be.artex.item.itemTypes.CustomBlock;
import be.artex.item.itemTypes.CustomItem;
import be.artex.item.itemTypes.CustomToolItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ItemUtil {
    private static final Map<ItemGroups, List<Item>> itemsInItemGroups = new EnumMap<>(ItemGroups.class);

    public static Item RUBY;
    public static Item RUBY_SWORD;
    public static Item RUBY_PICKAXE;
    public static Item RUBY_AXE;
    public static Item RUBY_SHOVEL;
    public static Item RUBY_HOE;
    public static Block RUBY_BLOCK;

    static {
        for (ItemGroups group : ItemGroups.values()) {
            itemsInItemGroups.put(group, new ArrayList<>());
        }
    }

    public static void onModInit() {
        // blocks
        RUBY_BLOCK = registerBlock(CustomBlock.RUBY_BLOCK);

        // Items
        RUBY = registerItem(CustomItem.RUBY);
        RUBY_SWORD = registerToolItem(CustomToolItem.RUBY_SWORD, SwordItem::new);
        RUBY_PICKAXE = registerToolItem(CustomToolItem.RUBY_PICKAXE, PickaxeItem::new);
        RUBY_AXE = registerToolItem(CustomToolItem.RUBY_AXE, AxeItem::new);
        RUBY_SHOVEL = registerToolItem(CustomToolItem.RUBY_SHOVEL, ShovelItem::new);
        RUBY_HOE = registerToolItem(CustomToolItem.RUBY_HOE, HoeItem::new);

        registerItemGroups();
    }

    private static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.INGREDIENTS).register(entries -> addItemsToGroup(entries, ItemGroups.INGREDIENTS));
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.COMBAT).register(entries -> addItemsToGroup(entries, ItemGroups.COMBAT));
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.TOOLS).register(entries -> addItemsToGroup(entries, ItemGroups.TOOLS));
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.BUILDING_BLOCKS).register(entries -> addItemsToGroup(entries, ItemGroups.BUILDING_BLOCKS));
    }

    private static void addItemsToGroup(FabricItemGroupEntries entries, ItemGroups group) {
        itemsInItemGroups.get(group).forEach(entries::add);
    }

    public static Item registerItem(CustomItem item) {
        Item i = new Item(item.getSettings());
        addToCorrectItemGroup(item, i);

        return registerItem(item.getName(), i);
    }

    public static <T extends Item> T registerToolItem(CustomToolItem item, ToolItemFactory<T> factory) {
        T i = factory.create(item.getToolMaterial(), item.getSettings());
        addToCorrectItemGroup(item, i);

        return Registry.register(Registries.ITEM, new Identifier(UselessOres.MODID, item.getName()), i);
    }

    public static Item registerBlockItem(Block block, CustomBlock customBlock, Item.Settings itemSettings) {
        Item i = new BlockItem(block, itemSettings);
        addToCorrectItemGroup(customBlock, i);

        return registerItem(customBlock.getName(), i);
    }

    public static Block registerBlock(CustomBlock customBlock) {
        Block block = new Block(customBlock.getSettings());

        // Register the block item here
        registerBlockItem(block, customBlock, new Item.Settings());

        // Register the block directly, do not create a new Block instance
        return registerBlock(customBlock.getName(), block);
    }

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(UselessOres.MODID, name), block);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(UselessOres.MODID, name), item);
    }

    private static void addToCorrectItemGroup(Custom item, Item i) {
        itemsInItemGroups.get(item.getItemGroup()).add(i);
    }

    @FunctionalInterface
    interface ToolItemFactory<T extends Item> {
        T create(ToolMaterial material, Item.Settings settings);
    }
}
