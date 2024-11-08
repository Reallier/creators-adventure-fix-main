package dev.misakacloud.ctsadvfix.mixin.ultimine;

import dev.ftb.mods.ftbultimine.integration.FTBUltiminePlugins;
import dev.ftb.mods.ftbultimine.shape.ShapeRegistry;
import dev.misakacloud.ctsadvfix.ultimine.shape.BigFlatShape;
import dev.misakacloud.ctsadvfix.ultimine.shape.PersonalTunnelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FTBUltiminePlugins.class)
public class UltimineMixin {

    @Inject(method = "init", remap = false, at = @At("HEAD"))
    private static void registerMyShape(CallbackInfo ci) {
        ShapeRegistry.register(new PersonalTunnelShape());
        ShapeRegistry.register(new BigFlatShape());
    }
}
