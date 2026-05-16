package katebulka.digamma.mixin.client;

import com.evandev.advancement_enhancement.advancements.AdvancementDisplayInfo;
import com.evandev.advancement_enhancement.api.IAdvancementEntryGui;
import com.evandev.advancement_enhancement.gui.EnhancedAdvancementTab;
import com.evandev.advancement_enhancement.gui.EnhancedAdvancementWidget;
import com.evandev.advancement_enhancement.gui.screens.EnhancedAdvancementsScreen;
import com.evandev.advancement_enhancement.reference.Resources;
import com.evandev.advancement_enhancement.util.CriterionGrid;
import com.evandev.advancement_enhancement.util.RenderUtil;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;
import java.util.Objects;

@Mixin(
        value = EnhancedAdvancementWidget.class
)
public abstract class EnhancedAdvancementWidgetMixin implements IAdvancementEntryGui {
    @Shadow private float hoverAnim = 0.0F;
    @Shadow private AdvancementProgress advancementProgress;
    @Shadow private DisplayInfo displayInfo;
    @Shadow public AdvancementDisplayInfo enhancedDisplayInfo;
    @Shadow protected int x;
    @Shadow protected int y;
    @Shadow private final List<EnhancedAdvancementWidget> children = Lists.newArrayList();
    @Shadow private int width;
    @Shadow private Minecraft minecraft;
    @Shadow private EnhancedAdvancementTab advancementTabGui;
    @Shadow private List<FormattedCharSequence> description;
    @Shadow private CriterionGrid criterionGrid;
    @Shadow private String title;

    @Shadow protected void render9Sprite(GuiGraphics guiGraphics, int x, int y, int width, int height, int textureHeight, int textureWidth, int textureDistance, int textureX, int textureY) {}
    @Shadow private void refreshHover() {}
    @Shadow
    public boolean isMouseOver(double scrollX, double scrollY, double mouseX, double mouseY) {
        throw new AssertionError();
    }

    @Unique private static final float ICON_SCALE = 0.2F;
    @Unique private static final double DESC_PAD = 1;
    @Unique private static final double DESC_OFFSET = 4;

