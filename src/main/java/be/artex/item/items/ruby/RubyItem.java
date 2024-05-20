package be.artex.item.items.ruby;

import be.artex.item.ItemGroupList;
import be.artex.item.UselessOreItem;
import net.minecraft.item.Item;

public class RubyItem extends UselessOreItem {
    @Override
    public String getName() {
        return "ruby";
    }

    @Override
    public Item.Settings getSettings() {
        return new Item.Settings();
    }

    @Override
    public ItemGroupList getItemGroup() {
        return ItemGroupList.INGREDIENTS;
    }
}
