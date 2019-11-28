
/*
  SELECT문 해석 순서
  
  5 : SELECT 컬럼명 AS 별칭, 계산식, 함수식
  1 : FROM 참조할 테이블명 
     + JOIN
  2 : WHERE 컬럼명 | 함수식 비교연산자 비교값
  3 : GROUP BY 그룹을 묶을 컬럼명
  4 : HAVING 그룹함수식 비교연산자 비교값
  6 : ORDER BY 컬럼명 | 별칭 | 컬럼순번 정렬방식 [NULLS FIRST | LAST];
*/

----------------------------------------------------------------------------------------------------------------------------------

-- SUBQUERY(서브쿼리)
/*
- 하나의 SQL문 안에 포함된 또다른 SQL문.
- 메인 쿼리(기존 쿼리)를 위해 보조 역할을 하는 쿼리문.
*/
         
-- SUBQUERY 조건
-- 1) 메인 쿼리가 실행되기 전 한번만 실행
-- 2) 비교 연산자 오른쪽에 기술해야 함
-- 3) 서브쿼리는 반드시 괄호로 묶어야 함
-- 4) 서브쿼리와 비교할 항목은 반드시 서브쿼리의 SELECT한 항목의 개수와 자료형을 일치시켜야 함.

         
         
-- 간단 서브쿼리 예시 1
-- 부서코드가 노옹철사원과 같은 소속의 직원의 
-- 이름, 부서코드 조회하기

-- 1) 사원명이 노옹철인 사람의 부서 조회
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '노옹철';
    
-- 2) 부서코드가 D9인 직원을 조회
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- 3) 부서코드가 노옹철사원과 같은 소속의 직원의 
-- 이름, 부서코드 조회하기
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME = '노옹철');

-- 간단 서브쿼리 예시 2                   
-- 전 직원의 평균 급여보다 많은 급여를 받고 있는 직원의 
-- 사번, 이름, 직급코드 ,급여 조회

-- 1) 전 직원의 평균 급여 조회 
SELECT ROUND(AVG(SALARY))
FROM EMPLOYEE;

-- 2) 전 직원들중 급여가 3047663원 이상인 사원들의 사번, 이름, 직급코드, 급여 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3047663;

-- 3) 전 직원의 평균 급여보다 많은 급여를 받고 있는 직원의 
-- 사번, 이름, 직급코드 ,급여 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= (SELECT ROUND(AVG(SALARY))
                 FROM EMPLOYEE);


----------------------------------------------------------------------------------------------------------------------------------

/*  서브쿼리의 유형
     
     - 단일행 서브쿼리 : 서브쿼리의 조회 결과 값의 개수가 1개인 서브쿼리
     
     - 다중행 서브쿼리 : 서브쿼리의 조회 결과 값의 개수가 여러개인 서브쿼리
     
     - 다중열 서브쿼리 : 서브쿼리의 SELECT절에 나열된 항목 수가 여러개인 서브쿼리
     
     - 다중행, 다중열 서브쿼리 : 서브쿼리 조회 결과가 여러 행, 여러 컬럼을 갖는 서브쿼리
    
     - 상관 서브쿼리 : 서브쿼리가 만든 결과 값을 메인 쿼리가 비교연산할 때 
                      메인쿼리 테이블의 값이 변경되면 서브쿼리의 결과 값도 변경되는 서브쿼리
    
     - 스칼라 서브쿼리 : SELECT 절에 사용되는 서브쿼리
    
     * 서브쿼리의 유형에 따라 서브쿼리 앞에 붙는 연산자가 달라짐.
    
*/
----------------------------------------------------------------------------------------------------------------------------------



-- 1. 단일행 서브쿼리 (SINGLE ROW SUBQUERY)
-- 서브쿼리의 조회 결과 값의 개수가 1개인 서브쿼리
-- 단일행 서브쿼리 앞에는 비교 연산자 사용
-- <, >, <=, >=, =, !=, <>, ^=





