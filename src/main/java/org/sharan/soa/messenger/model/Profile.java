package org.sharan.soa.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

	private String profileName;
	private String firstName;
	private String lastName;
	private Date created;
	private List<Link> links = new ArrayList<>();
	private long id;

	public Profile() {

	}

	public Profile(String profileName) {
		this.profileName = profileName;
	}

	public Profile(long id, String profileName, String firstName, String lastName) {
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	public Profile(String profileName, String firstName, String lastName) {
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.created = new Date();
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Profile [profileName=" + profileName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", created=" + created
				+ "]";
	}
	

}
