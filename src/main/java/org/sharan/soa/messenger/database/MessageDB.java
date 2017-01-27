package org.sharan.soa.messenger.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sharan.soa.messenger.model.Message;

public class MessageDB {

	private Connection connect = null;
	private Statement statement = null;

	public List<Message> getAllMessages() throws Exception {
		List<Message> messageList = new ArrayList<>();

		try {
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();
			String sql = "SELECT `messages`.`messageID` ,`messages`.`message`,`messages`.`author` FROM `A20363568_Itmd566`.`messages`";
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				//System.out.println("I was here");
				messageList.add(new Message(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
			}
			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return messageList;
	}

	public void updateMessage(Message message) {
		// TODO Auto-generated method stub

		try {
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();

			String sql = "UPDATE messages SET message= ?, author= ? WHERE messageID= ?;";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			// preparedStatement.setLong(1, profile.getId());
			preparedStatement.setString(1, message.getMessage());
			preparedStatement.setString(2, message.getAuthor());
			preparedStatement.setLong(3, message.getId());
			preparedStatement.execute();

			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}

	}

	public Message getMessages(Long messageID) throws Exception {
		Message message = null;

		try {
			// This will load the MySQL driver, each DB has its own driver
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();
			String sql = "SELECT `messages`.`messageID` ,`messages`.`message`,`messages`.`author` FROM `A20363568_Itmd566`.`messages` WHERE `messages`.`messageID` = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setLong(1, messageID);
			preparedStatement.executeQuery();
			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {
				message = new Message(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
			}
			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return message;
	}

	public Message addMessage(Message message) {
		// TODO Auto-generated method stub
		Message newMessage = null;

		try {
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();

			String sql = "INSERT INTO `A20363568_Itmd566`.`messages` (`message`, `author`) VALUES (?, ?);";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);

			preparedStatement.setString(1, message.getMessage());
			preparedStatement.setString(2, message.getAuthor());

			preparedStatement.execute();

			String sql1 = "SELECT `messages`.`messageID` ,`messages`.`message`,`messages`.`author` FROM `A20363568_Itmd566`.`messages` WHERE `messages`.`messageID` = ?";

			PreparedStatement preparedStatement1 = connect.prepareStatement(sql1);
			preparedStatement1.setLong(1, message.getId());
			preparedStatement1.execute();
			ResultSet resultSet = preparedStatement1.getResultSet();
			while (resultSet.next()) {
				newMessage = new Message(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
			}

			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return newMessage;

	}

	public Message deleteMessage(Long messageID) {
		// TODO Auto-generated method stub
		Message message=null;
		try {
			connect = ConnectionClass.getConnection();
			// create table
			statement = connect.createStatement();

			String sql = "DELETE FROM `A20363568_Itmd566`.`messages` WHERE `messageID`= ?;";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);

			preparedStatement.setLong(1, messageID);

			preparedStatement.execute();
			
			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return message;

	}

}
