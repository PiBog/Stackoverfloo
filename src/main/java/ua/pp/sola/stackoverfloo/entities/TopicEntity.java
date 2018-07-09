package ua.pp.sola.stackoverfloo.entities;


public class TopicEntity {

    private long topicId;
    private String topicName;
    private long userId;
    private long firstMessageId;
    private int rate;
    private long createDate;
    private long updateDate;

    public TopicEntity() {
    }

    public TopicEntity(String topicName, long userId) {
        this.topicName = topicName;
        this.userId = userId;
        this.firstMessageId=0;
        this.createDate = System.currentTimeMillis();
        this.updateDate = this.createDate;
    }

    public TopicEntity(long topicId, String topicName, long userId, long firstMessageId, int rate, long createDate, long updateDate) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.userId = userId;
        this.firstMessageId=firstMessageId;
        this.rate = rate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public long getTopicId() {
        return topicId;
    }


    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public long getUserId() {
        return userId;
    }

    public long getFirstMessageId() {
        return firstMessageId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getCreateDate() {
        return createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }


}
