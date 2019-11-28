-- 한줄 주석
-- 전체 사원 정보 조회
SELECT * FROM EMPLOYEE;

-- EMPLOYEE 테이블에서 사번, 이름, 월급 모두 조회
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE;

/*

범위 주석

*/
SELECT * FROM EMPLOYEE; -- 띄어쓰기 꼭 구분해야 함

-- 실습문제 --
-- 1. JOB 테이블의 모든 정보를 조회
SELECT * FROM JOB;
-- 2. JOB 테이블의 직급명만 조회
SELECT JOB_NAME FROM JOB;
-- 3. DEPARTMENT 테이블의 모든 정보 조회
SELECT * FROM DEPARTMENT;
-- 4. EMPLOYEE 테이블의 이름, 이메일, 전화번호, 고용일 조회
SELECT EMP_NAME, EMAIL, PHONE, HIRE_DATE FROM EMPLOYEE;
-- 5. EMPLOYEE 테이블의 고용일, 사원명, 월급 조회
SELECT HIRE_DATE, EMP_NAME, SALARY FROM EMPLOYEE;


-- 컬럼 값 산술 연산

-- EMPLOYEE 테이블에서 직원의 이름과 연봉 조회 (연봉 - 급여 * 12)
SELECT EMP_NAME, SALARY * 12
FROM EMPLOYEE;

-- EMPLOYEE 테이블에서 직원의 이름과 연봉 (별칭 : 연봉(원))조회, 보너스가 반영된 연봉(별칭 : 총 소득(원))
-- 보너스가 반영된 연봉 = (급여 + 급여*보너스) * 12

SELECT EMP_NAME, SALARY * 12, (SALARY + SALARY*BONUS) * 12
FROM EMPLOYEE;
-- 산술 연산 시 NULL값이 포함되어 있으면 결과가 NULL이됨.

-- 실습 문제 --
-- 1. EMPLOYEE 테이블에서 이름, 연봉, 총수령액(연봉+보너스), 실수령액(총수령액 - 세금3%) 조회
SELECT EMP_NAME, SALARY * 12, (SALARY + SALARY*BONUS) * 12, ((SALARY + SALARY*BONUS) * 12) * 0.97
FROM EMPLOYEE;

-- 2. EMPLOYEE 테이블에서 이름, 고용일, 근무일수(오늘날짜 - 고용일) 조회
-- DATE 형식 끼리는 산술 연산 가능
-- 현재 시간(날짜)를 조회하는 명령어 == SYSDATE
SELECT EMP_NAME, HIRE_DATE, SYSDATE - HIRE_DATE
FROM EMPLOYEE;
-- 24진수인 시간을 10진수로 나타내서 결과값이 이상하긴 함

-- 컬럼 별칭
-- as 별칭 / "별칭" / as "별칭" : 별칭에 특수문자가 포함될 시 쌍따옴표 사용
-- 특수문자X    특수문자O

-- EMPLOYEE 테이블에서 직원의 이름과 연봉 (별칭 : 연봉(원))조회, 보너스가 반영된 연봉(별칭 : 총 소득(원))
SELECT EMP_NAME, SALARY * 12 AS "연봉(원)", (SALARY + SALARY*BONUS) * 12 "총 연봉(원)"
FROM EMPLOYEE;


-- 리터럴
-- EMPLOYEE 테이블에서 직원의 사번, 이름, 급여, 단위(데이터 : 원) 조회
SELECT EMP_ID, EMP_NAME, SALARY,
'원' AS 단위
FROM employee;

-- DISTINCT
-- 컬럼에 포함된 중복값을 한 번씩만 표시하고자 할 때 사용하는 키워드

-- EMPLOYEE 테이블에서 직원의 직급 코드 조회
SELECT JOB_CODE
FROM EMPLOYEE;

-- EMPLOYEE 테이블에서 직원의 직급 코드 중복 제거하여 조회
SELECT DISTINCT JOB_CODE
FROM EMPLOYEE;

