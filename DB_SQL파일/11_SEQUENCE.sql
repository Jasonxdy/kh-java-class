

-- SEQUENCE
-- 순차적으로 정수 값을 자동으로 생성하는 객체
-- 자동 번호 발생기 역할을 함
    --> 보통 PRIMARY KEY를 생성하는 역할에 사용됨. (마지막 추가된것을 기억하고 사용할 수는 없으니까..)

/*  [표현식]
    CREATE SEQUENCE 시퀀스명
① [START WITH 숫자]               ->       처음 발생시킬 시작 값, 기본값 1
② [INCREMENT BY 숫자]             ->       다음 값에 대한 증가치, 기본값 1
③ [MAXVALUE 숫자 | NOMAXVALUE]    ->       발생시킬 최대값, 10의 27승-1까지 가능
④ [MINVALUE 숫자 | NOMINVALUE]    ->       발생시킬 최소값, -10의 26승
⑤ [CYCLE | NOCYCLE]               ->      시퀀스가 최대값까지 증가 완료 시
                                            CYCLE은 START WITH 설정 값으로 돌아감
                                            NOCYCLE은 에러 발생
⑥ [CACHE | NOCACHE]              ->       CACHE는 메모리 상에서 시퀀스 값 관리
                                            기본값 20
*/


CREATE SEQUENCE SEQ_EMPID
START WITH 300
INCREMENT BY 5
MAXVALUE 310
NOCYCLE
NOCACHE;

-- 사용자가 생성한 시퀀스 확인
SELECT * FROM USER_SEQUENCES;
-- 여기서 LAST_NUMBER : 마지막으로 SEQUENCE가 불러진 값


--------------------------------------------------------------------------------------------------------------------------------


--  SEQUENCE 사용
/*
    시퀀스명.CURRVAL // CURRENT VALUE : 현재 생성된 시퀀스의 값 (=LAST_VALUE)
    
    시퀀스명.NEXTVAL : 시퀀스를 증가시킨 값
            -기존 시퀀스 값에서 INCREMENT BY에 지정한 크기만큼 증가한 값
            
*/

-- ** 시퀀스 사용시 NEXTVAL을 수행한 후에만 CURRVAL 호출이 가능함
/*
    CURRVAL는 마지막으로 수행한 NEXTVAL의 값을 저장하여 보여주는 임시 값
    // 즉 NEXTVAL을 수행해야지만 CURRVAL이 생기는 것
    
    시퀀스 생성 이후 한번도 NEXTVAL을 호출하지 않으면 오류가 발생함.
*/

-- NEXTVAL 수행하지 않고 CURRVAL 호출하기
SELECT SEQ_EMPID.CURRVAL FROM DUAL; --> 에러 발생 (아직 시작도 안했는데 CURRVAL이 어딨냐면서 몰라함)

-- NEXTVAL 수행 후 CURRVAL 호출하기
SELECT SEQ_EMPID.NEXTVAL FROM DUAL;
--> NEXTVAL가 시퀀스 생성 후 처음으로 수행된 경우 START WITH 값을 반환한다

SELECT SEQ_EMPID.CURRVAL FROM DUAL;
SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 305
SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 310
SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 315? --> 에러 발생
--> 지정한 MAXVALUE 값을 초과할 수 없음

SELECT SEQ_EMPID.CURRVAL FROM DUAL; -- 310
--> CURRVAL는 마지막으로 수행이 성공한 NEXTVAL 값을 반환



-- 사번을 지정할 새로운 시퀀스를 생성하여 새로운 사원 추가
CREATE SEQUENCE SEQ_EID
START WITH 300
--INCREMENT BY 1 : 생략 시 1씩 증가가 기본값
MAXVALUE 10000
NOCYCLE
NOCACHE;

INSERT INTO EMPLOYEE
VALUES (SEQ_EID.NEXTVAL, '홍길동', '000101-1234567', 'gd_hong@kh.or.kr', '01043214321','D2', 'J7', 'S5',
2500000,0.1, 200, SYSDATE, NULL, DEFAULT);

SELECT * FROM EMPLOYEE; -- 300번에 홍길동이 추가됨

INSERT INTO EMPLOYEE
VALUES (SEQ_EID.NEXTVAL, '홍길순', '000101-2234567', 'gs_hong@kh.or.kr', '01043214321','D2', 'J7', 'S5',
2500000,0.1, 200, SYSDATE, NULL, DEFAULT);

SELECT * FROM EMPLOYEE; -- 301번에 홍길순이 추가됨



-- 시퀀스 삭제 (그냥 삭제하면 됨)
DROP SEQUENCE SEQ_EMPID;


-- 시퀀스 수정하기
-- START WITH는 변경 불가능!

ALTER SEQUENCE SEQ_EID
-- START WITH 400
-- ORA-02283: cannot alter starting sequence number
INCREMENT BY 2
MAXVALUE 5000
NOCYCLE
CACHE 20;


-- * CACHE 개수
-- 미리 다음 작업의 결과를 만들어 두는 메모리
--> 결과가 완성되어 있기 때문에 호출 시 반환만 하면 되므로 속도가 굉장히 빠름
--> 대신, 미리 일정 공간을 차지하고 있으므로 적당한 크기를 가지는 것이 중요하다.

-- * SEQUENCE에서 CACHE
-- 지정된 숫자 만큼 다음에 나올 NEXTVAL의 결과들을 미리 생성해둠

SELECT * FROM USER_SEQUENCES;


-----------------------------------------------------------------------------------------------------------------------------

-- 실습 문제 : USER_TEST 테이블 만들기


CREATE TABLE USER_TEST (
    USER_NO             NUMBER PRIMARY KEY,
    USER_ID             VARCHAR2(20) UNIQUE,
    USER_PWD            VARCHAR2(20) NOT NULL,
    PNO                 VARCHAR2(20) NOT NULL UNIQUE,
    GENDER              VARCHAR2(3),
    PHONE               VARCHAR2(20),
    ADDRESS             VARCHAR2(100),
    STATUS              VARCHAR2(3) DEFAULT 'N' NOT NULL,
    CHECK (GENDER IN ('남','여') AND STATUS IN ('Y','N'))
);

COMMENT ON COLUMN USER_TEST.USER_NO IS  '회원번호';
COMMENT ON COLUMN USER_TEST.USER_ID IS  '회원아이디';
COMMENT ON COLUMN USER_TEST.USER_PWD IS  '비밀번호';
COMMENT ON COLUMN USER_TEST.PNO IS  '주민등록번호';
COMMENT ON COLUMN USER_TEST.GENDER IS  '성별';
COMMENT ON COLUMN USER_TEST.PHONE IS  '연락처';
COMMENT ON COLUMN USER_TEST.ADDRESS IS  '주소';
COMMENT ON COLUMN USER_TEST.STATUS IS  '탈퇴여부';

DROP TABLE USER_TEST;


