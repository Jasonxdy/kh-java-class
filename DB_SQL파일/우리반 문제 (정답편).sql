-- 회사에서 이번달 생일자들에게 비행기 티켓을 주기로 한다
-- (단, 생일자 중 연봉이 제일 높은 1명에게는 주지 않는다)
-- 비행기 티켓 발급 대상자의
-- 사원번호, 사원명, 생일날짜, 부서명, 직급명, 연봉(보너스 포함)을 조회하시오
-- (단, 생일날짜는 [OO월 OO일]로 표시하고 연봉은 [000,000,000] 3자리수 마다 ','표시) (조유상)

SELECT EMP_ID 사원번호, EMP_NAME 사원명,
    TO_CHAR(TO_DATE(SUBSTR(EMP_NO, 1, 6), 'RRMMDD'), 'MM"월" DD"일"') 생일날짜,
    DEPT_TITLE 부서명, JOB_NAME 직급명,
    TO_CHAR((SALARY + SALARY * NVL(BONUS, 0)) * 12, '999,999,999') 연봉
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
JOIN JOB USING(JOB_CODE)
WHERE (SALARY + SALARY * NVL(BONUS, 0)) * 12 
       NOT IN(SELECT MAX((SALARY + SALARY * NVL(BONUS, 0)) * 12)
              FROM EMPLOYEE
              WHERE EXTRACT(MONTH FROM TO_DATE(SUBSTR(EMP_NO, 1, 6), 'RRMMDD'))
                  = EXTRACT(MONTH FROM SYSDATE))
AND EXTRACT(MONTH FROM TO_DATE(SUBSTR(EMP_NO, 1, 6), 'RRMMDD'))
    = EXTRACT(MONTH FROM SYSDATE);
    
    
--EMPLOYEE 테이블에서 
--중국에서 일하는 60년대생 직원의 
--사번, 이름, 나이, 직급을 나이 많은 순으로 조회
--(헤더는 '사번', '이름', '나이', '직급'으로 표시) (양서원)

SELECT EMP_ID 사번, EMP_NAME 이름, EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO,1,2),'RR'))+1 나이, JOB_NAME 직급
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
JOIN NATIONAL USING (NATIONAL_CODE)
WHERE NATIONAL_NAME = '중국'
AND SUBSTR(EMP_NO,1,2) BETWEEN 60 AND 69
ORDER BY 나이 DESC;


-- 직급이 대리인 사원들의 평균 급여보다 적은(미만) 급여를 받으면서  
-- 2000년 이전에 입사한 사원들의 사번, 이름, 급여( 2,000,000원 으로 표기), 입사일 조회
-- 출력 헤더는 "사번", "사원명", "급여(원)", "입사일"로 표시되도록 한다 (강정임)
SELECT EMP_ID 사번, EMP_NAME 사원명, TO_CHAR(SALARY, '999,999,999' ) || '원' "급여(원)", HIRE_DATE 입사일
FROM EMPLOYEE
WHERE SALARY < (SELECT AVG(SALARY)
                            FROM EMPLOYEE
                            JOIN JOB USING(JOB_CODE)
                            WHERE JOB_NAME = '대리')
AND HIRE_DATE < '2000/01/01';




-- 일본에서 근무하고 있는 직원들 중 
-- 남자 사원들의 사번, 이름, 지역코드, 부서명, 직급명, 월급을 조회하시오
-- 단, 월급은 보너스가 반영된 '\999,999,999'의 형태로 조회할 것 (김지원)

SELECT EMP_ID 사번, EMP_NAME 이름, LOCAL_CODE 지역코드, DEPT_TITLE 부서명, JOB_NAME 직급명, 
       TO_CHAR((SALARY+SALARY*NVL(BONUS,0)), 'L999,999,999') 보너스반영월급
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING(JOB_CODE)
JOIN LOCATION ON(LOCATION_ID = LOCAL_CODE)
WHERE NATIONAL_CODE = (SELECT NATIONAL_CODE
                       FROM NATIONAL
                       WHERE NATIONAL_NAME = '일본')
      AND SUBSTR(EMP_NO, 8, 1) = '1';
      

