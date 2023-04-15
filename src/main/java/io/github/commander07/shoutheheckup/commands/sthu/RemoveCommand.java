package io.github.commander07.shoutheheckup.commands.sthu;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.github.commander07.shoutheheckup.Config;
import io.github.commander07.shoutheheckup.ShouTheHeckUp;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class RemoveCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("sthu")
                .then(literal("remove")
                    .then(argument("name", word())
                        .executes(RemoveCommand::handle))));
    }

    public static int handle(CommandContext<FabricClientCommandSource> context) {
        String playerName = getString(context, "name");
        if (Config.ignoredPlayers.contains(playerName)) {
            Config.ignoredPlayers.remove(playerName);
            Config.write(ShouTheHeckUp.MODID);
            context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " §aRemoved player §b" + playerName + "§a from the ignore list!"));
        } else {
            context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " §cThe player does not exist from the list."));
        }
        return 1;
    }
}
