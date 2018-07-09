
INSERT INTO STACKOVERFLOO.USERS (
  USER_NAME, USER_PASSWORD, USER_ROLE, USER_ISACTIVE)
VALUES
  ('admin','$2a$10$en/5Kd.Trd.JrtrJmkdVJOW7PmhbXxiEDuXSGZWSZCyqf8NYiHzHW','ADMIN',TRUE ),
  ('moder','$2a$10$en/5Kd.Trd.JrtrJmkdVJOW7PmhbXxiEDuXSGZWSZCyqf8NYiHzHW','MODERATOR',TRUE ),
  ('user','$2a$10$en/5Kd.Trd.JrtrJmkdVJOW7PmhbXxiEDuXSGZWSZCyqf8NYiHzHW','USER',TRUE ),
  ('alex','$2a$10$en/5Kd.Trd.JrtrJmkdVJOW7PmhbXxiEDuXSGZWSZCyqf8NYiHzHW','USER',TRUE ),
  ('olly','$2a$10$en/5Kd.Trd.JrtrJmkdVJOW7PmhbXxiEDuXSGZWSZCyqf8NYiHzHW','MODERATOR',FALSE ),
  ('petra','$2a$10$en/5Kd.Trd.JrtrJmkdVJOW7PmhbXxiEDuXSGZWSZCyqf8NYiHzHW','USER',FALSE );

INSERT INTO STACKOVERFLOO.TOPICS (
  TOPIC_NAME, USER_ID, FST_MSG, RATE, CREATE_DATE, UPDATE_DATE)
VALUES
  ('How  can I  do   it?',1,1,0,186576789,1865767892),
  ('How  can I  do this?',2,2,-1,861576789,8615767892),
  ('How  can I  do that?',3,3,2,865276789,8635767892),
  ('How can You do that?',4,4,-3,865278789,863576783492),
  ('How  can I   did it?',5,5,4,865736789,8675767892);

INSERT INTO STACKOVERFLOO.MESSAGES (
  content, topic_id, user_id, hit, create_date, update_date)
VALUES
  ('messageEntity 1',1,1,FALSE ,69869870, 69869870),
  ('messageEntity 2',2,2,FALSE ,169869870, 169869870),
  ('messageEntity 3',3,3,FALSE ,269869870, 269869870),
  ('messageEntity 4',4,4,FALSE ,369869870, 369869870),
  ('messageEntity 5',5,5,FALSE ,469869870, 469869870),
  ('messageEntity 6',1,6,TRUE ,569869870, 669869870),
  ('messageEntity 7',2,1,FALSE ,669869870, 769869870),
  ('messageEntity 5',2,2,FALSE ,869869870, 969869870),
  ('messageEntity 9',2,3,FALSE ,1069869870, 1169869870),
  ('messageEntity 1',3,5,FALSE ,1269869870, 1369869870);

