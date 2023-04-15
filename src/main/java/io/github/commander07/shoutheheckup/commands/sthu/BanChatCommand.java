package io.github.commander07.shoutheheckup.commands.sthu;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.github.commander07.shoutheheckup.Config;
import io.github.commander07.shoutheheckup.ShouTheHeckUp;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class BanChatCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("sthu")
                .then(literal("banchat")
                    .executes(BanChatCommand::handle)));
    }

    public static int handle(CommandContext<FabricClientCommandSource> context) {
        Config.filterChat = !Config.filterChat;
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " Chat filtering is now " + (Config.filterChat ? "§aenabled" : "§cdisabled") + "§r."));
        Config.write(ShouTheHeckUp.MODID);
        return 1;
    }
}
