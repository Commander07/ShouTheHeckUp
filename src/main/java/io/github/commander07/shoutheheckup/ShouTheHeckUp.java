package io.github.commander07.shoutheheckup;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.commander07.shoutheheckup.commands.sthu.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShouTheHeckUp implements ModInitializer {
	public static final String MODID = "shoutheheckup-fabric";
	public static String prefix = "§6[§rSTHU§6]";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		MidnightConfig.init(MODID, Config.class);
		ClientCommandRegistrationCallback.EVENT.register(SthuCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(AddCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(RemoveCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(AddTextCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(RemoveTextCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(ListCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(ListTextCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(HideLevelCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(SetHideLevelCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(ToggleCommand::register);
		ClientCommandRegistrationCallback.EVENT.register(BanChatCommand::register);
	}
}
