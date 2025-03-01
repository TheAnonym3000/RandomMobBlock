package xyz.anonym.randommobblock.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandommobblockClient implements ClientModInitializer {
    public static final String MOD_ID = "randommobblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing...");
        LOGGER.info("Initialized!");
    }
}
