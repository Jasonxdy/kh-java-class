/*
    SYNONYM
    - 다른 DB (사용자)가 가진 객체에 대한 별명 혹은 줄임말 // 이 친구도 객체
    - 다른 사용자의 객체에 접근하는 경우 '사용자명.객체명'으로 접근해야 하지만
    SYNONYM (동의어)를 사용하면 간단한 이름으로 접근 가능.
*/


-- SYNONYM 생성 방법
/*
    [표현식]
    
    CREATE SYNONYM 별명 (줄임말)
    FOR 사용자명.객체명;
*/

-- 동의어 구분
-- 비공개 / 공개 동의어

-- 1. 비공개 동의어
-- 객체에 대한 접근 권한을 부여받은 사용자가 정의한 동의어로 해당 사용자만 사용 가능

-- SAMPLE 계정을 이용하여 KH 계정의 EMLOYEE 테이블 접근 시
-- 동의어를 설정하여 EMP로 접근하기

-- 1) (관리자 계정)
-- SAMPLE 계정에 KH 계정의 EMPLOYEE 테이블 조회 권한 부여
GRANT SELECT ON KH.EMPLOYEE TO SAMPLE;

-- 2) (SAMPLE 계정)
-- KH.EMPLOYEE 테이블 조회 확인
SELECT * FROM KH.EMPLOYEE;

-- 3) (SAMPLE 계정)
-- KH.EMPLOYEE 테이블의 별명을 EMP로 지정
CREATE SYNONYM EMP
FOR KH.EMPLOYEE;

-- 4) (관리자 계정)
-- SAMPLE 계정에 SYNONYM 생성 권한 부여
GRANT CREATE SYNONYM TO SAMPLE;

-- 5) (SAMPLE 계정)
-- CMD에서 다시 SYNONYM 생성
CREATE SYNONYM EMP FOR KH.EMPLOYEE;

-- 6) (SAMPLE 계정)
-- SYNONYM 조회
SELECT * FROM EMP;




-- 2. 공개 동의어
-- 모든 권한을 부여할 수 있는 계정 (DBA)이 정의한 동의어
-- 모든 사용자가 사용할 수 있는 동의어를 생성할 수 있음.
-- (PUBLIC)
-- EX) DUMMY TABLE -> DUAL (오라클 관리자 계정이 자동으로 줄여줌)

-- KH 계정의 DEPARTMENT 테이블을 DEPT라는 공개 동의어 설정

-- 1) (관리자 계정)
-- 공개 동의어 생성
CREATE PUBLIC SYNONYM DEPT
FOR KH.DEPARTMENT;

SELECT * FROM KH.DEPARTMENT;
SELECT * FROM DEPT;

-- 2) 다른 계정 (SAMPLE)도 공개 동의어 (DEPT) 사용 가능한지 확인
--> SAMPLE 계정에 KH 계정의 DEPARTMENT 테이블 SELECT 권한 부여
GRANT SELECT ON KH.DEPARTMENT TO SAMPLE;
-- 아무리 공개 동의어여도 해당 테이블에 대한 접근 권한이 없는 경우 조회 불가

-- 3) (SAMPLE 계정)
-- DEPT (공개 동의어) 사용 가능한지 확인
SELECT * FROM DEPT;




-- 3. 동의어 삭제

-- SAMPLE 계정의 EMP 동의어 삭제
DROP SYNONYM EMP;











