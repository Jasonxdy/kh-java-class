

/*
SELECT 구문 해석 순서 (오라클 하면서 항상 생각해야 하는 것)

5 : SELECT 컬럼명 AS 별칭, 계산식, 함수식, .... -- SELECT 절
1 : FROM 참조할 테이블명 -- FROM 절
2 : WHERE 컬럼명 | 함수식  비교연산자  비교값 -- WHERE 절
3 : GROUP BY 그룹으로 묶을 컬럼명 -- GROUP BY 절
4 : HAVING  그룹함수식  비교연산자  비교값 -- HAVING 절
6 : ORDER BY 컬럼명 | 별칭 | 컬럼순번   정렬방식  [NULLS FIRST/LAST]; -- ORDER BY 절 (항상 가장 마지막)

(SELECT 절은 이게 전부) -> FROM부터 순서대로 해석하다가, SELECT -> ORDER BY 순서로 진행한다고 생각
*/

-- EMPLOYEE 테이블에서 부서 코드가 D5이면 총무부, D6이면 기획부, D9이면 영업부로 처리하시오
-- 단, 부서코드가 D5, D6, D9인 직원의 정보만 조회
-- => CASE 구문 사용
-- 부서코드 오름차순으로 정렬

SELECT DEPT_CODE,
CASE 
WHEN DEPT_CODE = 'D5' THEN '총무부'
WHEN DEPT_CODE = 'D6' THEN '기획부'
WHEN DEPT_CODE = 'D9' THEN '영업부'
END AS 부서
FROM EMPLOYEE
WHERE DEPT_CODE IN ('D5','D6','D9')
ORDER BY DEPT_CODE;


-- GROUP BY절
-- 같은 값들이 여러개 기록된 컬럼을 가지고
-- 같은 값들은 하나의 그룹으로 묶어주는 구문.

-- GROUP BY 컬럼명|함수식 [컬럼명|함수식, ....]
--> 여러개의 값을 묶어서 하나로 처리할 목적으로 사용
-- 그룹으로 묶은 값에 대해서 SELECT 절에서 그룹함수를 사용함

-- SELECT DEPT_CODE, SUM(SALARY)
-- FROM EMPLOYEE; -- 에러 발생
-- 이유 : DEPT_CODE는 컬럼의 값이 여러개, 그룹함수인 SUM은 값이 1개. 따라서 오류

SELECT DEPT_CODE, SUM(SALARY) -- 3. 각 SUM값이 그 그룹들의 값이 됨
FROM EMPLOYEE -- 1. EMPLOYEE 테이블을 가져오고
GROUP BY DEPT_CODE;  -- 2. DEPT_CODE의 값들이 하나의 그룹명이 되고
-- 여기서 NULL도 그룹을 지은다

-- EMPLOYEE 테이블에서 부서코드, 그룹 별 급여의 합계 ,그룹별 급여의 평균(정수처리),
-- 인원수를 조회하고 부서 코드 순으로 정렬
SELECT DEPT_CODE 부서코드, SUM(SALARY) 합계, FLOOR(AVG(SALARY)) 평균, COUNT(*) 인원수
FROM EMPLOYEE
GROUP BY DEPT_CODE
ORDER BY DEPT_CODE;

-- EMPLOYEE 테이블에서 부서코드와 그룹별 보너스를 받는 사원의 수를 조회하고
-- 부서코드 순으로 정렬
SELECT DEPT_CODE, COUNT(BONUS)
FROM EMPLOYEE
--WHERE BONUS IS NOT NULL
GROUP BY DEPT_CODE
ORDER BY DEPT_CODE;
-- 이때 WHERE절이 있냐 없냐에 따라 값이 다른데 이는 해석 순서 때문이다..

