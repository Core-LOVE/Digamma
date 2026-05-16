package katebulka.digamma.mixin;

import katebulka.digamma.content.block.CupboardBlock;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.violetmoon.quark.addons.oddities.block.CrateBlock;
import org.violetmoon.quark.addons.oddities.module.CrateModule;
import org.violetmoon.zeta.module.ZetaModule;

@Mixin(
        value = CrateModule.class,
        remap = true,
        priority = 3000
)
public abstract class CrateModuleMixin {
    @Shadow
    public static Block crate;

    @Redirect(
            method = "register",
            at = @At(
                    value = "NEW",
                    target = "org/violetmoon/quark/addons/oddities/block/CrateBlock",
                    ordinal = 0
            )
    )
    private CrateBlock redirectCrateModule(ZetaModule module) {
        CupboardBlock myBlock = new CupboardBlock(module);
        crate = myBlock;
        return myBlock;
    }
}
