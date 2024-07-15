package freelancer.freelanceSeleniumProject;


import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

//import static org.junit.Assert.assertTrue;

//import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	WebDriver driver;
	@BeforeMethod
	public void implWait() {
		WebDriverManager.chromedriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
	}
	/**
	 * User name: automationQA9
	 * Pass: automationQA9
	 * URL: https://www.demoblaze.com/
	 * @throws InterruptedException 
	 */
	@Test
	public void ValidateLoginLogout() throws InterruptedException
	{
//		WebDriverManager.chromedriver();
//		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.demoblaze.com/");
		String URL = driver.getTitle();
		Assert.assertEquals(URL, "STORE" );
		WebElement logindash = driver.findElement(By.id("login2"));
		logindash.click();
		WebElement x = driver.findElement(By.xpath("(//span[text()=\"×\"])[3]"));
		boolean cross = x.getCssValue("cursor").equalsIgnoreCase("pointer");
		String crossBtn = cross ? "Cursor: Pointer": "Cursor:UNKNOWN";
		System.out.println(crossBtn);
		Assert.assertEquals(cross, true);
		
		// Login and password
		driver.findElement(By.id("loginusername")).sendKeys("automationQA9");
		driver.findElement(By.cssSelector("#loginpassword")).sendKeys("automationQA9");
		// Verify close button
		boolean close = driver.findElement(By.xpath("(//button[text()=\"Close\"])[3]")).getCssValue("cursor").equalsIgnoreCase("default");
		String closeBtn = close ? "Cursor: default": "Cursor:UNKNOWN";
		System.out.println(closeBtn);
		Assert.assertEquals(close, true);
		
		WebElement login = driver.findElement(By.xpath("//button[@onclick='logIn()']"));
		boolean loginele = login.getCssValue("Cursor").equalsIgnoreCase("default");
		Assert.assertEquals(loginele, true);
		login.click();
		
		// Explicit wait
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(300));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		String username = driver.findElement(By.id("nameofuser")).getText();
		Assert.assertEquals(username, "Welcome automationQA9");
		
		driver.findElement(By.id("logout2")).click();
		w.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
		String login2 = driver.findElement(By.id("login2")).getText();
		Assert.assertEquals(login2, "Log in");
		driver.quit();
	}
	
}
