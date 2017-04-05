package com.svetroid.main.bot.command.commands.owner;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import net.dv8tion.jda.core.entities.Guild;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ListGuilds extends Command {

	public ListGuilds() {
		settings.setOwnerCommand(true);
		settings.setNameAndAliases("guilds", "listguilds", "guildlist", "getguilds", "servers", "listservers", "serverlist", "getservers");
		settings.setHelp(Main.bot.getPrefix() + "guilds [page]");
		settings.setDescription("Lists all of the guilds that the bot is in.\nYou can also type \"count\" instead of a page number to see the total guild count.");
		settings.setEmbedColor(255, 60, 15);
	}

	@Override
	protected void execute(Input input, Output output) {
		List<Guild> guildList = Main.bot.getJDA().getGuilds();
		int guildCount = guildList.size();

		Map<Integer, List<Guild>> guildMap = new TreeMap<>();
		int pageSize = 10;
		int numPages = (int) (Math.ceil((double) (guildCount) / (double) (pageSize)));
		int page = 0;

		if (input.getArguments().length == 1) {
			if (input.getArgument(0).equalsIgnoreCase("count")) {
				output.sendMessage("I am in **" + guildCount + "** " + (input.getCommandAlias().contains("guild") ? "guild" : "server") + "s!");
				return;
			}
			page = Integer.parseInt(input.getArgument(0)) - 1;
			if (page > numPages - 1 || page < 0) page = numPages - 1;
		}

		for (int i = 0; i < numPages; i++) {
			int min = Math.min(guildCount, pageSize + pageSize * i);
			guildMap.put(i, guildList.subList(pageSize * i, min));
		}

		StringBuilder sb = new StringBuilder();
		String result;
		for (int i = 0; i < guildMap.get(page).size(); i++) {
			Guild currentGuild = guildMap.get(page).get(i);
			sb.append((i + page * pageSize + 1) + ". " + currentGuild.getName() + " (" + currentGuild.getId() + ")\n");
		}
		result = sb.toString().trim();
		output.sendMessage(createEmbed(settings.getEmbedColor(),
		                               (input.getCommandAlias().contains("guild") ? "Guild" : "Server") + "s" + " (" + guildCount + " total) | Page " + (page + 1) + " of " + numPages,
		                               result));
	}

}
