
-- 1번 : 계열 정보를 저장할 카테고리 테이블을 만들려고 한다. 다음과 같은 테이블을 작성하시오

CREATE TABLE TB_CATEGORY(
    NAME        VARCHAR2(10),
    USE_YN      CHAR(1) DEFAULT 'Y'
    );
    



-- 2번 : 과목 구분을 저장할 테이블을 만들려고 한다. 다음과 같은 테이블을 작성하시오.

CREATE TABLE TB_CLASS_TYPE(
    NO      VARCHAR2(5) PRIMARY KEY,
    NAME    VARCHAR2(10)
);




-- 3번 : TB_CATEGORY 테이블의 NAME 컬럼에 PRIMARY KEY를 생성하시오.
-- (KEY 이름을 생성하지 않아도 무방함. 만일 KEY 이름 지정하고자 한다면 이름은 본인이 알아서 적당한 이름을 사용한다)

ALTER TABLE TB_CATEGORY ADD CONSTRAINT CATEGORY_PK PRIMARY KEY (NAME);




-- 4번 : TB_CLASS_TYPE 테이블의 NAME 컬럼에 NULL 값이 들어가지 않도록 속성을 변경하시오

ALTER TABLE TB_CLASS_TYPE MODIFY NAME NOT NULL;









