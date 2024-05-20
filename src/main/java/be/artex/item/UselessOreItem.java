package be.artex.item;

import net.minecraft.item.Item;

public abstract class UselessOreItem {
    public abstract String getName();
    public abstract Item.Settings getSettings();
    public abstract ItemGroupList getItemGroup();
}
