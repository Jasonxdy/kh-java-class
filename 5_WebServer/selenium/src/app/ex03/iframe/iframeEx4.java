package app.ex03.iframe;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class iframeEx4 {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", 
					"C:/dev/selenium/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		
		driver.get("http://192.168.10.78:8080/selenium/exam/iframeTest2.html");
		
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
		
		
		// ************************
		// alert(경고창)이 실행 되었을 때
		// alert 창의 확인버튼 클릭하기
		driver.switchTo().alert().accept();
		// browser 하나에는 하나의 alert창이 있으므로 이렇게 지정 가능
		
		
		
		// 자동화 도구의 focus를 부모 프레임으로 다시 변경
		driver.switchTo().parentFrame(); // iframe의 부모 프레임으로 focus 맞춤
		
		// 태그 선택자 이용하여 <p> 태그 선택
		WebElement pTag = driver.findElement(By.tagName("p"));
		
		// 찾은 p태그의 내용을 읽어오기
		System.out.println("p태그 내용 : " + pTag.getText());
		
		
		
	}

}
