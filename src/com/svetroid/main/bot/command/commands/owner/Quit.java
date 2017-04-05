package com.svetroid.main.bot.command.commands.owner;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

public class Quit extends Command {

	public Quit() {
		settings.setOwnerCommand(true);
		settings.setNameAndAliases("quit", "exit", "terminate", "end", "shutdown");
		settings.setHelp(Main.bot.getPrefix() + "quit");
		settings.setDescription("Shuts down the bot.");
	}

	@Override
	protected void execute(Input input, Output output) {
		output.sendMessage("Shutting down. Bye!");
		input.getJDA().shutdown();
		System.exit(0);
	}
}