-- 11월에 태어난 여자 사원과 동일한 직급이면서 동일한 사수를 가진 사원의
-- 사번, 이름, 직급명, 사수번호, 주민번호, 고용일을 조회하시오. (김희원)
SELECT EMP_ID, EMP_NAME, JOB_NAME, MANAGER_ID, EMP_NO, HIRE_DATE
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE (JOB_CODE, MANAGER_ID) IN
                (SELECT JOB_CODE, MANAGER_ID
                 FROM EMPLOYEE
                 WHERE SUBSTR(EMP_NO,3,2) = 11
                 AND SUBSTR(EMP_NO,8,1) = 2)
ORDER BY EMP_ID;



-- 전 직원의 평균 급여보다 적은 급여를 받고 러시아에서 일하고 있는 직원의 
-- 사번, 이름, 부서명 ,지역명, 국가명 , 급여조회
-- 단, 급여의 내림차순으로 정렬하시오 (신덕수)

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, LOCAL_NAME, NATIONAL_NAME, SALARY
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
JOIN NATIONAL USING (NATIONAL_CODE)
WHERE SALARY <= (SELECT AVG(SALARY) FROM EMPLOYEE)
AND NATIONAL_NAME = '러시아'
ORDER BY SALARY DESC;


-- 직급별 80년대생 사원들 중 가장 어린 사원의
-- 사번, 이름, 주민번호, 나이, 부서명(NULL이면 '소속없음'), 직급명, 입사일, 보너스 포함 연봉을 조회하고
-- 직급을 오름차순으로 출력하세요.
-- 단, 출력 헤더는 '사번', '사원이름', '주민번호', '나이', '부서명', '직급명', '입사일', '연봉' 으로 한다.
-- 그리고 주민번호의 뒷자리는 성별을 표시하는 것 빼고는 *로 표현하시오.
-- 그리고 연봉은 \124,800,000 으로 출력되게 하세요. (\ : 원 단위 기호)  (이혜선)

SELECT EMP_ID 사번, EMP_NAME 사원이름, SUBSTR(EMP_NO,1, 8) || '******' AS 주민번호,
                 EXTRACT(YEAR FROM SYSDATE) -
                     (EXTRACT(YEAR FROM (TO_DATE(SUBSTR(EMP_NO, 1, 2),'RR')))) 나이,
                NVL(DEPT_TITLE, '소속없음') 부서명, 
               JOB_NAME 직급명, HIRE_DATE 입사일,
               TO_CHAR((SALARY + ( SALARY * NVL(BONUS , 0))) * 12, 'L999,999,999') AS 연봉
FROM EMPLOYEE E
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE)
WHERE EMP_NO = (SELECT MAX(EMP_NO)
                            FROM EMPLOYEE M
                            WHERE E.JOB_CODE = M.JOB_CODE
                            AND EMP_NO LIKE '8%')
ORDER BY J.JOB_CODE ASC;


--KH 대학교 수학과에서 학술 동아리를 만들었다.
--동아리 가입 요건은 다음과 같다.
--- 1. 수학과 학생만 가입 가능하다.
--- 2. 2000년 학번 이후부터 가입 가능하다.
--- 3. 학점 평균이 3.0 이상이어야한다.
--- 4. 재학중이어야한다.
--수학과 모든 학생 중 가입이 가능한 학생들의
--학번, 이름, 평점, 입학년도를 조회하시오.(서진웅)
SELECT STUDENT_NO 학번, STUDENT_NAME 이름, TRUNC(AVG(POINT),8) 평점, ENTRANCE_DATE 입학년도
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
JOIN TB_GRADE USING(STUDENT_NO)
WHERE ABSENCE_YN = 'N' AND DEPARTMENT_NAME ='수학과'
AND ENTRANCE_DATE >= '00/01/01'
GROUP BY STUDENT_NO, STUDENT_NAME, ENTRANCE_DATE
HAVING AVG(POINT)>= 3.0
ORDER BY 1;



