package io.github.commander07.shoutheheckup.commands.sthu;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.github.commander07.shoutheheckup.Config;
import io.github.commander07.shoutheheckup.ShouTheHeckUp;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class AddTextCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("sthu")
                .then(literal("addtext")
                        .then(argument("text", greedyString())
                            .executes(AddTextCommand::handle))));
    }

    public static int handle(CommandContext<FabricClientCommandSource> context) {
        String text = getString(context, "text");
        if (Config.ignoredPlayers.contains(text)) {
            context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " §cThis text already exists."));
        } else {
            context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " §aAdded text §b" + text + "§a to the ignore list!"));
            Config.bannedWords.add(text);
            Config.write(ShouTheHeckUp.MODID);
        }
        return 1;
    }
}