-- 예제 1-1
-- 전 직원의 급여 평균보다 많은 급여를 받는 직원의 
-- 이름, 직급, 부서, 급여를 직급 순으로 정렬하여 조회
SELECT EMP_NAME, JOB_CODE, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= (SELECT AVG(SALARY) FROM EMPLOYEE)
                    --> 조회하는 컬럼 1개, 결과 1개 조회
ORDER BY JOB_CODE;





-- 예제 1-2                    
-- 가장 적은 급여를 받는 직원의
-- 사번, 이름, 직급, 부서, 급여, 입사일을 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, DEPT_CODE, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY = (SELECT MIN(SALARY) FROM EMPLOYEE);





-- 예제 1-3
-- 노옹철 사원의 급여보다 많이 받는 직원의
-- 사번, 이름, 부서, 직급, 급여를 조회                 
SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > (SELECT SALARY FROM EMPLOYEE WHERE EMP_NAME = '노옹철');


-- ** 서브쿼리는 WHERE절 뿐만 아니라 SELECT, HAVING, FROM절에서도 사용 가능!!





-- 예제 1-4
-- 부서별(부서가 없는 사람 포함) 급여의 합계 중 가장 큰 부서의
-- 부서명, 급여 합계를 조회 

-- 1) 부서별 급여 합 중 가장 큰값 조회
SELECT MAX(SUM(SALARY))
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- 2) 부서별 급여합이 17700000인 부서의 부서명과 급여 합 조회
SELECT DEPT_TITLE, SUM(SALARY)
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
--> 부서가 없는 직원도 있기 때문에 LEFT JOIN
GROUP BY DEPT_TITLE
HAVING SUM(SALARY) = 17700000;

-- 3) 부서별(부서가 없는 사람 포함) 급여의 합계 중 가장 큰 부서의
-- 부서명, 급여 합계를 조회 
SELECT DEPT_TITLE, SUM(SALARY)
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
GROUP BY DEPT_TITLE
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY))
                        FROM EMPLOYEE
                        GROUP BY DEPT_CODE);
                    --> 컬럼 1개, 결과값 1개
                    
----------------------------------------------------------------------------------------------------------------------------------


-- 2. 다중행 서브쿼리(MULTI ROW SUBQUERY)
-- 서브쿼리의 조회 결과 값의 개수가 여러 행인 서브쿼리
/*
   
    * 다중행 서브쿼리 앞에는 일반 비교연산자 사용 X (값이 여러개라서 =, >,< 사용못함)

    - IN / NOT IN
    -> 여러 개의 결과값 중 하나라도 일치하는 값이 있다면   
                                 일치하는 값이 없다면

    > ANY, < ANY : 여러개의 결과값 중에서 하나라도 큰 / 작은 경우를 나타냄
    --> 여러개의 결과 값 중에서 가장 작은 값보다 크냐? / 가장 큰 값보다 작냐?
    -- // ANY 다음 중 '어떠한 것'보다 작다 / 크다
    
    > ALL, < ALL : 여러개의 결과값의 모든 값보다 큰 / 작은 경우를 나타냄
    --> 모든 결과값 중에서 가장 큰 값보다 크냐? / 가장 작은 값보다 작냐?
    
    EXSIST / NOT EXIST : 값이 존재하냐? / 존재하지 않냐?
    
*/




-- 예제 2-1
-- 부서별 최고 급여를 받는 직원의 
-- 이름, 직급, 부서, 급여를 부서 순으로 정렬하여 조회
-- 1) 부서별 최고 급여를 받는 직원의 급여 조회
SELECT MAX(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

SELECT EMP_NAME, JOB_CODE, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN (SELECT MAX(SALARY) FROM EMPLOYEE GROUP BY DEPT_CODE)
ORDER BY DEPT_CODE;




-- 예제 2-2
-- 사수에 해당하는 직원에 대해 조회 
-- 사번, 이름, 부서명, 직급명, 구분(사수 / 사원)


-- 1) 사수에 해당하는 사원 번호 조회
SELECT DISTINCT(MANAGER_ID)
FROM EMPLOYEE
WHERE MANAGER_ID IS NOT NULL;

