-- 함수(FUNCTION)
-- 컬럼의 값을 읽어서 계산한 결과를 리턴함

-- 단일행(SINGLE ROW) 함수
--> 컬럼에 기록된 N개의 값을 읽어서 N개의 결과를 리턴 (읽은 만큼 모두 다 리턴)

-- 그룹 (GROUP) 함수
--> 컬럼에 기록된 N개의 값을 읽어서 1개의 결과를 리턴

------------------------------------------------------------------------------------
-- 1. 문자 관련 함수

-- LENGTH / LENGTHB (B: 바이트)

SELECT LENGTH('오라클'), LENGTHB('오라클')
FROM DUAL; -- DUAL : DUMMY TABLE (가상 테이블) : DUMMY가 테스트, 실험용
-- 실행 결과 : 3 이랑 9 (문자 하나하나를 3바이트로 인식.. 이 버전(XE)이 그렇다는 것 //상식으로 알아둘 것)
/*
오라클 EXPRESS EDITION (XE)은 한글을 3BYTE로 인식
2BYTE로 인식시키고 싶을 경우 저장되는 컬럼의 타입을 NVARCHAR2로 지정
*/

-- EMPLOYEE 테이블에서 모든 사원의 이름, 이메일, 이메일 길이, 이메일 바이트 크기 조회
SELECT EMP_NAME, EMAIL, LENGTH(EMAIL), LENGTHB(EMAIL)
FROM EMPLOYEE;
-- 실행 결과 : 문자 개수 = 바이트수 // 왜냐면 영어랑 문자는 1BYTE로 취급.... 이렇게 한글과 다름


-- INSTR
--> INSTR ('문자열'|컬럼명, '문자', 찾을 위치의 시작값, [순번 (생략시 무조건 1)])
SELECT INSTR('AABAACAABBAA', 'B', 1, 1) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', -1, 1) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', -1, 2) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', -1, 3) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', 4, 2) FROM DUAL;

-----------------------------------------------------------------------------------------------

-- TRIM
-- 주어진 컬럼이나 문자열의 앞 / 뒤 / 양쪽에 있는 지정한 문자 제거
-- 문자를 지정하지 않은 경우 양쪽 공백제거
-- 제거할 위치를 지정하지 않은 경우 양쪽이 기본값
SELECT TRIM ('            백동현                ') FROM DUAL;

-- 양쪽 문자 제거
SELECT TRIM ('Z' FROM 'ZZZKHZZZ') FROM DUAL;
-- SELECT TRIM (BOTH 'Z' FROM 'ZZZKHZZZ') FROM DUAL; 이것도 가능

-- 앞쪽 문자 제거
SELECT TRIM (LEADING 'Z' FROM 'ZZZKHZZZ') FROM DUAL; -- 처음으로 다른 게 나올때 멈춤

-- 뒤쪽 문자 제거
SELECT TRIM (TRAILING 'Z' FROM 'ZZZKHZZZ') FROM DUAL; 

------------------------------------------------------------------------------------------------

-- ** SUBSTR
-- 컬럼이나 문자열에서 지정한 위치부터 지정한 개수의 문자열을 잘라내어 반환
-- (자바에 String.subString()과 유사함)
--> SUBSTR(STRING, POSITION, [LENGTH])
--> STRING : 문자 타입 컬럼 또는 문자열
--> POSITION : 문자열을 잘라낼 위치, 양수면 시작방향부터, 음수면 끝방향
----> LENGTH : 반환할 문자 개수 (생략 시 끝까지)
----> (음수 작성 시 NULL 리턴)
SELECT SUBSTR('SHOWMETHEMONEY', 1, 4) FROM DUAL;
SELECT SUBSTR('SHOWMETHEMONEY', 4) FROM DUAL;
SELECT SUBSTR('SHOWMETHEMONEY', 5, 2) FROM DUAL; -- ME
SELECT SUBSTR('SHOWMETHEMONEY',7, 3) FROM DUAL; -- THE
SELECT SUBSTR('SHOWMETHEMONEY',-8, 3) FROM DUAL; -- THE / LENGTH는 양수이므로 앞으로 센다

-- EMPLOYEE 테이블에서 EMP_NAME과 EMAIL @ 이후를 제외한 아이디 조회
SELECT EMP_NAME, SUBSTR(EMAIL, 1, INSTR (EMAIL,'@')-1)
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------------

