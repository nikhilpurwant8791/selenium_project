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
import freelancer.pageObjectModel.DashboardPage;
import freelancer.pageObjectModel.LoginPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyListOfItems 
{
	WebDriver driver;
	SoftAssert sa; 
	DashboardPage dp;

	@BeforeClass
	public void implWait() {
		WebDriverManager.chromedriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		sa = new SoftAssert();
		dp = new DashboardPage(driver);
		dp.visitURL();
		dp.login("automationQA9", "automationQA9");
	}

	@Test
	public void VerifyTheList() {
		List<WebElement> eleList = dp.ListOfElement();
		for(WebElement ele: eleList) {
			dp.ScrollUptoElement(ele);
			String text = ele.getText();
			System.out.println("List of element = "+text);
			if (text.equalsIgnoreCase("HTC One M9")) {
				sa.assertEquals(text, "HTC One M9");
				dp.clickNextBtn();
				List<WebElement> el = dp.ListOfElement();
				for(WebElement ele2: el) {
					dp.ScrollUptoElement(ele2);
					String text2 = ele2.getText();
					System.out.println("List of element = "+text2);
					if (text2.equalsIgnoreCase("MacBook Pro")) {
						sa.assertEquals(text2, "MacBook Pro");
					}
				}
			}
		}
	}

	@Test
	public void VerifyThePhonesList() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Phones']")).click();

		/*use Thread.sleep(), whenever following exception occurred
		java.lang.IllegalMonitorStateException: current thread not owner
		 */

		Thread.sleep(1000); 
		List<WebElement> eleList = dp.ListOfElement();
		int count = eleList.size();
		sa.assertEquals(count, 7);
		for(WebElement ele: eleList) {
			dp.ScrollUptoElement(ele);
			String text = ele.getText();
			System.out.println("List of element = "+text);
			if (text.equalsIgnoreCase("HTC One M9")) {
				sa.assertEquals(text, "HTC One M9");
			}
		}
	}

	@Test
	public void VerifyTheLaptopsList() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Laptops']")).click();

		/*use Thread.sleep(), whenever following exception occurred
		java.lang.IllegalMonitorStateException: current thread not owner
		 */

		Thread.sleep(1000); 
		List<WebElement> eleList = dp.ListOfElement();
		int count = eleList.size();
		sa.assertEquals(count, 6);
		for(WebElement ele: eleList) {
			dp.ScrollUptoElement(ele);
			String text = ele.getText();
			System.out.println("List of element = "+text);
			if (text.equalsIgnoreCase("MacBook Pro")) {
				sa.assertEquals(text, "MacBook Pro");
			}
		}
	}

	@Test
	public void VerifyTheMonitorsList() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Monitors']")).click();

		/*use Thread.sleep(), whenever following exception occurred
		java.lang.IllegalMonitorStateException: current thread not owner
		 */

		Thread.sleep(1000); 
		List<WebElement> eleList = dp.ListOfElement();
		int count = eleList.size();
		sa.assertEquals(count, 2);
		for(WebElement ele: eleList) {
			dp.ScrollUptoElement(ele);
			String text = ele.getText();
			System.out.println("List of element = "+text);
			if (text.equalsIgnoreCase("ASUS Full HD")) {
				sa.assertEquals(text, "ASUS Full HD");
			}
		}
	}

	@AfterClass
	public void closeBrowser() {
		dp.clickLogoutBtn();
		String loginBtn= dp.verifyLoginBtnOnDashboard();
		sa.assertEquals(loginBtn, "Log in");
		sa.assertAll();
		driver.quit();
	}

}
