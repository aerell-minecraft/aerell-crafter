package dev.fernaerell.aerellcrafter;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AerellCrafter implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Aerell Crafter");

	@Override
	public void onInitialize() {
        LOGGER.info("Thanks for using Aerell Crafter...");
		LOGGER.info("Create by Fern Aerell.");
	}
}