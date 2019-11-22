

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




















