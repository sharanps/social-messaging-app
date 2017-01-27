package org.sharan.soa.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.sharan.soa.messenger.database.MessageDB;
import org.sharan.soa.messenger.model.Message;

public class MessageService {

	public MessageService() {

	}

	public List<Message> getAllMessages() {
		List<Message> messageList = new ArrayList<>();
		MessageDB db = new MessageDB();
		try {
			messageList = db.getAllMessages();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageList;
	}

	public Message getMessage(Long id) {
		Message message = null;
		MessageDB db = new MessageDB();
		try {
			message = db.getMessages(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public Message addMessage(Message m) {
		List<Message> messageList = new ArrayList<>();
		MessageDB db = new MessageDB();
		try {
			messageList = db.getAllMessages();
			m.setId(messageList.size() + 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db.addMessage(m);
	}

	public Message updateMessage(Message m) {
		if (m.getId() <= 0) {
			return null;
		}
		MessageDB db = new MessageDB();
		db.updateMessage(m);
		return m;
	}

	public Message removeMessage(Long id) {
		MessageDB db = new MessageDB();
		return db.deleteMessage(id);
	}
}
