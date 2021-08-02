package quarris.traitable.mod;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quarris.traitable.api.TraitableAPI;
import quarris.traitable.mod.network.COpenConfigPacket;
import quarris.traitable.mod.network.PacketHandler;
import quarris.traitable.mod.traits.TraitHolder;
import quarris.traitable.mod.traits.TraitManager;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mod.EventBusSubscriber(modid = ModRef.ID)
public class CommonEvents {

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (TraitableAPI.hasAttachedTraits(event.getObject())) {
            event.addCapability(ModRef.createRes("trait_holder"), new TraitHolder.Provider(new TraitHolder(event.getObject())));
        }
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        TraitsCommand.register(event.getDispatcher());
    }

    public static int viewTraits(CommandContext<CommandSourceStack> ctx) {
        List<String> traitKeys = TraitableAPI.TRAIT_REGISTRY.getKeys().stream().map(rl -> rl.toString()).collect(Collectors.toList());
        ctx.getSource().sendSuccess(new TextComponent(String.join(", ", traitKeys)), false);
        return 0;
    }

    public static int getTraits(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        Set<String> traitKeys = TraitableAPI.getTraitsForEntity(EntityArgument.getEntity(ctx, "entity").getType()).stream().map(rl -> rl.toString()).collect(Collectors.toSet());
        ctx.getSource().sendSuccess(new TextComponent(String.join(", ", traitKeys)), false);
        return 0;
    }

}
