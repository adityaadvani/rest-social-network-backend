package com.adityaadvani.SocialNetwork.SocNet.database;

import java.util.HashMap;
import java.util.Map;

import com.adityaadvani.SocialNetwork.SocNet.model.Message;
import com.adityaadvani.SocialNetwork.SocNet.model.Profile;

public class DatabaseStub {

	private static long profileCount = 0;
	private static long messageCount = 0;
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long,Message> getMessages(){
		return messages;
	}
	
	public static Map<String,Profile> getProfiles(){
		return profiles;
	}
	
	public static long getProfileCount(){
		return profileCount;
	}
	
	public static void updateProfileCount(){
		++profileCount;
	}
	
	public static long getMessageCount(){
		return messageCount;
	}
	
	public static void updateMessageCount(){
		++messageCount;
	}
	
	
}
