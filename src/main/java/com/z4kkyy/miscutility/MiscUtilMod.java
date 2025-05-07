package com.z4kkyy.miscutility;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(MiscUtilMod.ModID)
public class MiscUtilMod {
    public static final String ModID = "miscutility";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MiscUtilMod(FMLJavaModLoadingContext context) {
        context.getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(new PlayerDeathEventHandler());
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("MiscUtility Mod is setting up!");
    }
}