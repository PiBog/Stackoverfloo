package ua.pp.sola.stackoverfloo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.sola.stackoverfloo.dao.TopicDAO;
import ua.pp.sola.stackoverfloo.entities.TopicEntity;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicDAO topicDAO;
    public static final long TEST_USER_ID = 1L;


    public List<TopicEntity> getTopicsList(int limit) {
        List<TopicEntity> topicEntityList;
        if (limit < 0) {
            throw new IllegalArgumentException("Quantity of TopicEntity instances can not be less 0!");
        } else if (limit == 0) {
            topicEntityList = topicDAO.getAllTopicsList();
        } else {
            topicEntityList = topicDAO.getLastNTopicsList(limit);
        }
        return topicEntityList;
    }

    public TopicEntity getTopicByTopicId(long topicId) {
        if (topicId < 1) {
            throw new IllegalArgumentException("TopicEntity ID must be more than 0!");
        }
        return topicDAO.getTopicByTopicId(topicId);
    }

    public List<TopicEntity> getTopicsListByUserId(long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("UserEntity ID must be more than 0!");
        }
        return topicDAO.getTopicsListByUserId(userId);
    }

    public TopicEntity createTopic(String topicText) {

        if (topicText.isEmpty()|| topicText=="") {
            throw new IllegalArgumentException("TopicEntity name can not be empty!");
        }
        long topicId = topicDAO.createTopic(new TopicEntity(topicText, TEST_USER_ID));
        return topicDAO.getTopicByTopicId(topicId);
    }

    public int updateTopic (long topicId, String topicText) {
        if (topicText.isEmpty()|| topicText=="") {
            throw new IllegalArgumentException("TopicEntity name can not be empty!");
        }
        TopicEntity topicEntity = topicDAO.getTopicByTopicId(topicId);
        topicEntity.setTopicName(topicText);
        topicEntity.setUpdateDate(System.currentTimeMillis());
        return topicDAO.updateTopic(topicEntity);
    }

    public int rateTopic (long topicId, String action) {
        TopicEntity topicEntity = topicDAO.getTopicByTopicId(topicId);
        if (action.equalsIgnoreCase("increase")) {
            increaseRate(topicEntity);
        } else if (action.equalsIgnoreCase("decrease")) {
            decreaseRate(topicEntity);
        } else {
            throw new IllegalArgumentException("Unknown parameter!");
        }
        return topicDAO.getTopicByTopicId(topicId).getRate();
    }

    private void increaseRate (TopicEntity topicEntity) {

        int rate = topicEntity.getRate();
        topicEntity.setRate(++rate);
        topicDAO.updateTopic(topicEntity);
    }

    private void decreaseRate (TopicEntity topicEntity) {

        int rate = topicEntity.getRate();
        topicEntity.setRate(--rate);
        topicDAO.updateTopic(topicEntity);
    }

}
