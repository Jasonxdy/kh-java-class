package app.ex02.google;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exam02_CssSelector {
	
	// project explorer사용하여 복붙하면 이름 자동으로 바뀌면서 생성가능
	
	public static void main(String[] args) {
		
		// 1. 크롬드라이버 위치 지정 (필수)
		System.setProperty("webdriver.chrome.driver",
							"C:/dev/selenium/chromedriver.exe");
		
		// 2. 웹 브라우저 조작 (오픈)
		WebDriver driver = new ChromeDriver();
		
		// 3. Google 접속
		driver.get("https://www.google.co.kr");
		
		// 4. 검색창 찾기 (css 선택자 이용)
		// * css 선택자를 사용하는 경우 복수의 요소가 선택될 수 있음
		List<WebElement> inputSearchs = 
				driver.findElements(By.cssSelector("#tsf > div:nth-child(2) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input"));
		
		// 검색된 요소의 개수 확인
		// System.out.println("검색된 클래스 개수 : " + inputSearchs.size());
		// -> 검색된 클래스 개수 1개로 확인
		
		// 요소가 1개만 있는 경우 (1)
//		WebElement inputSearch =
//				driver.findElement(By.className("gLFyf"));
		
		// List에 저장된 요소 중 첫번째 요소 접근 (2)
		// + 6. 검색어 입력
		inputSearchs.get(0).sendKeys("KH정보교육원");
		
		// 7. submit()을 이용해 검색어 제출
		inputSearchs.get(0).submit(); // form태그 안에 씌워져 있기 때문에 submit 이용 가능
		
		
		
	
		
		
		
		
		
		
	}

}
