package com.adityaadvani.SocialNetwork.SocNet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adityaadvani.SocialNetwork.SocNet.database.DatabaseStub;
import com.adityaadvani.SocialNetwork.SocNet.model.Message;
import com.adityaadvani.SocialNetwork.SocNet.model.Profile;
import com.adityaadvani.SocialNetwork.SocNet.model.Comment;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseStub.getMessages();
	private Map<String, Profile> profiles = DatabaseStub.getProfiles();
	
	public CommentService(){
		
	}
	
	public List<Comment> getAllComments(long messageId) {
		if(!messages.containsKey(messageId)) return null;
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
		if(!messages.containsKey(messageId)) return null;
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(!comments.containsKey(commentId)) return null;
		Comment comment = comments.get(commentId);
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
		if(comment.getAuthor().isEmpty()) return null;
		if(!profiles.containsKey(comment.getAuthor())) return null;
		if(!messages.containsKey(messageId)) return null;
		Message msg = messages.get(messageId);
		Map<Long, Comment> comments = msg.getComments();
		msg.updateCommentCount();
		comment.setId(msg.getCommentCount());
		comments.put(comment.getId(), comment);
		profiles.get(comment.getAuthor()).getMyCommentsInMessage().add(messageId);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		if(!messages.containsKey(messageId)) return null;
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0 || !comments.containsKey(comment.getId())) {
			return null;
		}
		Comment com = comments.get(comment.getId());
		comment.setAuthor(com.getAuthor());
		comment.setCreated(com.getCreated());
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
//		System.out.println("in remove comment");
		if(!messages.containsKey(messageId)) return null;
//		System.out.println("message exists");
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(!comments.containsKey(commentId)) return null;
//		System.out.println("comment found");
		Comment com = comments.remove(commentId);
//		System.out.println("comment deleted");
		boolean moreComsByAuthor = false;
		for(long l : comments.keySet()){
			Comment c = comments.get(l);
			if(c.getAuthor().equals(com.getAuthor())){
				moreComsByAuthor = true;
				break;
			}
		}
		if(!moreComsByAuthor){
			profiles.get(com.getAuthor()).getMyCommentsInMessage().remove((Long)messageId);
		}
		return com;
	}
}
