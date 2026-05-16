package katebulka.digamma.mixin;

import dev.tazer.clutternomore.neoforge.NeoForgeClientEvents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NeoForgeClientEvents.class)
public abstract class CNMNeoForgeClientEventsMixin {
//
//    @SubscribeEvent @Overwrite
//    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onKeyInput(InputEvent.Key event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onKeyInput(InputEvent.MouseButton.Post event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onMouseScrolling(InputEvent.MouseScrollingEvent event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onScreenScroll(ScreenEvent.MouseScrolled.Post event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onScreenInput(ScreenEvent.KeyPressed.Post event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onScreenInput(ScreenEvent.MouseButtonPressed.Post event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onScreenInput(ScreenEvent.KeyReleased.Post event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onScreenInput(ScreenEvent.MouseButtonReleased.Post event) {
//
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onRenderGui(RenderGuiEvent.Post event) {
//        ClutterNoMoreClient.onRenderGui(event.getGuiGraphics(), event.getPartialTick());
//    }
//
//    @SubscribeEvent @Overwrite
//    public static void onPlayerTick(PlayerTickEvent.Pre event) {
//
//    }
//
    @SubscribeEvent @Overwrite
    private static void clientSetup(FMLClientSetupEvent event) {

    }
}
