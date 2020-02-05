package app.ex04.waiting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardWriteEx2 {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:/dev/selenium/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		driver.get("http://192.168.10.78:8080/selenium/exam/login.html");
		
		// 묵시적 (암시적) 대기 (Implicitly Wait)
		// - 웹드라이브가 발생시키는 예외들을 일정 시간동안 대기시키고
		//	  그 시간동안 동작을 재시도함.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement error = driver.findElement(By.id("error"));
		
		
//		driver.close();
		
		
		
	}

}
