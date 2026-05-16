package katebulka.digamma.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.inventory.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

//@Mixin(InventoryMenu.class)
@Mixin(targets = "net/minecraft/world/inventory/InventoryMenu$1")
public abstract class InventoryShieldSlotMixin {
    @Unique
    private static final ResourceLocation EMPTY_ARMOR_SLOT_SHIELD_ALT = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_shield_alt");

    @ModifyReturnValue(
            method = "getNoItemIcon",
            at = @At("RETURN")
    )
    private Pair<ResourceLocation, ResourceLocation> nt_inventory_screen$setNoShieldItemIcon(Pair<ResourceLocation, ResourceLocation> original)
    {
        Minecraft client = Minecraft.getInstance();

        if (client.options.mainHand().get() == HumanoidArm.LEFT) {
            return Pair.of(InventoryMenu.BLOCK_ATLAS, EMPTY_ARMOR_SLOT_SHIELD_ALT);
        }

        return original;
    }
}
