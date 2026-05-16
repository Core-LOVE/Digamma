package katebulka.digamma.helper;

import dev.emi.emi.config.SidebarType;
import net.minecraft.world.item.ItemStack;
import tfar.dei.client.DiscoveredItems;

public class HiderHelper {
    private static final ThreadLocal<SidebarType> sidebarType = ThreadLocal.withInitial(() -> SidebarType.NONE);

    public static void setCurrentSidebarType(SidebarType type) {
        sidebarType.set(type);
    }

    public static SidebarType getCurrentSidebarType() {
        return sidebarType.get();
    }

    public static void clear() {
        sidebarType.remove();
    }

    public static boolean canDarken(ItemStack stack) {
        if (DiscoveredItems.discovered(stack.getItem().getDefaultInstance())) {
            return false;
        }

        SidebarType currentType = HiderHelper.getCurrentSidebarType();

        if (currentType == SidebarType.NONE) {
            return true;
        }

        return currentType == SidebarType.CRAFTABLES || currentType == SidebarType.CRAFT_HISTORY;
    }
}
