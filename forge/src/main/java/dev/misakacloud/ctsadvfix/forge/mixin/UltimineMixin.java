package dev.misakacloud.ctsadvfix.forge.mixin;

import dev.ftb.mods.ftbultimine.FTBUltimine;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = FTBUltimine.class)
public class UltimineMixin {
//    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbultimine/shape/ShapeRegistry;register(Ldev/ftb/mods/ftbultimine/shape/Shape;)V", shift = At.Shift.AFTER))
//    private void registerMyShape(CallbackInfo ci) {
//        ShapeRegistry.register(new PersonalTunnelShape());
//        ShapeRegistry.register(new BigFlatShape());
//    }
}
