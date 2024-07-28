package freelancer.freelanceSeleniumProject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import freelancer.pageObjectModel.AboutUsPopUp;
import freelancer.pageObjectModel.ContactPopup;
import freelancer.pageObjectModel.LoginPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyListOfItems 
{
	WebDriver driver;
	LoginPopup lp;
	ContactPopup cp;
	SoftAssert sa; 
	AboutUsPopUp ab;

	@BeforeClass
	public void implWait() {
		WebDriverManager.chromedriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		lp=new LoginPopup(driver);
		cp = new ContactPopup(driver);
		sa = new SoftAssert();
		ab = new AboutUsPopUp(driver);
		lp.visitURL();
	}
	
	@Test
	public void VerifyTheList() {
		ab.login("automationQA9", "automationQA9");
		List<WebElement> eleList = driver.findElements(By.xpath("//h4[@class='card-title']//a"));
		for(WebElement ele: eleList) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
			String text = ele.getText();
			System.out.println("List of element = "+text);
			if (text.equalsIgnoreCase("HTC One M9")) {
				System.out.println("Pass");
				driver.findElement(By.xpath("//button[text() ='Next']")).click();
				List<WebElement> el = driver.findElements(By.xpath("//h4[@class='card-title']//a"));
				for(WebElement ele2: el) {
					js.executeScript("arguments[0].scrollIntoView();", ele);
					String text2 = ele2.getText();
					System.out.println("List of element = "+text2);
					if (text2.equalsIgnoreCase("MacBook Pro")) {
						System.out.println("Pass");
					}
				}
			}
		}
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
