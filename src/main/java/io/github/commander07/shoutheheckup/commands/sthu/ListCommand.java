package io.github.commander07.shoutheheckup.commands.sthu;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.github.commander07.shoutheheckup.Config;
import io.github.commander07.shoutheheckup.ShouTheHeckUp;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ListCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("sthu")
                .then(literal("list")
                    .executes(ListCommand::handle)));
    }

    public static int handle(CommandContext<FabricClientCommandSource> context) {
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " Current ignored players: " + Config.ignoredPlayers));
        return 1;
    }
}