-- WHERE 절
-- 조회할 테이블에서 조건이 맞는 값을 가진 행을 골라내는 구문
/* [표현식]
SELECT 컬럼명
FROM 테이블명
WHERE 조건식;
*/

-- * 비교연산자
--  = (같다), > (크다), < (작다), >= (크거나 같다), <= (작거나 같다)
-- !=, ^=, <> (같지 않다) : 3종류 존재

-- EMPLOYEE 테이블에서 부서코드가 'D9'인 직원의 이름, 부서코드 조회
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- EMPLOYEE 테이블에서 급여가 4000000 이상인 직원의 이름, 급여 조회
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SALARY >= 4000000;

-- EMPLOYEE 테이블에서 부서코드가 D9이 아닌 사원의 사번,이름, 부서코드 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE <> 'D9';

-- EMPLOYEE 테이블에서 퇴사 여부가 N인 직원을 조회하고 근무 여부를 '재직중'으로 표시하여
-- 사번, 이름, 고용일, 근무여부를 조회

SELECT EMP_ID, EMP_NAME, HIRE_DATE, '재직중' /*AS (넣어도 상관 없음)*/ 근무여부
FROM EMPLOYEE
WHERE ENT_YN = 'N';

-- EMPLOYEE 테이블에서 실수령액(총수령액 - (연봉*세금 3%))이 5천만원 이상인 사원의
-- 이름, 월급, 실수령액, 고용일 조회
SELECT EMP_NAME, SALARY, (SALARY + (SALARY*BONUS))*12 - SALARY*12*0.03 실수령액, HIRE_DATE
FROM EMPLOYEE
WHERE (SALARY + (SALARY*BONUS))*12 - SALARY*12*0.03 >= 50000000;
/*이때 조건식에 별칭 사용하면 안됨...하는 방법 있긴한데 지금은 안됨*/

-- EMPLOYEE 테이블에서 부서코드가 'D6'이고 급여를 200만 이상 받는 직원의 이름, 부서코드, 급여를 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6' AND SALARY >= 2000000;

-- EMPLOYEE 테이블에서 부서코드가 'D6'이거나 급여를 200만 이상 받는 직원의 이름, 부서코드, 급여를 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6' OR SALARY >= 2000000;

-- EMPLOYEE 테이블에서 급여를 350만원 이상 600만원 이하로 받는 직원의 사번, 이름, 급여, 부서코드, 직급코드를 조회
SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE 3500000 <= SALARY AND SALARY <= 6000000;

-- 1. EMPLOYEE 테이블에 월급이 4000000 이상이고, JOB_CODE가 J2인 사원의 전체 내용 조회
SELECT
    *
FROM EMPLOYEE
WHERE SALARY>=4000000
AND JOB_CODE = 'J2';

-- 2. EMPLOYEE 테이블에 DEPT_CODE가 D9이거나 D5인 사원 중 고용일이 02년 1월 1일보다 빠른 사원의 이름, 부서코드, 고용일 조회
-- TIP : 다중 조건일 경우 순서 중요!
-- 먼저 조건을 검색해야되는 경우 괄호를 묶을 것
SELECT EMP_NAME, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE (DEPT_CODE = 'D9'
OR DEPT_CODE = 'D5')
AND HIRE_DATE < '02/01/01'; --이렇게 작성하는 경우 날짜로 인식됨 (리터럴)


-- BETWEEN 'A' AND 'B' (사이값을 한번에 처리할 수 있는 기능)
-- >> A 이상 B 이하 (이상/이하라는 것에 유의)

-- EMPLOYEE 테이블에서 급여를 350만원 이상 600만원 이하로 받는 직원의 사번, 이름, 급여, 부서코드, 직급코드를 조회
SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE SALARY BETWEEN '3500000' AND '6000000';

-- NOT 사용하기
-- EMPLOYEE 테이블에서 급여를 350만원 미만 600만원 초과로 받는 직원의 사번, 이름, 급여, 부서코드, 직급코드를 조회
SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE SALARY NOT BETWEEN '3500000' AND '6000000';
-- WHERE NOT SALARY BETWEEN '3500000' AND '6000000'; 도 가능
-- NOT 키워드는 컬럼명 또는 BETWEEN 앞에 붙이는게 가능