    @Overwrite
    public void drawHover(GuiGraphics guiGraphics, int scrollX, int scrollY, int left, int top) {
        if (!EnhancedAdvancementsScreen.enableEditMode) {
            boolean drawLeft = left + scrollX + this.x + this.width + 26 >= this.advancementTabGui.getScreen().internalWidth;
            String s = this.advancementProgress != null && this.advancementProgress.getProgressText() != null ? this.advancementProgress.getProgressText().getString() : null;
            int i = s == null ? 0 : this.minecraft.font.width(s);
            boolean drawTop;
            if (CriterionGrid.requiresShift && !Screen.hasShiftDown()) {
                int var28 = top + scrollY + this.y;
                int var29 = this.description.size();
                Objects.requireNonNull(this.minecraft.font);
                drawTop = var28 + var29 * 9 + 50 >= this.advancementTabGui.getScreen().height;
            } else if (this.criterionGrid.height < this.advancementTabGui.getScreen().height) {
                int var10000 = top + scrollY + this.y;
                int var10001 = this.description.size();
                Objects.requireNonNull(this.minecraft.font);
                drawTop = var10000 + var10001 * 9 + this.criterionGrid.height + 50 >= this.advancementTabGui.getScreen().height;
            } else {
                drawTop = false;
            }

            float percentageObtained = this.advancementProgress == null ? 0.0F : this.advancementProgress.getPercent();
            int j = Mth.floor(percentageObtained * (float)this.width);
            AdvancementWidgetType stateTitleLeft;
            AdvancementWidgetType stateTitleRight;
            AdvancementWidgetType stateIcon;
            if (percentageObtained >= 1.0F) {
                j = this.width / 2;
                stateTitleLeft = AdvancementWidgetType.OBTAINED;
                stateTitleRight = AdvancementWidgetType.OBTAINED;
                stateIcon = AdvancementWidgetType.OBTAINED;
            } else if (j < 2) {
                j = this.width / 2;
                stateTitleLeft = AdvancementWidgetType.UNOBTAINED;
                stateTitleRight = AdvancementWidgetType.UNOBTAINED;
                stateIcon = AdvancementWidgetType.UNOBTAINED;
            } else if (j > this.width - 2) {
                j = this.width / 2;
                stateTitleLeft = AdvancementWidgetType.OBTAINED;
                stateTitleRight = AdvancementWidgetType.OBTAINED;
                stateIcon = AdvancementWidgetType.UNOBTAINED;
            } else {
                stateTitleLeft = AdvancementWidgetType.OBTAINED;
                stateTitleRight = AdvancementWidgetType.UNOBTAINED;
                stateIcon = AdvancementWidgetType.UNOBTAINED;
            }

            int k = this.width - j;
            RenderSystem.enableBlend();
            int drawY = scrollY + this.y;
            int drawX;
            if (drawLeft) {
                drawX = scrollX + this.x - this.width + 26 + 6;
            } else {
                drawX = scrollX + this.x;
            }

            int boxHeight;
            int boxWidth = this.width;

            if (CriterionGrid.requiresShift && !Screen.hasShiftDown()) {
                double var31 = this.description.size() + DESC_PAD;
                Objects.requireNonNull(this.minecraft.font);
                boxHeight = (int) (32 + var31 * 9);
            } else {
                double var30 = this.description.size() + DESC_PAD;
                Objects.requireNonNull(this.minecraft.font);
                boxHeight = (int) (32 + var30 * 9 + this.criterionGrid.height);
            }
//
//            boxWidth = (int) (boxWidth + DESC_PAD);

            if (!this.description.isEmpty()) {
                if (drawTop) {
                    this.render9Sprite(guiGraphics, drawX, drawY + 26 - boxHeight, boxWidth, boxHeight, 10, 256, 26, 0, 52);
                } else {
                    this.render9Sprite(guiGraphics, drawX, drawY, boxWidth, boxHeight, 10, 256, 26, 0, 52);
                }
            }

            RenderUtil.setColor(this.enhancedDisplayInfo.getTitleColor(stateTitleLeft));
            int left_side = Math.min(j, 240);
            guiGraphics.blit(Resources.Gui.WIDGETS, drawX, drawY, 0, this.enhancedDisplayInfo.getTitleYMultiplier(stateTitleLeft) * 26, left_side, 26);
            if (left_side < j) {
                guiGraphics.blit(Resources.Gui.WIDGETS, drawX + left_side, drawY, 16, this.enhancedDisplayInfo.getTitleYMultiplier(stateTitleLeft) * 26, j - left_side, 26);
            }

            RenderUtil.setColor(this.enhancedDisplayInfo.getTitleColor(stateTitleRight));
            int right_side = Math.min(k, 240);
            guiGraphics.blit(Resources.Gui.WIDGETS, drawX + j, drawY, 256 - right_side, this.enhancedDisplayInfo.getTitleYMultiplier(stateTitleRight) * 26, right_side, 26);
            if (right_side < k) {
                guiGraphics.blit(Resources.Gui.WIDGETS, drawX + j + right_side - 2, drawY, 256 - k + right_side - 2, this.enhancedDisplayInfo.getTitleYMultiplier(stateTitleRight) * 26, k - right_side + 2, 26);
            }

            float f = this.advancementProgress == null ? 0.0F : this.advancementProgress.getPercent();
            AdvancementWidgetType advancementState;
            if (f >= 1.0F) {
                advancementState = AdvancementWidgetType.OBTAINED;
            } else {
                advancementState = AdvancementWidgetType.UNOBTAINED;
            }

            RenderSystem.enableBlend();
            guiGraphics.pose().pushPose();
            float scale = 1.0F + this.hoverAnim * ICON_SCALE;
            float centerX = (float)(scrollX + this.x + 3) + 13.0F;
            float centerY = (float)(scrollY + this.y) + 13.0F;
            guiGraphics.pose().translate(centerX, centerY, 0.0F);
            guiGraphics.pose().scale(scale, scale, 1.0F);
            guiGraphics.pose().translate(-centerX, -centerY, 0.0F);
            guiGraphics.blitSprite(advancementState.frameSprite(this.displayInfo.getType()), scrollX + this.x + 3, scrollY + this.y, 26, 26);
            RenderUtil.setColor(this.enhancedDisplayInfo.defaultIconColor());
            guiGraphics.renderFakeItem(this.displayInfo.getIcon(), scrollX + this.x + 8, scrollY + this.y + 5);
            guiGraphics.pose().popPose();

            if (drawLeft) {
                guiGraphics.drawString(this.minecraft.font, this.title, drawX + 5, scrollY + this.y + 9, -1);
                if (s != null) {
                    guiGraphics.drawString(this.minecraft.font, s, scrollX + this.x - i, scrollY + this.y + 9, -1);
                }
            } else {
                guiGraphics.drawString(this.minecraft.font, this.title, scrollX + this.x + 32, scrollY + this.y + 9, -1);
                if (s != null) {
                    guiGraphics.drawString(this.minecraft.font, s, scrollX + this.x + this.width - i - 5, scrollY + this.y + 9, -1);
                }
            }

            int yOffset;
            if (drawTop) {
                yOffset = drawY + 26 - boxHeight + 7;
            } else {
                yOffset = scrollY + this.y + 9 + 17;
            }

            for(int k1 = 0; k1 < this.description.size(); ++k1) {
                Font var32 = this.minecraft.font;
                FormattedCharSequence var10002 = (FormattedCharSequence)this.description.get(k1);
                int var10003 = drawX + 5;
                Objects.requireNonNull(this.minecraft.font);
                guiGraphics.drawString(var32, var10002, var10003, (float) ((yOffset + k1 * 9) + (DESC_OFFSET)), -5592406, false);
            }

            if (this.criterionGrid != null && !CriterionGrid.requiresShift || Screen.hasShiftDown()) {
                int xOffset = drawX + 5;
                int var33 = this.description.size();
                Objects.requireNonNull(this.minecraft.font);
                yOffset += var33 * 9;

                for(int colIndex = 0; colIndex < this.criterionGrid.columns.size(); ++colIndex) {
                    CriterionGrid.Column col = (CriterionGrid.Column)this.criterionGrid.columns.get(colIndex);

                    for(int rowIndex = 0; rowIndex < col.cells().size(); ++rowIndex) {
                        Font var34 = this.minecraft.font;
                        Component var35 = (Component)col.cells().get(rowIndex);
                        Objects.requireNonNull(this.minecraft.font);
                        guiGraphics.drawString(var34, var35, xOffset, yOffset + rowIndex * 9, -5592406, false);
                    }

                    xOffset += col.width();
                }
            }

//            guiGraphics.renderFakeItem(this.displayInfo.getIcon(), scrollX + this.x + 8, scrollY + this.y + 5);
        }
    }

