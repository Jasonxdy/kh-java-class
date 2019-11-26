-- 회사에서 이번달 생일자들에게 비행기 티켓을 주기로 한다
-- (단, 생일자 중 연봉이 제일 높은 1명에게는 주지 않는다)
-- 비행기 티켓 발급 대상자의
-- 사원번호, 사원명, 생일날짜, 부서명, 직급명, 연봉(보너스 포함)을 조회하시오
-- (단, 생일날짜는 [OO월 OO일]로 표시하고 연봉은 [000,000,000] 3자리수 마다 ','표시) (조유상)


SELECT EMP_ID, EMP_NAME, 생일, DEPT_TITLE, JOB_NAME, 연봉
FROM (SELECT EMP_ID, EMP_NAME, TO_CHAR(TO_DATE(SUBSTR(EMP_NO,1,6), 'RRMMDD'), 'MM"월" DD"일"') 생일,
        DEPT_TITLE, JOB_NAME, TO_CHAR(SALARY * (1+ NVL(BONUS,0)) * 12, 'L999,999,999') 연봉
        FROM EMPLOYEE
        LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
        JOIN JOB USING (JOB_CODE)
        WHERE EXTRACT (MONTH FROM SYSDATE) = SUBSTR(EMP_NO,3,2)
        ORDER BY TO_CHAR(SALARY * (1+ NVL(BONUS,0)) * 12, 'L999,999,999') ASC)
WHERE ROWNUM <= 3
ORDER BY 연봉 DESC;
    
--EMPLOYEE 테이블에서 
--중국에서 일하는 60년대생 직원의 
--사번, 이름, 나이, 직급을 나이 많은 순으로 조회
--(헤더는 '사번', '이름', '나이', '
JOIN JOB USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
JOIN NATIONAL USING (NATIONAL_CODE)
WHERE NATIONAL_NAME = '중국'
AND EMP_NO LIKE '6%';


-- 직급이 대리인 사원들의 평균 급여보다 적은(미만) 급여를 받으면서  
-- 2000년 이전에 입사한 사원들의 사번, 이름, 급여( 2,000,000원 으로 표기), 입사일 조회
-- 출력 헤더는 "사번", "사원명", "급여(원)", "입사일"로 표시되도록 한다 (강정임)
SELECT EMP_ID 사번, EMP_NAME 사원명, TO_CHAR(SALARY, '9,999,999') "급여(원)", HIRE_DATE 입사일
FROM EMPLOYEE
WHERE SALARY < (SELECT AVG(SALARY)
                FROM EMPLOYEE
                LEFT JOIN JOB USING (JOB_CODE)
                WHERE JOB_NAME = '대리')
AND HIRE_DATE < TO_DATE('00/01/01', 'RR/MM/DD');
-- AND HIRE_DATE < '2000/01/01';



-- 일본에서 근무하고 있는 직원들 중 
-- 남자 사원들의 사번, 이름, 지역코드, 부서명, 직급명, 월급을 조회하시오
-- 단, 월급은 보너스가 반영된 '\999,999,999'의 형태로 조회할 것 (김지원)
SELECT EMP_ID, EMP_NAME, LOCAL_CODE, DEPT_TITLE, JOB_NAME, TO_CHAR(SALARY*(1+NVL(BONUS,0)), 'L999,999,999')
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
JOIN NATIONAL USING (NATIONAL_CODE)
WHERE NATIONAL_NAME = '일본'
AND SUBSTR(EMP_NO,8,1) = '1';



-- 11월에 태어난 여자 사원과 동일한 직급이면서 동일한 사수를 가진 사원의
-- 사번, 이름, 직급명, 사수번호, 주민번호, 고용일을 조회하시오. (김희원)
SELECT EMP_ID, EMP_NAME, JOB_NAME, MANAGER_ID, EMP_NO, HIRE_DATE
FROM EMPLOYEE
LEFT JOIN JOB USING (JOB_CODE)
WHERE (JOB_CODE, MANAGER_ID) = (SELECT JOB_CODE, MANAGER_ID
                                FROM EMPLOYEE
                                WHERE SUBSTR(EMP_NO,3,2) = '11'
                                AND SUBSTR(EMP_NO,8,1) = '2');


-- 전 직원의 평균 급여보다 적은 급여를 받고 러시아에서 일하고 있는 직원의 
-- 사번, 이름, 부서명 ,지역명, 국가명 , 급여조회
-- 단, 급여의 내림차순으로 정렬하시오 (신덕수)

-- PASS



-- 직급별 80년대생 사원들 중 가장 어린 사원의
-- 사번, 이름, 주민번호, 나이, 부서명(NULL이면 '소속없음'), 직급명, 입사일, 보너스 포함 연봉을 조회하고
-- 직급을 오름차순으로 출력하세요.
-- 단, 출력 헤더는 '사번', '사원이름', '주민번호', '나이', '부서명', '직급명', '입사일', '연봉' 으로 한다.
-- 그리고 주민번호의 뒷자리는 성별을 표시하는 것 빼고는 *로 표현하시오.
-- 그리고 연봉은 \124,800,000 으로 출력되게 하세요. (\ : 원 단위 기호)  (이혜선)
SELECT EMP_ID 사번, EMP_NAME 이름, EMP_NO 주민번호, EXTRACT (YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO,1,2),'RR')) 나이,
NVL(DEPT_TITLE,'소속없음') 부서명, JOB_NAME 직급명, HIRE_DATE 입사일, SALARY * (1 + NVL(BONUS, 0)) * 12 연봉
FROM EMPLOYEE E
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE EMP_NO = (SELECT MAX(EMP_NO)
                FROM EMPLOYEE M
                WHERE (E.DEPT_CODE = M.DEPT_CODE
                OR (E.DEPT_CODE IS NULL AND M.DEPT_CODE IS NULL))
                AND M.EMP_NO LIKE '8%');


