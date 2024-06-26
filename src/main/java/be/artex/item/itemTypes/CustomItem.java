package be.artex.item.itemTypes;

import be.artex.item.ItemGroups;
import net.minecraft.item.Item;

public enum CustomItem implements Custom {
    RUBY("ruby", new Item.Settings(), ItemGroups.INGREDIENTS),
    ;

    private final String name;
    private final Item.Settings settings;
    private final ItemGroups itemGroup;

    CustomItem(String name, Item.Settings settings, ItemGroups itemGroup) {
        this.name = name;
        this.settings = settings;
        this.itemGroup = itemGroup;
    }

    public String getName() {
        return name;
    }

    public Item.Settings getSettings() {
        return settings;
    }

    @Override
    public ItemGroups getItemGroup() {
        return itemGroup;
    }
}