-- 2) 직원의 사번, 이름, 부서명, 직급 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE);

-- 3) 사수에 해당하는 직원에 대한 정보 추출 조회(이 때, 구분은 '사수'로)
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사수' 구분
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE EMP_ID IN (SELECT DISTINCT(MANAGER_ID)
                  FROM EMPLOYEE
                  WHERE MANAGER_ID IS NOT NULL);

-- 4) 일반 직원에 해당하는 사원들 정보 조회 (이때, 구분은 '사원'으로)
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사원' 구분
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE EMP_ID NOT IN (SELECT DISTINCT(MANAGER_ID)
                      FROM EMPLOYEE
                      WHERE MANAGER_ID IS NOT NULL);            
                        
-- 5) 3, 4의 조회 결과를 하나로 합침 -> UNION
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사수' 구분
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE EMP_ID IN (SELECT DISTINCT(MANAGER_ID)
                  FROM EMPLOYEE
                  WHERE MANAGER_ID IS NOT NULL)

UNION

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사원' 구분
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE EMP_ID NOT IN (SELECT DISTINCT(MANAGER_ID)
                      FROM EMPLOYEE
                      WHERE MANAGER_ID IS NOT NULL);    

-- 6) 3, 4의 조회 결과를 하나로 합침 ->SUBQUERY
-- SELECT 절에서도 서브쿼리 사용 가능
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME,
CASE
WHEN EMP_ID IN (SELECT DISTINCT(MANAGER_ID)
                  FROM EMPLOYEE
                  WHERE MANAGER_ID IS NOT NULL)
            THEN '사수'
ELSE '사원'
END 구분
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE);




-- 예제 2-3
-- 대리 직급의 직원들 중에서 과장 직급의 최소 급여보다 많이 받는 직원의
-- 사번, 이름, 직급, 급여를 조회하세요
-- 단, > ANY 혹은 < ANY 연산자를 사용하세요


-- 1) 직급이 대리인 직원들의 사번, 이름, 직급명, 급여 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
LEFT JOIN JOB USING (JOB_CODE)
WHERE JOB_NAME = '대리';

-- 2) 직급이 과장인 직원들 급여 조회
SELECT SALARY
FROM EMPLOYEE
LEFT JOIN JOB USING (JOB_CODE)
WHERE JOB_NAME = '과장';

-- 3) 대리 직급의 직원들 중에서 과장 직급의 최소 급여보다 많이 받는 직원
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
LEFT JOIN JOB USING (JOB_CODE)
WHERE JOB_NAME = '대리'
AND SALARY < ANY (SELECT SALARY
                  FROM EMPLOYEE
                  LEFT JOIN JOB USING (JOB_CODE)
                  WHERE JOB_NAME = '과장');

-- 이때 서브쿼리를 SELECT MIN(SALARY)로 작성해도 값은 나오지만
-- 이것은 단일행 서브쿼리이므로 연습을 위해 > ANY 사용함





-- 예제 2-4
-- 차장 직급 급여의 가장 큰 값보다 많이 받는 과장 직급의 직원
-- 사번, 이름, 직급, 급여를 조회하세요
-- 단, > ALL 혹은 < ALL 연산자를 사용하세요

---- 1) 과장 직급의 직원
--SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
--FROM EMPLOYEE
--LEFT JOIN JOB USING (JOB_CODE)
--WHERE JOB_NAME = '대리';
--
---- 2) 차장 직급의 급여
--SELECT SALARY
--FROM EMPLOYEE
--LEFT JOIN JOB USING (JOB_CODE)
--WHERE JOB_NAME = '차장';

