package be.artex.item;

import be.artex.UselessOres;
import be.artex.item.items.ruby.RubyItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemUtil {
    private static final HashMap<ItemGroupList, ArrayList<Item>> itemsInItemGroups;

    static {
        itemsInItemGroups = new HashMap<>();

        for (ItemGroupList group : ItemGroupList.values()) {
            itemsInItemGroups.put(group, new ArrayList<>());
        }
    }

    public static Item RUBY;

    public static void onModInit() {
        RUBY = registerItem(new RubyItem());

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ItemUtil::addItemToIngredients);
    }

    public static void addItemToIngredients(FabricItemGroupEntries entries) {
        ArrayList<Item> itemsInIngredients = itemsInItemGroups.get(ItemGroupList.INGREDIENTS);

        for (Item item : itemsInIngredients) {
            entries.add(item);
        }
    }

    protected static Item registerItem(UselessOreItem item) {
        Item i = new Item(item.getSettings());

        switch (item.getItemGroup()) {
            case INGREDIENTS ->
                itemsInItemGroups.get(ItemGroupList.INGREDIENTS).add(i);

            default -> {

            }
        }

        return registerItem(item.getName(), i);
    }

    protected static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(UselessOres.MODID, name), item);
    }


}
