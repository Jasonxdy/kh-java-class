package app.ex01.tutorial;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exam01 {
	
	public static void main(String[] args) {
		
		/*
		 * Selenium
		 * - "Web Application"의 UI 테스트를 위한 자동화 도구.
		 * - 사람이 웹 브라우저를 실행/종료시키거나 화면상의
		 * 	 화면상의 컴포넌트를 제어하는 것을 자동으로 진행할 수 있는 여러 메소드를 제공함.
		 * 
		 */
		
		// 1. 크롬 드라이버 위치 지정 (필수)
		System.setProperty("webdriver.chrome.driver",
							"C:/dev/selenium/chromedriver.exe");
		
		
		// 2. 웹브라우저 조작을 위한 객체
		// 		WebDriver 객체 생성 -> 브라우저 시작
		WebDriver driver = new ChromeDriver();
		
		// 3. 네이버 접속하기
		driver.get("https://www.naver.com");
		// get() -> 지정된 주소 이동
		// close() -> 브라우저 종료
		
		// 4. 브라우저 종료
		// driver.close();
		// 실제 테스트 시 브라우저 종료 코드는 필수
		
		
		
		
		
		
		
		
		
		
	}

}
