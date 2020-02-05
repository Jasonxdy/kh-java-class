package app.ex03.iframe;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class iframeEx2 {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", 
					"C:/dev/selenium/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		
		driver.get("http://192.168.10.78:8080/selenium/exam/iframeTest.html");
		
		// ** 자동화 도구의 focus를 iframe으로 변경
		driver.switchTo().frame("subIframe"); // "frame의 이름 (name)"
		
		WebElement inputId = driver.findElement(By.id("id"));
		WebElement inputPwd = driver.findElement(By.id("password"));

		inputId.sendKeys("test");
		inputPwd.sendKeys("test");
		
		// 로그인 버튼 클릭 (자바스크립트 함수 이용)
		// JavaScriptExecutor 인터페이스
		// - 셀레니움에서 자바스크립트 코드를 실행할 수 있게 해주는 역할.
		// - 자바스크립트 뿐만 아니라 제이쿼리도 사용 가능.
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("fnLogin()");
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
