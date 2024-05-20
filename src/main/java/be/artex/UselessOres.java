package be.artex;

import be.artex.item.ItemUtil;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UselessOres implements ModInitializer {
	public static final String MODID = "useless_ores";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		ItemUtil.onModInit();

		LOGGER.info("Initializing " + MODID + "..." );
	}
}