-- 3) 차장 직급 급여의 가장 큰 값보다 많이 받는 과장 직급
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
LEFT JOIN JOB USING (JOB_CODE)
WHERE JOB_NAME = '과장'
AND SALARY > ALL (SELECT SALARY
                   FROM EMPLOYEE
                   LEFT JOIN JOB USING (JOB_CODE)
                   WHERE JOB_NAME = '차장');


-- ** 서브쿼리 중첩 사용[응용]

-- NATIONAL_CODE가 KO인 부서에서 근무하고 있는 직원의 
-- 모든 정보 조회

-- LOCATION 테이블에서 NATIONAL_CODE가 KO인 LOCAL_CODE 조회
SELECT LOCAL_CODE
FROM LOCATION
WHERE NATIONAL_CODE = 'KO';

-- DEPARTMENT 테이블에서 LOCATION_ID가 'L1'인 DEPT_ID
SELECT DEPT_ID
FROM DEPARTMENT
WHERE LOCATION_ID = (SELECT LOCAL_CODE
                     FROM LOCATION
                     WHERE NATIONAL_CODE = 'KO');

-- 최종적으로 부서가 한국인 곳에서 근무하고 있는 직원 정보 모두 조회
SELECT *
FROM EMPLOYEE
WHERE DEPT_CODE IN (SELECT DEPT_ID
                    FROM DEPARTMENT
                    WHERE LOCATION_ID = (SELECT LOCAL_CODE
                                          FROM LOCATION
                                          WHERE NATIONAL_CODE = 'KO'));


                              
                              
----------------------------------------------------------------------------------------------------------------------------------

-- 3.  다중열 서브쿼리
-- 서브쿼리 SELECT 절에 나열된 컬럼 수가 여러개인 서브 쿼리

-- 예제 3-1
-- 퇴사한 여직원과 같은 부서, 같은 직급에 해당하는
-- 사원의 이름, 직급, 부서, 입사일을 조회        

-- 1) 퇴사한 여직원 조회
SELECT EMP_NAME, JOB_CODE, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE ENT_YN = 'Y'
AND SUBSTR(EMP_NO,8,1) = '2';

-- 2) 퇴사한 여직원과 같은 부서, 같은 직급 (단일열 (=단일행) 표현 시 -> 하나의 컬럼만 비교)
SELECT EMP_NAME, JOB_CODE, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
-- 퇴사한 여직원과 같은 부서
WHERE DEPT_CODE = (SELECT DEPT_CODE
                    FROM EMPLOYEE
                    WHERE ENT_YN = 'Y'
                    AND SUBSTR(EMP_NO,8,1) = '2')
-- 퇴사한 여직원과 같은 직급
AND     JOB_CODE = (SELECT JOB_CODE
                    FROM EMPLOYEE
                    WHERE ENT_YN = 'Y'
                    AND SUBSTR(EMP_NO,8,1) = '2')
-- 현재 재직중인 직원 (이태림 제외)
AND ENT_YN = 'N';
             
-- 3) 퇴사한 여직원과 같은 부서, 같은 직급 (다중 열 서브쿼리)
SELECT EMP_NAME, JOB_CODE, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE (DEPT_CODE, JOB_CODE)/*이 둘이 하나의 비교할 컬럼이라는 명시 하기위해 괄호*/ =
(SELECT DEPT_CODE, JOB_CODE
 FROM EMPLOYEE
 WHERE ENT_YN = 'Y'
 AND SUBSTR(EMP_NO,8,1) = '2')
 
 AND ENT_YN = 'N';



             
----------------------------------------------------------------------------------------------------------------------------------        
           
-- 4. 다중행 다중열 서브쿼리
-- 서브쿼리의 조회 결과 행 수와, 컬럼 수가 여러개인 서브쿼리

