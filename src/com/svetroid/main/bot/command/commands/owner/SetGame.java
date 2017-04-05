package com.svetroid.main.bot.command.commands.owner;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.StringUtils;
import net.dv8tion.jda.core.entities.Game;

public class SetGame extends Command {

	public SetGame() {
		settings.setOwnerCommand(true);
		settings.setNameAndAliases("setgame", "play", "playing");
		settings.setHelp(Main.bot.getPrefix() + "setgame [text]");
		settings.setDescription("Sets the bot's game. This will be reset on startup.");
	}

	@Override
	protected void execute(Input input, Output output) {
		String game = StringUtils.arrayToString(input.getArguments(), " ");
		input.getJDA().getPresence().setGame(Game.of(game));
		output.sendMessage("**" + Main.bot.getJDA().getSelfUser().getName() + "** is now playing `" + game + "`!");
	}

}
