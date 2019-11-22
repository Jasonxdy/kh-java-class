

-- VIEW
/*
    - SELECT 쿼리 실행의 결과 화면을 저장한 객체
    - 논리적인 가상 테이블
    - 실질적인 데이터를 저장하고 있지 않음
    - 하지만 테이블을 사용하는 것과 동일하게 사용 가능.
    
    
    [표현식]
    
    CREATE [OR REPLACE] [FORCE | NO FORCE] VIEW 뷰이름
    AS 서브쿼리
    [WITH CHECK OPTION]
    [WITH READ ONLY];
    
*/

-- 1. VIEW 생성

-- 1) 모든 사번, 이름, 부서명, 근무지역 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, NATIONAL_NAME
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
LEFT JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
LEFT JOIN NATIONAL USING (NATIONAL_CODE);


-- 2) 가상의 테이블인 VIEW에 위 2)의 SELECT 결과를 저장
    --> VIEW 생성

CREATE OR REPLACE VIEW V_EMPLYOEE
AS SELECT EMP_ID, EMP_NAME, DEPT_TITLE, NATIONAL_NAME
    FROM EMPLOYEE
    LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    LEFT JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
    LEFT JOIN NATIONAL USING (NATIONAL_CODE);

-- ORA-01031: insufficient privileges
--> VIEW 생성 권한이 없어서 오류 발생

-- KH 계정에 VIEW 생성 권한 부여
-- 1> SYS AS SYSDBA로 접속 변경
-- 2> KH 계정에 VIEW 생성 권한 부여
GRANT CREATE VIEW TO KH;

-- 3> 다시 KH 계정으로 접속을 변경하여 뷰 생성
CREATE OR REPLACE VIEW V_EMPLOYEE
AS SELECT EMP_ID, EMP_NAME, DEPT_TITLE, NATIONAL_NAME
    FROM EMPLOYEE
    LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    LEFT JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
    LEFT JOIN NATIONAL USING (NATIONAL_CODE);

-- 3) 생성한 VIEW 조회
SELECT * FROM V_EMPLYOEE;

COMMIT;



-- 베이스 테이블의 정보가 변경되면
-- 해당 테이블로 인해 만들어진 VIEW의 데이터도 변경됨.

-- 사번이 205인 사원의 이름을 '정중앙' 변경

UPDATE EMPLOYEE
SET EMP_NAME = '정중앙'
WHERE EMP_ID = 205;

-- 베이스테이블(EMPLOYEE)조회
SELECT * FROM EMPLOYEE
WHERE EMP_ID = 205;

-- 그에 따른 VIEW의 데이터 조회
SELECT * FROM V_EMPLOYEE
WHERE EMP_ID = 205;
-- 인라인뷰와 같은 개념




-- VIEW에 사용될 서브쿼리의 SELECT 절에 함수가 사용된 경우
-- VIEW 생성 시 반드시 해당 컬럼에 별칭을 지정해야 함.

-- 모든 사원의 사번, 이름 직급명, 성별, 근무년수를 뷰로 생성하기 (2가지 방법 존재)

-- 1번 : 서브쿼리에 별칭 지정하기
CREATE OR REPLACE VIEW V_EMP_JOB
AS SELECT EMP_ID 사번, EMP_NAME 이름, JOB_NAME 직급명, DECODE(SUBSTR(EMP_NO, 8, 1), '1','남','2','여') 성별,
    EXTRACT (YEAR FROM SYSDATE) - EXTRACT (YEAR FROM HIRE_DATE) 근무년수
    FROM EMPLOYEE
    JOIN JOB USING (JOB_CODE);

-- 2번 : VIEW 이름 옆에 괄호로 순서대로 컬럼 별칭 표시하기
CREATE OR REPLACE VIEW V_EMP_JOB2 (사번, 이름, 직급명, 성별, 근무년수)
AS SELECT EMP_ID, EMP_NAME, JOB_NAME, DECODE(SUBSTR(EMP_NO, 8, 1), '1','남','2','여'),
    EXTRACT (YEAR FROM SYSDATE) - EXTRACT (YEAR FROM HIRE_DATE)
    FROM EMPLOYEE
    JOIN JOB USING (JOB_CODE);