-- 예제 4-1
-- 본인 직급의 평균 급여를 받고 있는 직원의
-- 사번, 이름, 직급, 급여를 조회하세요
-- 단, 급여와 급여 평균은 만원단위로 계산하세요 TRUNC(컬럼명, -5)      

-- 1) 급여를 200, 600만 받는 직원 (200만, 600만이 평균급여라 생각 할 경우)
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN ('2000000', '6000000');

-- 2) 직급별 평균 급여
SELECT JOB_CODE, TRUNC(AVG(SALARY),-4)
FROM EMPLOYEE
GROUP BY JOB_CODE;

-- 3) 본인 직급의 평균 급여를 받고 있는 직원
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE (JOB_CODE, SALARY) IN  (SELECT JOB_CODE, TRUNC(AVG(SALARY),-4)
                                            FROM EMPLOYEE
                                            GROUP BY JOB_CODE);


-- 이때 WHERE절의 (JOB_CODE, SALARY)에서 SALARY의 경우 서브쿼리의 원칙 중 타입과 개수만 맞으면 된다는 법칙 때문에
-- 굳이 형식 안맞춰도 된다는 것에 유의할 것!!


 
---------------------------------------------------------------------------------------------------------------------------------- 

-- 5. 상[호연]관 서브쿼리 (매우 어려움.. 이해 할것)
-- 상관 쿼리는 메인쿼리가 사용하는 테이블 값을 서브쿼리가 이용해서 결과를 만드는 서브쿼리
-- 메인 쿼리의 테이블 값이 변경되면 서브쿼리의 결과값도 바뀌게 됨
-- 속도적으로 굉장히 빠른 쿼리

-- 예제 5-1
-- 사수가 있는 직원의 사번, 이름, 부서명, 사수사번 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, MANAGER_ID
FROM EMPLOYEE E
WHERE EXISTS (SELECT EMP_ID
                FROM EMPLOYEE M
                WHERE E.MANAGER_ID = EMP_ID);
                -- E.MANAGER_ID가 EMP_ID에 존재하는 경우의 EMP_ID가 올라감..
                
-- 내가 나를, 즉 같은 테이블을 서브쿼리로 참조를 해야 할때 상관 쿼리를 주로 사용한다!


-- 예제 5-2
-- 직급별 급여 평균보다 급여를 많이 받는 직원의 
-- 이름, 직급코드, 급여 조회
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE E
WHERE SALARY > (SELECT AVG(SALARY)
                FROM EMPLOYEE M
                WHERE E.JOB_CODE = M.JOB_CODE);
--              E.JOB_CODE의 값 (EX: J1)과 같은 M.JOB_CODE들 (J1들)만 뽑아내서 새로운 테이블 생성
--              그 후 그 테이블의 SALARY 평균을 구하는 식인 것임...


---------------------------------------------------------------------------------------------------------------------------------- 

-- 6. 스칼라 서브쿼리
-- SELECT 절에 사용되는 서브쿼리
-- 결과로 1행만 반환
-- * SQL에서 단일 값을 '스칼라'라고 함


-- 예제 6-1
-- 사원명, 직급, 각 직원들이 속한 직급의 급여 평균 조회
SELECT EMP_NAME, JOB_CODE,
FLOOR((SELECT AVG(SALARY)
FROM EMPLOYEE M
WHERE E.JOB_CODE = M.JOB_CODE)) 직급평균
FROM EMPLOYEE E
GROUP BY EMP_NAME, JOB_CODE;

-- 이때 상관 쿼리나 스칼라 서브쿼리를 사용하는 이유는 JOIN이나 이런 것보다 한 30배정도 차이가 남
-- 그래서 사용해줌

---------------------------------------------------------------------------------------------------------------------------------- 

-- 7. 인라인 뷰(INLINE-VIEW)
-- 서브쿼리가 만든 결과의 집합 (RESULT SET)을 테이블 대신 사용

