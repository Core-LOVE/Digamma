package katebulka.digamma.mixin.client;

//import dev.tazer.clutternomore.common.shape_map.ShapeMap;
//import net.minecraft.client.Minecraft;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.neoforged.api.distmarker.Dist;
//import net.neoforged.api.distmarker.OnlyIn;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.ModifyVariable;
//import org.spongepowered.asm.mixin.injection.Redirect;

//@Mixin(
//        value = Minecraft.class,
//        priority = 2500
//)
//@OnlyIn(Dist.CLIENT)
public abstract class NewPickBlockMixin {
//    @ModifyVariable(
//            method = "pickBlock",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;findSlotMatchingItem(Lnet/minecraft/world/item/ItemStack;)I",
//                    shift = At.Shift.BEFORE),
//            ordinal = 0
//    )
//    private ItemStack pickBlock(ItemStack targetStack) {
//        Item parentItem = ShapeMap.getParent(targetStack.getItem());
//
//        if (parentItem != targetStack.getItem()) {
//            return parentItem.getDefaultInstance();
//        }
//
//        return targetStack;
//    }
}
