package io.github.commander07.shoutheheckup.commands.sthu;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import io.github.commander07.shoutheheckup.Config;
import io.github.commander07.shoutheheckup.ShouTheHeckUp;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SthuCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> fabricClientCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess) {
        fabricClientCommandSourceCommandDispatcher.register(literal("sthu")
                .executes(SthuCommand::handle));
    }

    public static int handle(CommandContext<FabricClientCommandSource> context) {
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " --------------- Help Page ---------------"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu add - Adds a player to ignore shout"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu addtext - Ignores a line from shout"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu remove - Removes a player from list"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu removetext - Removes line from list"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu list - Lists players ignored so far"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu listtext - Lists banned shout lines"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu toggle - Toggles on/off mod feature"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu sethidelevel - Set hide shout level"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu hidelevel - Shows hide lv behaviors"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " /sthu banchat - Also blocks chat messages"));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " Current Hide Level: " + (Config.hideLevel == 3 ? "§4HIGHEST" : (Config.hideLevel == 2 ? "§cHIGH" : (Config.hideLevel == 1 ? "§eMedium" : (Config.hideLevel == 0 ? "§aLow" : "§7Failed to get"))))));
        context.getSource().getPlayer().sendMessage(Text.literal(ShouTheHeckUp.prefix + " -----------------------------------------"));
        return 1;
    }
}
