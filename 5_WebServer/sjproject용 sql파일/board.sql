-------------------------------------------------------------- BOARD 관련 -------------------------------------------------------------------------------

DROP TABLE CATEGORY;
DROP TABLE BOARD_TYPE;
DROP TABLE BOARD;
DROP SEQUENCE SEQ_BNO;



----------------------------------------------------
------------------  CATEGORY   --------------   
----------------------------------------------------

CREATE TABLE CATEGORY(
  CATEGORY_CD NUMBER PRIMARY KEY,
  CATEGORY_NM VARCHAR2(30) CHECK(CATEGORY_NM IN('운동', '영화', '음악',  '요리', '게임', '기타'))  
);

INSERT INTO CATEGORY VALUES(10, '운동');
INSERT INTO CATEGORY VALUES(20, '영화');
INSERT INTO CATEGORY VALUES(30, '음악');
INSERT INTO CATEGORY VALUES(40, '요리');
INSERT INTO CATEGORY VALUES(50, '게임');
INSERT INTO CATEGORY VALUES(60, '기타');

COMMIT;

SELECT * FROM CATEGORY;

----------------------------------------------------
-------------  BOARD_TYPE   ---------------   
----------------------------------------------------

CREATE TABLE BOARD_TYPE(
    BOARD_CD NUMBER PRIMARY KEY,
    BOARD_NM VARCHAR2(50) NOT NULL
);

INSERT INTO BOARD_TYPE VALUES(1, '자유게시판');
INSERT INTO BOARD_TYPE VALUES(2, '질문게시판');

COMMIT;

SELECT * FROM BOARD_TYPE;


----------------------------------------------------
--------------------- BOARD -------------------   
----------------------------------------------------
CREATE TABLE BOARD(
    BOARD_NO NUMBER PRIMARY KEY,
    BOARD_TITLE VARCHAR2(300) NOT NULL,
    BOARD_CONTENT CLOB NOT NULL, -- Charator Long Byte = 4GB, varchar2 = 4000BYTE
    BOARD_COUNT NUMBER DEFAULT 0,
    BOARD_CREATE_DT DATE DEFAULT SYSDATE,
    BOARD_MODIFY_DT DATE DEFAULT SYSDATE,
    BOARD_STATUS CHAR(1) DEFAULT 'Y' CHECK(BOARD_STATUS IN ('Y','B','N')),
    BOARD_WRITER NUMBER REFERENCES MEMBER NOT NULL,
    BOARD_CATEGORY NUMBER REFERENCES CATEGORY NOT NULL,
    BOARD_TYPE NUMBER REFERENCES BOARD_TYPE NOT NULL
);

CREATE SEQUENCE SEQ_BNO;



INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '첫 번째 게시글 입니다.', '게시글 TEST1.', 
            DEFAULT, DEFAULT, DEFAULT, DEFAULT, 1, 10, 1);
            
INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '두 번째 게시글 입니다.', '게시글 TEST2.', 
            DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, 20, 1);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '세 번째 게시글 입니다.', '게시글 TEST3.', 
            DEFAULT, DEFAULT, DEFAULT, DEFAULT, 1, 30, 1);            
            
INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '네 번째 게시글 입니다.', '게시글 TEST4.', 
            DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, 40, 1);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '다섯 번째 게시글 입니다.', '게시글 TEST5.', 
            DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, 50, 1);
            
INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '여섯 번째 게시글 입니다.', '게시글 TEST6.', 
            DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, 50, 2);
            
INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '일곱 번째 게시글 입니다.', '게시글 TEST7.', 
            DEFAULT, DEFAULT, DEFAULT, DEFAULT, 2, 50, 1);
            
COMMIT;

SELECT * FROM BOARD;

----------------------------------------------------
------------- ATTACHMENT 관련   --------------   
----------------------------------------------------
-- Board의 부가적인 컬럼
DROP TABLE ATTACHMENT;

CREATE TABLE ATTACHMENT (
  FILE_NO NUMBER PRIMARY KEY,
  BOARD_ID NUMBER REFERENCES BOARD NOT NULL,
  FILE_ORIGIN_NAME VARCHAR2(255) NOT NULL,
  FILE_CHANGE_NAME VARCHAR2(255) NOT NULL,
  FILE_PATH VARCHAR2(1000) NOT NULL, -- 파일은 server에 저장
  FILE_UPLOAD_DATE DATE DEFAULT SYSDATE,
  FILE_LEVEL NUMBER,
  FILE_DOWNLOAD_COUNT NUMBER DEFAULT 0,
  FILE_STATUS CHAR(1) DEFAULT 'Y'
);

CREATE SEQUENCE SEQ_FID;

CREATE OR REPLACE VIEW V_REPLY AS
SELECT REPLY_NO, REPLY_CONTENT, BOARD_ID, MEMBER_ID, REPLY_MODIFY_DT 
FROM  REPLY JOIN BOARD ON(BOARD_ID = BOARD_NO) JOIN MEMBER ON(REPLY_WRITER = MEMBER_NO)
WHERE REPLY_STATUS='Y'
ORDER BY REPLY_NO;