-- REPLACE
-- 컬럼의 문자 또는 무낮열에서 특정 문자(열)을 지정한 문자(열)로 변환 후 반환
--> REPLACE(STRING, 변환전 문자(열), 변환 후 문자(열))
SELECT REPLACE  ('서울시 강남구 역삼동', '역삼동', '삼성동')
FROM DUAL;

SELECT EMAIL, REPLACE(EMAIL,'kh.or.kr', 'gmail.com')
FROM EMPLOYEE;

------------------------------------------------------------------------------------------------------------

-- ROUND : 반올림
--> ROUND(숫자 | 숫자로된 컬럼 [, 위치])   // 위치 : 소수점 몇째자리에서 반올림 할 지
SELECT ROUND(123.456) FROM DUAL; -- 소수를 반올림
SELECT ROUND(123.678) FROM DUAL;
-- 반올림하여 소수점 첫째자리 까지 조회
SELECT ROUND(123.456, 1) FROM DUAL; -- 123.5

SELECT ROUND(123.456, 2) FROM DUAL; -- 123.46

SELECT ROUND(123.456, -2) FROM DUAL; -- 100

---------------------------------------------------------------------------------------------------------------

-- EXTRACT : 년, 월, 일 정보를 추출하여 반환
-- EXTRACT (YEAR FROM 날짜타입) : 년도 반환
-- EXTRACT (MONTH FROM 날짜타입) : 월 반환
-- EXTRACT (DAY FROM 날짜타입) : 일 반환

-- EMPLOYEE 테이블에서 모든 사원의 이름, 입사 년도, 입사 월, 입사 일 조회
SELECT EMP_NAME,
EXTRACT(YEAR FROM HIRE_DATE) "입사 년도",
EXTRACT(MONTH FROM HIRE_DATE) "입사 월",
EXTRACT(DAY FROM HIRE_DATE) "입사 일"
FROM EMPLOYEE;

---------------------------------------------------------------------------------------------------------------

------------- 실습문제----------------------------
-- 1. EMPLOYEE 테이블에서 사원명, 입사일-오늘, 오늘-입사일 조회
-- 단, 별칭은 근무일수1, 근무일수2로 하고
-- 모두 정수처리, 양수가 되도록 처리
SELECT EMP_NAME, FLOOR(ABS(HIRE_DATE-SYSDATE)) AS 근무일수1, FLOOR(SYSDATE-HIRE_DATE) AS 근무일수2
FROM EMPLOYEE;
-- 이때 FLOOR랑 ABS 자리가 바뀌면 안됨... 음수에서 버리는거랑 양수에서 버리는게 다름

-- 2. EMPLOYEE 테이블에서 사번이 홀수인 직원들의 정보 모두 조회
SELECT
    *
FROM EMPLOYEE
-- WHERE MOD(EMP_ID, 2) = 1; -- 자바와는 달리 오라클은 알아서 형변환을 해서 작용해주고, 안되면 오류뜸 (자동으로 형변환 해줌)
-- 단 ,원칙적으로는 형변환을 통해 타입을 맞춰주는 것이 좋음 (자동 형변환은 작동이 오래 걸리므로...)
WHERE MOD(TO_NUMBER(EMP_ID), 2) = 1;

-- 3. EMPLOYEE 테이블에서 근무 년수가 20년 이상인 직원 정보 조회
SELECT
    *
FROM EMPLOYEE
--WHERE (EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE))  >= 20;
--WHERE MONTHS_BETWEEN (SYSDATE, HIRE_DATE) >= 240;
WHERE ADD_MONTHS(HIRE_DATE, 240) < SYSDATE;
-- 240개월을 더한게 현재 날짜보다 작으면 20년 이상임

-- 4. EMPLOYEE 테이블에서 사원명, 입사일, 입사한 월의 근무일수를 조회
SELECT EMP_NAME, HIRE_DATE, LAST_DAY(HIRE_DATE) - HIRE_DATE AS "입사한 월의 근무일수"
-- 정석적으로는 EXTRACT해서 일수 뽑아내는 게 좋다
FROM EMPLOYEE;

-- 날짜 포맷 변경
ALTER SESSION SET NLS_DATE_FORMAT = 'RR/MM/DD';
-- R : 2000년 이후로 인식
-- Y : 0년 이후로 인식

