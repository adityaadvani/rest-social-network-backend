package com.adityaadvani.SocialNetwork.SocNet.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adityaadvani.SocialNetwork.SocNet.model.Message;
import com.adityaadvani.SocialNetwork.SocNet.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService ms = new MessageService();
	
	@GET
	public List<Message> getMessages(){
		return ms.getAllMessaages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id){
		return ms.getMessage(id);
	}
	
	@POST
	public Message addMessge(Message message){
		return ms.addMessage(message);	
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
//		System.out.println("message id: " + message.getId());
//		System.out.println("Sending to ms.updateMessage");
		return ms.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id){
		ms.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}