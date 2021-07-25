package quarris.traitable.mod.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class TraitableConfigScreen extends Screen {

    private final ConfirmPopupWidget.IConfirmation confirmation = (result) -> {
        if (result == ConfirmPopupWidget.IConfirmation.ConfirmType.YES) {
            this.save();
            this.onClose();
        } else if (result == ConfirmPopupWidget.IConfirmation.ConfirmType.NO) {
            this.discard = true;
            this.onClose();
        } else if (result == ConfirmPopupWidget.IConfirmation.ConfirmType.CANCEL) {
            this.confirmPopup = null;
        }
    };

    private static final Component CONFIRMATION_TITLE = new TranslatableComponent("traitable.screen.config.confirm_save");

    private boolean modified;
    private boolean discard;
    private ConfirmPopupWidget confirmPopup = null;

    public TraitableConfigScreen() {
        super(new TranslatableComponent("traitable.screen.config.title"));
    }

    @Override
    protected void init() {
        if (this.confirmPopup != null) {
            this.popup(CONFIRMATION_TITLE, this.confirmation);
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.confirmPopup != null) {
            this.confirmPopup.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void onClose() {
        if (this.confirmPopup != null) {
            this.confirmPopup = null;
        }
        if (this.modified && !this.discard) {
            this.popup(CONFIRMATION_TITLE, this.confirmation);
        } else {
            super.onClose();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.confirmPopup != null) {
            if (button == 0 && this.confirmPopup.isMouseOver(mouseX, mouseY)) {
                this.confirmPopup.onClick(mouseX, mouseY);
                return true;
            }
            return false;
        } else {
            this.modified = true;
            return super.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void save() {
        System.out.println("Saving");
        this.modified = false;
    }

    public void popup(Component title, ConfirmPopupWidget.IConfirmation result) {
        this.confirmPopup = new ConfirmPopupWidget((this.width - 170) / 2, (this.height - 120) / 2, 200, 100, title, result);
    }
}
