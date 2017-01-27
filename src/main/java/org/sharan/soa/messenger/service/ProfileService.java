package org.sharan.soa.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sharan.soa.messenger.database.DatabaseClass;
import org.sharan.soa.messenger.database.ProfileDB;
import org.sharan.soa.messenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public void initProfiles() {

	}

	public List<Profile> getAllProfiles() {
		List<Profile> profileList = new ArrayList<>();
		ProfileDB db = new ProfileDB();
		try {
			profileList = db.getAllProfiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profileList;
	}

	public Profile addProfile(Profile p) {
		
		List<Profile> profileList = new ArrayList<>();
		ProfileDB db = new ProfileDB();
		try {
			profileList = db.getAllProfiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setId(profileList.size()+1);
		return db.addProfile(p);
	}

	public Profile updateProfile(Profile p) {
		if (p.getId() <= 0) {
			return null;
		}
		ProfileDB db = new ProfileDB();
		db.updatePofile(p);
		return p;
	}

	public Profile deleteProfile(String profileName) {
		ProfileDB db = new ProfileDB();
		return db.deleteProfile(profileName);
	}

	public Profile getProfile(String profileName) {
		List<Profile> profileList = new ArrayList<>();
		ProfileDB db = new ProfileDB();
		try {
			profileList = db.getAllProfiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Profile p : profileList){
			if(p.getProfileName() == profileName){
				return p;
			}
		}
		return new Profile("No Profile Found");
		
	}
}
