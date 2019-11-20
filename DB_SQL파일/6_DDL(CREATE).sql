/*
    데이터 딕셔너리란?
    - 자원을 효율적으로 관리하기 위한 다양한 정보를 저장하는 시스템 테이블
    - 데이터 딕셔너리는 사용자가 테이블을 생성하거나 사용자를 변경하는 등의
    작업을 진행할 때 데이터베이스 서버에 의해 자동으로 갱신되는 테이블
    
    - USER_TABLES : 사용자 계정이 소유한 테이블의 정보를 조회할 수 있는 딕셔너리 뷰
    - USER_TAB_COLUMNS : 사용자가 작성한 테이블의 컬럼과 관련된 정보 확인용 VIEW
    - USER_CONSTRAINTS : 사용자가 작성한 제약조건을 확인하는 VIEW
    - USER_CONS_COLUMNS : 제약 조건이 걸려있는 컬럼 확인용 VIEW
    
    
    // ORACLE DBMS가 자동으로 만들어주며 자원의 관리를 위한 기능으로 보면 될듯
*/



/*

    DDL (DATA DEFINITION LANGUAGE) : 데이터 정의 언어
    
    - 객체 (OBJECT)를 만들고 (CREATE), 수정하고 (ALTER), 삭제 (DROP)하는 등의 
    데이터의 전체적인 구조를 정의하는 언어로 주로 DB관리자, 설계자가 사용하는 언어
    
    * 오라클에서 객체(OBJECT) 종류
      테이블 (TABLE), 뷰 (VIEW), 시퀀스 (SEQUENCE), 인덱스 (INDEX), 패키지(PACKAGE),
      트리거 (TRIGGER), 프로시저 (PROCEDURE), 함수 (FUNCTION), 동의어 (SYNONYM), 사용자 (USER)
      
*/
    ---------------------------------------------------------------------------------------
 
 /*
    <CREATE>
    - 테이블이나 인덱스, 뷰 등 다양한 DB 객체를 생성하는 구문
    
    1. 테이블 만들기
    - 테이블이란?
        행(ROW)과 열(COLUMN)으로 구성되는 가장 기본적인 데이터베이스 객체
    
    데이터베이스 내에서 모든 데이터는 테이블을 통해 저장됨.
    
    
    [표현식]
    CREATE TABLE 테이블명(
        컬럼명  자료형(크기)  [제약조건]  [기본값],
        컬럼명  자료형(크기)  [제약조건]  [기본값],
        ... //원하는만큼
        
        [제약조건]
    );

 */
 
 CREATE TABLE MEMBER (
 MEMBER_ID          VARCHAR2(20),
 MEMBER_PWD         VARCHAR2(20),
 MEMBER_NAME       VARCHAR2(30),
 ENROLL_DATE        DATE DEFAULT SYSDATE --> 기본값
 );
 
 
 -- 테이블 생성 확인
 SELECT * FROM MEMBER;
 
 SELECT * FROM USER_TABLES;
 -- USER_TABLES : 사용자가 작성한 테이블을 확인할 수 있는 가상의 테이블 (== VIEW)
 -- 데이터 딕셔너리에 정의되어 있음
 
 
 -- 컬럼 코멘트(주석) 추가
 COMMENT ON COLUMN MEMBER.MEMBER_ID IS '회원아이디';
 COMMENT ON COLUMN MEMBER.MEMBER_PWD IS '회원비밀번호';
 COMMENT ON COLUMN MEMBER.MEMBER_NAME IS '회원이름';
 COMMENT ON COLUMN MEMBER.ENROLL_DATE IS '회원가입일';
 
 
 -- 생성된 테이블 컬럼 정보 확인
 SELECT * FROM USER_TAB_COLUMNS
 WHERE TABLE_NAME = 'MEMBER';
 -- USER_TAB_COLUMNS : 사용자가 작성한 테이블의 컬럼과 관련된 정보 확인용 VIEW

 DESC MEMBER;
