package com.adityaadvani.SocialNetwork.SocNet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adityaadvani.SocialNetwork.SocNet.model.Profile;
import com.adityaadvani.SocialNetwork.SocNet.model.Message;
import com.adityaadvani.SocialNetwork.SocNet.model.Comment;
import com.adityaadvani.SocialNetwork.SocNet.database.DatabaseStub;
import com.adityaadvani.SocialNetwork.SocNet.service.MessageService;
import com.adityaadvani.SocialNetwork.SocNet.service.CommentService;

public class ProfileService {
	
	MessageService ms = new MessageService();
	CommentService cs = new CommentService();
	
	private Map<String, Profile> profiles = DatabaseStub.getProfiles();
	private Map<Long, Message> messages = DatabaseStub.getMessages();
	
	public ProfileService(){
		
	}
	
	

	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String pName){
		return profiles.get(pName);
	}
	
	public Profile addProfile(Profile profile){
		if(profiles.containsKey(profile.getProfileName())) return null;
		
		DatabaseStub.updateProfileCount();
		profile.setId(DatabaseStub.getProfileCount());
		profile.setCreated(new Date());
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile,String OldName){
		if(!profiles.containsKey(OldName)) return null;
		Profile p = profiles.get(OldName);
		if(profile.getFirstName()==null||profile.getFirstName().isEmpty())profile.setFirstName(p.getFirstName());
		if(profile.getLastName()==null || profile.getLastName().isEmpty())profile.setLastName(p.getLastName());
		profile.setId(p.getId());
		profile.setCreated(p.getCreated());
		profile.setMyMessages(p.getMyMessages());

		
		if(profile.getProfileName()!=null && !profile.getProfileName().isEmpty() && !profile.getProfileName().equals(OldName)){
			ArrayList<Long> myMessages = p.getMyMessages();
			for(long l: myMessages){
				Message msg = messages.get(l);
				msg.setAuthor(profile.getProfileName());
			}
			ArrayList<Long> myCommentsInMessages = p.getMyCommentsInMessage();
			for(long l : myCommentsInMessages){
				Message msg = messages.get(l);
				Map<Long,Comment> coms = msg.getComments();
				for(long ll : coms.keySet()){
					if(coms.get(ll).getAuthor().equals(OldName)){
						coms.get(ll).setAuthor(profile.getProfileName());
					}
				}
			}
			profiles.remove(OldName);
		} else {
			profile.setProfileName(OldName);
		}
		
		profiles.put(profile.getProfileName(),profile);
		
		return profile;
	}
	
	public Profile removeProfile(String pName){
		if(!profiles.containsKey(pName)) return null;
		ArrayList<Long> myMessages = new ArrayList<>(profiles.get(pName).getMyMessages());
		ArrayList<Long> myCommentsInMessages = new ArrayList<>(profiles.get(pName).getMyCommentsInMessage());
		for(long l : myMessages){
			ms.removeMessage(l);
		}
		
		myCommentsInMessages = new ArrayList<>(profiles.get(pName).getMyCommentsInMessage());
//		for(long l: myCommentsInMessages){
		//	System.out.println("comment present in: " + l);
//		}
		
		for(long msgId : myCommentsInMessages){
			if(!messages.containsKey(msgId)) continue;
			Message msg = messages.get(msgId);
			if(msg.getComments() == null) continue;
			Map<Long,Comment> coms = msg.getComments();
			Set<Long> comIds = new HashSet<>(coms.keySet());
			for(long comId : comIds){
				Comment c = coms.get(comId);
				if(c.getAuthor().equals(pName)){
//					System.out.println(pName + " has a comment in message id " + msgId + " at commentId " + comId);
					cs.removeComment(msgId, comId);
				}
			}
		}
		return profiles.remove(pName);
	}	
}
