package com.z4kkyy.miscutility;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;



public class PlayerDeathEventHandler {

    public static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {

        // Ensure this process only runs for players, on the server side
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        int x = (int) Math.round(player.getX());
        int y = (int) Math.round(player.getY());
        int z = (int) Math.round(player.getZ());

        String dimension = player.level()
                                .dimension()
                                .location()
                                .toString()
                                .replace("minecraft:", "");

        switch (dimension) {
            case "overworld" -> dimension = "Overworld (現世)";
            case "the_nether" -> dimension = "Nether (ネザー)";
            case "the_end" -> dimension = "End (エンド)";
        }

        String message = "At (%s, %s, %s) in %s,".formatted(x, y, z, dimension);
        Component messageComponent = Component.literal(message);

        MinecraftServer server = player.getServer();
        server.getPlayerList().broadcastSystemMessage(messageComponent, false);
        LOGGER.info("Player {} died at ({}, {}, {}) in {}",
            player.getName().getString(),
            x, y, z,
            player.level().dimension().location().toString());
    }
}
