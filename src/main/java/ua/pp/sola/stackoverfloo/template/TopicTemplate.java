package ua.pp.sola.stackoverfloo.template;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.pp.sola.stackoverfloo.dao.TopicDAO;
import ua.pp.sola.stackoverfloo.entities.TopicEntity;
import ua.pp.sola.stackoverfloo.map.TopicMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TopicTemplate implements TopicDAO {

    private static final Logger logger = Logger.getLogger(TopicTemplate.class.getName());


    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<TopicEntity> getTopicsListByUserId(long userId) {
        String query = "SELECT * FROM STACKOVERFLOO.TOPICS WHERE USER_ID = ?";
        return jdbcTemplate.query(query, new Object[]{userId}, new TopicMapper());
    }

    @Override
    public List<TopicEntity> getAllTopicsList() {
        String query = "SELECT * FROM STACKOVERFLOO.TOPICS";
        return jdbcTemplate.query(query, new TopicMapper());
    }

    @Override
    public List<TopicEntity> getLastNTopicsList(int limit) {
        String query = "SELECT * FROM STACKOVERFLOO.TOPICS ORDER BY create_date DESC LIMIT ?";
        return jdbcTemplate.query(query, new Object[]{limit}, new TopicMapper());
    }

    @Override
    public TopicEntity getTopicByTopicId(long topicId) {

        String query = "SELECT * FROM STACKOVERFLOO.TOPICS WHERE topic_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{topicId}, new TopicMapper());
    }

    @Override
    public long createTopic(final TopicEntity topicEntity) {
        final String query = "INSERT INTO STACKOVERFLOO.TOPICS (" +
                "TOPIC_NAME, USER_ID, fst_msg, CREATE_DATE, UPDATE_DATE)" +
                " VALUES(?,?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, topicEntity.getTopicName());
            statement.setLong(2, topicEntity.getUserId());
            statement.setLong(3, topicEntity.getFirstMessageId());
//            statement.setInt(4, topicEntity.getRate());
            statement.setLong(4, topicEntity.getCreateDate());
            statement.setLong(5, topicEntity.getUpdateDate());
            return statement;
        }, holder);

        return holder.getKey().longValue();
    }


    @Override
    public int updateTopic(final TopicEntity topic) {
        final String query = "UPDATE STACKOVERFLOO.TOPICS " +
                "SET TOPIC_NAME = ?,  RATE = ?, UPDATE_DATE = ? WHERE topic_id = ?";

        return jdbcTemplate.update(connection -> {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, topic.getTopicName());
            statement.setInt(2, topic.getRate());
            statement.setLong(3, topic.getUpdateDate());
            statement.setLong(3, topic.getTopicId());
            return statement;
        });
    }


    //    @Override
//    public List<TopicEntity> getTopicsListByText(String topicText) {
//        String query = "SELECT * FROM STACKOVERFLOO.TOPICS WHERE topic_name LIKE  '%" + "?" + "%'"; //  !!!
//        logger.debug("Aply query {}", query);
//
//        List<TopicEntity> topicEntityList = new ArrayList<>();
//
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, new Object[]{topicText});
//        for (Map row : rows) {
//            TopicEntity topicEntity = new TopicEntity(
//                    (Long)row.get("topic_id"),
//                    (String)row.get("topic_name"),
//                    (Long)row.get("user_id"),
//                    (Long)row.get("fst_msg"),
//                    (Integer)row.get("rate"),
//                    (Long)row.get("create_date"),
//                    (Long)row.get("update_date"));
//            topicEntityList.add(topicEntity);
//        }
//
//        return topicEntityList;
//    }

}