-- 같은 년도에 입사를 한 사람들 중 에서
-- 직급이 가장 높은 사람의 사번, 이름, 입사년도, 부서코드, 부서명 조회
-- 단 2000년도 이후의 입사자들만 조회
-- 입사년도 오름차순 정렬 (윤소희)
SELECT EMP_ID 사번, EMP_NAME 사원명, EXTRACT(YEAR FROM HIRE_DATE) 입사년도 ,MIN(JOB_CODE) 부서코드 ,JOB_NAME 부서명
FROM EMPLOYEE 
JOIN JOB USING (JOB_CODE)
GROUP BY EMP_ID,EMP_NAME,EXTRACT(YEAR FROM HIRE_DATE),JOB_NAME
HAVING EXTRACT(YEAR FROM HIRE_DATE) >= '2000'
ORDER BY 입사년도;





-- 신입사원 채용을 위해 회사에서 
-- 각 부서별 가장 직급이 높은 사원의 급여를 10% 삭감하여 
-- 인건비를 줄이기로 결정했다. 퇴사하지 않은
-- 가장 직급이 높은 사원의 사번, 이름, 부서명, 직급명, 삭감된 급여, 
--  보너스 포함 연봉을 조회하시오.
--  (만약, 부서명이 존재하지 않는 경우 '미지정'으로 출력) (최유리)
SELECT EMP_ID 사번, EMP_NAME 사원명, NVL(DEPT_TITLE,'미지정') 부서명, JOB_NAME 직급명, 
SALARY*0.9 AS "삭감된 급여",
(SALARY*0.9)*(1+NVL(BONUS,0)) AS "보너스 포함 연봉"
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
LEFT JOIN JOB USING (JOB_CODE)
WHERE (DEPT_CODE, JOB_CODE) IN (SELECT DEPT_CODE, MIN(JOB_CODE)
                                            FROM EMPLOYEE
                                            GROUP BY DEPT_CODE)
AND ENT_YN = 'N';



-- 퇴직한 여성과 같은 부서에서 근무하고 있으면서
-- NATIONAL_CODE가 EU인 직원들의
-- 사번, 이름, 부서명, 직급명, 급여(순위를 매겨서)를 나타내시오. (박준건)
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, SALARY, RANK()OVER(ORDER BY SALARY DESC) AS 순위
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
JOIN JOB USING (JOB_CODE)
WHERE DEPT_CODE = (SELECT DEPT_CODE
                                FROM EMPLOYEE
                                WHERE ENT_YN = 'Y')
AND NATIONAL_CODE = (SELECT NATIONAL_CODE
                                FROM LOCATION
                                JOIN NATIONAL USING (NATIONAL_CODE)
                                WHERE NATIONAL_NAME = '러시아')
AND ENT_YN = 'N'                                
ORDER BY SALARY desc; 



--전형돈 사원이 고용일로부터 지금까지 쌓인 퇴직금을 이번달 말 기준으로 미리 정산받겠다고 한다.
--    전형돈 사원이 근무하는 지역의 은행으로 송금해야 하는데, 
--    전형돈 사원이 근무하는 지역과 퇴직금을 계산하여 조회하시오.
--    (사번,이름,직급명,고용일,연봉,근무일수,퇴직금,근무지역 조회)
--    (단, 1.연간 상여금은 보너스로 대체 
--        2.연차수당 및 각종 수당 제외, 세금은 생각하지 X
--        3.연봉과 퇴직금은 원화단위로 조회)
--    ***퇴직금 = 1일평균임금 * 30일 * (근무일수/365)***
--(조미현)
SELECT EMP_ID,EMP_NAME,JOB_NAME,HIRE_DATE,
TO_CHAR((SALARY*NVL(BONUS,0))+SALARY*12,'L999,999,999') 연봉,
FLOOR(MONTHS_BETWEEN(LAST_DAY(SYSDATE),HIRE_DATE)*30) 근무일수,
TO_CHAR(FLOOR(SALARY*(FLOOR(MONTHS_BETWEEN(LAST_DAY(SYSDATE),HIRE_DATE)*30)/365)),'L999,999,999')퇴직금,
LOCAL_NAME
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN LOCATION ON (LOCATION_ID=LOCAL_CODE)
WHERE EMP_NAME = '전형돈';


