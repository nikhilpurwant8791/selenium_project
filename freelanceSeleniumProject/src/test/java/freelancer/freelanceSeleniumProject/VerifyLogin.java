package freelancer.freelanceSeleniumProject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import freelancer.pageObjectModel.CommonProperties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyLogin 
{
	WebDriver driver;
	CommonProperties cp;
	SoftAssert sa; 
	
	@BeforeMethod
	public void implWait() {
		WebDriverManager.chromedriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		cp=new CommonProperties(driver);
		sa = new SoftAssert();
	}
	/**
	 * User name: automationQA9
	 * Pass: automationQA9
	 * URL: https://www.demoblaze.com/
	 * @throws InterruptedException 
	 */
	
	@Test
	public void ValidateLogin() 
	{
		cp.visitURL();
		String URL = driver.getTitle();
		sa.assertEquals(URL, "STORE" );
		cp.login("automationQA9", "automationQA9");
	}
	
	@AfterMethod
	public void logout() {
		cp.clickLogoutBtn();
		String loginBtn= cp.verifyLoginBtnOnDashboard();
		sa.assertEquals(loginBtn, "Log in");
		sa.assertAll();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
}
