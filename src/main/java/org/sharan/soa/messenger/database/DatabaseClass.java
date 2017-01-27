package org.sharan.soa.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.sharan.soa.messenger.model.Comment;
import org.sharan.soa.messenger.model.Message;
import org.sharan.soa.messenger.model.Profile;

public class DatabaseClass {
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();

	public DatabaseClass(){

	}
	
	public static void setMessages(Map<Long, Message> messages) {
		DatabaseClass.messages = messages;
	}

	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static void setProfiles(Map<String, Profile> profiles) {
		DatabaseClass.profiles = profiles;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
}
