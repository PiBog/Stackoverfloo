CREATE SCHEMA STACKOVERFLOO;

CREATE TABLE STACKOVERFLOO.USERS (
	USER_ID BIGSERIAL PRIMARY KEY,
	USER_NAME VARCHAR(20) NOT NULL UNIQUE,
	USER_PASSWORD VARCHAR(128) NOT NULL,
	USER_ROLE VARCHAR(10) NOT NULL,
	USER_ISACTIVE BOOLEAN DEFAULT TRUE
);

CREATE TABLE STACKOVERFLOO.TOPICS (
	TOPIC_ID BIGSERIAL PRIMARY KEY,
	TOPIC_NAME VARCHAR(100) NOT NULL,
	USER_ID BIGINT NOT NULL,
	FST_MSG BIGINT NOT NULL UNIQUE,
	RATE INT DEFAULT 0,
	CREATE_DATE BIGINT NOT NULL,
	UPDATE_DATE BIGINT,
	FOREIGN KEY(USER_ID) REFERENCES STACKOVERFLOO.USERS(USER_ID)
);

CREATE TABLE STACKOVERFLOO.MESSAGES(
	MESSAGE_ID BIGSERIAL PRIMARY KEY,
	CONTENT TEXT NOT NULL,
	TOPIC_ID BIGINT,
	USER_ID BIGINT,
	HIT BOOLEAN DEFAULT FALSE,
	CREATE_DATE BIGINT,
	UPDATE_DATE BIGINT,
	FOREIGN KEY(TOPIC_ID) REFERENCES STACKOVERFLOO.TOPICS(TOPIC_ID),
	FOREIGN KEY(USER_ID) REFERENCES STACKOVERFLOO.USERS(USER_ID)

);

