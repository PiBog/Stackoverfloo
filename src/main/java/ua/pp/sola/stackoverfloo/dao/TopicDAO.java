package ua.pp.sola.stackoverfloo.dao;

import ua.pp.sola.stackoverfloo.entities.TopicEntity;

import java.util.List;

public interface TopicDAO {

    long createTopic(TopicEntity topicEntity);

    int updateTopic(TopicEntity topicEntity); //!!!

    TopicEntity getTopicByTopicId (long topicId); // !!!

//    List<TopicEntity> getTopicsListByText (String topicText);

    List<TopicEntity>  getTopicsListByUserId (long userId); // !!!

    List<TopicEntity> getAllTopicsList();

    List<TopicEntity> getLastNTopicsList(int limit);
}
