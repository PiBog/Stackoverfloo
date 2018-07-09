package ua.pp.sola.stackoverfloo.template;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ua.pp.sola.stackoverfloo.entities.TopicEntity;
import ua.pp.sola.stackoverfloo.entities.UserEntity;
import ua.pp.sola.stackoverfloo.map.TopicMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TopicTemplateTest {


    @InjectMocks
    TopicTemplate topicDAO;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    TopicMapper topicMapper;

    private TopicEntity topicEntity;

    private static final Long USER_ID = 1L;

    @Before
    public void initialization() {
        topicEntity = new TopicEntity(1, "topicName", USER_ID, 1, 2, 8709907087087L, 8809907087087L);
    }


    @Test
    public void testGetTopicsListByUserId() {

        List<TopicEntity> expectedList = new ArrayList<>();
        expectedList.add(topicEntity);
        String query = "SELECT * FROM STACKOVERFLOO.TOPICS WHERE USER_ID = ?";

        when(jdbcTemplate.query(eq(query), any(Object[].class), any(TopicMapper.class))).thenReturn(expectedList);

        List<TopicEntity> resultList = topicDAO.getTopicsListByUserId(USER_ID);

        assertEquals(expectedList.size(), resultList.size());
        assertTrue(USER_ID == resultList.get(0).getUserId());

    }

    @Test
    public void testGetAllTopicsList() {
        List<TopicEntity> expList = new ArrayList<>();
        expList.add(topicEntity);
        expList.add(topicEntity);
        String query = "SELECT * FROM STACKOVERFLOO.TOPICS";

        when(jdbcTemplate.query(eq(query), any(TopicMapper.class))).thenReturn(expList);

        List<TopicEntity> resultList = topicDAO.getAllTopicsList();

        assertEquals(expList.size(), resultList.size());
    }

    @Test
    public void testGetLastNTopicsList() {
        List<TopicEntity> expList = new ArrayList<>();
        expList.add(topicEntity);
        expList.add(topicEntity);
        String query = "SELECT * FROM STACKOVERFLOO.TOPICS ORDER BY create_date DESC LIMIT ?";

        when(jdbcTemplate.query(eq(query), any(Object[].class), any(TopicMapper.class))).thenReturn(expList);

        List<TopicEntity> resultList = topicDAO.getLastNTopicsList(5);

        assertEquals(expList.size(), resultList.size());
    }

    @Test
    public void testGetTopicByTopicId() {
        String query = "SELECT * FROM STACKOVERFLOO.TOPICS WHERE topic_id = ?";

        when(jdbcTemplate.queryForObject(eq(query), any(Object[].class), any(TopicMapper.class))).thenReturn(topicEntity);

        TopicEntity resultTopic = topicDAO.getTopicByTopicId(1);

        assertEquals(topicEntity.getTopicId(), resultTopic.getTopicId());
    }


//    @Test
//    public void testCreateTopic() {
//
//        when(jdbcTemplate.update(any(PreparedStatementCreator.class))).thenReturn(1);
//
//        int result = userDAO.createUser(user);
//
//        assertEquals(result, 1);
//    }

    @Test
    public void testUpdateTopic() {


    }


}