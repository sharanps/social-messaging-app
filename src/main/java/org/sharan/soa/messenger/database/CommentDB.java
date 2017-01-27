package org.sharan.soa.messenger.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sharan.soa.messenger.model.Comment;

public class CommentDB {

	private Connection connect = null;
	private Statement statement = null;

	public List<Comment> getAllComments() throws Exception {
		List<Comment> commentList = new ArrayList<>();

		try {
			// This will load the MySQL driver, each DB has its own driver
			// System.out.println("Here");
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = DriverManager.getConnection(url, username, loginPassword);
			// create table
			statement = connect.createStatement();
			String sql = "SELECT `comments`.`commentsID` ,`comments`.`comments`,`comments`.`author` FROM `itm566`.`comments`";
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				// System.out.println("I was here");
				commentList.add(new Comment(resultSet.getInt(1), resultSet
						.getString(2), resultSet.getString(3)));
			}
			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return commentList;
	}

	public void updateComment(Long messageID, Comment comment) {
		// TODO Auto-generated method stub

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = DriverManager.getConnection(url, username, loginPassword);
			// create table
			statement = connect.createStatement();

			String sql = "UPDATE `itm566`.`comments` SET comments= ? WHERE commentsID= ?;";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			// preparedStatement.setLong(1, profile.getId());
			preparedStatement.setString(1, comment.getMessage());
			// preparedStatement.setString(2, comment.getAuthor());
			preparedStatement.setLong(2, comment.getId());
			preparedStatement.execute();

			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}

	}

	public Comment getComments(Long messageID) throws Exception {
		Comment comment = null;

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = DriverManager.getConnection(url, username, loginPassword);
			// create table
			statement = connect.createStatement();
			String sql = "SELECT `comments`.`commentsID` ,`comments`.`comments`,`comments`.`author` FROM `itm566`.`comments` WHERE `comments`.`messageID` = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setLong(1, messageID);
			preparedStatement.executeQuery();
			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {
				comment = new Comment(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3));
			}
			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return comment;
	}

	public Comment addComment(Long messageID, Comment comment) {
		// TODO Auto-generated method stub
		Comment newComment = null;

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = DriverManager.getConnection(url, username, loginPassword);
			// create table
			statement = connect.createStatement();

			String sql = "INSERT INTO `itm566`.`comments` (`comments`,`messageID`,`author`) VALUES (?,?,?) ;";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);

			preparedStatement.setString(1, comment.getMessage());
			preparedStatement.setLong(2, comment.getId());
			preparedStatement.setString(3, comment.getAuthor());

			preparedStatement.execute();

			String sql1 = "SELECT `comments`.`commentsID` ,`comments`.`comments` ,`comments`.`author` FROM `itm566`.`comments` WHERE `comments`.`messageID` = ?";

			PreparedStatement preparedStatement1 = connect
					.prepareStatement(sql1);
			preparedStatement1.setLong(1, comment.getId());
			preparedStatement1.execute();
			ResultSet resultSet = preparedStatement1.getResultSet();
			while (resultSet.next()) {
				newComment = new Comment(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3));
			}

			// end create table
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return newComment;

	}

	public Comment deleteComment(Long messageID, Long commentsID) {
		// TODO Auto-generated method stub
		Comment comment = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/itm566";
			String username = "root";
			String loginPassword = "root";
			connect = DriverManager.getConnection(url, username, loginPassword);
			// create table
			statement = connect.createStatement();

			String sql = "DELETE FROM `itm566`.`comments` WHERE `messageID`= ? AND `commentsID`=?";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);

			preparedStatement.setLong(1, messageID);
			preparedStatement.setLong(2, commentsID);
			preparedStatement.execute();
			// end create table

			String sql1 = "SELECT `comments`.`commentsID` ,`comments`.`comments` ,`comments`.`author` FROM `itm566`.`comments` WHERE `comments`.`messageID` = ?";

			PreparedStatement preparedStatement1 = connect
					.prepareStatement(sql1);
			preparedStatement1.setLong(1, commentsID);
			preparedStatement1.execute();
			ResultSet resultSet = preparedStatement1.getResultSet();
			while (resultSet.next()) {
				comment = new Comment(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3));
			}
		} catch (Exception e) {
			System.out.println("There was an error");
			e.printStackTrace();
		}
		return comment;

	}

}
