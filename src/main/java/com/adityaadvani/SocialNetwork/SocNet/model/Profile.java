package com.adityaadvani.SocialNetwork.SocNet.model;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Profile {

	private long id;
	private String firstName;
	private String lastName;
	private String profileName;
	private Date created;
	private ArrayList<Long> myMessages = new ArrayList<>();
	private ArrayList<Long> myCommentsInMessage = new ArrayList<>();
	
	public Profile(){
		this.created = new Date();
	}
	
	public Profile(long id, String pName, String fName, String lName){
		this.id = id;
		this.profileName = pName;
		this.firstName = fName;
		this.lastName = lName;
		this.created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@XmlTransient
	public ArrayList<Long> getMyMessages() {
		return myMessages;
	}

	public void setMyMessages(ArrayList<Long> myMessages) {
		this.myMessages = myMessages;
	}

	@XmlTransient
	public ArrayList<Long> getMyCommentsInMessage() {
		return myCommentsInMessage;
	}

	public void setMyCommentsInMessage(ArrayList<Long> myCommentsInMessage) {
		this.myCommentsInMessage = myCommentsInMessage;
	}
	
}