-- EMPLOYEE 테이블에서 고용일이 '90/01/01' ~ '01/01/01'인 사원의 모든 정보 조회
SELECT
    *
FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '90/01/01' AND '01/01/01';


-- 연결 연산자(||)
-- 여러 컬럼을 하나의 컬럼인 것 처럼 연결하거나 컬럼과 리터럴을 연결할 수 있다.
-- EMPLOYEE 테이블에서 사번, 이름, 급여를 연결하여 조회

SELECT EMP_ID || EMP_NAME || SALARY
FROM EMPLOYEE;

-- 컬럼과 리터럴 연결
SELECT EMP_NAME || '님의 월급은 ' || SALARY || '원 입니다.' AS 메세지
FROM EMPLOYEE;


-- ** LIKE (중요)
/*
비교하려는 값이 지정한 특정 패턴을 만족시키는지 조회할 때 
비교대상컬럼명 LIKE '문자패턴'
형식으로 검사 가능.

- 문자 패턴
'A%' (A로 시작하는 값)
'%A' (A로 끝나는 값)
'%A%' (A가 포함되는 값) : 중간이 아님에 유의

- 문자 수
'_' (한글자)
'__' (두글자)
*/

--EMPLOYEE 테이블에성 성이 전씨인 사원의 사번, 이름, 고용일 조회
SELECT EMP_ID, EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '전%';


-- EMPLOYEE 테이블에서 이름에 '하'가 포함된 직원의 이름, 주민번호, 부서코드 조회
SELECT EMP_NAME, EMP_NO, DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%하%';

-- EMPLOYEE 테이블에서 전화번호 네번째 자리가 7로 시작하는 사원의 사번, 이름, 전화번호 조회
SELECT EMP_ID, EMP_NAME, PHONE
FROM EMPLOYEE
WHERE PHONE LIKE '___7%';

-- EMPLOYEE 테이블에서 이메일 중 앞글자가 3자리인 사원의 사번, 이름, 이메일 주소 조회

-- ESCAPE OPTIONS 사용 X
SELECT EMP_ID, EMP_NAME, EMAIL
FROM EMPLOYEE
WHERE EMAIL LIKE '____%'; -- 언더바 구분이 안돼서 값 조회 실패

-- ESCAPE OPTIONS 사용 O
SELECT EMP_ID, EMP_NAME, EMAIL
FROM EMPLOYEE
WHERE EMAIL LIKE '___@_%' ESCAPE '@'; -- 언더바 구분이 안돼서 값 조회 실패

-- NOT LIKE
-- 특정 패턴을 만족하지 않는 값을 조회

-- EMPLOYEE 테이블에서 김씨 성이 아닌 사원의 사번, 이름, 고용일 조회
SELECT EMP_ID, EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE /*NOT*/ EMP_NAME NOT LIKE '김%';


-------------- 실습 문제 -------------------

-- 1. EMPLOYEE 테이블에서 이름 끝이 '연'으로 끝나는 사원의 이름 조회
SELECT EMP_NAME
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%연';

-- 2. EMPLOYEE 테이블에서 전화번호 처음 3자리가 010이 아닌 사원의 이름, 전화번호를 조회
SELECT EMP_NAME, PHONE
FROM EMPLOYEE
WHERE PHONE NOT LIKE '010%';

-- 3. EMPLOYEE 테이블에서
-- 메일주소 '_'의 앞이 4자 이면서 DEPT_CODE가 D9 또는 D6이고
-- 고용일이 90/01/01 ~ 00/12/01이고,
-- 급여가 270만 이상인 사원의 전체를 조회
SELECT
    *
FROM EMPLOYEE
WHERE EMAIL LIKE '____@_%' ESCAPE '@' 
AND (DEPT_CODE = 'D9' OR DEPT_CODE = 'D6')
AND HIRE_DATE BETWEEN '90/01/01' AND '00/12/01'
AND SALARY >= 2700000;

