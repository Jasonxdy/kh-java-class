package app.ex01.tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exam02_ID {
	
	public static void main(String[] args) {
		
		// 1. 크롬드라이버 위치 지정 (필수)
		System.setProperty("webdriver.chrome.driver",
				"c:/dev/selenium/chromedriver.exe");
		
		// 2. 웹 브라우저 조작
		WebDriver driver = new ChromeDriver();
		
		// 3. 네이버 접속
		driver.get("https://www.naver.com");
		
		// 4. 브라우저에서 검색창을 찾음
		WebElement inputSearch = driver.findElement(By.id("query"));
		
		// 5. 검색어 입력
		inputSearch.sendKeys("KH정보교육원");
		
		// 6. 검색버튼 찾기
		WebElement searchBtn = driver.findElement(By.id("search_btn"));
		
		// 7. 검색버튼 클릭
		searchBtn.click();
		
		// 8. 브라우저 종료
		// driver.close();
		
		
		
		
		
	}
	

}
