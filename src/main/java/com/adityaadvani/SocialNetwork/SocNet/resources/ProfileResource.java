package com.adityaadvani.SocialNetwork.SocNet.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adityaadvani.SocialNetwork.SocNet.service.ProfileService;
import com.adityaadvani.SocialNetwork.SocNet.model.Profile;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService ps = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return ps.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String pName){
		return ps.getProfile(pName);
	}
	
	@POST
	public Profile addProfile(Profile profile){
		return ps.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String pName, Profile profile){
		return ps.updateProfile(profile,pName);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String pName){
		ps.removeProfile(pName);
	}
}
