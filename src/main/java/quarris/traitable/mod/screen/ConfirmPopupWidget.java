package quarris.traitable.mod.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class ConfirmPopupWidget extends AbstractWidget {

    private final Button yesButton;
    private final Button noButton;
    private final Button cancelButton;

    public ConfirmPopupWidget(int x, int y, int width, int height, Component title, IConfirmation confirm) {
        super(x, y, width, height, title);

        this.setMessage(title);

        this.yesButton = new Button(x + 20, y + height - 40, 40, 20, new TranslatableComponent("traitable.screen.confirm_popup.yes"),
                button -> confirm.onConfirm(IConfirmation.ConfirmType.YES));
        this.noButton = new Button(x + 80, y + height - 40, 40, 20, new TranslatableComponent("traitable.screen.confirm_popup.no"),
                button -> confirm.onConfirm(IConfirmation.ConfirmType.NO));
        this.cancelButton = new Button(x + 140, y + height - 40, 40, 20, new TranslatableComponent("traitable.screen.confirm_popup.cancel"),
                button -> confirm.onConfirm(IConfirmation.ConfirmType.CANCEL));
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yesButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.noButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.cancelButton.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontrenderer = minecraft.font;
        this.renderBg(matrixStack, minecraft, mouseX, mouseY);
        int j = getFGColor();
        drawCenteredString(matrixStack, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + 20, j | Mth.ceil(this.alpha * 255.0F) << 24);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, Minecraft minecraft, int mouseX, int mouseY) {
        this.fillGradient(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height, 0xaa222222, 0xaa222222);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (this.yesButton.isMouseOver(mouseX, mouseY)) {
            this.yesButton.onClick(mouseX, mouseY);
        } else if (this.noButton.isMouseOver(mouseX, mouseY)) {
            this.noButton.onClick(mouseX, mouseY);
        } else if (this.cancelButton.isMouseOver(mouseX, mouseY)) {
            this.cancelButton.onClick(mouseX, mouseY);
        }
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {
    }

    public interface IConfirmation {

        void onConfirm(ConfirmType type);

        enum ConfirmType {
            YES, NO, CANCEL
        }

    }
}
