package app.ex04.waiting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardWriteEx1 {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:/dev/selenium/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		// 1. 명시적 대기 (Explicitly Wait)
		// 특정 요소가 나타날 때까지 지정한 최대 시간만큼 대기하는 방식
		WebDriverWait wait = new WebDriverWait(driver, 10); // 10초 대기
		driver.get("http://192.168.10.78:8080/selenium/exam/login.html");
		
		WebElement inputId = driver.findElement(By.id("id"));
		WebElement inputPwd = driver.findElement(By.id("password"));
		
		inputId.sendKeys("test");
		inputPwd.sendKeys("test");
		
//		WebElement loginBtn = driver.findElement(By.tagName("button"));
//		loginBtn.click();
		
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("fnLogin()");
		
		// 웹페이지 타이틀에 "훈련생도 평가" 글자가 표시될 때까지 명시적 대기
		wait.until(ExpectedConditions.titleContains("훈련생도 평가"));
		
		// 자바스크립트 코드를 실행하여 게시판 페이지로 이동
		exe.executeScript("javascript:location.href='currBoard.html'");
		
		// 웹페이지 타이틀에 "우리반 게시판" 글자가 표시될 때까지 명시적 대기
		wait.until(ExpectedConditions.titleContains("우리반 게시판"));
		
		exe.executeScript("fnWriteForm()");
		
		// 아이디가 title인 요소가 클릭될 때까지  대기
		wait.until(ExpectedConditions.elementToBeClickable(By.id("title")));
		
		WebElement inputTitle = driver.findElement(By.id("title"));
		inputTitle.sendKeys("잘잤당 히히 ^_^");
		
		// iframeMsg 이름의 iframe으로 focus 이동
		driver.switchTo().frame("iframeMsg");
		
		WebElement inputMsg = driver.findElement(By.id("areaMsg"));
		inputMsg.sendKeys("이따 가서 또 자야지");
		
		driver.switchTo().parentFrame();
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.tagName("button"))));
		
		exe.executeScript("fnRegister()");
		
		
		// alert창으로 focus 이동 후 확인클릭
		driver.switchTo().alert().accept();
		
		driver.close();
		
		
		
	}

}
