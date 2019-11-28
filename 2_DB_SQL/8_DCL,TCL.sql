/*
    DCL (DATA CONTROL LANGUAGE) : 데이터 제어 언어
    
    - 데이터베이스, 데이터베이스 객체에 대한 접근 권한을 제어(부여,회수)하는 언어
    
    
    - DCL
    GRANT (권한 부여)
    REVOKE (권한 회수)
    
    - TCL
    COMMIT
    ROLLBACK
    SAVEPOINT
    
*/

-- 권한은 크게 시스템, 객체 권한으로 나뉘어 있음


-- 시스템 권한 부여 (GRANT)
-- 사용자에게 권한을 부여할 때 사용

/*
    [표기법]
    
    GRANT 권한 1, 권한 2, ...
    TO 사용자계정명

    * 시스템 권한 종류
    CREATE SESSION : 데이터베이스 접속 권한
    CREATE TABLE : 테이블 생성
    CREATE VIEW : 뷰 생성 권한
    CREATE SEQUENCE : 시퀀스 생성 권한
    CREATE PROCEDURE : 프로시져(함수) 생성 권한
    CREATE USER : 계정 생성 권한
    DROP USER : 계정 삭제 권한
    DROP ANY TABLE : 임의의 테이블 삭제 권한
*/


/*
    계정의 종류
    - 관리자 계정 (SYS AS SYSDBA, SYSTEM)
    : 데이터베이스의 생성과 관리를 담당하는 계정으로 모든 권한과 책임을 가지는 계정
    
    - 사용자 계정 (EX. KH계정)
    : 데이터베이스에 대하여 질의(SELECT), 갱신(DML), 보고서 작성 등의 작업을 수행할 수 있는 계정으로,
    업무에 필요한 최소한의 권한만을 가지는 것을 원칙으로 함.
    
*/


-- 테스트용 계정 SAMPLE 생성
CREATE USER sample IDENTIFIED BY sample;
-- CMD -> SQLPLUS
    --> SAMPLE 계정 로그인 시 USER SAMPLE LACKS CREATE SESSION PRIVILEGE; LOGON DENIED
    --> 접속 권한이 없어서 로그인 불가능
    
-- KH 계정으로 SAMPLE 계정에 DB 접속 권한 부여
GRANT CREATE SESSION TO SAMPLE;
--> ORA-01031: insufficient privileges
-- KH 계정에는 DB 접속 권한을 부여할 권한이 없어서 오류 발생함

-- 관리자 계정으로 SAMPLE 계정에 DB 접속 권한 부여
GRANT CREATE SESSION TO SAMPLE;
--> 접속 권한 부여 성공

-- SAMPLE 로그인 성공 후 테이블 생성
--> ORA-01950: no privileges on tablespace 'SYSTEM'
--> 테이블 생성 권한이 없어서 에러 발생

-- 관리자 계정으로 테이블 생성 권한을 부여

GRANT CREATE TABLE TO SAMPLE;
--> 다시 SQLPLUS에서 테이블 생성
--> ORA-01950: no privileges on tablespace 'SYSTEM'
--> 또 에러 발생

-- 테이블 스페이스 (TABLE SAPCE)
-- 테이블, 뷰, 인덱스 등 DB 객체들이 저장되는 논리적 공간
-- 테이블 스페이스 할당량 부여
ALTER USER SAMPLE QUOTA 2M ON SYSTEM;
-- 다시 SQLPLUS에서 테이블 생성

-----------------------------------------------------------------------------------------------------------------------------

-- 객체 권한 부여

-- 객체 권한 : 특정 DB 객체를 조작할 수 있는 권한을 부여
/*

    [표기법]
    
    GRANT 권한종류 [(컬럼명) | ALL]
    ON 객체명 | ROLE 이름 | PUBLIC
    TO 사용자계정명

    * 객체 권한 종류
    [권한 종류]            [설정 객체]
    SELECT(DML)           TABLE, VIEW, SEQUENCE
    INSERT(DML)           TABLE, VIEW
    UPDATE(DML)           TABLE, VIEW
    DELETE(DML)           TABLE, VIEW
    ALTER(DDL)            TABLE, SEQUENCE
    REFERENCES            TABLE
    INDEX                 TABLE
    EXECUTE(.EXE 약자)     PROCEDURE
    
*/


