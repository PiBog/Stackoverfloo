package ua.pp.sola.stackoverfloo.map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import ua.pp.sola.stackoverfloo.entities.MessageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper {

    private static final Logger logger = Logger.getLogger(MessageMapper.class.getName());


    @Override
    public MessageEntity mapRow(ResultSet resultSet, int i) throws SQLException {

        int messageId = resultSet.getInt("MESSAGE_ID");
        String content = resultSet.getString("CONTENT");
        int topicId = resultSet.getInt("TOPIC_ID");
        int userId = resultSet.getInt("USER_ID");
        boolean hit = resultSet.getBoolean("HIT");
        long createDate = resultSet.getLong("CREATE_DATE");
        long updDate = resultSet.getLong("UPDATE_DATE");

        return new MessageEntity.Builder()
                .messageId(messageId)
                .content(content)
                .topicId(topicId)
                .userId(userId)
                .hit(hit)
                .createDate(createDate)
                .updateDate(updDate)
                .build();
    }
}
