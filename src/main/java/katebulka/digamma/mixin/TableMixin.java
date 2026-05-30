package katebulka.digamma.mixin;

import org.spongepowered.asm.mixin.Mixin;
import com.feliscape.artistry.content.block.TableBlock;

@Mixin(
        value = TableBlock.class,
        remap = false
)
public class TableMixin {

}
