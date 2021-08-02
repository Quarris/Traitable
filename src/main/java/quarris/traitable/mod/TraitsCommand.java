package quarris.traitable.mod;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TextComponent;
import quarris.traitable.mod.network.COpenConfigPacket;
import quarris.traitable.mod.network.PacketHandler;

public class TraitsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("traitable")
            .then(Commands.literal("traits")
                .then(Commands.literal("get")
                    .then(Commands.argument("entity", EntityArgument.entity()))
                    .executes(CommonEvents::getTraits))
                .then(Commands.literal("view").executes(CommonEvents::viewTraits)))
            .then(Commands.literal("config")
                .executes(source -> {
                    source.getSource().sendSuccess(new TextComponent("Opening Traitable Gui!"), false);
                    PacketHandler.sendTo(source.getSource().getPlayerOrException(), new COpenConfigPacket());
                    return 1;
                }))
        );
    }
}