/*
 해외영업부에서 일하는 모든 직원들중에
 각 해외영업부(3개)의 평균 월급보다
 많은 월급을 받는 직원의 사번, 이름, 부서명, 연봉을 조회하시오.
 이때 출력헤더를 사번, 사원명, 부서, 연봉으로 설정한다.
 또한 연봉은 \123,456,000 으로 표시한다. (문영준)
*/
SELECT EMP_ID 사번, EMP_NAME 사원명, DEPT_TITLE 부서, TO_CHAR((SALARY + SALARY * NVL(BONUS,0)) * 12,'L999,999,999') 연봉
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY > ANY (SELECT AVG(SALARY)
                                 FROM DEPARTMENT
                                  JOIN EMPLOYEE ON (DEPT_ID = DEPT_CODE)
                                  GROUP BY DEPT_TITLE)
AND DEPT_TITLE LIKE '해외%';



--회사의 입사자 중 연락이 닿지 않는 사람의 
--이름, 부서명, 직급, 연봉을 조회해라.
--단, 조회된 사람의 연봉 10%를 삭감하고, \ 단위로 표시할 것
-- 연봉 컬럼을 ' 삭감된 연봉' 으로 표시할 것 (김태균)
SELECT EMP_NAME, DEPT_TITLE, JOB_NAME,
TO_CHAR( (SALARY +  (SALARY* NVL(BONUS,0) ) ) *0.9 , 'L999,999,999') 
 AS "삭감된 연봉"
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE)
WHERE PHONE IS NULL;


-- 2007년에 지도교수 별 학생들의 학점 평균을 조회하라 (지도교수 없는 학생 포함)
-- 별칭: 지도교수, 학생 평점  
-- 평점은 소수점 셋째자리에서 반올림. 학생 평점 내림차순 정렬.  (김인호)
SELECT PROFESSOR_NAME 지도교수,
TO_CHAR(ROUND(AVG(POINT),2), '0.00') "학생 평점"
FROM TB_STUDENT
LEFT JOIN TB_PROFESSOR ON (COACH_PROFESSOR_NO = PROFESSOR_NO)
JOIN TB_GRADE USING (STUDENT_NO)
WHERE SUBSTR(TERM_NO, 1, 4) = 2007
GROUP BY PROFESSOR_NAME
ORDER BY 2 DESC;


-- EMPLOYEE 테이블에서 태어난 날짜가 5일과 8일인 사원의
-- 사번, 이름, 생일, 사수 사번, 사수 이름을 조회 
-- 이 때, 사수가 없는 경우 사수 사번과 이름을 '없음 ' 으로 표시
-- 사번 오름차순으로 정렬 (김별하 )
SELECT E.EMP_ID 사번, E.EMP_NAME 이름, SUBSTR(E.EMP_NO,5,2) 생일,
            NVL(E.MANAGER_ID, '없음') "사수 사번", NVL(M.EMP_NAME, '없음') "사수 이름"
FROM EMPLOYEE E
LEFT JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID)
WHERE SUBSTR(E.EMP_NO,5,2) IN ('05','08')
ORDER BY E.EMP_ID ASC;


-- 아시아 권에 나라에 속한 부서들의
-- 부서별 급여가 제일 높은 사원의 사원 이름과, 부서명, 급여, 나라 이름을 조회하시오
-- 단 부서코드가 없는 사원들 중 급여가 제일 높은 사원은 
-- 나라 이름과 부서명을 '미지정' 으로 표시하여 조회 할 것
-- * 컬럼명은 이름, 부서명, 급여, 나라 로 표시 (김태훈)
SELECT EMP_NAME 이름, NVL(DEPT_TITLE, '미지정') 부서명, SALARY 급여,
NVL(NATIONAL_NAME,'미지정') 나라
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
LEFT JOIN LOCATION ON(LOCATION_ID = LOCAL_CODE)
LEFT JOIN NATIONAL USING(NATIONAL_CODE)
WHERE (LOCAL_NAME LIKE 'ASIA%'
AND (DEPT_CODE, SALARY) IN (
    SELECT DEPT_CODE, MAX(SALARY)
    FROM EMPLOYEE
    GROUP BY DEPT_CODE
))
OR (LOCAL_NAME IS NULL AND SALARY = (
    SELECT MAX(SALARY)
    FROM EMPLOYEE
    WHERE DEPT_CODE IS NULL
 ));
 
 
 --직급이 과장인 남자 직원들보다 
