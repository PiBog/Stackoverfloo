package ua.pp.sola.stackoverfloo.entities;

public class MessageEntity {

    private long messageId;
    private String content;
    private long topicId;
    private long userId;
    private boolean hit;
    private long createDate;
    private long updateDate;

    public MessageEntity() {
    }

    public MessageEntity(String content, long topicId, long userId) {
        this.content = content;
        this.topicId = topicId;
        this.userId = userId;
        this.createDate = System.currentTimeMillis();
        this.updateDate = this.createDate;
    }

    public long getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public long getTopicId() {
        return topicId;
    }

    public long getUserId() {
        return userId;
    }

    public boolean isHit() {
        return hit;
    }

    public long getCreateDate() {
        return createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    //I'll use a pattern builder here because there are many parameters
    public static class Builder {

        private long messageId;
        private String content;
        private long topicId;
        private long userId;
        private boolean hit;
        private long createDate;
        private long updateDate;


        public Builder messageId (long messageId){
            this.messageId = messageId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder topicId(long topicId){
            this.topicId = topicId;
            return this;
        }

        public Builder userId (long userId){
            this.userId = userId;
            return this;
        }

        public Builder hit (boolean hit){
            this.hit = hit;
            return this;
        }

        public Builder createDate (long createDate){
            this.createDate = createDate;
            return this;
        }

        public Builder updateDate (long updateDate){
            this.updateDate = updateDate;
            return this;
        }

        public MessageEntity build(){
            return new MessageEntity(this);
        }
    }

    private MessageEntity(Builder builder){
        this.messageId = builder.messageId;
        this.content = builder.content;
        this.topicId = builder.topicId;
        this.userId = builder.userId;
        this.hit = builder.hit;
        this.createDate = builder.createDate;
        this.updateDate = builder.updateDate;
    }


}
