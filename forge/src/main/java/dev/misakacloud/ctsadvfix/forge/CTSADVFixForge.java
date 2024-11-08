package dev.misakacloud.ctsadvfix.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(dev.misakacloud.ctsadvfix.CTSADVFix.MOD_ID)
public final class CTSADVFixForge {
    public CTSADVFixForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(dev.misakacloud.ctsadvfix.CTSADVFix.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        dev.misakacloud.ctsadvfix.CTSADVFix.init();
    }
}
