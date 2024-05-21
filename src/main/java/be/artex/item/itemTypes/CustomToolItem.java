package be.artex.item.itemTypes;

import be.artex.item.ItemGroups;
import be.artex.item.materials.ToolMaterials;
import net.minecraft.item.*;

public enum CustomToolItem implements Custom {
    RUBY_SWORD("ruby_sword",
            new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.RUBY, 3.25f, -2.4f)),
            ItemGroups.COMBAT, ToolMaterials.RUBY),
    RUBY_PICKAXE("ruby_pickaxe",
            new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.RUBY, 1, -2.8f)),
            ItemGroups.TOOLS, ToolMaterials.RUBY),
    RUBY_AXE("ruby_axe",
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.RUBY, 6, -3.1f)),
            ItemGroups.TOOLS, ToolMaterials.RUBY),
    RUBY_SHOVEL("ruby_shovel",
            new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.RUBY, 1.5f, -3)),
            ItemGroups.TOOLS, ToolMaterials.RUBY),
    RUBY_HOE("ruby_hoe",
            new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.RUBY, -2, -1)),
            ItemGroups.TOOLS, ToolMaterials.RUBY),;

    private final String name;
    private final Item.Settings settings;
    private final ItemGroups itemGroup;
    private final ToolMaterials toolMaterial;

    CustomToolItem(String name, Item.Settings settings, ItemGroups itemGroup, ToolMaterials toolMaterial) {
        this.name = name;
        this.settings = settings;
        this.itemGroup = itemGroup;
        this.toolMaterial = toolMaterial;
    }

    public String getName() {
        return name;
    }

    public Item.Settings getSettings() {
        return settings;
    }

    public ItemGroups getItemGroup() {
        return itemGroup;
    }

    public ToolMaterials getToolMaterial() {
        return toolMaterial;
    }
}
