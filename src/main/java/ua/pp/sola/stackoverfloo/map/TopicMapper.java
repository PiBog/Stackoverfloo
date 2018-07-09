package ua.pp.sola.stackoverfloo.map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import ua.pp.sola.stackoverfloo.entities.TopicEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicMapper implements RowMapper<TopicEntity> {

    private static final Logger logger = Logger.getLogger(TopicMapper.class.getName());


    @Override
    public TopicEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        logger.info("start mapping object");
        long topicId = resultSet.getLong("TOPIC_ID");
        String topicName = resultSet.getString("TOPIC_NAME");
        long userId = resultSet.getLong("USER_ID");
        long firstMessageId = resultSet.getLong("FST_MSG");
        int rate = resultSet.getInt("RATE");
        long createDate = resultSet.getLong("CREATE_DATE");
        long updDate = resultSet.getLong("UPDATE_DATE");
        logger.info("end mapping object");
        return new TopicEntity(topicId, topicName, userId, firstMessageId, rate, createDate, updDate);
    }


}