--높은 급여를 받고 있는 여자 직원들의 이름, 부서명, 직급명, 급여 정보를
--급여별 내림차순으로 조회  (최원지_
SELECT EMP_NAME, DEPT_TITLE, JOB_NAME, SALARY
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
WHERE SUBSTR(EMP_NO,8,1) = '2'
AND SALARY >ALL (SELECT SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE 
SUBSTR(EMP_NO,8,1) = '1' AND JOB_NAME = '과장'
)
GROUP BY  EMP_NAME, DEPT_TITLE,JOB_NAME, SALARY
ORDER BY SALARY DESC;



-- 현재 재직중인 직원들중에서 부서가 '해외영업 2부'인 직원들 의 평균 총수령액 보다 적게 받는 직원들중
-- 입사한 달이 4월인 사원의 사번, 이름 구하시오
-- 해외영업2부 직원들 제외 (한송희)
SELECT EMP_ID, EMP_NAME
FROM EMPLOYEE
WHERE  (SALARY*12) + (SALARY*12*NVL(BONUS,0) ) <  (
                SELECT AVG( (SALARY*12) + (SALARY*12*NVL(BONUS,0) ) )
                FROM EMPLOYEE
                JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
                JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
                WHERE NATIONAL_CODE = 'CH' 
                ) AND DEPT_CODE <> (
                SELECT DISTINCT(DEPT_CODE)
                FROM EMPLOYEE
                JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
                JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
                WHERE NATIONAL_CODE = 'CH' 
                    
                )
AND ENT_DATE IS NULL
AND SUBSTR(HIRE_DATE,4,2)  = '04';


-- 여자 직원들 중 보너스를 포함한 연봉의 금액이 가장 큰 직원보다
-- 보너스를 포함한 연봉 연봉을 많이 받는 남자 직원의 사번 이름 연봉 부서명 직급명을 조회(전지혜)
SELECT EMP_ID, EMP_NAME, (SALARY + SALARY * NVL(BONUS, 0)) *12
보너스포함연봉, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE SUBSTR(EMP_NO, 8, 1) = 1 AND
(SALARY + SALARY * NVL(BONUS, 0)) *12 >=
(SELECT MAX((SALARY + SALARY * NVL(BONUS, 0)) *12)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) = 2);
 
 
 
--부서별 최대 급여를 받는 직원의 이름,나이,부서명,직급명, 연봉(= 급여 + (급여*보너스))*12) 조회)
--- 정렬은 연봉이 높은 순서대로 정렬할 것.
--- 부서코드가 NULL인 경우 소속없음으로 표시할것 (정승환)
SELECT EMP_NAME AS 이름, NVL(DEPT_TITLE,'소속없음') AS 부서명, 
            EXTRACT(YEAR FROM SYSDATE)
- EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO,1,2),'RR')) AS 나이,
            JOB_NAME AS 직급,
            (SALARY + (SALARY*NVL(BONUS,0)))*12 AS 연봉
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE SALARY IN (SELECT MAX(SALARY)
                 FROM EMPLOYEE
                 GROUP BY DEPT_CODE)
ORDER BY 연봉 DESC;



-- 각 부서별 최대급여를 받는 여성사원의
-- 사번, 직원명, 부서명, 직급명, 나이를 조회하고
-- 급여가 가장 높은순으로 조회하세요 (조은희)
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME,
       EXTRACT(YEAR FROM SYSDATE)
       - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO,1,2),'RR')) 나이
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE SALARY IN (SELECT MAX(SALARY)
                 FROM EMPLOYEE
                 WHERE SUBSTR(EMP_NO,8,1)='2'
                 GROUP BY DEPT_CODE);    
      
                 
