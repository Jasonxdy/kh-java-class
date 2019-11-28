select username from dba_users;

CREATE USER kh IDENTIFIED BY kh; /*kh라는 계정을 kh라는 비밀번호로 생성*/
GRANT RESOURCE, CONNECT TO kh; /*resource(객체)에 대한 권한(role)을 부여*/
/*CONNECT : 권한들을 말아서 모아놓음*/