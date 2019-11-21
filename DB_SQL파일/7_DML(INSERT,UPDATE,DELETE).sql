
-- DML (DATA MANIPULATION LANGUAGE)
-- 테이블에 값을 삽입하거나(INSERT), 수정하거나(UPDATE), 삭제(DELETE)하는 구문

------------------------------------------------------------------------------------------------------------------------------


-- 1. INSERT

-- 새로운 행을 추가하는 구문
    --> 테이블의 행 개수 증가

-- [표현식]
/*

    INSERT INTO 테이블명 [(컬럼명, 컬럼명, 컬럼명, ....)]
    VALUES (데이터1, 데이터2, 데이터3, ....)
    
    -- INSERT시 모든 컬럼에 데이터를 추가할 경우
    -- (컬럼명, 컬럼명, ...) 생략 가능
    -- 단, 컬럼 순서를 지켜서 VALUES를 작성해야 함
    
*/


-- EMPLOYEE 테이블에 새로운 행 추가하기
INSERT INTO EMPLOYEE
VALUES (900, '장채현', '901123-2080503', 'ch_jang@kh.or.kr', '01055569512', 'D1', 'J7', 'S3',4300000, 0.2, '200', SYSDATE, NULL, DEFAULT);

-- 장채현 사원의 모든 정보 조회
SELECT * FROM EMPLOYEE
WHERE EMP_NAME = '장채현';

COMMIT;
-- DCL, 현재까지 작업한 DML 내용을 DB에 반영 (DBMS -> DB에 값을 넣는 과정)


-- 일단 테이블 새로 하나 만들고
CREATE TABLE EMP_01(
    EMP_ID          NUMBER,
    EMP_NAME        VARCHAR2(30),
    DEPT_TITLE      VARCHAR2(20)
);

-- INSERT시 VALUES 대신 서브쿼리 사용 가능 (SELECT 사용하여 RESULT SET 도출 후 그대로 TABLE에 삽입)

INSERT INTO EMP_01 (EMP_ID, EMP_NAME, DEPT_TITLE)
            (SELECT EMP_ID, EMP_NAME, DEPT_TITLE
            FROM EMPLOYEE
            LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID));
            
SELECT
    *
FROM EMP_01;

--------------------------------------------------------------------------------------------------------------------------------

-- 2. INSERT ALL

-- INSERT시 서브쿼리가 사용(FROM)하는 테이블이 같은 경우 
-- 두 개 이상의 테이블에 INSERT ALL을 이용하여 한번에 삽입 가능.
-- 단, 각 서브쿼리의 조건절(WHERE)이 같아야 함.

-- INSERT ALL 예제용 테이블 복사
CREATE TABLE EMP_DEPT
AS  SELECT EMP_ID, EMP_NAME, DEPT_CODE,HIRE_DATE
    FROM EMPLOYEE
    WHERE 1 = 0;
    -- WHERE절이 무조건 FALSE이기 때문에 이를 만족하는 값이 없어서 값은 복사되지 않고, 컬럼명과 데이터 타입만 복사가 됨
    
SELECT * FROM EMP_DEPT;

CREATE TABLE EMP_MANAGER
AS SELECT EMP_ID, EMP_NAME, MANAGER_ID
    FROM EMPLOYEE
    WHERE 1 = 0;

SELECT * FROM EMP_MANAGER;


-- EMP_DEPT 테이블에 EMPLOYEE 테이블에 있는 부서코드가 D1인 직원을 조회해 사번, 이름, 부서코드, 입사일을 삽입하고,
-- EMP_MANAGER 테이블에 EMPLOYEE 테이블에 있는 부서코드가 D1인 직원을 조회해서 사번, 이름, 사수사번을 삽입.

-- 1) 각각 INSERT 하는 경우
INSERT INTO EMP_DEPT
            (SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE
             FROM EMPLOYEE
             WHERE DEPT_CODE = 'D1');
             
SELECT * FROM EMP_DEPT;


INSERT INTO EMP_MANAGER
            (SELECT EMP_ID, EMP_NAME, MANAGER_ID
            FROM EMPLOYEE
            WHERE DEPT_CODE = 'D1');
            
SELECT * FROM EMP_MANAGER;


ROLLBACK;
-- DCL, 현재까지 작성한 DML 내용을 DB에 반영하지 않고 다 지워버림 (원래 상태로 되돌림)


-- 2) INSERT ALL 사용
INSERT ALL
INTO EMP_DEPT VALUES(EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE)
INTO EMP_MANAGER VALUES(EMP_ID, EMP_NAME, MANAGER_ID)
    SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE, MANAGER_ID
    FROM EMPLOYEE
    WHERE DEPT_CODE = 'D1';


