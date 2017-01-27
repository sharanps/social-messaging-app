package org.sharan.soa.messenger.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sharan.soa.messenger.model.Profile;


public class ProfileDB {

	private Connection connect = null;
	private Statement statement = null;

	public List<Profile> getAllProfiles() throws Exception {
		List<Profile> profileList = new ArrayList<>();

		try {
			connect = ConnectionClass.getConnection();
			statement = connect.createStatement();
			String sql = "SELECT `profile`.`id` ,`profile`.`profileName`,`profile`.`firstName`,"
					+ "`profile`.`lastName` FROM `itm566`.`profile`";
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				profileList.add(new Profile(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4)));
			}
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return profileList;
	}

	public void updatePofile(Profile profile) {
		// TODO Auto-generated method stub

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();
			String sql = "UPDATE profile SET profileName= ?, firstName= ?, lastName= ? WHERE id= ?;";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			// preparedStatement.setLong(1, profile.getId());
			preparedStatement.setString(1, profile.getProfileName());
			preparedStatement.setString(2, profile.getFirstName());
			preparedStatement.setString(3, profile.getLastName());
			preparedStatement.setLong(4, profile.getId());
			preparedStatement.execute();

			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}

	}

	public Profile getProfiles(String profileName) throws Exception {
		Profile profile = null;

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();
			String sql = "SELECT `profile`.`id` ,`profile`.`profileName`,`profile`.`firstName`,"
					+ "`profile`.`LastName` FROM `itm566`.`profile` WHERE `profile`.`profileName` = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, profileName);
			preparedStatement.executeQuery();
			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {
				profile = new Profile(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4));
			}
			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return profile;
	}

	public Profile addProfile(Profile profile) {
		// TODO Auto-generated method stub
		Profile newProfile = null;

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();

			String sql = "INSERT INTO `itm566`.`profile` (`profileName`, `firstName`, `lastName`) VALUES (?, ?, ?);";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);

			preparedStatement.setString(1, profile.getProfileName());
			preparedStatement.setString(2, profile.getFirstName());
			preparedStatement.setString(3, profile.getLastName());

			preparedStatement.execute();

			String sql1 = "SELECT `profile`.`id` ,`profile`.`profileName`,`profile`.`firstName`,"
					+ "`profile`.`LastName` FROM `itm566`.`profile` WHERE `profile`.`profileName` = ?";

			PreparedStatement preparedStatement1 = connect.prepareStatement(sql1);
			preparedStatement1.setString(1, profile.getProfileName());
			preparedStatement1.execute();
			ResultSet resultSet = preparedStatement1.getResultSet();
			while (resultSet.next()) {
				newProfile = new Profile(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4));
			}

			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return newProfile;

	}

	public Profile deleteProfile(String profileName) {
		// TODO Auto-generated method stub

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();

			String sql = "DELETE FROM `itm566`.`profile` WHERE `profileName`= ?;";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);

			preparedStatement.setString(1, profileName);

			preparedStatement.execute();
			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return null;

	}

}
