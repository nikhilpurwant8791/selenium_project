package freelancer.freelanceSeleniumProject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import freelancer.pageObjectModel.ContactPopup;
import freelancer.pageObjectModel.LoginPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyHeaderMenu 
{
	WebDriver driver;
	LoginPopup lp;
	ContactPopup cp;
	SoftAssert sa; 

	@BeforeClass
	public void implWait() {
		WebDriverManager.chromedriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		lp=new LoginPopup(driver);
		cp = new ContactPopup(driver);
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
		lp.clickDashboardLoginBtn();
		boolean cross = lp.verifyLoginPopupCrossSign();
		Assert.assertEquals(cross, true, "Cursor: Pointer");
		lp.enterUsername("automationQA9");
		lp.enterPasswrod("automationQA9");
		boolean close = lp.verifyPopupCloseBtn();
		Assert.assertEquals(close, true, "Cursor: default");
		boolean loginPopup = lp.verifyPopupLoginBtn();
		Assert.assertEquals(loginPopup, true);
		lp.clickPopupLoginBtn();
		String uname = lp.verifyUserNameAfterLogin();
		Assert.assertEquals(uname, "Welcome automationQA9");
	}

	@Test
	public void validateContactPopup() throws InterruptedException {
		lp.visitURL();
		lp.login("automationQA9", "automationQA9");
		lp.clickContact();
		boolean headerText = cp.contactPopupHeaderText();
		Assert.assertEquals(headerText, true);
		boolean cross = cp.verifyContactPopupCrossSign();
		Assert.assertEquals(cross, true);
		cp.enterEmail("abc@gmail.com");
		cp.enterName("Tester");
		cp.enterMessage();
		boolean close = cp.verifyCloseBtn();
		Assert.assertEquals(close, true);
		boolean alertMsg = cp.clickSendMessageBtnAndCaptureAlertText();
		Assert.assertEquals(alertMsg, true);
		cp.acceptAlertPopup();
	}

	@AfterMethod
	public void logout() {
		lp.clickLogoutBtn();
		String loginBtn= lp.verifyLoginBtnOnDashboard();
		sa.assertEquals(loginBtn, "Log in");
		sa.assertAll();
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
