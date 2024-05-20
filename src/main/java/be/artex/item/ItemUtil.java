package be.artex.item;

import be.artex.UselessOres;
import be.artex.item.itemTypes.CustomItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemUtil {
    private static final HashMap<ItemGroups, ArrayList<net.minecraft.item.Item>> itemsInItemGroups;

    public static net.minecraft.item.Item RUBY;

    // HashMap
    static {
        itemsInItemGroups = new HashMap<>();

        for (ItemGroups group : ItemGroups.values()) {
            itemsInItemGroups.put(group, new ArrayList<>());
        }
    }

    // when mod initialize
    public static void onModInit() {
        // items;
        RUBY = registerItem(CustomItem.RUBY);

        // blocks;

        // ItemGroups (must be at the end);
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.INGREDIENTS).register(ItemUtil::addItemToIngredients);
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.COMBAT).register(ItemUtil::addItemToCombat);
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.item.ItemGroups.TOOLS).register(ItemUtil::addItemToTools);
    }

    // add items in itemgroups
    public static void addItemToIngredients(FabricItemGroupEntries entries) {
        ArrayList<net.minecraft.item.Item> itemsInIngredients = itemsInItemGroups.get(ItemGroups.INGREDIENTS);

        for (net.minecraft.item.Item item : itemsInIngredients) {
            entries.add(item);
        }
    }

    public static void addItemToTools(FabricItemGroupEntries entries) {
        ArrayList<net.minecraft.item.Item> itemsInIngredients = itemsInItemGroups.get(ItemGroups.TOOLS);

        for (net.minecraft.item.Item item : itemsInIngredients) {
            entries.add(item);
        }
    }

    public static void addItemToCombat(FabricItemGroupEntries entries) {
        ArrayList<net.minecraft.item.Item> itemsInIngredients = itemsInItemGroups.get(ItemGroups.COMBAT);

        for (net.minecraft.item.Item item : itemsInIngredients) {
            entries.add(item);
        }
    }

    // Register item ingame;
    protected static Item registerItem(CustomItem item) {
        Item i = new Item(item.getSettings());

        switch (item.getItemGroup()) {
            case INGREDIENTS ->
                itemsInItemGroups.get(ItemGroups.INGREDIENTS).add(i);

            case TOOLS ->
                itemsInItemGroups.get(ItemGroups.TOOLS).add(i);

            case COMBAT ->
                itemsInItemGroups.get(ItemGroups.COMBAT).add(i);

            default -> {

            }
        }

        return registerItem(item.getName(), i);
    }

    protected static net.minecraft.item.Item registerItem(String name, net.minecraft.item.Item item) {
        return Registry.register(Registries.ITEM, new Identifier(UselessOres.MODID, name), item);
    }
}
