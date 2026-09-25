package com.cleanitem;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanitemMod implements ModInitializer {
    public static final String MOD_ID = "cleanitem";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("CleanItem Mod initialized!");
    }
}