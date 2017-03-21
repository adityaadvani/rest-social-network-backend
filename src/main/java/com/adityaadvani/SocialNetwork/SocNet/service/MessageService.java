package com.adityaadvani.SocialNetwork.SocNet.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adityaadvani.SocialNetwork.SocNet.model.Message;
import com.adityaadvani.SocialNetwork.SocNet.model.Profile;
import com.adityaadvani.SocialNetwork.SocNet.database.DatabaseStub;
import com.adityaadvani.SocialNetwork.SocNet.service.CommentService;

public class MessageService {
	
	CommentService cs = new CommentService();

	private Map<Long, Message> messages = DatabaseStub.getMessages();
	private Map<String,Profile> profiles = DatabaseStub.getProfiles();

	public MessageService(){
//		messages.put(1L,new Message(1L,"Hello World", "Aditya"));
//		messages.put(2L,new Message(2L,"Second message", "Aditya"));
	}
	
	
	public List<Message> getAllMessaages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id){
		if(!messages.containsKey(id)) return null;
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		String author = message.getAuthor();
		if(author.isEmpty() || !profiles.containsKey(author))return null;
		
		DatabaseStub.updateMessageCount();
		message.setId(DatabaseStub.getMessageCount());
		messages.put(message.getId(),message);
		profiles.get(message.getAuthor()).getMyMessages().add(message.getId());
		return messages.get(message.getId());
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0 || !messages.containsKey(message.getId())){
			return null;
		}
		Message oldmsg = messages.get(message.getId());
		message.setAuthor(oldmsg.getAuthor());
		message.setCreated(oldmsg.getCreated());
		message.setComments(oldmsg.getComments());
		message.setCommentCount(oldmsg.getCommentCount());
		messages.put(message.getId(),message);
		return messages.get(message.getId());
	}
	
	public Message removeMessage(long id){
		if(!messages.containsKey(id)) return null;
		profiles.get(messages.get(id).getAuthor()).getMyMessages().remove((Long)id);
		Set<Long> comIds = new HashSet<>(messages.get(id).getComments().keySet());
		for(long comId : comIds){
			cs.removeComment(id, comId);
		}
		return messages.remove(id);
	}
}
