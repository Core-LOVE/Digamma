package katebulka.digamma.mixin.client;

import com.ordana.immersive_weathering.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(
        value = ClientDynamicResourcesHandler.class,
        remap = false
)
public abstract class IWClientDynamicResourcesHandlerMixin {
    @Overwrite
    public boolean dependsOnLoadedPacks() {
        return false;
    }

    @Overwrite
    public void addLeafPilesModel(StaticResource resource, String id, ResourceLocation texturePath) {

    }

    @Overwrite
    public void regenerateDynamicAssets(ResourceManager manager) {

    }

    @Overwrite
    public void addDynamicTranslations(AfterLanguageLoadEvent lang) {

    }
}
