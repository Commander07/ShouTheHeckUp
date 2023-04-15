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

public class AddCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        fabricClientCommandSourceCommandDispatcher.register(literal("sthu")
                .then(literal("add")
                        .then(argument("name", word())
                            .executes(AddCommand::handle))));
    }

    public static int handle(CommandContext<FabricClientCommandSource> context) {
        String playerName = getString(context, "name");
        if (Config.ignoredPlayers.contains(playerName)) {
            context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " §cThis player already exists."));
        } else {
            context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " §aAdded player §b" + playerName + "§a to the ignore list!"));
            Config.ignoredPlayers.add(playerName);
            Config.write(ShouTheHeckUp.MODID);
        }
        return 1;
    }
}
