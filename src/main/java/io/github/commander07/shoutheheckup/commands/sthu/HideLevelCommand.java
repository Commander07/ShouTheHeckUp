package io.github.commander07.shoutheheckup.commands.sthu;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.github.commander07.shoutheheckup.ShouTheHeckUp;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class HideLevelCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("sthu")
                .then(literal("hidelevel")
                    .executes(HideLevelCommand::handle)));
    }

    public static int handle(CommandContext<FabricClientCommandSource> context) {
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " all - Suppresses ALL shout message"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " high - Suppresses shout message"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " medium - Replaces shout message"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " low - Replaces shout message with sender name"));
        return 1;
    }
}
