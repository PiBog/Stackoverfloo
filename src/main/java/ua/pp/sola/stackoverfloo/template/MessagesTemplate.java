package ua.pp.sola.stackoverfloo.template;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.pp.sola.stackoverfloo.dao.MessageDAO;
import ua.pp.sola.stackoverfloo.entities.MessageEntity;
import ua.pp.sola.stackoverfloo.map.MessageMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class MessagesTemplate implements MessageDAO {

    private static final Logger logger = Logger.getLogger(MessagesTemplate.class.getName());


    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<MessageEntity> getMessagesByTopicId(String columnName, long id) {
        String query = "SELECT * FROM STACKOVERFLOO.MESSAGES WHERE topic_id = ?";
        return jdbcTemplate.query(query, new Object[] {id}, new MessageMapper());
    }

    @Override
    public MessageEntity getMessageById(long messageId) {
        String query = "SELECT * FROM STACKOVERFLOO.MESSAGES WHERE message_id = ?";
        return (MessageEntity) jdbcTemplate.queryForObject(query, new Object[]{messageId}, new MessageMapper());  //!!!
    }

    @Override
    public long createMessage(final MessageEntity messageEntity) {
        final String query = "INSERT INTO STACKOVERFLOO.MESSAGES " +
                "(CONTENT, TOPIC_ID, USER_ID, HIT, CREATE_DATE, UPDATE_DATE) VALUES(?,?,?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, messageEntity.getContent());
            statement.setLong(2, messageEntity.getTopicId());
            statement.setLong(3, messageEntity.getUserId());
            statement.setBoolean(4, messageEntity.isHit());
            statement.setLong(5, messageEntity.getCreateDate());
            statement.setLong(6, messageEntity.getUpdateDate());
            return statement;
        }, holder);
        return holder.getKey().longValue();
    }

    @Override
    public int updateMessage(final MessageEntity messageEntity) {
        final String query = "UPDATE STACKOVERFLOO.MESSAGES " +
                "SET CONTENT = ?, HIT = ?, UPDATE_DATE = ? WHERE message_id = ?";

        return jdbcTemplate.update(connection -> {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, messageEntity.getContent());
            statement.setBoolean(2, messageEntity.isHit());
            statement.setLong(3, messageEntity.getUpdateDate());
            statement.setLong(4, messageEntity.getMessageId());
            return statement;
        });
    }
}