-- DESC(DESCRIBE, 묘사의 약자) : 테이블의 구조를 간단히 표시하는 구문
--  ㄴ 오라클에서만 사용할 수 있다

 
 
 -- 샘플 데이터 삽입
 INSERT INTO MEMBER
 VALUES ('MEM1', '1234ABCD','홍길동','2019-11-20' /*이런식으로 써도 DATE형태로 알아서 전환*/);
 
 INSERT INTO MEMBER
 VALUES ('MEM2', 'QWER1234','김영희', SYSDATE);
 
 INSERT INTO MEMBER
 VALUES ('MEM3', '1Q2W3E4R','박철수', DEFAULT); -- DEFAULT로 해도 설정해놔서 괜찮음
 
 INSERT INTO MEMBER (MEMBER_ID, MEMBER_PWD, MEMBER_NAME) -- 값이 들어갈 순서 지정
 VALUES ('MEM4', 'ZXCV9876','김태훈');
 -- DEFAULT가 설정된 컬럼은 값을 삽입하지 않아도 기본값으로 적용됨
 
 
 SELECT
     *
 FROM MEMBER;
 
 ----------------------------------------------------------------------------------------------------------
 
 
 -- 제약 조건(CONSTRAINTS)
 /*
    - 사용자가 원하는 조건의 데이터만 유지하기 위해서 특정 컬럼에 설정하는 제약
    // 예를 들어 MEMBER_ID의 경우 중복되면 안되기 때문에 중복 미지원 같은것을 건다던지..
    
    - 테이블 작성 시 컬럼에 대해 값 기록에 대한 제약조건 설정 가능
    
    - 데이터의 무결성을 보장하기 위해서 사용
    
    - 입력 데이터에 문제가 없는지 자동으로 검사할 목적으로 사용
    
    - 데이터의 수정/삭제 가능 여부 검사 목적으로 사용
    // RDBMS의 경우 테이블들이 관계적으로 연결 (JOIN)되어 있는데 하나를 수정 혹은 삭제 시
    연결되어 있는 테이블이랑 관계를 끊을 것인지 결정하기 위해 사용
    
    
    * 제약 조건의 종류
    PRIMARY KEY
    UNIQUE
    NOT NULL
    CHECK
    FOREIGN KEY
    
 */
 
 
 
 -- NOT NULL 제약조건
 -- 해당 컬럼에 반드시 값이 기록되어야 하는 경우에 사용하는 제약 조건
 -- 데이터 삽입, 수정 시 NULL 값을 허용하지 않도록
 -- ** 컬럼 레벨에서 제한
 
 
 CREATE TABLE MEM_NOCONST (
    MEM_NO      NUMBER,
    MEM_ID      VARCHAR2(20),
    MEM_PWD     VARCHAR2(20),
    MEM_NAME    VARCHAR2(30),
    MEM_GENDER  CHAR(1),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50)
 );
 
 INSERT INTO MEM_NOCONST
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_NOCONST
 VALUES (2, NULL, NULL, NULL, NULL, '010-1234-5678','aaa@kh.or.kr');
 -- 필수 입력사항인 아이디, 비밀번호가 없음... 따라서 제약을 걸어보자!
 SELECT
     *
 FROM MEM_NOCONST;
 
 -- NOT NULL 제약 조건을 설정한 테이블 생성
 
 -- 테이블 생성 시 제약조건 설정 방법
 -- 1) 컬럼 레벨 방식 : 컬럼 기입 시 컬럼 뒤쪽에 제약조건 추가 방법
 -- 2) 테이블 레벨 방식 : 테이블 생성 구문 마지막에 별도로 제약 조건 추가
 
 
 CREATE TABLE MEM_NOTNULL (
    MEM_NO      NUMBER NOT NULL,
    MEM_ID      VARCHAR2(20) NOT NULL,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    MEM_GENDER  CHAR(1),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50)
 );
 
 -- 제약 조건 확인
 SELECT * FROM USER_CONSTRAINTS;
 -- USER_CONSTRAINTS : 사용자가 작성한 제약조건을 확인하는 VIEW
 
 SELECT * FROM USER_CONS_COLUMNS;
 -- USER_CONS_COLUMNS : 제약 조건이 걸려있는 컬럼 확인용 VIEW
 
 INSERT INTO MEM_NOTNULL
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_NOTNULL
 VALUES (2, NULL, NULL, NULL, NULL, '010-1234-5678','aaa@kh.or.kr');
 --> NOT NULL 제약 조건을 위배하여 오류 발생! (ORA-01400)
 
 SELECT
     *
 FROM MEM_NOTNULL;
 
 
 ------------------------------------------------------------------------------------------
 -- * UNIQUE 제약조건
 -- 컬럼 입력 값에 대해서 중복을 제한하는 제약 조건
 -- 컬럼에 존재하는 기존의 데이터 중 중복이 있으면 안됨.
 -- 컬럼레벨, 테이블레벨 모두 설정 가능
 
 INSERT INTO MEM_NOTNULL
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 -- 데이터 중복에 대한 제약조건 UNIQUE가 설정된 테이블 생성
 CREATE TABLE MEM_UNIQUE (
    MEM_NO      NUMBER NOT NULL UNIQUE,
    MEM_ID      VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    MEM_GENDER  CHAR(1),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50)
 );
 -- 컬럼 레벨로 제약조건 설정 시 띄어쓰기를 통해 여러 개 설정 가능
 
 INSERT INTO MEM_UNIQUE
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 SELECT * FROM MEM_UNIQUE;
 
 
 -- 오류 보고에 나타난 제약조건명을 이용하여 해당 제약조건이 설정된 테이블명, 컬럼명, 제약조건 타입 조회
 
 SELECT UCC.TABLE_NAME, UCC.COLUMN_NAME, UC.CONSTRAINT_TYPE
 FROM USER_CONSTRAINTS UC, USER_CONS_COLUMNS UCC -- 오라클 방식 JOIN
 WHERE UCC.CONSTRAINT_NAME = UC.CONSTRAINT_NAME 
 AND UCC.CONSTRAINT_NAME = 'SYS_C007053';
 
 
 
 -- 테이블 레벨 제약조건 설정 구문
 -- [CONSTRAINT 제약조건명] 제약조건(컬럼명)
  -- 테이블 레벨 제약조건 설정 방법
 
 CREATE TABLE MEM_UNIQUE2 (
    MEM_NO      NUMBER NOT NULL,
    MEM_ID      VARCHAR2(20) NOT NULL,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    MEM_GENDER  CHAR(1),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50),
    CONSTRAINT MEM_UNIQUE2_MEM_NO /*이름은 제약조건 확인시 확인하기 쉬운 이름으로 지음*/ UNIQUE(MEM_NO),
    CONSTRAINT MEM_UNIQUE2_MEM_ID UNIQUE(MEM_ID)
 );
 --// 음...테이블레벨에서 제약조건 설정 시 그냥 제약조건 확인하기가 쉽다는 장점만 있는건가..?
 
 INSERT INTO MEM_UNIQUE2
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 
 
 ----------------------------------------------------------------------------------------------------------
 
 
 -- 복합키
 -- 두개 이상의 컬럼을 묶어서 제약조건 설정
 -- UNIQUE, PRIMARY KEY 가능
 -- * 반드시 테이블 레벨로만 설정
 
 CREATE TABLE MEM_UNIQUE3 (
    MEM_NO      NUMBER NOT NULL,
    MEM_ID      VARCHAR2(20) NOT NULL,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    MEM_GENDER  CHAR(1),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50),
    UNIQUE(MEM_NO, MEM_ID)
 );
 
 -- // 복합키의 경우 제약조건을 같이 걸어준 여러개의 컬럼에서 그들을 합쳐서 중복 검사를 하기 때문에
 -- // 1, A 넣고 2, A넣어도 다른걸로 인식함 (컬럼레벨, 테이블레벨과 다른 지점)
 
 INSERT INTO MEM_UNIQUE3
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_UNIQUE3
 VALUES (2,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_UNIQUE3
 VALUES (2,'user02', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_UNIQUE3
 VALUES (2,'user02', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 SELECT * FROM MEM_UNIQUE3;
 
 
 
 -- 컬럼 레벨로 제약조건 설정하기
 
 CREATE TABLE MEM_NOTNULL2 (
    MEM_NO      NUMBER CONSTRAINT MEM_NN2_NO_NN NOT NULL,
    MEM_ID      VARCHAR2(20) NOT NULL,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    MEM_GENDER  CHAR(1),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50)
 );
 
 -- 제약 조건 이름 확인
 
 SELECT * FROM
 USER_CONS_COLUMNS C1
 JOIN USER_CONSTRAINTS C2 USING (CONSTRAINT_NAME)
 WHERE C2.TABLE_NAME = 'MEM_NOTNULL2';
 
 ------------------------------------------------------------------------------------------------------
 
 -- CHECK 제약 조건
 -- 컬럼에 기록되는 값을 제한할 수 있는 제약조건
 -- CHECK(컬럼명  비교연산자  비교값)
 --> 비교값으로는 리터럴만 사용 가능 (변할 수 있는 값이나, 함수는 사용 불가능!!!!)
 -- 컬럼, 테이블 레벨 모두 설정 가능
 
 
 CREATE TABLE MEM_CHECK (
    MEM_NO      NUMBER NOT NULL,
    MEM_ID      VARCHAR2(20) NOT NULL,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    MEM_GENDER  CHAR(1) CHECK (MEM_GENDER IN ('M', 'F')),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50),
    CONSTRAINT MEM_CHECK_MEM_NO UNIQUE(MEM_NO),
    CONSTRAINT MEM_CHECK_MEM_ID UNIQUE(MEM_ID)
    -- CHECK (MEM_GENDER IN ('M', 'F')) // 여기에 두는 경우 테이블레벨 
 );
 
 INSERT INTO MEM_CHECK
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_CHECK
 VALUES (2,'user02', 'pass02', '홍길순', 'F', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_CHECK
 VALUES (3,'user03', 'pass03', '홍길동', 'A', '010-1234-1234','gd_hong123@kh.or.kr');
 -- 성별이 M 또는 F가 아니라서 CHECK 에러남
 -- 보통 FRONT END 단에서 바로바로 처리되므로 막상 많이 쓰지는 않음..
 
 SELECT * FROM MEM_CHECK;
 
 ------------------------------------------------------------------------------------------------------
 
 -- PRIMARY KEY (기본키) 제약 조건
 -- 테이블에서 한 행의 정보를 식별할 수 있게 하기 위하여 사용할 컬럼에 추가하는 제약 조건
 --> 각 행들을 구분할 수 있는 식별자 역할을 지정할 경우 사용
 -- EX) 회원번호, 부서코드, 직급코드, 수강생번호, 주민등록번호
 
 -- PRIMARY KEY는 NOT NULL 제약조건 + UNIQUE 제약조건
 -- // 단 NOT NULL 이면서 UNIQUE라고 PRIMARY KEY는 아니다!
 -- 한 테이블당 '한번만' (한 컬럼 또는 복합키) 설정 가능
 -- 컬럼, 테이블 레벨 모두 설정 가능
 
 
 CREATE TABLE MEM_PRIMARYKEY (
    MEM_NO      NUMBER PRIMARY KEY,
    MEM_ID      VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    MEM_GENDER  CHAR(1) CHECK (MEM_GENDER IN ('M', 'F')),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50)
    -- PRIMARY KEY (MEM_NO)
    -- CONASTRAINT MEM_PK PRIMARY KEY (MEM_NO) : 이것들은 테이블 레벨 제약조건
 );
 
 -- NOT NULL + UNIQUE 제약 조건을 한 컬럼에 지정하여도 해당 컬럼인 PRIMARY KEY인 것은 아니다.
 -- PRIMARY KEY는 테이블 내에서 각 행들을 식별(구별)해주는 유일한 값을 지정하는 것이기 때문에
 -- 중복 X, NULL 값 삽입 X의 같은 제약조건이어도 의미가 다르다.
 
 INSERT INTO MEM_PRIMARYKEY
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_PRIMARYKEY
 VALUES (1,'user02', 'pass02', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 -- UNIQUE CONSTRAINT 오류남
 
 INSERT INTO MEM_PRIMARYKEY
 VALUES (NULL,'user02', 'pass02', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 -- NULL 제약조건 오류남
 
 
 SELECT * FROM MEM_PRIMARYKEY;
 
 
 
 
 -- PRIMARY KEY 복합키 사용 --> 테이블 레벨만 가능
 
 CREATE TABLE MEM_PRIMARYKEY2 (
    MEM_NO      NUMBER,
    MEM_ID      VARCHAR2(20) NOT NULL UNIQUE,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    MEM_GENDER  CHAR(1) CHECK (MEM_GENDER IN ('M', 'F')),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(50),
    CONSTRAINT MEM_PK2_PK PRIMARY KEY(MEM_NO, MEM_ID)
 );
 
 -- PRIMARY KEY를 복합키로 사용할 경우 속도적으로 굉장히 빠름
 INSERT INTO MEM_PRIMARYKEY2
 VALUES (1,'user01', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM_PRIMARYKEY2
 VALUES (1,'user02', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 -- 지금은 복합키로 설정이 되어 있기때문에 한쌍이 INDEX가 됨
 
 INSERT INTO MEM_PRIMARYKEY2
 VALUES (NULL,'user02', 'pass01', '홍길동', 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 -- 웹상에서는 요즘 보통 아이디, 이메일을 PRIMARY KEY로 삼음
 
 SELECT * FROM MEM_PRIMARYKEY2;
 
 ------------------------------------------------------------------------------------------------------------------
 
 -- *****  FOREIGN KEY (외래키) 제약 조건
 -- 해당 컬럼에 참조된 다른 테이블이 제공하는 값만 사용할 수 있도록 하는 제약 조건
 -- // 참조 : 자바에서의 참조와 동일 (객체 대신 테이블을 참조한다고 생각하면 됨)
 -- // 다른 테이블이 제공하는 값을 CHECK 제약조건처럼 사용한다고 생각하면 될듯
 
 
 -- 관계형 데이터베이스 (RDBMS)
 -- 이 관계를 맺기 위해 사용하는 제약조건이 FOREIGN KEY
    --> 테이블간의 연결고리 역할
 -- 이때, 부모 테이블 (참조하는 테이블)의 PRIMARY KEY는 자식 테이블(참조를 하는 컬럼을 보유한 테이블)의
 -- FOREIGN KEY로 지정을 하게됨
 -- 컬럼, 테이블 레벨 모두 가능
 
 -- 회원 등급을 나타내는 테이블 (부모테이블)
 CREATE TABLE MEM_GRADE(
    GRADE_CODE          NUMBER PRIMARY KEY,
    GRADE_NAME          VARCHAR2(30) NOT NULL
 );
 
 INSERT INTO MEM_GRADE VALUES (10, '일반회원');
 INSERT INTO MEM_GRADE VALUES (20, '우수회원');
 INSERT INTO MEM_GRADE VALUES (30, '특별회원');
    
 SELECT * FROM MEM_GRADE;
 
 -- 회원 테이블 생성 (자식테이블)
 CREATE TABLE MEM (
    MEM_NO      NUMBER PRIMARY KEY,
    MEM_ID      VARCHAR2(20) NOT NULL,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    GRADE_CODE  NUMBER REFERENCES MEM_GRADE (GRADE_CODE),
    MEM_GENDER  CHAR(1) CHECK(MEM_GENDER IN ('M','F')),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(20)
    -- FOREIGN KEY (GRADE_CODE) REFERENCES MEM_GRADE[(GRADE_CODE)]
                                                    --> FOREIGN KEY 설정 시 자동으로 부모 기본키(PRIMARY KEY)를 참조하므로
                                                    --  [생략] 가능하다...!!!
 );
 
 --> 회원 등급은 10, 20, 30, NULL만 가능
    --> 부모 테이블 (MEM_GRADE의 GRADE_CODE에 있는 값만 가능...!)
 INSERT INTO MEM
 VALUES (1,'user01', 'pass01', '홍길동', 10, 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM
 VALUES (2,'user02', 'pass02', '홍길순', 20, 'F', '010-1234-1111','gd_hong1234@kh.or.kr');
 
 INSERT INTO MEM
 VALUES (3,'user03', 'pass03', '이순신', 30, 'M', '010-1234-2222','gd_hong1235@kh.or.kr');
 
 INSERT INTO MEM
 VALUES (4,'user04', 'pass04', '조동영', NULL, 'M', '010-1234-3333','gd_hong1236@kh.or.kr');
 
 INSERT INTO MEM
 VALUES (5,'user05', 'pass05', '안될듯', 40, 'M', '010-1234-3333','gd_hong1236@kh.or.kr');
 -- 입력하려는 값 중 GRADE_CODE가 부모 테이블에 존재하지 않아 찾을 수 없다 (= 참조할 수 없다)라는 오류를 발생시킴
 -- //integrity constraint (KH.SYS_C007106) violated - parent key not found
        --> 이런 상황을 참조 무결성이 위배된 상황이라고 함.
 
 -- 참조 무결성
 -- 참조하는 테이블의 컬럼 값에 없는 데이터를 삽입할 수 없게하는 것
 -- // 참조하는 테이블의 컬럼 값에 없는 데이터를 삽입하는 경우 데이터의 통일성이 깨지기 때문에 RELATIONAL의 의미가 퇴색되어 버린다..!
 
 SELECT * FROM MEM;
 
 -- FOREIGN KEY는 연결고리 역할을 하기 때문에 테이블을 연결하여 조회하는 JOIN 구문에 사용할 컬럼으로 적합.
 
 -- JOIN해서 조회하기
 SELECT MEM_ID, MEM_NAME, GRADE_CODE, GRADE_NAME
 FROM MEM
 LEFT JOIN MEM_GRADE USING (GRADE_CODE);
 
 
 
 -- 부모 테이블의 값을 삭제할 경우
 DELETE FROM MEM_GRADE
 WHERE GRADE_CODE = 10;
 -- 자식 테이블의 GRADE_CODE가 10인 값이 기록이 되어 있어 삭제 시 참조 무결성을 위배하게 되어 에러가 발생됨.
 
 
 
 -- FOREIGN KEY 삭제 옵션
 -- 부모 테이블의 데이터 삭제 시 자식 테이블에 기록된 데이터를 삭제 또는 NULL로 변환하는 옵션
 
 -- 1) ON DELETE SET NULL
 -- 부모 테이블 데이터 삭제 시 자식 데이터 NULL로 변경하는 옵션
 
 
 CREATE TABLE MEM2 (
    MEM_NO      NUMBER PRIMARY KEY,
    MEM_ID      VARCHAR2(20) NOT NULL,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    GRADE_CODE  NUMBER REFERENCES MEM_GRADE (GRADE_CODE) ON DELETE SET NULL,
    MEM_GENDER  CHAR(1) CHECK(MEM_GENDER IN ('M','F')),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(20)
 );
 
 INSERT INTO MEM_GRADE
 VALUES(40, 'VVVIP');
 
 INSERT INTO MEM2
 VALUES (1,'user01', 'pass01', '홍길동', 10, 'M', '010-1234-1234','gd_hong123@kh.or.kr');
 
 INSERT INTO MEM2
 VALUES (2,'user02', 'pass02', '홍길순', 20, 'F', '010-1234-1111','gd_hong1234@kh.or.kr');
 
 SELECT * FROM MEM2;
 
 -- 자식 테이블 중 아무도 참조하고 있지 않은 값 40을 삭제
 DELETE FROM MEM_GRADE
 WHERE GRADE_CODE = 40;
 --> 삭제를 진행해도 참조 무결성이 깨재지 않으므로 가능
 
 -- 실습을 위해 다시 추가
 INSERT INTO MEM_GRADE
 VALUES (40, 'VVVIP');
 
 -- 자식 테이블에 GRADE_CODE = 40인 데이터 추가
 INSERT INTO MEM2
 VALUES (3,'user03', 'pass03', '고길동', 40, 'M', '010-1234-1235','gd_hong123@kh.or.kr');
 
 -- 일단 확인
 SELECT * FROM MEM2;
 SELECT * FROM MEM_GRADE;
 
 -- 부모 테이블에서 자식에 참조 당하고 있는 컬럼 값 삭제
 DELETE FROM MEM_GRADE WHERE GRADE_CODE = 40;
 
 -- 삭제 후 자식 테이블 확인
 SELECT * FROM MEM2;
 --> GRADE_CODE가 40인 컬럼 값이 NULL로 변환
    --> 참조 무결성이 지켜짐
    
 
 -- 2) ON DELETE CASCADE 옵션
 -- 부모 테이블 데이터 삭제 시 해당 데이터를 참조하고 있던 자식 테이블의 행을 삭제시키는 옵션
 
 -- // 이런걸 '계단식 삭제'라고 함
 
 CREATE TABLE MEM3 (
    MEM_NO      NUMBER PRIMARY KEY,
    MEM_ID      VARCHAR2(20) NOT NULL,
    MEM_PWD     VARCHAR2(20) NOT NULL,
    MEM_NAME    VARCHAR2(30) NOT NULL,
    GRADE_CODE  NUMBER REFERENCES MEM_GRADE (GRADE_CODE) ON DELETE CASCADE,
    MEM_GENDER  CHAR(1) CHECK(MEM_GENDER IN ('M','F')),
    MEM_PHONE   VARCHAR2(20),
    MEM_EMAIL   VARCHAR2(20)
 );
 
 
 INSERT INTO MEM_GRADE
 VALUES(40, 'VVVIP');
 
 INSERT INTO MEM3
 VALUES (1,'user01', 'pass01', '홍길동', 10, 'M', '010-1234-1234','gd_hong123@kh.or.kr');

 INSERT INTO MEM3
 VALUES (2,'user02', 'pass02', '홍길순', 20, 'F', '010-1234-1111','gd_hong1234@kh.or.kr');
 
 INSERT INTO MEM3
 VALUES (3,'user03', 'pass03', '고길동', 40, 'M', '010-1234-1235','gd_hong123@kh.or.kr');
 
 SELECT * FROM MEM3;
 SELECT * FROM MEM_GRADE;
 
 -- 부모 테이블의 컬럼값 중 참조 당하고 있는 값 삭제
 DELETE FROM MEM_GRADE
 WHERE GRADE_CODE = 40;
 
 -- 삭제 후 자식 테이블 확인
 SELECT * FROM MEM3;
 
 
 ------------------------------------------------------------------------------------------------------------
 
 -- SUBQUERY를 이용한 테이블 생성
 -- 테이블 복사, 특정 조회 형태를 저장한 테이블 생성
 -- 컬럼명, 데이터타입, 컬럼 값이 모두 복사되고 
 -- 제약조건은 NOT NULL만 복사가 됨...(!!)
 
 
 -- EMPLOYEE 테이블 복사
 CREATE TABLE EMPLOYEE_COPY
 AS SELECT * FROM EMPLOYEE;
 -- DDL에서 사용되는 SUBQUERY는 괄호 안써도 됨...? 유일한 서브쿼리라서?
 
 SELECT * FROM EMPLOYEE;
 
 
 -- 사번, 이름, 급여, 부서명, 직급명을 저장하고 있는 테이블 생성
 CREATE TABLE EMPLOYEE_COPY2
 AS SELECT EMP_ID, EMP_NAME, SALARY, DEPT_TITLE, JOB_NAME
    FROM EMPLOYEE
    LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    JOIN JOB USING (JOB_CODE);
 
 SELECT * FROM EMPLOYEE_COPY2;
    --> 이렇게 뽑아서 테이블 생성도 가능하다
    
 
 -- 테이블의 컬럼, 데이터 타입만 복사하기
 CREATE TABLE EMPLOYEE_COPY3
 AS SELECT * FROM EMPLOYEE
 WHERE 1 = 0;
    --> WHERE절은 그게 참인 경우의 행만 조회함... 따라서 아예 거짓인 것을 설정하면
    -- 컬럼, 데이터타입만 복사된다!
 
 SELECT * FROM EMPLOYEE_COPY3;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 