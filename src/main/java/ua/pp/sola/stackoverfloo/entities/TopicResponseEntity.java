package ua.pp.sola.stackoverfloo.entities;

import java.util.List;

public class TopicResponseEntity {

    private TopicEntity topicEntity;
    private List<MessageEntity> messageEntityList;

    public TopicResponseEntity(TopicEntity topicEntity, List<MessageEntity> messageEntityList) {
        this.topicEntity = topicEntity;
        this.messageEntityList = messageEntityList;
    }

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public List<MessageEntity> getMessageEntityList() {
        return messageEntityList;
    }
}
