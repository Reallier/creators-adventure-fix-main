package dev.misakacloud.ctsadvfix.mixin.tmf;

import com.daqem.tinymobfarm.item.LassoItem;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LassoItem.class)
public class LassoItemMixin {

    /**
     * 跳过检查是否是 boss
     *
     * @param instance
     * @return
     */
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;canChangeDimensions()Z"))
    private boolean skipBossCheck(LivingEntity instance) {
        return true;
    }
}