-- EMPLOYEE 테이블의 입사일 기준으로 2000년 1월 1일 이전에 입사한 사원의 사번, 이름, 입사일, 급여를 조회해서
-- EMP_OLD 테이블에 삽입하고 그 후에 입사한 사원의 정보는 EMP_NEW 테이블에 삽입

CREATE TABLE EMP_OLD
AS SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
    FROM EMPLOYEE
    WHERE 1 = 0;

CREATE TABLE EMP_NEW
AS  SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
    FROM EMPLOYEE
    WHERE 1 = 0;
    
INSERT ALL
WHEN HIRE_DATE < '2000/01/01'
THEN INTO EMP_OLD VALUES (EMP_ID, EMP_NAME, HIRE_DATE, SALARY)
WHEN HIRE_DATE >= '2000/01/01'
THEN INTO EMP_NEW VALUES (EMP_ID, EMP_NAME, HIRE_DATE, SALARY)
SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
FROM EMPLOYEE;

SELECT * FROM EMP_OLD;
SELECT * FROM EMP_NEW;    


-------------------------------------------------------------------------------------------------------------------------------

-- 3. UPDATE
-- 테이블에 기록된 컬럼의 값을 수정하는 구문
-- 테이블 전체 행의 개수에는 변화가 없다

-- [표현식]
/*
    UPDATE 테이블명
    SET 컬럼명1 = 변경할 값1,
    SET 컬럼명2 = 변경할 값2,
    SET 컬럼명3 = 변경할 값3,
    ...
    [WHERE 컬럼명 비교연산자 비교값];
*/
-- WHERE절 안걸면 컬럼 전체가 값이 변경된다...!


-- DEPARTMENT 테이블 복사 (실습시 항상 복사본 만들어서 해볼것)
CREATE TABLE DEPT_COPY
AS SELECT * FROM DEPARTMENT;

SELECT * FROM DEPT_COPY;

COMMIT;

-- DEPT_COPY 테이블에서 부서코드가 'D9'인 행의 부서명을
-- '전략기획팀'으로 수정

UPDATE DEPT_COPY
SET DEPT_TITLE = '전략기획팀';
--> 수정 조건을 설정하지 않으면 모든 컬럼값이 변경됨.

UPDATE DEPT_COPY
SET DEPT_TITLE = '전략기획팀'
WHERE DEPT_ID = 'D9';


-- UPDATE 구문에서 서브쿼리 사용

-- [표현식]
/*
    UPDATE 테이블명
    SET 컬럼명 = (단일행 서브쿼리) // 단일행 서브쿼리 : 행도, 열도 1개인 값
*/

-- 평상 시 유재식 사원을 부러워하던 방명수 사원의 급여와 보너스율을 유재식 사원과
-- 동일하게 변경해주기로 했다.
-- 이를 반영하는 UPDATE문을 작성하시오

CREATE TABLE EMP_SALARY
AS SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY, BONUS
    FROM EMPLOYEE;

-- EMP_SALARY 테이블에서 유재식, 방명수 정보만 조회
SELECT * FROM EMP_SALARY
WHERE EMP_NAME IN ('유재식', '방명수');

COMMIT;

UPDATE EMP_SALARY
SET SALARY  =  (SELECT SALARY
                FROM EMP_SALARY
                WHERE EMP_NAME = '유재식'),
    BONUS =    (SELECT BONUS
                FROM EMP_SALARY
                WHERE EMP_NAME = '유재식')
WHERE EMP_NAME = '방명수';
                



-- 다중행, 다중열 서브쿼리를 사용한 UPDATE

-- 다중열
-- 방명수 사원의 급여 인상 소식을 전해들은 다른 사원들이 단체로 파업했다.
-- 이를 해결하기 위해 노옹철, 전형돈, 정중하, 하동운 사원의 급여와 보너스를
-- 유재식 사원과 같게 변경하는 UPDATE문을 작성하시오

SELECT *
FROM EMP_SALARY
WHERE EMP_NAME IN ('노옹철', '전형돈', '정중하', '하동운');

UPDATE EMP_SALARY
SET (SALARY, BONUS) = (SELECT SALARY, BONUS
                        FROM EMP_SALARY
                        WHERE EMP_NAME = '유재식') -- 이 서브쿼리는 다중열이 됨
WHERE EMP_NAME IN ('노옹철', '전형돈', '정중하', '하동운');


-- 다중행
-- EMP_SALARY 테이블에서 아시아 지역에 근무하는 직원의 보너스율을 0.3으로 변경


