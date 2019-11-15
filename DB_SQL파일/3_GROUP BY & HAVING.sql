

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






