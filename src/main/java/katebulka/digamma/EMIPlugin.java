package katebulka.digamma;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;

import dev.emi.emi.api.stack.EmiStack;
import katebulka.digamma.handler.AlloySmeltRecipeHandler;
import katebulka.digamma.handler.CauldronRecipeHandler;
import katebulka.digamma.handler.NetherAlloySmeltRecipeHandler;
import net.mcreator.concoction.init.ConcoctionModMenus;
import net.mcreator.concoction.init.ConcoctionModRecipes;
import net.mcreator.concoction.recipe.cauldron.CauldronBrewingRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.stirdrem.overgeared.screen.ModMenuTypes;
//import java.net.mcreator.*;

@EmiEntrypoint
public class EMIPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        registry.addRecipeHandler(ModMenuTypes.ALLOY_SMELTER_MENU.get(), new AlloySmeltRecipeHandler());
        registry.addRecipeHandler(ModMenuTypes.NETHER_ALLOY_SMELTER_MENU.get(), new NetherAlloySmeltRecipeHandler());

        registry.addWorkstation(CauldronBrewingEmiRecipe.CATEGORY, EmiStack.of(Items.CAULDRON));

        var recipes = registry.getRecipeManager().getAllRecipesFor(ConcoctionModRecipes.CAULDRON_BREWING_RECIPE_TYPE.get());

        for (RecipeHolder<CauldronBrewingRecipe> holder : recipes) {
            registry.addRecipe(new CauldronBrewingEmiRecipe.Recipe(holder));
        }

        registry.addRecipeHandler(ConcoctionModMenus.BOILING_CAULDRON_INTERFACE.get(), new CauldronRecipeHandler());
    }
}