------------------------------------------------------------------------------------------------------------------------------

-- 4. 형변환 함수
-- TO_CHAR(날짜 | 숫자 [,포맷])
--> 날짜 또는 숫자형 데이터를 문자형 데이터로 변경
---> 포맷이 지정되어 있으면 해당 포맷 형태로 변경
SELECT TO_CHAR(1234) FROM DUAL;
SELECT TO_CHAR(1234, '99999') FROM DUAL;
--> 5칸을 지정 후 오른쪽 정렬, 빈칸은 공백으로 채움

SELECT TO_CHAR(1234, '00000') FROM DUAL;
--> 5칸을 지정 후 오른쪽 정렬, 빈칸은 0으로 채움

SELECT TO_CHAR(1234, 'L99999') FROM DUAL;
--> 현재 설정된 나라의 화폐 단위 반환 (L)

SELECT TO_CHAR(1234, '$99999') FROM DUAL;
--> 달러는 그냥 입력

SELECT TO_CHAR(1234, 'L999,999,999') FROM DUAL;
--> 자릿수 콤마 구분

SELECT TO_CHAR(1234, '000,000,000') FROM DUAL;
--> 콤마 유지하면서 빈칸 0으로 채움

SELECT TO_CHAR(1000, '9.9EEEE') FROM DUAL;
--> 부동소수점으로 표현 (1.0E+03)

SELECT TO_CHAR(1000, '999') FROM DUAL;
--> 설정한 포맷의 범위를 넘어선 경우 #으로 출력됨

-- EMPLOYEE 테이블에서 사원명, 급여 조회
-- 단, 급여는 '\9,000,000' 형식으로 표시할 것
SELECT EMP_NAME, TO_CHAR(SALARY, 'L9,999,999') AS 급여
FROM EMPLOYEE;

-------------------------------------------------------------------------------------------------------------------------------

-- TO_DATE
SELECT TO_CHAR (SYSDATE) FROM DUAL;
SELECT TO_CHAR (SYSDATE, 'PM HH24:MI:SS') FROM DUAL;
-- 월은 MM, 분은 MI
SELECT TO_CHAR (SYSDATE, 'AM HH:MI:SS') FROM DUAL;
-- PM, AM은 그 뜻을 가지고 있지는 않다.. 그냥 뭘 쳐도 오후면 오후라고, 오전이면 오전이라고 나옴
SELECT TO_CHAR (SYSDATE, 'YYYY-MM-DD DY PM HH:MI:SS') FROM DUAL;

SELECT TO_CHAR (SYSDATE, 'YEAR, Q') || '분기' FROM DUAL;

-- EMPLOYEE 테이블에서 모든 사원의 이름, 고용일 조회
-- 단, 고용일은 '2019-11-14' 형식으로 조회할 것
SELECT EMP_NAME, TO_CHAR(HIRE_DATE, 'YYYY"년" MM"월" DD"일"')
FROM EMPLOYEE;

---------------------------------------------------------------------------------------------------------
-- TO_DATE : 문자 또는 숫자형 데이터를 날짜형으로 변환
SELECT TO_DATE(20191114) FROM DUAL; -- 그냥 안써도 되고
SELECT TO_DATE('20191114', 'YYYYMMDD') FROM DUAL; --> 문자를 날짜로
SELECT TO_DATE(20191114, 'YYYYMMDD') FROM DUAL; --> 숫자를 날짜로
SELECT TO_CHAR (TO_DATE(191114), 'YYYY-MM-DD') FROM DUAL; 

SELECT TO_DATE('981114', 'YYMMDD') FROM DUAL;
SELECT TO_CHAR (TO_DATE(981114, 'YYMMDD'), 'YYYY-MM-DD') FROM DUAL;
--> 2000년도가 기준이라 2098년 나옴
-- 그래서 RR 써야함

-- EMPLOYEE 테이블에서
-- 2000년도부터 입사한 사원의 사번, 이름, 입사일 조회
SELECT EMP_ID, EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE HIRE_DATE >= TO_DATE(20000101,'YYYYMMDD');

------------------------------------------------------------------------------------------------------------

-- TO_NUMBER

SELECT TO_NUMBER('123456789') FROM DUAL;
SELECT '100' + '100' FROM DUAL;
--> 문자 값 내부에 숫자만 존재하므로 오라클이 자동으로 숫자로 형변환이 가능함

