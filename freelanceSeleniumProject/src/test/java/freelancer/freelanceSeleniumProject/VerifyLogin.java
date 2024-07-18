package freelancer.freelanceSeleniumProject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import freelancer.pageObjectModel.LoginPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyLogin 
{
	WebDriver driver;
	LoginPopup lp;
	SoftAssert sa; 
	
	@BeforeMethod
	public void implWait() {
		WebDriverManager.chromedriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		lp=new LoginPopup(driver);
		sa = new SoftAssert();
	}
	/**
	 * User name: automationQA9
	 * Pass: automationQA9
	 * URL: https://www.demoblaze.com/
	 */
	
	@Test
	public void ValidateLogin() 
	{
		lp.visitURL();
		String URL = driver.getTitle();
		sa.assertEquals(URL, "STORE" );
		lp.login("automationQA9", "automationQA9");
	}
	
	@AfterMethod
	public void logout() {
		lp.clickLogoutBtn();
		String loginBtn= lp.verifyLoginBtnOnDashboard();
		sa.assertEquals(loginBtn, "Log in");
		sa.assertAll();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
}