-- 아시아 지역에 근무하는 직원 조회
SELECT EMP_ID, EMP_NAME, SALARY, BONUS, LOCAL_NAME
FROM EMP_SALARY
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
WHERE LOCAL_NAME LIKE 'ASIA%';


-- 아시아 지역에 근무하는 직원의 보너스 0.3으로 변경
UPDATE EMP_SALARY
SET BONUS = 0.3
WHERE EMP_ID IN (SELECT EMP_ID
                FROM EMP_SALARY
                LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
                JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
                WHERE LOCAL_NAME LIKE 'ASIA%');

---------------------------------------------------------------------------------------------------------------------------------
COMMIT;

-- UPDATE시 변경할 값은 해당 컬럼에 대한 제약조건을 위배되지 않게 해야 함 (무결성 침범).
UPDATE EMPLOYEE
SET DEPT_CODE = 'A5'
WHERE DEPT_CODE = 'D6';
-- FOREIGN KEY 제약조건 위배

--------------------------------------------------------------------------------------------------------------------------------

-- 4. MERGE (병합)
-- 구조가 같은 두 개의 테이블을 하나로 합치는 기능.
-- 테이블에서 지정하는 조건의 값이 존재하면 UPDATE
                                    --> WHEN MATCHED THEN
-- 조건 값이 없으면 INSERT
            --> WHEN NOT MATCHED THEN

-- PDF 참조

--------------------------------------------------------------------------------------------------------------------------------


-- 5. DELETE
-- 테이블의 행을 삭제하는 구문
    --> 테이블 행의 개수가 줄어듦

-- [표현식]
/*

    DELETE FROM 테이블명
    [WHERE 컬럼명 비교연산자 비교값];
    
    -- // WHERE절을 설정하지 않으면 테이블의 모든 데이터가 삭제됨
*/

COMMIT;

SELECT * FROM EMPLOYEE;

-- 장채현 삭제
DELETE FROM EMPLOYEE
WHERE EMP_NAME = '장채현';

ROLLBACK;

-- 전체 삭제
SELECT * FROM EMPLOYEE_COPY;
DELETE FROM EMPLOYEE_COPY;
ROLLBACK; -- 롤백

-- DELETE가 수행되지 않는 경우
DELETE FROM DEPARTMENT
WHERE DEPT_ID = 'D1';
--> 자식 테이블에 'D1' 값이 기록이 되어있어 삭제 시 참조 무결성이 위배되므로 오류 발생

DELETE FROM DEPARTMENT
WHERE DEPT_ID = 'D3';
--> FOREIGN KEY 제약조건이 설정이 되어 있어도 값이 참조되지 않았다면 삭제 가능함!
ROLLBACK;


-- FOREIGN KEY 제약조건으로 인해 삭제가 불가능한 상황
    --> 제약조건을 비활성시키면 삭제 가능
ALTER TABLE EMPLOYEE
DISABLE CONSTRAINT SYS_C007157 CASCADE; -- CASCADE : ??

-- 비활성화 후 다시 D1 삭제
DELETE FROM DEPARTMENT
WHERE DEPT_ID = 'D1';

SELECT * FROM DEPARTMENT;

SELECT * FROM EMPLOYEE;
-- D1은 남아있음 (제약조건 사라진 상태이므로)

ROLLBACK;

-- 비활성화 된 제약 조건을 다시 활성화
ALTER TABLE EMPLOYEE
ENABLE CONSTRAINT SYS_C007157; 

-- 활성화 후 다시 D1 삭제
DELETE FROM DEPARTMENT
WHERE DEPT_ID = 'D1';
--> 제약조건 다시 설정되어서 삭제 불가

--------------------------------------------------------------------------------------------------------------------------------

-- TRUNCATE
-- 테이블의 전체 행을 삭제하는 DDL
-- DELETE보다 수행속도가 빠름.
-- DB상에 메모리 공간이 확보가 된다.

-- COMMIT, ROLLBACK을 사용할 수 있는 경우
    --> DML 구문에 대해서만 사용 가능

-- DML 구문을 작성하다가 DDL 구문을 작성하면 앞에서 작성한 DML 구문이 모두 COMMIT됨
    --> ROLLBACK 불가!

SELECT * FROM EMP_SALARY;

-- DELETE를 이용해서 모두 삭제
DELETE FROM EMP_SALARY;

SELECT * FROM EMP_SALARY;

-- DELETE는 DML 구문이므로 ROLLBACK 가능
ROLLBACK;

-- TRUNCATE로 삭제
TRUNCATE TABLE EMP_SALARY;
SELECT * FROM EMP_SALARY;
ROLLBACK;
-- ROLLBACK해도 안돌아와.... TRUNCATE는 DDL구문이므로










