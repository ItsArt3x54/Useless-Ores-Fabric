package be.artex.item;

import be.artex.UselessOres;
import be.artex.item.itemTypes.CustomItem;
import be.artex.item.itemTypes.tools.ToolItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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

    static {
        for (ItemGroups group : ItemGroups.values()) {
            itemsInItemGroups.put(group, new ArrayList<>());
        }
    }

    public static void onModInit() {
        RUBY = registerItem(CustomItem.RUBY);
        RUBY_SWORD = registerToolItem(ToolItem.RUBY_SWORD, SwordItem::new);
        RUBY_PICKAXE = registerToolItem(ToolItem.RUBY_PICKAXE, PickaxeItem::new);
        RUBY_AXE = registerToolItem(ToolItem.RUBY_AXE, AxeItem::new);
        RUBY_SHOVEL = registerToolItem(ToolItem.RUBY_SHOVEL, ShovelItem::new);
        RUBY_HOE = registerToolItem(ToolItem.RUBY_HOE, HoeItem::new);

        registerItemGroups();
    }

    private static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.INGREDIENTS).register(entries -> addItemsToGroup(entries, ItemGroups.INGREDIENTS));
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.COMBAT).register(entries -> addItemsToGroup(entries, ItemGroups.COMBAT));
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.TOOLS).register(entries -> addItemsToGroup(entries, ItemGroups.TOOLS));
    }

    private static void addItemsToGroup(FabricItemGroupEntries entries, ItemGroups group) {
        itemsInItemGroups.get(group).forEach(entries::add);
    }

    public static Item registerItem(CustomItem item) {
        Item i = new Item(item.getSettings());
        addToCorrectItemGroup(item, i);

        return registerItem(item.getName(), i);
    }

    public static <T extends Item> T registerToolItem(ToolItem item, ToolItemFactory<T> factory) {
        T i = factory.create(item.getToolMaterial(), item.getSettings());
        addToCorrectItemGroup(item, i);
        return Registry.register(Registries.ITEM, new Identifier(UselessOres.MODID, item.getName()), i);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(UselessOres.MODID, name), item);
    }

    private static void addToCorrectItemGroup(CustomItem item, Item i) {
        itemsInItemGroups.get(item.getItemGroup()).add(i);
    }

    private static void addToCorrectItemGroup(ToolItem item, Item i) {
        itemsInItemGroups.get(item.getItemGroup()).add(i);
    }

    @FunctionalInterface
    interface ToolItemFactory<T extends Item> {
        T create(ToolMaterial material, Item.Settings settings);
    }
}