--본인이 사수 이면서 사수가 있는 직원과 같은 근무지역의 
--임금이 최 상위자 의
--사번 이름  부서명 임금, 근무 지역을 조회 (이훈)
SELECT EMP_ID , EMP_NAME, SALARY,DEPT_TITLE , LOCAL_NAME
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
JOIN LOCATION ON  (LOCATION_ID = LOCAL_CODE)
WHERE (SALARY , LOCAL_NAME) IN (

    SELECT  MAX(SALARY)  , LOCAL_NAME
    FROM EMPLOYEE
    LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    JOIN JOB USING (JOB_CODE)
    JOIN LOCATION ON  (LOCATION_ID = LOCAL_CODE)
    WHERE  LOCAL_NAME IN (
    
        SELECT LOCAL_NAME
        FROM EMPLOYEE E
        LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
        JOIN JOB USING (JOB_CODE)
        JOIN LOCATION ON  (LOCATION_ID = LOCAL_CODE)
        WHERE  EXISTS (
                SELECT EMP_ID
                FROM EMPLOYEE M
                WHERE E.EMP_ID = M.MANAGER_ID)
        )AND MANAGER_ID IS NOT NULL
        GROUP BY LOCAL_NAME
);


-- 2001년에 입사하고 사원번호가 홀수인 사원 중에서
-- 급여가 가장 높은 사원의 사원번호, 이름, 입사일, 근무지역명, 급여를 표시하는 SQL문 작성 (윤희빈)
SELECT EMP_ID, EMP_NAME, HIRE_DATE, LOCAL_NAME, SALARY
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
WHERE SALARY =( SELECT MAX(SALARY) 
                            FROM EMPLOYEE
                            WHERE MOD(TO_NUMBER(EMP_ID), 2) = 1
                            GROUP BY HIRE_DATE
                            HAVING EXTRACT(YEAR FROM HIRE_DATE) = '2001');



--EMPLOYEE 테이블에서 부서명, 해당부서의 남자 직원 수("남자 직원수"), 여자 직원 수("여자 직원수")를 조회하여라.
--(이때 부서가 없는 경우에는 '소속 없음'으로 표기하며, 직원 수는 '명'으로 표기할 것) (조동영)
SELECT NVL(DEPT_TITLE, '소속 없음'),
    (SELECT COUNT(E1.EMP_ID)
    FROM EMPLOYEE E1
    WHERE SUBSTR(E1.EMP_NO,8,1) = '1'
    AND (E3.DEPT_CODE = E1.DEPT_CODE
    OR (E1.DEPT_CODE IS NULL AND E3.DEPT_CODE IS NULL))
    )|| '명' "남자 직원 수",
        
    (SELECT COUNT(E2.EMP_ID)
    FROM EMPLOYEE E2
    WHERE SUBSTR(E2.EMP_NO,8,1) = '2'
    AND (E3.DEPT_CODE = E2.DEPT_CODE
     OR (E2.DEPT_CODE IS NULL AND E3.DEPT_CODE IS NULL))
     )|| '명' "여자 직원 수"
    
FROM EMPLOYEE E3
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
GROUP BY DEPT_CODE, NVL(DEPT_TITLE, '소속 없음');



--기업 KH그룹이 창립이후 한번도 휴가를 사용하지 못하게 하였다는
--신고가 들어왔다. 이에 경영진은 휴가 또는 휴가 비용을 지불하는 것으로
--내부 협의를 진행하였다.
--직원들의 직원이름 밀린휴가일과 휴가 비용을 구하시오
--휴가일은 15일을 기준으로 하루씩 생긴다고하며
--여성들에게는 생리휴가로 매달 1일이 추가가 된다.
--ASIA1, 2 이외의 근무자들은 15일에 2일씩 휴가가 생긴다.
--휴가 비용은 월급 * BONUS 이며 BONUS가 0.2 이하인 경우 0.2로 통일하여 일괄 지급한다.
--휴가비용은 내림차순으로 정렬
--출력시 직원이름, 쌓인휴가일, 휴가비용으로 표시되게 하시오
-- (원태연)
SELECT EMP_NAME 직원이름, 근무지별휴가+여성휴가 쌓인휴가일, TO_CHAR((SALARY*휴가일),'L999,999,999,999') 휴가비용
FROM ( SELECT EMP_NAME,
         TRUNC(ABS(SYSDATE-TO_DATE(HIRE_DATE))/15,0) 휴가일 ,
         CASE WHEN NATIONAL_CODE IN ('KO','JP') THEN TRUNC(ABS(SYSDATE-TO_DATE(HIRE_DATE))/15,0)
         ELSE TRUNC(ABS(SYSDATE-TO_DATE(HIRE_DATE))/15,0)*2
END 근무지별휴가
,
CASE WHEN TO_CHAR(SUBSTR(EMP_NO,8,1)) = '2' THEN TRUNC(MONTHS_BETWEEN(SYSDATE,HIRE_DATE),0)
ELSE 0 END 여성휴가
,
CASE WHEN NVL(BONUS,0) <= 0.2 THEN 0.2
ELSE BONUS END 보너스
,SALARY
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
LEFT JOIN LOCATION ON (LOCAL_CODE=LOCATION_ID)
)
ORDER BY 휴가비용 DESC
;


