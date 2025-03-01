package xyz.anonym.randommobblock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandommobblockClient implements ClientModInitializer {
    public static final String MOD_ID = "randommobblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);@Override
    public void onInitializeClient() {
        LOGGER.info("Initializing...");
        List<String> mobs = Arrays.asList("ALLAY", "ARMADILLO", "AXOLOTL", "BAT", "BEE", "BLAZE", "BOGGED", "BREEZE", "CAMEL", "CAT", "CAVE_SPIDER", "CHICKEN", "COD", "COW", "CREAKING", "CREEPER", "DOLPHIN", "DONKEY", "DROWNED", "ENDERMAN", "ENDERMITE", "EVOKER", "FOX", "FROG", "GHAST", "GLOW_SQUID", "GOAT", "GUARDIAN", "HOGLIN", "HORSE", "HUSK", "ILLUSIONER", "IRON_GOLEM", "LLAMA", "MAGMA_CUBE", "MOOSHROOM", "MULE", "OCELOT", "PANDA", "PARROT", "PHANTOM", "PIG", "PIGLIN", "PIGLIN_BRUTE", "PILLAGER", "POLAR_BEAR", "PUFFERFISH", "RABBIT", "RAVAGER", "SALMON", "SHEEP", "SHULKER", "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SNIFFER", "SNOW_GOLEM", "SPIDER", "SQUID", "STRAY", "STRIDER", "TADPOLE", "TRADER_LLAMA", "TROPICAL_FISH", "TURTLE", "VEX", "VILLAGER", "VINDICATOR", "WANDERING_TRADER", "WITCH", "WITHER_SKELETON", "WOLF", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIFIED_PIGLIN");
        LOGGER.info("Initialized!");
        PlayerBlockBreakEvents.AFTER.register((world, playerEntity, blockPos, blockState, blockEntity) -> {
            Random random = new Random();
            //Check if mob is saved or not /(maybe:
            // if (map.get(blockstate.getBlock().toString()) = null) {
                String temp = mobs.get(random.nextInt(mobs.size())).toLowerCase();
                //map.put(blockstate.getBLock().toString(), temp)
            //} else {
                //String temp = map.get(blockstate.getBlock().toString())
            //})
            try {
                //noinspection OptionalGetWithoutIsPresent
                EntityType.get(temp).get().spawn((ServerWorld) world, blockPos, SpawnReason.EVENT);
            } catch (Exception e) {
                LOGGER.warn("I made a serious mistake while coding...");
                LOGGER.error("A error occured! Here the Exception: {}", String.valueOf(e));
            }
        });
    }
}
