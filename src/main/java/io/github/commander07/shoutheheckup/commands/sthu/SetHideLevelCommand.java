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

public class SetHideLevelCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("sthu")
                .then(literal("sethidelevel")
                    .then(argument("level", word())
                        .executes(SetHideLevelCommand::handle))));
    }

    public static int handle(CommandContext<FabricClientCommandSource> context) {
        String level = getString(context, "level");
        switch (level) {
            case "all" -> Config.hideLevel = 3;
            case "high" -> Config.hideLevel = 2; // Entirely hide
            case "medium" -> Config.hideLevel = 1; // Shows [Blocked Shout Message]
            case "low" -> Config.hideLevel = 0; // Shows [Blocked Shout Message by player]
            default -> {
                context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " §cInvalid hide level."));
                return 0;
            }
        }
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " §aHideLevel has been changed to §b" + level + "§a."));
        Config.write(ShouTheHeckUp.MODID);
        return 1;
    }
}
