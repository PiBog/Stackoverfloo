---userEntity's actions

INSERT INTO STACKOVERFLOO.USERS (USER_NAME, USER_PASSWORD, USER_ROLE, USER_ISACTIVE) VALUES('','','',''); --creates new userEntity

UPDATE STACKOVERFLOO.USERS SET USER_PASSWORD  = '' WHERE USER_ID = ''; --updates userEntity password

SELECT * FROM stackoverfloo.topics ORDER BY create_date DESC LIMIT 10; --shows recent 10 controllers

INSERT INTO STACKOVERFLOO.TOPICS (TOPIC_NAME, USER_ID, CREATE_DATE, UPDATE_DATE) VALUES('','','',''); --creates new topicEntity

UPDATE STACKOVERFLOO.TOPICS SET TOPIC_NAME = '' WHERE TOPIC_ID = ''; --edits topicEntity

SELECT * FROM STACKOVERFLOO.messages WHERE topic_id = ''; --selects and shows topicEntity with messages to topicEntity

UPDATE STACKOVERFLOO.MESSAGES SET HIT = TRUE WHERE MESSAGE_ID = ''; --marks best answer

INSERT INTO STACKOVERFLOO.MESSAGES (CONTENT, TOPIC_ID, USER_ID, HIT, CREATE_DATE, UPDATE_DATE) VALUES('','','','','',''); --creats new messageEntity

SELECT * FROM STACKOVERFLOO.TOPICS WHERE USER_ID = ''; --selects controllers list by userEntity id

SELECT * FROM STACKOVERFLOO.TOPICS WHERE topic_name LIKE '%' ORDER by topic_id asc; --searchs text

--moder's possibilities

UPDATE STACKOVERFLOO.TOPICS SET RATE = '' WHERE TOPIC_ID = ''; --changing rate

--admin's possibilities

SELECT * FROM STACKOVERFLOO.USERS WHERE USER_ID = ''; --selects userEntity by userEntity id

SELECT * FROM STACKOVERFLOO.USERS ; --selects a users list

UPDATE STACKOVERFLOO.USERS SET USER_ISACTIVE = ''  WHERE USER_ID = ''; --blocks userEntity

UPDATE STACKOVERFLOO.USERS SET USER_ISACTIVE = ''  WHERE USER_ID = ''; --blocks userEntity