-- EMPLOYEE 테이블에서 성별과 성별 별 급여 평균 (정수 처리),
-- 급여 합계, 인원 수 조회하고 인원수로 내림차순 정렬
SELECT DECODE(SUBSTR(EMP_NO,8,1), '1', '남', '2', '여') AS 성별,
FLOOR(AVG(SALARY)) 평균, SUM(SALARY) 합계, COUNT(*) 인원수
FROM EMPLOYEE
GROUP BY DECODE(SUBSTR(EMP_NO,8,1), '1', '남', '2', '여') -- 이때 별칭으로 하면 안되는 이유 : 해석 순서 때문!!
ORDER BY 인원수 DESC; -- 이때 ORDER BY는 해석 순서 마지막이라서 별칭 사용 가능!


-- EMPLOYEE 테이블에서 부서코드별로 같은 직급인 사원의 급여 합계를 조회하고, 부서코드 순으로 정렬
SELECT DEPT_CODE, JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE, JOB_CODE
ORDER BY DEPT_CODE;
-- 이때 SUM(SALARY)가 300만원 이상인 사람만 보고 싶을 경우... WHERE는 사용불가 (왜냐면 WHERE가 GROUP BY 보다 먼저처리됨)
-- 따라서... 사용하는 것은 아래!

-------------------------------------------------------------------------------------------------------------------

-- HAVING 절
-- 그룹 함수로 구해올 그룹에 대한 조건을 설정할 때 사용하는 구문
-- HAVING 컬럼명|함수식   비교연산자      비교값

-- 부서별 급여 평균이 300만 이상인 부서 조회 (HAVING 절 사용 X : 원하는 결과 안나옴)
SELECT DEPT_CODE, FLOOR(AVG(SALARY)) 급여평균
FROM EMPLOYEE
WHERE SALARY >= 3000000
--> 이유 : 각 부서별로 급여가 300만 이상인 직원들의 급여만 평균값이 계산되어 조회됨..
GROUP BY DEPT_CODE
ORDER BY DEPT_CODE;

-- 부서별 급여 평균이 300만 이상인 부서 조회 (HAVING 절 사용 O)
SELECT DEPT_CODE, FLOOR(AVG(SALARY)) 급여평균
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING FLOOR(AVG(SALARY)) >= 3000000
ORDER BY DEPT_CODE;


-- EMPLOYEE 테이블에서 부서별 그룹의 급여 합계 중 900만원을 초과하는 부서의 부서코드와 급여 합계를 조회
-- 부서코드 순으로 정렬
SELECT DEPT_CODE, SUM(SALARY) 급여합계
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING SUM(SALARY) > 9000000
ORDER BY DEPT_CODE;

-- [참고] : 서브 쿼리 (쿼리문 안에 있는 쿼리문..!)
-- 급여 합계가 가장 높은 부서의 부서코드와 부서합계를 조회
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING SUM(SALARY) = 
    (SELECT MAX(SUM(SALARY))
    FROM EMPLOYEE
    GROUP BY DEPT_CODE);

---------------------------------------------------------------------------------------------------------

-- SET OPERATION (집합 연산)
/*
- 여러가지의 조건이 있을 때 그에 해당하는 여러 개의 결과값을 결합하고 싶을 때 사용하는 연산
- 초보자들이 사용하기 쉽다는 장점이 있다.
 --> 조건을 어떠헤 엮어야 하는지 생각하기 쉽기 때문
 --> 대신, 연산과정이 늘어나기 때문에 속도 저하가 발생
 

    UNION (합집합) == OR
    INTERSECT (교집합) == AND
    UNION ALL == UNION + INTERSECT
     --> 중복된 결과가 2번 포함됨
    MINUS (차집합) --> 중복 제거
*/    


-- UNION : 여러 개의 쿼리 결과를 하나로 합치는 연산자
-- 중복된 영역을 하나로 합침

-- EMPLOYEE 테이블에서 부서 코드가 'D5'인 직원과 급여가 300만 초과인 직원의
-- 사번, 이름, 부서코드, 급여를 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
UNION
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000;

-- 위 쿼리문을 OR 연산자를 이용해서 조회하기
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' OR SALARY >= 3000000;


-- INTERSECT : 교집합

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'

INTERSECT

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3000000;


-- UNION ALL : 집합의 합연산

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'

UNION ALL

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3000000;


-- MINUS : 차집합

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'

MINUS

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3000000;













