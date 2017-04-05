package com.svetroid.main.bot.authentication;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.Writer;

public class Authentication {

	private String token;
	private String invite;
	private String ownerID;
	private String defaultPrefix;
	private String prefix;
	private ApiKey discordpw;
	private ApiKey osu;

	public void setPrefix(String prefix) {
		this.prefix = prefix;
		Gson gson = new Gson();
		String json = gson.toJson(this);
		try {
			Writer writer = new FileWriter("res/auth/bot-auth.json");
			writer.write(json);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getToken() {
		return token;
	}

	public String getInvite() {
		return invite;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public String getDefaultPrefix() {
		return defaultPrefix;
	}

	public String getPrefix() {
		return prefix;
	}

	public ApiKey getDiscordpw() {
		return discordpw;
	}

	public ApiKey getOsu() {
		return osu;
	}


}
