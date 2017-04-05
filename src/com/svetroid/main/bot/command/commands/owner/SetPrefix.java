package com.svetroid.main.bot.command.commands.owner;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.bot.command.CommandHandler;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.StringUtils;

public class SetPrefix extends Command {

	public SetPrefix() {
		settings.setOwnerCommand(true);
		settings.setName("setprefix");
		settings.setHelp(Main.bot.getPrefix() + "setprefix [prefix]");
		settings.setDescription("Sets the bot's prefix. Use \"default\" to return the prefix to the default one.");
	}

	@Override
	protected void execute(Input input, Output output) {
		String prefixString;
		if (input.getArguments().length == 1) {
			if (input.getArgument(0).equalsIgnoreCase("default")) {
				prefixString = Main.bot.getDefaultPrefix();
			} else if (input.getRawContent().endsWith("_")) {
				prefixString = StringUtils.replaceLast(input.getArgument(0), "_", " ");
			} else {
				prefixString = input.getArgument(0);
			}

			if (prefixString != null) {
				String oldPrefix = Main.bot.getPrefix();
				Main.bot.getAuth().setPrefix(prefixString);
				output.sendMessage("Set prefix to `" + Main.bot.getPrefix() + "`!");
				for (Command c : CommandHandler.commands) c.getSettings().setHelp(c.getSettings().getHelp().replace(oldPrefix, Main.bot.getPrefix()));
			}

		}
	}

}
