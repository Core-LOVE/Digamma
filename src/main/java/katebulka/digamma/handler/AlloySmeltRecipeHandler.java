package katebulka.digamma.handler;

import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import net.minecraft.world.inventory.Slot;
import net.stirdrem.overgeared.compat.emi.AlloySmeltingEmiRecipe;
import net.stirdrem.overgeared.screen.AlloySmelterMenu;

import java.util.List;

public class AlloySmeltRecipeHandler implements StandardRecipeHandler<AlloySmelterMenu> {
    @Override
    public List<Slot> getInputSources(AlloySmelterMenu menu) {
        List<Slot> list = Lists.newArrayList();

        for (int i = 0; i <= 35; ++i) {
            list.add(menu.getSlot(i));
        }

        return list;
    }

    @Override
    public List<Slot> getCraftingSlots(AlloySmelterMenu menu) {
        List<Slot> list = Lists.newArrayList();

        for (int i = 36; i <= 39; i++) {
            list.add(menu.getSlot(i));
        }

        return list;
    }

    @Override
    public boolean supportsRecipe(EmiRecipe recipe) {
        return recipe instanceof AlloySmeltingEmiRecipe;
    }
}
