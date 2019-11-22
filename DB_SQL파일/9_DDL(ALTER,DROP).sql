
-- DDL



-- ALTER
-- 객체를 수정하는 구문

/*
    [표현식]
    
    - 테이블 객체 수정 시
    ALTER TABLE 테이블명
    수정할 내용
    
    
    - 수정할 내용
    1) 컬럼 추가 / 수정 / 삭제
    2) 제약 조건 추가 / 삭제
    3) 컬럼 자료형 변경
    4) DEFAULT 값 변경
    5) 테이블명, 컬럼명, 제약조건명 변경
*/




-- 1. 컬럼 추가, 수정, 삭제 

SELECT * FROM DEPT_COPY;


-- 컬럼 추가 (ADD)
ALTER TABLE DEPT_COPY
ADD (CNAME VARCHAR2(20));

SELECT * FROM DEPT_COPY;

-- 컬럼 추가 시 DEFAULT 값 지정
ALTER TABLE DEPT_COPY
ADD (LNAME VARCHAR2(40) DEFAULT '한국');

SELECT * FROM DEPT_COPY;



-- 컬럼 수정 (MODIFY)
ALTER TABLE DEPT_COPY
MODIFY DEPT_ID CHAR(3)
MODIFY DEPT_TITLE VARCHAR2(30)
MODIFY LOCATION_ID VARCHAR2(2)
MODIFY CNAME CHAR(20)
MODIFY LNAME DEFAULT '미국';

SELECT * FROM DEPT_COPY;

-- 주의!
-- 컬럼의 데이터 저장 크기를 수정할 때
-- 기존에 기록된 값보다 작은 크기로는 변경 불가

ALTER TABLE DEPT_COPY
MODIFY DEPT_TITLE VARCHAR2(10);

-- LNAME 컬럼 DEFAULT값 '한국'으로 변경하기
ALTER TABLE DEPT_COPY
MODIFY LNAME DEFAULT '한국';

INSERT INTO DEPT_COPY
VALUES ('D11', '생산부', 'L2', NULL, DEFAULT);

SELECT * FROM DEPT_COPY;




-- 컬럼 삭제 (DROP COLUMN 컬럼명)

CREATE TABLE DEPT_COPY2
AS SELECT * FROM DEPT_COPY;

SELECT COLUMN_NAME, DATA_TYPE, NULLABLE, DATA_DEFAULT,COLUMN_ID, COMMENTS
FROM USER_TAB_COLUMNS -- 유저가 만든 컬럼들 있는 딕셔너리
NATURAL JOIN USER_COL_COMMENTS -- 유저가 만든 컬럼의 코멘트가 있는 딕셔너리
WHERE TABLE_NAME = 'DEPT_COPY2';

-- 이때 DATA_DEFAULT값이 사라짐 // 왜냐면 테이블 복사 시 NOT NULL 말고는 복사 안됨


-- DEPT_ID 컬럼 삭제
ALTER TABLE DEPT_COPY2
DROP COLUMN DEPT_ID;

-- 확인
SELECT * FROM DEPT_COPY2;

SELECT COLUMN_NAME, DATA_TYPE, NULLABLE, DATA_DEFAULT,COLUMN_ID, COMMENTS
FROM USER_TAB_COLUMNS
NATURAL JOIN USER_COL_COMMENTS
WHERE TABLE_NAME = 'DEPT_COPY2';


-- 테이블의 모든 컬럼 삭제하기

ALTER TABLE DEPT_COPY2
DROP COLUMN LNAME;

ALTER TABLE DEPT_COPY2
DROP COLUMN LOCATION_ID;

ALTER TABLE DEPT_COPY2
DROP COLUMN CNAME;

-- 마지막 테이블은 삭제 불가
ALTER TABLE DEPT_COPY2
DROP COLUMN DEPT_TITLE;
-- ORA-12983: cannot drop all columns in a table
--> 테이블에 남아있는 컬럼이 없다면 존재 의미가 없으므로 컬럼이 삭제가 되지 않음.


-- TCL이 작용할 수 있는 구문은 DML만 가능
-- DDL 구문은 버퍼에 저장되지 않고 바로 DB에 반영이 된다
    --> 따라서, DDL 구문에서는 ROLLBACK 불가능
    
ROLLBACK;

SELECT * FROM DEPT_COPY2;


-- 제약조건이 설정되어있는 컬럼 삭제
CREATE TABLE TB1 (
    PK        NUMBER PRIMARY KEY,
    FK        NUMBER REFERENCES TB1,
    COL1      NUMBER, 
    CHECK (PK > 0 AND COL1 > 0) -- 한번에 2컬럼에 대한 제약조건 걸기
);

SELECT * FROM TB1;


-- 테이블에 설정된 제약조건 확인
SELECT * FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'TB1';


-- PK 컬럼 삭제
ALTER TABLE TB1
DROP COLUMN PK;
--> 불가능 : 같은 테이블 내 다른 컬럼이 참조중.. 따라서 삭제시 참조 무결성 위배


-- 종속 제약조건(FK)과 함께 PK 컬럼 삭제
ALTER TABLE TB1
DROP COLUMN PK CASCADE CONSTRAINTS;


-- 이때 4개의 제약조건 전체가 삭제됨... ㄷㄷ
-- 왜냐면, CHECK가 조건 2개를 달고 있는 경우 한개만 삭제되어도 세트로 사라짐
-- 그리고 CASCADE로 해주었기 때문에 FOREIGN KEY도 삭제됨

