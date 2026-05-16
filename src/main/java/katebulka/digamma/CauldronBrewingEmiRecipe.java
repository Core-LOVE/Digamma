package katebulka.digamma;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.mcreator.concoction.recipe.cauldron.CauldronBrewingRecipe;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

public class CauldronBrewingEmiRecipe {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("concoction", "cauldron_brewing");
    public static final EmiStack WORKSTATION = EmiStack.of(Items.CAULDRON);
    public static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(
            ID,
            WORKSTATION,
            new EmiTexture(ResourceLocation.fromNamespaceAndPath("concoction", "textures/gui/hud/boiling_cauldron_gui.png"), 0, 0, 16, 16)
    ) {
        @Override
        public Component getName() {
            return Component.translatable("gui.cooking_cauldron.title");
        }
    };

    public static class Recipe implements EmiRecipe {
        private final ResourceLocation id;
        private final CauldronBrewingRecipe recipe;
        private final List<EmiIngredient> inputs;
        private final List<EmiStack> outputs;
        private final EmiStack catalyst;

        public Recipe(RecipeHolder<CauldronBrewingRecipe> holder) {
            this.recipe = holder.value();
            this.id = holder.id();

            this.inputs = List.of(
                    EmiIngredient.of(recipe.getIngredient(0)),
                    EmiIngredient.of(recipe.getIngredient(1)),
                    EmiIngredient.of(recipe.getIngredient(2)),
                    EmiIngredient.of(recipe.getIngredient(3))
            );

            var outputMap = recipe.getOutput();
            String itemId = (String) outputMap.get("id");
            int count = Integer.parseInt((String) outputMap.get("count"));
            ItemStack outputStack = new ItemStack(
                    BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemId)),
                    count
            );
            this.outputs = List.of(EmiStack.of(outputStack));

            String interaction = (String) outputMap.get("interactionType");
            this.catalyst = switch (interaction) {
                case "bucket" -> EmiStack.of(Items.BUCKET);
                case "bottle" -> EmiStack.of(Items.GLASS_BOTTLE);
                case "bowl"   -> EmiStack.of(Items.BOWL);
                default       -> EmiStack.EMPTY;
            };
        }

        @Override
        public boolean supportsRecipeTree() {
            return true;
        }

        @Override
        public EmiRecipeCategory getCategory() {
            return CATEGORY;
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public List<EmiIngredient> getInputs() {
            return inputs;
        }

        @Override
        public List<EmiStack> getOutputs() {
            return outputs;
        }

        @Override
        public int getDisplayWidth() {
            return 134;
        }

        @Override
        public int getDisplayHeight() {
            return 78;
        }

        @Override
        public void addWidgets(WidgetHolder widgets) {
            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("concoction", "textures/gui/hud/boiling_cauldron_gui.png");

            widgets.addTexture(
                    texture,
                    0, 0, 134, 78, 0, 0, 134, 78, 256, 256);

            int offset = 1;

            widgets.addSlot(inputs.get(0), 11 - offset, 20 - offset).drawBack(false);
            widgets.addSlot(inputs.get(1), 29 - offset, 20 - offset).drawBack(false);
            widgets.addSlot(inputs.get(2), 11 - offset, 38 - offset).drawBack(false);
            widgets.addSlot(inputs.get(3), 29 - offset, 38 - offset).drawBack(false);

            widgets.addSlot(outputs.get(0), 103 - offset, 31 - offset).recipeContext(this).drawBack(false);

            if (!catalyst.isEmpty()) {
                widgets.addSlot(catalyst, 63 - offset, 8 - offset).catalyst(true).drawBack(false);
            }

            int animationTicks = 5000;

            widgets.addAnimatedTexture(texture, 50, 27, 44, 24, 134, 0,
                    animationTicks, true, false, false);

            widgets.addTooltip((mouseX, mouseY) -> {
                if (mouseX >= 50 && mouseX < 94 && mouseY >= 27 && mouseY < 51) {
                    return List.of(
                            ClientTooltipComponent.create(
                                    Component.translatable("gui.cooking_cauldron.time.seconds",
                                                    recipe.getCookingTime() / 20)
                                            .getVisualOrderText()
                            )
                    );
                }
                return List.of();
            }, 0, 0, getDisplayWidth(), getDisplayHeight());
        }
    }
}
