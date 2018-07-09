package ua.pp.sola.stackoverfloo.dao;

import ua.pp.sola.stackoverfloo.entities.MessageEntity;

import java.util.List;

public interface MessageDAO {

    List<MessageEntity> getMessagesByTopicId(String columnName, long id);

    MessageEntity getMessageById (long messageId);

    long createMessage (MessageEntity messageEntity);

    int updateMessage (MessageEntity messageEntity);
}
