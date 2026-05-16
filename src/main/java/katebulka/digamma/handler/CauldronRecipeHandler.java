package katebulka.digamma.handler;

import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import katebulka.digamma.CauldronBrewingEmiRecipe;
import net.mcreator.concoction.world.inventory.BoilingCauldronInterfaceMenu;
import net.minecraft.world.inventory.Slot;

import java.util.List;

public class CauldronRecipeHandler implements StandardRecipeHandler<BoilingCauldronInterfaceMenu> {
    @Override
    public List<Slot> getInputSources(BoilingCauldronInterfaceMenu menu) {
        List<Slot> list = Lists.newArrayList();

        for (int i = 0; i <= 35; ++i) {
            list.add(menu.getSlot(i));
        }

        return list;
    }

    @Override
    public List<Slot> getCraftingSlots(BoilingCauldronInterfaceMenu menu) {
        List<Slot> list = Lists.newArrayList();

        for (int i = 36; i <= 39; i++) {
            list.add(menu.getSlot(i));
        }

        return list;
    }

    @Override
    public boolean supportsRecipe(EmiRecipe recipe) {
        return recipe instanceof CauldronBrewingEmiRecipe.Recipe;
    }
}