    @Overwrite
    public void draw(GuiGraphics guiGraphics, int scrollX, int scrollY, double unzoomedX, double unzoomedY) {
        boolean isHovered = this.isMouseOver((double)scrollX, (double)scrollY, unzoomedX, unzoomedY);
        if (isHovered) {
            this.hoverAnim = Math.min(1.0F, this.hoverAnim + 0.15F);
        } else {
            this.hoverAnim = Math.max(0.0F, this.hoverAnim - 0.15F);
        }

        if (!this.displayInfo.isHidden() || this.advancementProgress != null && this.advancementProgress.isDone()) {
            float f = this.advancementProgress == null ? 0.0F : this.advancementProgress.getPercent();
            AdvancementWidgetType advancementState;
            if (f >= 1.0F) {
                advancementState = AdvancementWidgetType.OBTAINED;
            } else {
                advancementState = AdvancementWidgetType.UNOBTAINED;
            }

            int baseColor = this.enhancedDisplayInfo.getIconColor(advancementState);
            if (this.hoverAnim > 0.0F) {
                int r = baseColor >> 16 & 255;
                int g = baseColor >> 8 & 255;
                int b = baseColor & 255;
                r = (int)((float)r + (float)(255 - r) * this.hoverAnim * 0.4F);
                g = (int)((float)g + (float)(255 - g) * this.hoverAnim * 0.4F);
                b = (int)((float)b + (float)(255 - b) * this.hoverAnim * 0.4F);
                baseColor = -16777216 | r << 16 | g << 8 | b;
            }

            RenderUtil.setColor(baseColor);
            RenderSystem.enableBlend();
            guiGraphics.pose().pushPose();
            float scale = 1.0F + this.hoverAnim * ICON_SCALE;
            float centerX = (float)(scrollX + this.x + 3) + 13.0F;
            float centerY = (float)(scrollY + this.y) + 13.0F;
            guiGraphics.pose().translate(centerX, centerY, 0.0F);
            guiGraphics.pose().scale(scale, scale, 1.0F);
            guiGraphics.pose().translate(-centerX, -centerY, 0.0F);
            guiGraphics.blitSprite(advancementState.frameSprite(this.displayInfo.getType()), scrollX + this.x + 3, scrollY + this.y, 26, 26);
            RenderUtil.setColor(this.enhancedDisplayInfo.defaultIconColor());
            guiGraphics.renderFakeItem(this.displayInfo.getIcon(), scrollX + this.x + 8, scrollY + this.y + 5);
            guiGraphics.pose().popPose();
        }

        for(EnhancedAdvancementWidget advancementWidget : this.children) {
            advancementWidget.draw(guiGraphics, scrollX, scrollY, unzoomedX, unzoomedY);
        }

    }
}