--KH 대학교 수학과에서 학술 동아리를 만들었다.
--동아리 가입 요건은 다음과 같다.
--- 1. 수학과 학생만 가입 가능하다.
--- 2. 2000년 학번 이후부터 가입 가능하다.
--- 3. 학점 평균이 3.0 이상이어야한다.
--- 4. 재학중이어야한다.
--수학과 모든 학생 중 가입이 가능한 학생들의
--학번, 이름, 평점, 입학년도를 조회하시오.(서진웅)
SELECT STUDENT_NO, STUDENT_NAME, TRUNC(AVG(POINT),8), ENTRANCE_DATE
FROM TB_STUDENT S
JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
JOIN TB_GRADE USING (STUDENT_NO)
WHERE DEPARTMENT_NAME = '수학과'
AND ENTRANCE_DATE >= '20000101'
AND ABSENCE_YN = 'N'
GROUP BY STUDENT_NO, STUDENT_NAME,ENTRANCE_DATE
HAVING AVG(POINT) >= 3.0;


-- 같은 년도에 입사를 한 사람들 중 에서
-- 직급이 가장 높은 사람의 사번, 이름, 입사년도, 부서코드, 부서명 조회
-- 단 2000년도 이후의 입사자들만 조회
-- 입사년도 오름차순 정렬 (윤소희)

SELECT EMP_ID, EMP_NAME, EXTRACT(YEAR FROM HIRE_DATE), DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE E
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE JOB_CODE = (SELECT MIN(JOB_CODE)
                    FROM EMPLOYEE J
                    JOIN JOB USING (JOB_CODE)
                    WHERE EXTRACT(YEAR FROM E.HIRE_DATE) = EXTRACT(YEAR FROM J.HIRE_DATE)
                    AND HIRE_DATE >= '20000101');
-- 일단 포기




-- 신입사원 채용을 위해 회사에서 
-- 각 부서별 가장 직급이 높은 사원의 급여를 10% 삭감하여 
-- 인건비를 줄이기로 결정했다. 퇴사하지 않은
-- 가장 직급이 높은 사원의 사번, 이름, 부서명, 직급명, 삭감된 급여, 
--  보너스 포함 연봉을 조회하시오.
--  (만약, 부서명이 존재하지 않는 경우 '미지정'으로 출력) (최유리)

SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '미지정'), JOB_NAME, SALARY * 0.9, SALARY * (1+NVL(BONUS,0)) * 12 연봉
FROM EMPLOYEE E
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE)
WHERE (DEPT_CODE, JOB_CODE) IN (SELECT DEPT_CODE, MIN(JOB_CODE)
                                FROM EMPLOYEE
                                JOIN JOB USING (JOB_CODE)
                                GROUP BY DEPT_CODE)
AND ENT_YN = 'N';





-- 퇴직한 여성과 같은 부서에서 근무하고 있으면서
-- NATIONAL_CODE가 EU인 직원들의
-- 사번, 이름, 부서명, 직급명, 급여(순위를 매겨서)를 나타내시오. (박준건)




-- 전형돈 사원이 고용일로부터 지금까지 쌓인 퇴직금을 이번달 말 기준으로 미리 정산받겠다고 한다.
--    전형돈 사원이 근무하는 지역의 은행으로 송금해야 하는데, 
--    전형돈 사원이 근무하는 지역과 퇴직금을 계산하여 조회하시오.
--    (사번,이름,직급명,고용일,연봉,근무일수,퇴직금,근무지역 조회)
--    (단, 1.연간 상여금은 보너스로 대체 
--        2.연차수당 및 각종 수당 제외, 세금은 생각하지 X
--        3.연봉과 퇴직금은 원화단위로 조회)
--    ***퇴직금 = 1일평균임금 * 30일 * (근무일수/365)***
--(조미현)