--  직원들 중 자신보다 직급이 낮은 직원을 사수로 두고있는 직원의 이름, 급여, 부서, 직급을 출력하고
--  위 직원들의 사수로 설정된 직원의 이름, 급여, 부서, 직급을 출력하시오 (박지현)
SELECT EMP_NAME 이름,SALARY 급여, DEPT_TITLE 부서, JOB_NAME 직급,
    (SELECT EMP_NAME
    FROM EMPLOYEE M
    WHERE E.MANAGER_ID = M.EMP_ID) AS 사수,
    (SELECT SALARY
    FROM EMPLOYEE M
    WHERE E.MANAGER_ID = M.EMP_ID) AS 사수급여,
    (SELECT DEPT_TITLE
    FROM EMPLOYEE M
    LEFT JOIN DEPARTMENT ON (M.DEPT_CODE = DEPT_ID)
    WHERE E.MANAGER_ID = M.EMP_ID) AS 사수부서,
    (SELECT JOB_NAME
    FROM EMPLOYEE M
    JOIN JOB USING(JOB_CODE)
    WHERE E.MANAGER_ID = M.EMP_ID) AS 사수직급
FROM EMPLOYEE E
LEFT JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
JOIN JOB USING(JOB_CODE)
WHERE SUBSTR(JOB_CODE,2,1) < NVL((
    SELECT SUBSTR(JOB_CODE,2,1)
    FROM EMPLOYEE M
    WHERE E.MANAGER_ID = M.EMP_ID),0);
    
    

--70년대 생이며, 아시아이외지역에서 근무하는 직원의 
--사번, 나이, 이름, 직급명, 보너스포함 연봉, 근무지역 을 조회하시오 (안중하)
SELECT  EMP_ID, 
    EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO, 1, 2), 'RR')) + 1 나이,
    EMP_NAME, JOB_NAME,
    TO_CHAR((SALARY + SALARY*NVL(BONUS,0))*12, 'L999,999,999')
    AS 보너스포함연봉, LOCAL_NAME AS 근무지역
FROM EMPLOYEE
LEFT JOIN JOB USING (JOB_CODE)
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON ( LOCAL_CODE = LOCATION_ID)
WHERE (SUBSTR(EMP_NO,1,2), LOCAL_NAME) IN 
                ( SELECT SUBSTR(EMP_NO,1,2) AS 출생년도, LOCAL_NAME
                FROM EMPLOYEE
                LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
                JOIN LOCATION ON ( LOCAL_CODE = LOCATION_ID)
                WHERE (SUBSTR(EMP_NO,1,2) BETWEEN '70' AND '79') AND
                LOCAL_NAME NOT LIKE '%ASIA%'
                AND ENT_YN = 'N');
                
                
-- 각 학과별 최고 평점 (4년 평균)을 가진 사람의 이름, 학과이름, 평점을 조회  (정환조)    
SELECT STUDENT_NAME, DEPARTMENT_NAME ,평점
FROM (SELECT  DEPARTMENT_NAME,STUDENT_NAME, AVG(POINT) AS "평점"
            FROM TB_STUDENT
            JOIN TB_GRADE USING (STUDENT_NO)
            JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
            GROUP BY  STUDENT_NAME, DEPARTMENT_NAME)
WHERE (DEPARTMENT_NAME,평점) IN (SELECT DEPARTMENT_NAME ,MAX(평점)
                        FROM (SELECT  DEPARTMENT_NAME,STUDENT_NAME, AVG(POINT) AS "평점"
                         FROM TB_STUDENT
                         JOIN TB_GRADE USING (STUDENT_NO)
                       JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
                         GROUP BY  STUDENT_NAME, DEPARTMENT_NAME)
                        GROUP BY DEPARTMENT_NAME);