SELECT '1,000' + '1,000' FROM DUAL;
--> 문자값 내부가 숫자 + 문자의 형태로 자동 현변환 불가
--> 따라서 TO_NUMBER 필요

SELECT TO_NUMBER('1,000', '9,999') +
TO_NUMBER('1,000', '9,999') FROM DUAL;
--> 콤마 어디를 숫자로 인식시킬지 고름
SELECT TO_NUMBER('1,000', '9,999') +
TO_NUMBER('1,000', '9,999') FROM DUAL;

-------------------------------------------------------------------------------------------------------------

-- 5. NULL 처리 함수
-- NVL (컬럼명, 컬럼값이 NULL일 때 변경할 값)

SELECT EMP_NAME, NVL(BONUS, 0)
FROM EMPLOYEE;

SELECT EMP_NAME, (SALARY * 12) + (SALARY * NVL(BONUS, 0) * 12) AS 총수령액 -- NULL은 계산하면 무조건 NULL이므로.. 0으로 만들어줄 필요 있음
FROM EMPLOYEE;

-- NVL2(컬럼명, 변경값1, 변경값2)
-- 해당 컬럼에 값이 있으면 변경값1로 변경
-- 해당 컬럼이 NULL이면 변경값2로 변경

-- EMPLOYEE 테이블에서 기존 보너스를 받던 사원으 ㅣ보너스를 0.8로
-- 보너스를 받지 못했던 사원으 ㅣ보너스를 0.3으로 변경하여
-- 이름, 기존 보너스, 변경된 보너스 조회

SELECT EMP_NAME, BONUS, NVL2(BONUS, 0.8, 0.3)
FROM EMPLOYEE;

-- NULLIF(비교대상1, 비교대상2)
-- 두 개의 값이 동일하면 NULL, 다르면 비교대상1 반환
SELECT NULLIF('123','9123') FROM DUAL; --> 123 반환
SELECT NULLIF('123','123') FROM DUAL; --> NULL 반환





-- 함수 연습 문제

--1. EMPLOYEE 테이블에서
--  직원명과 주민번호를 조회
--  단, 주민번호 9번째 자리부터 끝까지는 '*'문자로 채움
--  예 : 홍길동 771120-1******
-- HINT. 연결 연산자

SELECT EMP_NAME, SUBSTR(EMP_NO, 1, 8)||'******' AS 주민번호
FROM EMPLOYEE;

--2. EMPLOYEE 테이블에서
--  직원명, 직급코드, 연봉(원) 조회
--  단, 총수령액은 ￦57,000,000 으로 표시
--  (총수령액은 보너스가 적용된 1년치 급여)
SELECT EMP_NAME, JOB_CODE, TO_CHAR(SALARY*(1+NVL(BONUS,0))*12, 'L999,999,999')
FROM EMPLOYEE;

-- 3. EMPLOYEE 테이블에서
--   부서코드가 D5, D9인 직원들 중에서 2004년도에 입사한 직원의 
--   사번 사원명 부서코드 입사일 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE DEPT_CODE IN ('D5','D9')
AND EXTRACT(YEAR FROM HIRE_DATE) = '2004';
-- 선생님 답안
-- AND TO_NUMBER(SUBSTR(HIRE_DATE,1,2)) = 4;

-- 4. EMPLOYEE 테이블에서
--   직원명, 입사일, 입사한 달의 근무일수 조회
--   단, 입사한 날도 근무일수에 포함해서 +1 할 것
SELECT EMP_NAME, HIRE_DATE, LAST_DAY(HIRE_DATE) - HIRE_DATE + 1 AS 근무일수
FROM EMPLOYEE;


--5. EMPLOYEE 테이블에서
--  직원명, 부서코드, 생년월일, 나이(만) 조회
--  단, 생년월일은 주민번호에서 추출해서, 
--  ㅇㅇ년 ㅇㅇ월 ㅇㅇ일로 출력되게 함.
--  나이는 주민번호에서 추출해서 날짜데이터로 변환한 다음, 계산.
SELECT EMP_NAME, DEPT_CODE, TO_CHAR(TO_DATE(SUBSTR(EMP_NO,1,6)), 'YY"년" MM"월" DD"일"') AS 생년월일, EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO,1,6)))+1 AS "나이(만)"
FROM EMPLOYEE;