--------------------------------------------------------------------------------------------------------------------------------

-- 2. 제약조건 추가 삭제

-- DEPT_COPY 테이블의 제약조건 확인
SELECT * FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'DEPT_COPY';


-- 컬럼 제약조건 추가 (ADD CONSTRAINT)
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DCOPY_DID_PK PRIMARY KEY (DEPT_ID)
ADD CONSTRAINT DCOPY_DTITLE_UNIQUE UNIQUE (DEPT_TITLE)
--> SQL 구문은 정상이지만 SQL DEVELPER 오류로 인하여 빨간줄이 보임
MODIFY LNAME CONSTRAINT DCOPY_LNAME_NN NOT NULL;

SELECT * FROM DEPT_COPY;



-- 제약조건 삭제 (DROP CONSTRAINT 제약조건명)
ALTER TABLE DEPT_COPY
DROP CONSTRAINT DCOPY_DID_PK;



-- 제약조건 삭제 확인
SELECT * FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'DEPT_COPY';


-- DEPT_COPY 테이블에 있는 모든 제약조건 삭제
ALTER TABLE DEPT_COPY
DROP CONSTRAINT DCOPY_DTITLE_UNIQUE
DROP CONSTRAINT DCOPY_LNAME_NN
DROP CONSTRAINT SYS_C007170
DROP CONSTRAINT SYS_C007171;

-- 제약조건 삭제 확인
SELECT * FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'DEPT_COPY';

-- NOT NULL 제약조건 삭제방법2
ALTER TABLE DEPT_COPY
MODIFY LNAME CONSTRAINT DCOPY_LNAME_NN NOT NULL;

-- 컬럼에 NULL 값이 저장될 수 있게 수정
ALTER TABLE DEPT_COPY
MODIFY LNAME NULL;

--------------------------------------------------------------------------------------------------------------------------------

-- 3. 컬럼명, 제약조건명, 테이블명 변경

-- 컬럼명 변경 (RENAME COLUMN 컬럼명 TO 변경명)
SELECT * FROM DEPT_COPY;

ALTER TABLE DEPT_COPY
RENAME COLUMN DEPT_TITLE TO DEPT_NAME;

SELECT * FROM DEPT_COPY;


-- 제약조건명 변경 (RENAME CONSTRAINT 제약조건명 TO 변경명)
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DCOPY_DID_PK PRIMARY KEY (DEPT_ID)
ADD CONSTRAINT DCOPY_DTITLE_UNIQUE UNIQUE (DEPT_NAME)
MODIFY LNAME CONSTRAINT DCOPY_LNAME_NN NOT NULL;

-- DCOPY_DTITLE_UNIQUE 제약조건을 
-- DCOPY_DNAME_UNQ로 변경
ALTER TABLE DEPT_COPY
RENAME CONSTRAINT DCOPY_DTITLE_UNIQUE TO DCOPY_DNAME_UNQ;

-- 제약조건명 변경 확인
SELECT * FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'DEPT_COPY';

--------------------------------------------------------------------------------------------------------------------------------


-- 테이블명 변경 (RENAME TO 변경명)

-- DEPT_COPY 테이블을 DEPT_TEST로 이름 변경
ALTER TABLE DEPT_COPY
RENAME TO DEPT_TEST;

SELECT * FROM DEPT_COPY;
--> 테이블명이 변경되어 조회시 없다고 오류 발생

SELECT * FROM DEPT_TEST;


--------------------------------------------------------------------------------------------------------------------------------


-- DROP
-- 데이터베이스 객체*를 삭제하는 구문
-- * 데이터베이스 객체? TABLE, VIEW, SEQUENCE INDEX, SYNONYM, PROCEDURE등이 있음

-- 테이블 삭제 (DROP TABLE)
-- TB1 테이블 삭제
DROP TABLE TB1;

SELECT * FROM TB1;
--> 삭제되어 조회 불가

-- DEPT_TEST 테이블 삭제
DROP TABLE DEPT_TEST;


CREATE TABLE EMP_COPY
AS SELECT * FROM EMPLOYEE;

CREATE TABLE DEPT_COPY
AS SELECT * FROM DEPARTMENT;

ALTER TABLE DEPT_COPY
ADD CONSTRAINT PK PRIMARY KEY (DEPT_ID);
-- 혹은 ADD PRIMARY KEY (DEPT_ID);

ALTER TABLE EMP_COPY
ADD CONSTRAINT FK FOREIGN KEY (DEPT_CODE) REFERENCES DEPT_COPY;
-- 혹은 ADD FOREIGN KEY (DEPT_CODE) REFERENCES DEPT_COPY;
-- 즉 CONSTRAINT치면 CONSTRAINT명을 쳐줘야 함... 약간 변수랑 비슷하기도 하네

SELECT * FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'EMP_COPY';


DROP TABLE DEPT_COPY;
--> EMP_COPY의 FOREIGN KEY 제약조건에 의하여 삭제 불가능

-- EMP_COPY의 FOREIGN KEY 제약조건과 함께 DEPT_COPY 테이블 삭제하기
DROP TABLE DEPT_COPY CASCADE CONSTRAINT;
-- 종속되어있던 FOREIGN KEY의 제약조건도 삭제된다!!











