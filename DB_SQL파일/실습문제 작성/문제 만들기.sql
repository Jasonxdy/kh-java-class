/*
EMPLOYEE 테이블에서 부서명, 해당부서의 남자 직원 수("남자 직원수"), 여자 직원 수("여자 직원수")를 조회하여라.
(이때 부서가 없는 경우에는 '소속 없음'으로 표기하며, 직원 수는 '명'으로 표기할 것)
*/

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