SELECT * FROM V_EMP_JOB;
SELECT * FROM V_EMP_JOB2;



-- 생성된 VIEW를 통해 DML 구문 사용시 베이스테이블 변화 확인
COMMIT;

SELECT * FROM JOB;

CREATE OR REPLACE VIEW V_JOB
AS SELECT JOB_CODE, JOB_NAME
    FROM JOB;
    
SELECT * FROM V_JOB;

-- VIEW에 INSERT 구문 사용해보기
INSERT INTO V_JOB VALUES ('J8', '인턴');

-- VIEW에 추가된 데이터 확인
SELECT * FROM V_JOB;

-- 베이스 테이블도 변경 되었는지 확인
SELECT * FROM JOB;


INSERT INTO V_JOB
VALUES ('J0', NULL);

SELECT * FROM JOB;

--------------------------------------------------------------------------------------------------------------------------------


-- * DML 명령어로 VIEW 조작이 불가능한 경우 *
/*
1. 뷰 정의에 포함되지 않은 컬럼을 조작하는 경우
2. 뷰에 포함되지 않은 컬럼 중에 베이스가 되는 컬럼이 NOT NULL 제약조건이 지정된 경우
3. 산술 표현식으로 정의된 경우
4. 그룹함수나 GROUP BY절을 포함한 경우
5. DISTINCT를 포함한 경우
6. JOIN을 이용해 여러 테이블을 연결한 경우
*/


-- 1. 뷰 정의에 포함되지 않은 컬럼을 조작하는 경우
CREATE OR REPLACE VIEW V_JOB3
AS SELECT JOB_CODE FROM JOB;


-- 뷰에 정의되지 않은 컬럼(JOB_NAME)을 조작해보기
INSERT INTO V_JOB3
VALUES ('J9', '알바');
-- ORA-00913: too many values
-- VIEW 자체에는 JOB_NAME이 없기 때문에 오류

UPDATE V_JOB3
SET JOB_NAME = '수습'
WHERE JOB_CODE = 'J8';
-- ORA-00904: "JOB_NAME": invalid identifier

DELETE FROM V_JOB3
WHERE JOB_NAME = '인턴';
-- ORA-00904: "JOB_NAME": invalid identifier

SELECT * FROM V_JOB4;


-- 2. 뷰에 포함되지 않은 컬럼 중에 베이스가 되는 컬럼이 NOT NULL 제약조건이 지정된 경우
-- (이거 중요)
 
CREATE OR REPLACE VIEW V_JOB4
AS SELECT JOB_NAME FROM JOB;

INSERT INTO V_JOB4
VALUES ('알바');
-- ORA-01400: cannot insert NULL into ("KH"."JOB"."JOB_CODE")
-- 베이스 테이블인 JOB에 JOB_CODE 컬럼은 PRIMARY KEY 제약 조건이 설정되어 있으므로
-- NULL 값이 삽입될 수 없음. --> 따라서 에러 발생


UPDATE V_JOB4
SET JOB_NAME = '수습'
WHERE JOB_NAME = '인턴';
-- UPDATE는 가능

DELETE FROM V_JOB4
WHERE JOB_NAME IS NULL;
-- DELETE도 가능

-- 왜냐면 INSERT와는 달리 행의 개수가 추가되는 것은 가능

SELECT * FROM V_JOB4;

-- 3) 산술 표현식으로 정의된 경우
 CREATE OR REPLACE VIEW V_EMP_SAL
 AS SELECT EMP_ID, EMP_NAME, SALARY, (SALARY * (1+NVL(BONUS,0)) * 12) 연봉
     FROM EMPLOYEE;
     
SELECT * FROM V_EMP_SAL;
     
INSERT INTO V_EMP_SAL
VALUES(800, '정진훈', 3000000);
-- ORA-01733: virtual column not allowed here

SELECT * FROM V_EMP_SAL;

UPDATE V_EMP_SAL
SET 연봉 = 8000000
WHERE EMP_ID = 200;
-- 실제 베이스 테이블에는 연봉이라는 컬럼이 존재하지 않아서 값을 넣을 수 없다

-- 산술 표현식이 정의된 뷰에는 INSERT, UPDATE 불가!
COMMIT;

