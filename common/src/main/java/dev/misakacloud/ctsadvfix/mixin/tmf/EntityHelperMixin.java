package dev.misakacloud.ctsadvfix.mixin.tmf;

import com.daqem.tinymobfarm.util.EntityHelper;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityHelper.class)
public class EntityHelperMixin {

    /**
     * 对所有情况添加掉落物
     *
     * @param cir
     */
    @Inject(method = "generateLoot", at = @At("RETURN"))
    private static void addLootsToTable(CallbackInfoReturnable<ObjectArrayList<ItemStack>> cir) {
        CompoundTag inferiumEssenceTag = new CompoundTag();
        inferiumEssenceTag.putString("id", "mysticalagriculture:inferium_essence");
        inferiumEssenceTag.putByte("Count", (byte) 2);
        cir.getReturnValue().add(ItemStack.of(inferiumEssenceTag));
    }
}