-- 해외영업부에서 일하는 모든 직원들중에
-- 각 해외영업부(3개)의 평균 월급보다
-- 많은 월급을 받는 직원의 사번, 이름, 부서명, 연봉을 조회하시오.
-- 이때 출력헤더를 사번, 사원명, 부서, 연봉으로 설정한다.
-- 또한 연봉은 \123,456,000 으로 표시한다. (문영준)



--회사의 입사자 중 연락이 닿지 않는 사람의 
--이름, 부서명, 직급, 연봉을 조회해라.
--단, 조회된 사람의 연봉 10%를 삭감하고, \ 단위로 표시할 것
-- 연봉 컬럼을 ' 삭감된 연봉' 으로 표시할 것 (김태균)



-- 2007년에 지도교수 별 학생들의 학점 평균을 조회하라 (지도교수 없는 학생 포함)
-- 별칭: 지도교수, 학생 평점  
-- 평점은 소수점 셋째자리에서 반올림. 학생 평점 내림차순 정렬.  (김인호)


-- EMPLOYEE 테이블에서 태어난 날짜가 5일과 8일인 사원의
-- 사번, 이름, 생일, 사수 사번, 사수 이름을 조회 
-- 이 때, 사수가 없는 경우 사수 사번과 이름을 '없음 ' 으로 표시
-- 사번 오름차순으로 정렬 (김별하 )


-- 아시아 권에 나라에 속한 부서들의
-- 부서별 급여가 제일 높은 사원의 사원 이름과, 부서명, 급여, 나라 이름을 조회하시오
-- 단 부서코드가 없는 사원들 중 급여가 제일 높은 사원은 
-- 나라 이름과 부서명을 '미지정' 으로 표시하여 조회 할 것
-- * 컬럼명은 이름, 부서명, 급여, 나라 로 표시 (김태훈)

 

--직급이 과장인 남자 직원들보다 
--높은 급여를 받고 있는 여자 직원들의 이름, 부서명, 직급명, 급여 정보를
--급여별 내림차순으로 조회  (최원지_
SELECT EMP_NAME, DEPT_TITLE, JOB_NAME, SALARY



-- 현재 재직중인 직원들중에서 부서가 '해외영업 2부'인 직원들 의 평균 총수령액 보다 적게 받는 직원들중
-- 입사한 달이 4월인 사원의 사번, 이름 구하시오
-- 해외영업2부 직원들 제외 (한송희)



-- 여자 직원들 중 보너스를 포함한 연봉의 금액이 가장 큰 직원보다
-- 보너스를 포함한 연봉 연봉을 많이 받는 남자 직원의 사번 이름 연봉 부서명 직급명을 조회(전지혜)

 
 
 
--부서별 최대 급여를 받는 직원의 이름,나이,부서명,직급명, 연봉(= 급여 + (급여*보너스))*12) 조회)
--- 정렬은 연봉이 높은 순서대로 정렬할 것.
--- 부서코드가 NULL인 경우 소속없음으로 표시할것 (정승환)



-- 각 부서별 최대급여를 받는 여성사원의
-- 사번, 직원명, 부서명, 직급명, 나이를 조회하고
-- 급여가 가장 높은순으로 조회하세요 (조은희)

      
                 
--본인이 사수 이면서 사수가 있는 직원과 같은 근무지역의 
--임금이 최 상위자 의
--사번 이름  부서명 임금, 근무 지역을 조회 (이훈)



-- 2001년에 입사하고 사원번호가 홀수인 사원 중에서
-- 급여가 가장 높은 사원의 사원번호, 이름, 입사일, 근무지역명, 급여를 표시하는 SQL문 작성 (윤희빈)




--EMPLOYEE 테이블에서 부서명, 해당부서의 남자 직원 수("남자 직원수"), 여자 직원 수("여자 직원수")를 조회하여라.
--(이때 부서가 없는 경우에는 '소속 없음'으로 표기하며, 직원 수는 '명'으로 표기할 것) (조동영)




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




--  직원들 중 자신보다 직급이 낮은 직원을 사수로 두고있는 직원의 이름, 급여, 부서, 직급을 출력하고
--  위 직원들의 사수로 설정된 직원의 이름, 급여, 부서, 직급을 출력하시오 (박지현)




--70년대 생이며, 아시아이외지역에서 근무하는 직원의 
--사번, 나이, 이름, 직급명, 보너스포함 연봉, 근무지역 을 조회하시오 (안중하)



                
-- 각 학과별 최고 평점 (4년 평균)을 가진 사람의 이름, 학과이름, 평점을 조회  (정환조)    