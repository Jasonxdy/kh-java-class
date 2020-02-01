INSERT INTO BOARD_TYPE VALUES(10, '찾아요');
INSERT INTO BOARD_TYPE VALUES(20, '봤어요');
INSERT INTO BOARD_TYPE VALUES(30, '분양합니다');
INSERT INTO BOARD_TYPE VALUES(40, '만남그후');
INSERT INTO BOARD_TYPE VALUES(50, '자유게시판');

ALTER TABLE BOARD MODIFY BOARD_STATUS DEFAULT 'N';

SELECT COUNT(*) FROM BOARD WHERE BOARD_STATUS = 'N' AND MEM_ID = ?

INSERT INTO BOARD_TYPE VALUES(1, 'FIND');
INSERT INTO BOARD_TYPE VALUES(2, 'SEE');
INSERT INTO BOARD_TYPE VALUES(3, 'ADOPT');
INSERT INTO BOARD_TYPE VALUES(4, 'REVIEW');
INSERT INTO BOARD_TYPE VALUES(5, 'FREE');

commit;
rollback;

INSERT INTO BOARD VALUES (1, '게시글1', '게시글1 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 1);
INSERT INTO BOARD VALUES (2, '게시글2', '게시글2 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 2);
INSERT INTO BOARD VALUES (3, '게시글3', '게시글3 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 3);
INSERT INTO BOARD VALUES (4, '게시글4', '게시글4 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 4);
INSERT INTO BOARD VALUES (5, '게시글5', '게시글5 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 1);
INSERT INTO BOARD VALUES (6, '게시글6', '게시글6 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 5);
INSERT INTO BOARD VALUES (7, '게시글7', '게시글7 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 2);
INSERT INTO BOARD VALUES (8, '게시글8', '게시글8 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 4);
INSERT INTO BOARD VALUES (9, '게시글9', '게시글9 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 4);
INSERT INTO BOARD VALUES (10, '게시글10', '게시글10 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 2);
INSERT INTO BOARD VALUES (11, '게시글11', '게시글11 내용', DEFAULT, DEFAULT,  DEFAULT,  DEFAULT, DEFAULT, 'user01', 2);

SELECT  BOARD_NO, BOARD_CODE, BOARD_TITLE, BOARD_MODIFY_DT FROM BOARD WHERE BOARD_STATUS = 'N' AND MEM_ID = 'user01' ORDER BY BOARD_NO DESC;


SELECT RNUM, BOARD_NO, BOARD_CODE, BOARD_TITLE, BOARD_MODIFY_DT FROM ( SELECT ROWNUM RNUM, BOARD_NO, BOARD_CODE, BOARD_TITLE, BOARD_MODIFY_DT FROM BOARD WHERE BOARD_STATUS = 'N' AND MEM_ID = 'user01' ORDER BY BOARD_NO DESC) WHERE RNUM BETWEEN 2 AND 4;
            

SELECT COUNT(*) FROM BOARD WHERE BOARD_STATUS = 'N' AND MEM_ID = 'user01';

commit;

INSERT INTO M_COMMENT VALUES(1, '게시글 1의 댓글 1', DEFAULT, DEFAULT, DEFAULT, 'user01', 1);
INSERT INTO M_COMMENT VALUES(2, '게시글 1의 댓글 2', DEFAULT, DEFAULT, DEFAULT, 'user01', 1);
INSERT INTO M_COMMENT VALUES(3, '게시글 1의 댓글 3', DEFAULT, DEFAULT, DEFAULT, 'user01', 1);
INSERT INTO M_COMMENT VALUES(4, '게시글 2의 댓글 1', DEFAULT, DEFAULT, DEFAULT, 'user01', 2);
INSERT INTO M_COMMENT VALUES(5, '게시글 2의 댓글 2', DEFAULT, DEFAULT, DEFAULT, 'user01', 2);
INSERT INTO M_COMMENT VALUES(6, '게시글 3의 댓글 1', DEFAULT, DEFAULT, DEFAULT, 'user01', 3);
INSERT INTO M_COMMENT VALUES(7, '게시글 3의 댓글 2', DEFAULT, DEFAULT, DEFAULT, 'user01', 3);
INSERT INTO M_COMMENT VALUES(8, '게시글 3의 댓글 3', DEFAULT, DEFAULT, DEFAULT, 'user01', 3);
INSERT INTO M_COMMENT VALUES(9, '게시글 3의 댓글 4', DEFAULT, DEFAULT, DEFAULT, 'user01', 3);
INSERT INTO M_COMMENT VALUES(10, '게시글 1의 댓글 4', DEFAULT, DEFAULT, DEFAULT, 'user01', 1);
INSERT INTO M_COMMENT VALUES(11, '게시글 2의 댓글 3', DEFAULT, DEFAULT, DEFAULT, 'user01', 2);
INSERT INTO M_COMMENT VALUES(12, '게시글 3의 댓글 5', DEFAULT, DEFAULT, DEFAULT, 'user01', 3);
INSERT INTO M_COMMENT VALUES(13, '게시글 1의 댓글 5', DEFAULT, DEFAULT, DEFAULT, 'user01', 1);
INSERT INTO M_COMMENT VALUES(14, '게시글 1의 댓글 6', DEFAULT, DEFAULT, DEFAULT, 'user01', 1);
INSERT INTO M_COMMENT VALUES(15, '게시글 6의 댓글 6', DEFAULT, DEFAULT, DEFAULT, 'user01', 6);


commit;

-- 게시판 코드, 게시글 제목, 댓글 내용, 댓글 작성일 -> 댓글 작성 순으로
SELECT  RNUM, COMM_NO, BOARD_NO, BOARD_CODE, BOARD_TITLE, COMM_CONTENT, COMM_MODIFY_DT FROM (SELECT ROWNUM RNUM, COMM_NO, BOARD_NO, BOARD_CODE, BOARD_TITLE, COMM_CONTENT, COMM_MODIFY_DT FROM (SELECT COMM_NO, BOARD_NO, BOARD_CODE, BOARD_TITLE, COMM_CONTENT, COMM_MODIFY_DT FROM M_COMMENT JOIN BOARD USING (BOARD_NO) WHERE COMM_STATUS = 'N' AND BOARD_STATUS = 'N' AND M_COMMENT.MEM_ID = ? ORDER BY COMM_NO DESC)) WHERE RNUM BETWEEN ? AND ?;


SELECT COUNT(*) FROM M_COMMENT
JOIN BOARD USING (BOARD_NO)
WHERE COMM_STATUS = 'N' AND MEM_ID = 'user01'
AND BOARD_STATUS = 'N';


SELECT * FROM M_COMMENT
JOIN BOARD USING (BOARD_NO)
WHERE MEM_ID = 'user02';

ALTER TABLE M_COMMENT
RENAME COLUMN MEMBER_ID TO MEM_ID;

SELECT COUNT(*) 
FROM M_COMMENT 
JOIN BOARD USING (BOARD_NO)
WHERE COMM_STATUS = 'N'
AND BOARD_STATUS = 'N'
AND M_COMMENT.MEM_ID = 'user01';


ALTER TABLE ANSWER MODIFY ANSWER_CONTENT NULL;
ALTER TABLE ANSWER MODIFY ANSWER_DATE NULL;


SELECT COUNT(*) FROM ASK JOIN ANSWER ON (ASK_NO = ANSWER_NO) WHERE ASK_STATUS = 'N' AND MEMBER_ID = ?

SELECT COUNT(*) FROM ASK WHERE ASK_STATUS = 'N' AND MEMBER_ID = 'user01';

ALTER TABLE ASK DROP CONSTRAINT ;

ALTER TABLE ASK
MODIFY (ASK_MODIFY_DT DEFAULT SYSDATE);


INSERT INTO ASK VALUES (1, 'user01의 1:1문의 1', 'user01의 1:1문의 1 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');
INSERT INTO ASK VALUES (2, 'user01의 1:1문의 2', 'user01의 1:1문의 2 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');
INSERT INTO ASK VALUES (3, 'user02의 1:1문의 1', 'user02의 1:1문의 1 내용', DEFAULT, DEFAULT, DEFAULT, 'user02');
INSERT INTO ASK VALUES (4, 'user08의 1:1문의 1', 'user08의 1:1문의 1 내용', DEFAULT, DEFAULT, DEFAULT, 'user08');
INSERT INTO ASK VALUES (5, 'user01의 1:1문의 3', 'user01의 1:1문의 3 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');
INSERT INTO ASK VALUES (6, 'user01의 1:1문의 4', 'user01의 1:1문의 4 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');
INSERT INTO ASK VALUES (7, 'user01의 1:1문의 5', 'user01의 1:1문의 5 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');
INSERT INTO ASK VALUES (8, 'user08의 1:1문의 2', 'user08의 1:1문의 2 내용', DEFAULT, DEFAULT, DEFAULT, 'user08');
INSERT INTO ASK VALUES (9, 'user01의 1:1문의 6', 'user01의 1:1문의 6 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');
INSERT INTO ASK VALUES (10, 'user08의 1:1문의 3', 'user08의 1:1문의 3 내용', DEFAULT, DEFAULT, DEFAULT, 'user08');
INSERT INTO ASK VALUES (11, 'user01의 1:1문의 7', 'user01의 1:1문의 7 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');
INSERT INTO ASK VALUES (12, 'user01의 1:1문의 8', 'user01의 1:1문의 8 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');
INSERT INTO ASK VALUES (13, 'user01의 1:1문의 9', 'user01의 1:1문의 9 내용', DEFAULT, DEFAULT, DEFAULT, 'user01');

INSERT INTO ANSWER VALUES(1, '게시글 1에대한 답변', DEFAULT);
INSERT INTO ANSWER VALUES(2, '게시글 2에대한 답변', DEFAULT);
INSERT INTO ANSWER VALUES(3, '게시글 3에대한 답변', DEFAULT);
INSERT INTO ANSWER VALUES(4, '게시글 4에대한 답변', DEFAULT);
INSERT INTO ANSWER VALUES(5, '게시글 5에대한 답변', DEFAULT);
INSERT INTO ANSWER VALUES(6, '게시글 6에대한 답변', DEFAULT);
INSERT INTO ANSWER VALUES(7, '게시글 7에대한 답변', DEFAULT);
INSERT INTO ANSWER VALUES(8, '게시글 8에대한 답변', DEFAULT);
INSERT INTO ANSWER VALUES(9, NULL, DEFAULT);
INSERT INTO ANSWER VALUES(10, NULL, DEFAULT);
INSERT INTO ANSWER VALUES(11, NULL, DEFAULT);
INSERT INTO ANSWER VALUES(12, NULL, DEFAULT);
INSERT INTO ANSWER VALUES(13, NULL, DEFAULT);

SELECT COUNT(*) FROM ASK WHERE ASK_STATUS = 'N' AND MEMBER_ID = 'user01';

SELECT RNUM, ASK_NO, ASK_TITLE, ASK_MODIFY_DT, ANSWER_CONTENT FROM(SELECT ROWNUM RNUM, ASK_NO, ASK_TITLE, ASK_MODIFY_DT, ANSWER_CONTENT FROM (SELECT ASK_NO, ASK_TITLE, ASK_MODIFY_DT, ANSWER_CONTENT FROM ASK JOIN ANSWER ON (ANSWER_NO = ASK_NO) WHERE MEMBER_ID = ? AND ASK_STATUS = 'N' ORDER BY ASK_NO DESC)) WHERE RNUM BETWEEN ? AND ?;

commit;

SELECT MEM_WEB_TELL, MEM_COMMENT_TELL, MEM_ASK_TELL, MEM_REALTIME_TELL FROM MEMBER WHERE MEM_ID = 'user01';




-- VIEW 추가
-- < 환조 >
CREATE OR REPLACE VIEW V_BOARD AS
SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, BOARD_MODIFY_DT, BOARD_COUNT, BOARD_URL, BOARD_STATUS,
        MEM_ID, BOARD.BOARD_CODE, BOARD_TYPE_NAME
FROM BOARD
JOIN BOARD_TYPE ON(BOARD.BOARD_CODE = BOARD_TYPE.BOARD_CODE)
WHERE BOARD_STATUS='N'
ORDER BY BOARD_NO DESC;

CREATE OR REPLACE VIEW V_SEARCH AS
SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, BOARD_MODIFY_DT, BOARD_COUNT, BOARD_URL, BOARD_STATUS,
        BOARD.MEM_ID, BOARD.BOARD_CODE, BOARD_TYPE_NAME, MEM_NAME
FROM BOARD
JOIN BOARD_TYPE ON(BOARD.BOARD_CODE = BOARD_TYPE.BOARD_CODE)
JOIN MEMBER ON(MEMBER.MEM_ID = BOARD.MEM_ID)
WHERE BOARD_STATUS='N'
ORDER BY BOARD_NO DESC;


SELECT * FROM ANIMAL
WHERE ANIMAL_CODE IN(SELECT ANIMAL_CODE
                                    FROM FIND
                                    WHERE BOARD_NO IN
                                        (SELECT BOARD_NO FROM 
                                            (SELECT ROWNUM RNUM, BOARD_NO 
                                            FROM V_BOARD 
                                            WHERE BOARD_CODE=1) 
                                       WHERE RNUM BETWEEN 1 AND 5))
AND ANIMAL_STATUS='N';


DROP TABLE BREED CASCADE CONSTRAINT;


CREATE SEQUENCE SEQ_BNO;

CREATE SEQUENCE SEQ_ANO;

CREATE SEQUENCE SEQ_FID;

INSERT ALL
INTO ASK VALUES (SEQ_ASKNO.NEXTVAL, '문의글3', '문의글 3에 대한 내용입니다', DEFAULT, DEFAULT, DEFAULT, 'user01')
INTO ANSWER VALUES (SEQ_ASKNO.NEXTVAL, '문의글 3에 대한 답변', '문의글 3에 대한 답변 내용', DEFAULT)
VALUES (SEQ_ASKNO.NEXTVAL, '문의글3', '문의글 3에 대한 내용입니다', SYSDATE, SYSDATE, 'N', 'user01', SEQ_ASKNO.CURRVAL, NULL, NULL)
SELECT ASK_NO, ASK_TITLE
FROM ASK;

SELECT SEQ_ASKNO.NEXTVAL FROM DUAL;
SELECT SEQ_ASKNO.CURRVAL FROM DUAL;




SELECT * FROM ASK
                    JOIN ANSWER ON (ASK_NO = ANSWER_NO)
;


CREATE SEQUENCE SEQ_TEST;
DROP SEQUENCE SEQ_TEST;
SELECT SEQ_TEST.NEXTVAL, SEQ_TEST.NEXTVAL FROM DUAL;





drop table breed cascade constraints;

CREATE TABLE ANIMAL(
    ANIMAL_CODE NUMBER PRIMARY KEY,
    ANIMAL_GENDER VARCHAR2(1) CHECK(ANIMAL_GENDER IN ('M', 'F', 'N')) NOT NULL,
    ANIMAL_TYPE VARCHAR2(20) NOT NULL,
    ANIMAL_STATUS VARCHAR2(1) DEFAULT 'N' CHECK(ANIMAL_STATUS IN ('Y','N')) NOT NULL,
    ANIMAL_BREED VARCHAR2(30) NOT NULL
);



INSERT INTO ANIMAL VALUES(24, 'M', '개', DEFAULT, '골든리트리버');
commit




