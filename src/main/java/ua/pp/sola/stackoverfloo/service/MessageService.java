package ua.pp.sola.stackoverfloo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.sola.stackoverfloo.dao.MessageDAO;
import ua.pp.sola.stackoverfloo.entities.MessageEntity;

import java.util.List;


@Service
public class MessageService {

    private static final Logger logger = Logger.getLogger(MessageService.class.getName());

    @Autowired
    private MessageDAO messageDAO;

    public List<MessageEntity> getMessagesByTopicId(long topicId) {
        if (topicId < 1) {
            throw new IllegalArgumentException("Topic ID must be more than 0!");
        }
        return messageDAO.getMessagesByTopicId("topic_id", topicId);
    }

    public List<MessageEntity> getMessagesByUserId(long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("User ID must be more than 0!");
        }
        return messageDAO.getMessagesByTopicId("user_id", userId);
    }

    public MessageEntity getMessageById(long messageId) {
        if (messageId < 1) {
            throw new IllegalArgumentException("MessageEntity ID must be more than 0!");
        }
        return messageDAO.getMessageById(messageId);
    }


    public MessageEntity createMessage(String messageText, long topicId) {

        if (messageText.isEmpty()) {
            throw new IllegalArgumentException("TopicEntity name can not be empty!");
        }
        long userId = 1L;
        long messageId = messageDAO.createMessage(new MessageEntity(messageText, topicId, userId));
        return messageDAO.getMessageById(messageId);
    }

    public int updateMessage(MessageEntity messageEntity) {
        if (messageEntity == null) {
            throw new IllegalArgumentException("TopicEntity can not be null!");
        }
        messageEntity.setUpdateDate(System.currentTimeMillis());
        return messageDAO.updateMessage(messageEntity);
    }

    public int setHit(MessageEntity messageEntity) {
        if (messageEntity == null) {
            throw new IllegalArgumentException("TopicEntity can not be null!");
        }
        messageEntity.setHit(true);
        return messageDAO.updateMessage(messageEntity);
    }

}


