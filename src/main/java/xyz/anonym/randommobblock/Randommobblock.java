package xyz.anonym.randommobblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Randommobblock implements ModInitializer {
    public static final String MOD_ID = "randommobblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing...");
        String worldstr = "world";
        List<String> mobs = Arrays.asList("ALLAY", "ARMADILLO", "AXOLOTL", "BAT", "BEE", "BLAZE", "BOGGED", "BREEZE", "CAMEL", "CAT", "CAVE_SPIDER", "CHICKEN", "COD", "COW", "CREAKING", "CREEPER", "DOLPHIN", "DONKEY", "DROWNED", "ENDERMAN", "ENDERMITE", "EVOKER", "FOX", "FROG", "GHAST", "GLOW_SQUID", "GOAT", "GUARDIAN", "HOGLIN", "HORSE", "HUSK", "ILLUSIONER", "IRON_GOLEM", "LLAMA", "MAGMA_CUBE", "MOOSHROOM", "MULE", "OCELOT", "PANDA", "PARROT", "PHANTOM", "PIG", "PIGLIN", "PIGLIN_BRUTE", "PILLAGER", "POLAR_BEAR", "PUFFERFISH", "RABBIT", "RAVAGER", "SALMON", "SHEEP", "SHULKER", "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SNIFFER", "SNOW_GOLEM", "SPIDER", "SQUID", "STRAY", "STRIDER", "TADPOLE", "TRADER_LLAMA", "TROPICAL_FISH", "TURTLE", "VEX", "VILLAGER", "VINDICATOR", "WANDERING_TRADER", "WITCH", "WITHER_SKELETON", "WOLF", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIFIED_PIGLIN");
        SQLHelper sqlHelper = new SQLHelper("randommobblock/"+worldstr.replace("/","").replace(" ","-").replace("\\","")+".db");
        LOGGER.info("Initialized!");

        PlayerBlockBreakEvents.AFTER.register((world, playerEntity, blockPos, blockstate, blockEntity) -> {
            Random random = new Random();
            String temp;
            try {
                if (sqlHelper.getMob(blockstate.getBlock().toString()) == null) {
                    temp = mobs.get(random.nextInt(mobs.size())).toLowerCase();
                    sqlHelper.addBlockMob(blockstate.getBlock().toString(), temp);
                } else {
                    temp = sqlHelper.getMob(blockstate.getBlock().toString());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                //noinspection OptionalGetWithoutIsPresent (Try-Catch-Statement is  here to fix it!)
                EntityType.get(temp).get().spawn((ServerWorld) world, blockPos, SpawnReason.EVENT);
            } catch (Exception e) {
                LOGGER.warn("I made a serious mistake while coding...");
                LOGGER.error("A error occured! Here the Exception: {}", String.valueOf(e));
            }
        });
    }
}