DELETE FROM V_EMP_SAL
WHERE 연봉 = 124800000;
-- 단 , DELETE의 경우 연봉으로 찾은 행의 다른 컬럼들이 베이스테이블에 존재하기 때문에 값을 삭제하는 것은 가능

-- 즉, DELETE는 가능!

ROLLBACK;

SELECT * FROM V_EMP_SAL;
SELECT * FROM EMPLOYEE;



-- 4. 그룹함수나 GROUP BY절을 포함한 경우

SELECT SUM (SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;
--> 그룹 함수 또는 GROUP BY 절 사용하여 뷰 생성 시 뷰를 통해 조회되는 각 행이 베이스테이블의 어떤 행의 값인지
--> 알 수 없음..!
    --> 따라서 INSERT, UPDATE, DELETE시 모두 에러 발생
    
CREATE OR REPLACE VIEW V_GROUPDEPT
AS SELECT DEPT_CODE, SUM(SALARY) 합계, AVG(SALARY) 평균
    FROM EMPLOYEE
    GROUP BY DEPT_CODE;
INSERT INTO V_GROUPDEPT VALUES('D10', 6000000, 4000000);
DELETE FROM V_GROUPDEPT WHERE DEPT_CODE = 'D1';


--5. DISTINCT를 포함한 경우
-- // 이 경우에도 중복이 제거되었기 때문에 어떤 것을 지칭하는지 몰라서 불가능

CREATE OR REPLACE VIEW V_DT_EMP
AS SELECT DISTINCT JOB_CODE
    FROM EMPLOYEE;
INSERT INTO V_DT_EMP VALUES('J9');
DELETE FROM V_DT_EMP WHERE JOB_CODE = 'J1';



--6. JOIN을 이용해 여러 테이블을 연결한 경우
CREATE OR REPLACE VIEW V_JOINEMP
AS SELECT EMP_ID, EMP_NAME, DEPT_TITLE
    FROM EMPLOYEE
    JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
    
-- INSERT/UPDATE 에러 발생
INSERT INTO V_JOINEMP
VALUES(888,'조세오','인사관리부');
-- DEPARTMENT 테이블의 경우 PK가 DEPT_ID이므로 DEPT_TITLE에 값을 추가 시 PK가 NULL이 되어
-- 참조 무결성이 위배되게 된다..

--> 그래서 JOIN된 뷰는 INSERT/UPDATE가 불가능하다

DELETE FROM V_JOINEMP
WHERE EMP_ID = 200;
-- 이건 안하고 넘어가심..내가 나중에 TEST 해볼 것


-- * VIEW 구조 확인하기
-- 사용자 정의 뷰를 확인할 수 있는 데이터 딕셔너리 (USER_VIEWS)
SELECT * FROM USER_VIEWS;
-- //확인해보면 VIEW안에는 그냥 서브쿼리가 저장되어 있고 호출 시 해당 서브쿼리 실행되어
-- // RESULT SET을 보여주는 것

--------------------------------------------------------------------------------------------------------------------------------

-- VIEW 옵션

-- 1) OR REPLACE
-- 기존에 동일한 이름을 가진 뷰가 존재하는 경우 
-- 해당 뷰를 덮어쓰고 없는 경우 새로운 뷰를 생성함

CREATE VIEW V_EMP2
AS SELECT EMP_NO, EMP_NAME
    FROM EMPLOYEE;

-- 생성 확인
SELECT * FROM V_EMP2;

-- 같은 이름으로 EMP_ID로 변경하여 REPLACE
CREATE OR REPLACE VIEW V_EMP2
AS SELECT EMP_ID, EMP_NAME
    FROM EMPLOYEE;
-- 확인
SELECT * FROM V_EMP2;


-- 4) WITH READ ONLY : 뷰에 대한 조회만 가능하게 하는 옵션 (DML 불가)

CREATE OR REPLACE VIEW V_DEPT
AS SELECT * FROM DEPARTMENT
WITH READ ONLY;

SELECT * FROM V_DEPT;

DELETE FROM V_DEPT;
-- ORA-42399: cannot perform a DML operation on a read-only view


-- 보통 실무에서는 WITH READ ONLY로 사용함







