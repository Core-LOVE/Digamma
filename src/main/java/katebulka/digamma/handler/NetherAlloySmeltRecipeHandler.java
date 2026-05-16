package katebulka.digamma.handler;

import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import net.minecraft.world.inventory.Slot;
import net.stirdrem.overgeared.compat.emi.NetherAlloySmeltingEmiRecipe;
import net.stirdrem.overgeared.compat.emi.ShapedNetherAlloySmeltingEmiRecipe;
import net.stirdrem.overgeared.screen.NetherAlloySmelterMenu;

import java.util.List;

public class NetherAlloySmeltRecipeHandler implements StandardRecipeHandler<NetherAlloySmelterMenu> {
    @Override
    public List<Slot> getInputSources(NetherAlloySmelterMenu menu) {
        List<Slot> list = Lists.newArrayList();

        for (int i = 0; i <= 35; ++i) {
            list.add(menu.getSlot(i));
        }

        return list;
    }

    @Override
    public List<Slot> getCraftingSlots(NetherAlloySmelterMenu menu) {
        List<Slot> list = Lists.newArrayList();

        for (int i = 36; i <= 44; i++) {
            list.add(menu.getSlot(i));
        }

        return list;
    }

    @Override
    public boolean supportsRecipe(EmiRecipe recipe) {
        return recipe instanceof NetherAlloySmeltingEmiRecipe ||
                recipe instanceof ShapedNetherAlloySmeltingEmiRecipe;
    }
}
