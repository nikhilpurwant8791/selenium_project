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
		driver.navigate().to("https://www.demoblaze.com/");
		String URL = driver.getTitle();
		sa.assertEquals(URL, "STORE" );
		cp.clickDashboardLoginBtn();
		boolean cross = cp.verifyLoginPopupCrossSign();
		sa.assertEquals(cross, true, "Cursor: Pointer");
		cp.enterUsername("automationQA9");
		cp.enterPasswrod("automationQA9");
		boolean close = cp.verifyPopupCloseBtn();
		sa.assertEquals(close, true, "Cursor: default");
		boolean loginPopup = cp.verifyPopupLoginBtn();
		sa.assertEquals(loginPopup, true);
		cp.clickPopupLoginBtn();
		String username = cp.verifyUserNameAfterLogin();
		sa.assertEquals(username, "Welcome automationQA9"); 
        sa.assertAll(); 
	}
	
	@AfterMethod
	public void logout() {
		cp.clickLogoutBtn();
		String loginBtn= cp.verifyLoginBtnOnDashboard();
		sa.assertEquals(loginBtn, "Log in");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
}
