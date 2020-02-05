package app.ex05.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {
	
	public static void main (String[] args) {
		
		System.setProperty("webdriver.chrome.driver",
				"C:/dev/selenium/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		driver.get("https://www.iei.or.kr/login/login.kh");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("id")));
		
		WebElement inputId = driver.findElement(By.id("id"));
		WebElement inputPwd = driver.findElement(By.id("password"));
		
		wait.until(ExpectedConditions.elementToBeClickable(inputId));
		
		
		inputId.sendKeys("dicobomb");
		inputPwd.sendKeys("whehddud91");
		
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		
		exe.executeScript("javascript:fnLogin()");
		
		wait.until(ExpectedConditions.titleContains("KH정보교육원 - 수강생 평가"));
		
		exe.executeScript("location.href='/login/currBoard.kh'");
		
		wait.until(ExpectedConditions.titleContains("KH정보교육원 - 우리반 게시판"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#content_right > div.board_view_botton > a")));
		driver.findElement(By.xpath("//*[@id=\"content_right\"]/div[6]/a")).click();
		
		
		wait.until(ExpectedConditions.titleContains("KH정보교육원 - 우리반 게시판"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("title")));
		
		driver.findElement(By.id("title")).sendKeys("도착했나");
		
		driver.switchTo().frame("tx_canvas_wysiwyg");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.className("tx-content-container")));
		
		WebElement content = driver.findElement(By.className("tx-content-container"));
		
		content.sendKeys("도착했나봐");
		
		driver.switchTo().parentFrame();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tx_editor_form\"]/div/a[1]")));
		exe.executeScript("javascript:fnRegister()");
		
		driver.close();
		
		
	}
	
	

}