-- IS NULL : 컬럼값이 NULL인 경우
--  IS NOT NULL : 컬럼값이 NULL이 아닌 경우

-- EMPLOYEE 테이블에서 보너스를 받지 않는 사원의 사번, 이름, 급여, 보너스 조회
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE BONUS IS NULL;

-- EMPLOYEE 테이블에서 보너스를 받는 사원의 사번, 이름, 급여, 보너스 조회
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE BONUS IS NOT NULL;

-- EMPLOYEE 테이블에서 관리자도 없고 부서 배치도 받지 않은 사원의 이름, 관리자, 부서코드 조회
SELECT EMP_NAME, MANAGER_ID, DEPT_CODE
FROM EMPLOYEE
WHERE MANAGER_ID IS NULL
AND DEPT_CODE IS NULL;

-- EMPLOYEE 테이블에서 부서배치를 받았지만 보너스를 지급받지 못하는 사원의 이름, 보너스, 부서코드 조회
SELECT EMP_NAME, BONUS, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE IS NOT NULL
AND BONUS IS NULL;

-- EMPLOYEE 테이블에서 부서배치를 받았지만 보너스를 지급받지 못하는 사원의 이름, 보너스, 부서코드 조회
SELECT EMP_NAME, BONUS, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE IS NULL
AND BONUS IS NOT NULL; 

-- IN
-- 비교하려는 값과 목록에 일치하는 값이 있으면 TRUE를 반환하는 연산자
-- 비교대상컬럼명 IN (XXX, XXX, XXX, ...);

-- EMPLOYEE 테이블에서 D6 부서와 D8 부서원들의 이름, 부서코드, 급여 조회

-- IN 사용 X
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6'
OR DEPT_CODE = 'D8';
-- IN 사용 O
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE IN ('D6', 'D8');
-- 코드 길이가 짧아져서 좋다는 장점이 있다


-- 연산자 우선순위
/*
1. 산술 연산자
2. 연결 연산자
3. 비교 연산자
4. IS NULL /IS NOT NULL, LIKE, IN, NOT IN
5. BETWEEN AND / NOT BETWEEN AND
6. NOT
7. AND
8. OR
--> 순서를 잘 기억할것!!
*/

--------------------------------------------------------------------------------------------------
-- *** (별 3개짜리) ORDER BY 절
-- SELECT한 결과(RESULT SET)를 정렬할 때 작성하는 구문
-- SELECT 구문 제일 마지막에 작성
-- SELECT 실행 순서 중 가장 마지막!
/*
SELECT 컬럼명 [, 컬럼명, 컬럼명, ...]
FROM 테이블명
[WHERE 조건식]
[ORDER BY 컬럼명 | 별칭 | 컬럼순서 정렬방법 [NULLS FIRST|LAST]]
-- CF> DB쪽에서 또는은 | 한개만
*/

-- NULLS FIRST : 정렬 기준인 컬럼에 NULL 값이 있으면 앞부분에 정렬하기
-- NULLS LAST : 정렬 기준인 컬럼에 NULL 값이 있으면 뒷부분에 정렬하기

-- EMPLOYEE 테이블에서 급여 오름차순 순서대로 이름, 급여, 부서코드, 직급코드, 고용일 조회하기
SELECT EMP_NAME, SALARY, DEPT_CODE, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
ORDER BY SALARY; -- ASC : 오름차순(생략가능// 기본설정 되어있다)

-- EMPLOYEE 테이블에서 연봉 오름차순 순서대로 이름, 급여, 부서코드, 직급코드, 고용일 조회하기
SELECT EMP_NAME, SALARY*12 AS 연봉, DEPT_CODE, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
-- ORDER BY SALARY*12 DESC; -- DESC : 내림
-- ORDER BY 연봉 DESC; -- 별칭도 가능
ORDER BY 2 DESC; -- 컬럼 순서(2번째)도 가능 // 잘 쓰지는 않는다.. 컬럼이 바뀔 수 있어서