-- SQLPLUS에서 SAMPLE 계정으로 KH 계정의 EMPLOYEE 테이블 조회
SELECT * FROM kh.EMPLOYEE; -- 여기 치지 말고 CMD에서 쳐야함
--> ORA-00942: table or view does not exist
-- 접근 권한이 없어서 보이지 않는 상태

-- 관리자 계정으로 SAMPLE 계정에게 KH 계정의 EMPLOYEE 테이블 조회 권한 부여
GRANT SELECT ON kh.EMPLOYEE TO SAMPLE;


-- 권한 회수 (REVOKE)

-- 관리자 계정으로 SAMPLE 계정에게 부여된 kh.EMPLOYEE 테이블 조회 권한을 회수하기
REVOKE SELECT ON KH.EMPLOYEE FROM SAMPLE;
-- 권한이 회수되어 KH.EMPLOYEE 조회 시 에러 발생



-- SAMPLE 계정 DB 접속 권한 회수
REVOKE CREATE SESSION FROM SAMPLE;
-- ORA-01045: user SAMPLE lacks CREATE SESSION privilege; logon denied
-- 접속 불가




/* (중요)

    ROLE : 사용자에게 허가할 수 있는 권한들의 집합.
            ROLE을 이용하면 권한 부여와 회수에 용이함.
    
    
    CONNECT : 사용자가 데이터 베이스에 접속 가능하도록 하기 위한
            CREATE SESSION 권한이 작성되어 있는 ROLE // 이거 하나만 존재.. 좀더 직관적으로 이해하기 위한 것
    
    
    RESOURCE : CREATE 구문을 통한 객체 생성 권한과 
               INSERT, UPDATE, DELETE 구문을 사용할 수 있는 권한을 모아놓은 ROLE

*/

-- 관리자 계정으로 SAMPLE 계정에 CONNECT, RESOURCE ROLE 부여

GRANT CONNECT, RESOURCE TO SAMPLE;

--------------------------------------------------------------------------------------------------------------------------------

-- TCL (TRANSACTION CONTROL LANGUAGE) : 트랜잭션 제어 언어

-- TRANSACTION 이란?
/*

    - 데이터베이스의 논리적 연산 단위
    - 데이터 변경 사항을 묶어 하나의 트랜잭션에 담아 처리함. // 트랜잭션 주머니같은 것
    - 트랜잭션의 대상이 되는 SQL : INSERT, UPDATE, DELETE (DML) // 트랜잭션 연산단위
    
    
    * COMMIT / ROLLBACK
    - COMMIT 또는 ROLLBACK 명령이 입력되기 전 까지는 데이터 변경사항이
      메모리 버퍼에 임시 저장되어 있는 상태로 존재.

    - COMMIT : 메모리 버퍼에 임시 저장된 데이터를 DB에 반영   
    - ROLLBACK : 메모리 버퍼에 임시 저장된 데이터를 삭제하고 마지막 COMMIT 상태로 돌아감
    
    
    * SAVEPOINT
    - 저장 지점을 정의하면 롤백할 때 트랜잭션에 포함된 전체 작업을 롤백하는 것이 아닌
      현 시점에서 지정한 SAVEPOINT까지 트랜잭션 일부만 롤백
      
*/

INSERT INTO MEMBER
VALUES('MEM5', '12345678', '이순신', SYSDATE);

SELECT * FROM MEMBER;
-- 이때 읽는것은 메모리버퍼에서 읽는 것

COMMIT; --> 현재 변경사항 DB 반영

DELETE FROM MEMBER
WHERE MEMBER_ID = 'MEM5';

SELECT * FROM MEMBER;

ROLLBACK; -- 변경사항을 반영하지 않고 최근 COMMIT 상태로 돌아감

SELECT * FROM MEMBER;

DELETE FROM MEMBER
WHERE MEMBER_ID = 'MEM5';

-- MEM5가 삭제된 상태를 SAVEPOINT로 지정
SAVEPOINT SP1;

-- MEM4도 삭제해보자
DELETE FROM MEMBER
WHERE MEMBER_ID = 'MEM4';

SELECT * FROM MEMBER;

-- SP1으로 ROLLBACK
ROLLBACK TO SP1;
-- 그냥 ROLLBACK하면 COMMIT까지로 돌아감
































