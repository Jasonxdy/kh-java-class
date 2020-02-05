package app.ex03.iframe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class iframeEx1 {
	
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
		
		// 로그인 버튼 클릭
		// 태그 선택자 이용! (로그인 버튼이 class, id 없으므로)
		WebElement loginBtn = driver.findElement(By.tagName("button"));
		
		loginBtn.click();
		
		
		
		
		
		
		
		
	}

}
