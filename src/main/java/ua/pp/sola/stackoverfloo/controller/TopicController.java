package ua.pp.sola.stackoverfloo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.pp.sola.stackoverfloo.entities.MessageEntity;
import ua.pp.sola.stackoverfloo.entities.TopicEntity;
import ua.pp.sola.stackoverfloo.entities.TopicResponseEntity;
import ua.pp.sola.stackoverfloo.service.MessageService;
import ua.pp.sola.stackoverfloo.service.TopicService;
import ua.pp.sola.stackoverfloo.template.QueryTemplates;

import java.util.*;

@RestController
@RequestMapping(value = "/rest/topics")
public class TopicController {

    private static final Logger LOGGER = Logger.getLogger(TopicController.class.getName());
    private static final int LAST_RECENT_TOPICS_NUMBER = 3;

    @Autowired
    TopicService topicService;
    @Autowired
    MessageService messageService;

/**
 * Get all topics
 */
    @RequestMapping( value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<TopicEntity>> getTopicsList() {
        ResponseEntity responseEntity;
        List<TopicEntity> topicEntityList = topicService.getTopicsList(0);
        if (topicEntityList.isEmpty()) {
            LOGGER.info("TopicEntity list is empty");
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(topicEntityList, HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * Get last 3 topics
     */
    @RequestMapping(value = "/recent/", method = RequestMethod.GET)
    public ResponseEntity<List<TopicEntity>> getRecentTopicsList() {
        ResponseEntity responseEntity;
        List<TopicEntity> topicEntityList = topicService.getTopicsList(LAST_RECENT_TOPICS_NUMBER);
        if (topicEntityList.isEmpty()) {
            LOGGER.info("TopicEntity list is empty");
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(topicEntityList, HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * Gets topic by its id
     */
    @RequestMapping(value = "/topic/{id}/", method = RequestMethod.GET)
    public ResponseEntity<TopicResponseEntity> getTopicByTopicId(@PathVariable("id") long id) {

        ResponseEntity<TopicResponseEntity> responseEntity;
        TopicEntity topicEntity = topicService.getTopicByTopicId(id);

        if (topicEntity == null) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {

            List<MessageEntity> messageEntityList = messageService.getMessagesByTopicId(id);
            responseEntity = new ResponseEntity<>(new TopicResponseEntity(topicEntity, messageEntityList), HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * Get last all topics by user Id
     */
    @RequestMapping(value = "/user/{id}/", method = RequestMethod.GET)
    public ResponseEntity<List<TopicEntity>> getTopicsListByUserId(@PathVariable("id") long id) {
        ResponseEntity responseEntity;
        List<TopicEntity> topicEntityList = topicService.getTopicsListByUserId(id);
        if (topicEntityList.isEmpty()) {
            LOGGER.info("TopicEntity list is empty");
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(topicEntityList, HttpStatus.OK);
        }
        return responseEntity;
    }


    /**
     * Creates new topic
     */
    @RequestMapping(value = "/topic/new/", method = RequestMethod.POST)
    public ResponseEntity<TopicResponseEntity> createTopic(@RequestBody String topicText, @RequestBody String message) {

        TopicEntity topicEntity = topicService.createTopic(topicText);
        List<MessageEntity> messageEntityList = new ArrayList<>();
        messageEntityList.add(messageService.createMessage(message, topicEntity.getTopicId()));
        return new ResponseEntity(new TopicResponseEntity(topicEntity,messageEntityList), HttpStatus.CREATED);
    }

    /**
     * Update topic by Entity
     */
    @RequestMapping(value = "/topic/{id}/", method = RequestMethod.PUT)
    public ResponseEntity updateTopic(@PathVariable("id") long id, @RequestBody String newTopicText) {

        topicService.updateTopic(id, newTopicText);
        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * Rate topic by its id
     */
    @RequestMapping(value = "/moderate/{id}/", method = RequestMethod.PUT)
    public Map<String, Object> rateTopic(@PathVariable("id") long id, @RequestBody String action) {

        int rate = topicService.rateTopic(id, action);
        Map<String, Object> responseContainer = new LinkedHashMap<>();
        responseContainer.put("rate", rate);
        return responseContainer;
    }

}




//     /**
//     * Gets topics list with text pattern
//     */
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public ResponseEntity<List<TopicEntity>> getTopicsListByText(String topicText) {
//        ResponseEntity responseEntity;
//        List<TopicEntity> topicEntityList = topicService.getTopicsListByText(topicText);
//        if (topicEntityList.isEmpty()) {
//            logger.info("TopicEntity list is empty");
//            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
//        } else {
//            responseEntity = new ResponseEntity<List<TopicEntity>>(topicEntityList, HttpStatus.OK);
//        }
//        return responseEntity;
//    }