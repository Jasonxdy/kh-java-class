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
SELECT EMP_NAME, SUBSTR(EMAIL, 1, INSTR(EMAIL,'@')-1)
FROM EMPLOYEE;