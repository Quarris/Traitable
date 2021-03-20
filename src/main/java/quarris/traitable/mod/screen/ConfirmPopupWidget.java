package quarris.traitable.mod.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ConfirmPopupWidget extends Widget {

    private final Button yesButton;
    private final Button noButton;
    private final Button cancelButton;

    public ConfirmPopupWidget(int x, int y, int width, int height, ITextComponent title, IConfirmation confirm) {
        super(x, y, width, height, title);

        this.setMessage(title);

        this.yesButton = new Button(x + 20, y + height - 40, 40, 20, new TranslationTextComponent("traitable.screen.confirm_popup.yes"),
                button -> confirm.onConfirm(IConfirmation.ConfirmType.YES));
        this.noButton = new Button(x + 80, y + height - 40, 40, 20, new TranslationTextComponent("traitable.screen.confirm_popup.no"),
                button -> confirm.onConfirm(IConfirmation.ConfirmType.NO));
        this.cancelButton = new Button(x + 140, y + height - 40, 40, 20, new TranslationTextComponent("traitable.screen.confirm_popup.cancel"),
                button -> confirm.onConfirm(IConfirmation.ConfirmType.CANCEL));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yesButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.noButton.render(matrixStack, mouseX, mouseY, partialTicks);
        this.cancelButton.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontrenderer = minecraft.fontRenderer;
        this.renderBg(matrixStack, minecraft, mouseX, mouseY);
        int j = getFGColor();
        drawCenteredString(matrixStack, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + 20, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, Minecraft minecraft, int mouseX, int mouseY) {
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

    public interface IConfirmation {

        void onConfirm(ConfirmType type);

        enum ConfirmType {
            YES, NO, CANCEL
        }

    }
}
