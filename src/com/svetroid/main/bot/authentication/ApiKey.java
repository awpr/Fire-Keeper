package com.svetroid.main.bot.authentication;

public class ApiKey {

	private String key;
	private boolean shouldUse;

	public ApiKey(String key, boolean shouldUse) {
		this.key = key;
		this.shouldUse = shouldUse;
	}

	public String getKey() {
		return key;
	}

	public boolean shouldUse() {
		return shouldUse;
	}
}
