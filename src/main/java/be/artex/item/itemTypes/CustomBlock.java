package be.artex.item.itemTypes;

import be.artex.item.ItemGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public enum CustomBlock implements Custom {
    RUBY_BLOCK("ruby_block", AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK), ItemGroups.COMBAT);

    private final String name;
    private final Block.Settings settings;
    private final ItemGroups itemGroup;

    CustomBlock(String name, Block.Settings settings, ItemGroups itemGroup) {
        this.name = name;
        this.settings = settings;
        this.itemGroup = itemGroup;
    }

    public String getName() {
        return name;
    }

    public ItemGroups getItemGroup() {
        return itemGroup;
    }

    public Block.Settings getSettings() {
        return settings;
    }
}