-- 예제 7-1 : 인라인뷰를 활용한 TOP-N분석
-- 전 직원 중 급여가 높은 상위 5명의
-- 순위, 이름, 급여 조회

-- * ROWNUM : 조회된 순서대로 1부터 번호를 매기는 컬럼
-- ROWNUM이 어떻게 출력되는지 번호 매기는 것 확인
SELECT ROWNUM, EMP_NAME, SALARY
FROM EMPLOYEE;

-- 급여 내림차순 정렬하여 번호 매기기
SELECT ROWNUM, EMP_NAME, SALARY
FROM EMPLOYEE
ORDER BY SALARY DESC;
--> ORDER BY의 해석 순서가 마지막이기 때문에 번호부터 매겨진 후 급여 내림차순으로 정렬됨

-- 서브쿼리를 이용하여 정렬 결과를 먼저 만든 후 ROWNUM을 이용하여 순서 매기기
-- 먼저 정렬 결과를 만들고
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
ORDER BY SALARY DESC;

-- ROWNUM으로 번호 매기기
SELECT ROWNUM, EMP_NAME, SALARY
FROM (SELECT EMP_NAME, SALARY
      FROM EMPLOYEE
      ORDER BY SALARY DESC)  --> '인라인뷰'
WHERE ROWNUM <= 5;
-- 이때 ROWNUM은 다른것들과 다르게 SELECT절에서 나오지만 WHERE절에 사용해도 해석 순서상 문제는 없다


-- 예제 7-2
-- 급여 평균이 3위 안에 드는 부서의 
-- 부서코드와 부서명, 평균급여를 조회

SELECT ROWNUM, DEPT_CODE, DEPT_TITLE, 평균급여 -- 이때 FLOOR(AVG(SALARY))라고 하면 안됨..
                                                -- 왜냐면 FROM이 새로 생성되었기 때문에
FROM 
    (SELECT DEPT_CODE, DEPT_TITLE, FLOOR(AVG(SALARY)) 평균급여
    FROM EMPLOYEE
    LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
    GROUP BY DEPT_CODE, DEPT_TITLE
    ORDER BY 평균급여 DESC)
WHERE ROWNUM <= 3;

---------------------------------------------------------------------------------------------------------------------------------- 


-- 8. WITH
-- 서브쿼리에 이름을 붙여주고 서브 쿼리 사용 시 이름을 불러 사용하게 함
-- 인라인뷰로 사용될 서브쿼리에 주로 사용됨.
-- 실행 속도가 빨라지고, 메인 쿼리가 간결해지는 장점이 있음


-- 예제 8-1
-- 전 직원의 급여 순위 
-- 순위, 이름, 급여 조회
WITH TOPN_SAL AS (SELECT EMP_NAME, SALARY
                  FROM EMPLOYEE
                  ORDER BY SALARY DESC) -- 세미콜론(;) 없음

SELECT ROWNUM, EMP_NAME, SALARY
FROM TOPN_SAL;

---------------------------------------------------------------------------------------------------------------------------------- 

-- 9. RANK() OVER / DENSE_RANK() OVER
-- // ROWNUM처럼 순위 매기는 기능 , IN-LINE VIEW 따로 안해줘도 됨


--  RANK() OVER : 동일한 순위 이후의 등수를 동일한 인원 수 만큼 건너 뛰고 계산
--  EX) 공동 1위 2명 --> 1등, 1등, 그 다음은 3등... 이런식

-- 전 직원 급여 순위
SELECT EMP_NAME, SALARY,
        RANK() OVER(ORDER BY SALARY DESC) AS 순위
FROM EMPLOYEE;
-- 19, 19, 21

-- DENSE_RANK() OVER : 동일한 순위 이후의 등수를 건너뛰지 않고 차례대로 증가
SELECT EMP_NAME, SALARY,
        DENSE_RANK() OVER(ORDER BY SALARY DESC) AS 순위
FROM EMPLOYEE;
-- 19, 19, 20

