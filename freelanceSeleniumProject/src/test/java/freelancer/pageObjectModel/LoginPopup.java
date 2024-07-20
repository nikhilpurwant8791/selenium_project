package freelancer.pageObjectModel;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPopup extends CommonProperties {
	WebDriver driver;

	@FindBy(xpath = "(//span[text()='×'])[3]")
	private WebElement loginPopupCrossSign;

	@FindBy(id = "loginusername")
	private WebElement loginPopupUsername;

	@FindBy(css = "#loginpassword")
	private WebElement loginPopupPassword;

	@FindBy(xpath = "(//button[text()='Close'])[3]")
	private WebElement loginPopupCloseBtn;

	@FindBy(xpath = "//button[@onclick='logIn()']")
	private WebElement loginPopupLoginBtn;

	public LoginPopup(WebDriver driver)
	{
		super (driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyLoginPopupCrossSign() {
		boolean cross = loginPopupCrossSign.getCssValue("cursor").equalsIgnoreCase("pointer");
		return cross;
	}

	public void enterUsername(String username) {
		loginPopupUsername.clear();
		loginPopupUsername.sendKeys(username);
	}

	public void enterPasswrod(String pass) {
		loginPopupPassword.clear();
		loginPopupPassword.sendKeys(pass);
	}

	public boolean verifyPopupCloseBtn() {
		boolean close = loginPopupCloseBtn.getCssValue("cursor").equalsIgnoreCase("default");
		return close;
	}

	public boolean verifyPopupLoginBtn() {
		boolean loginBtn = loginPopupLoginBtn.getCssValue("cursor").equalsIgnoreCase("default");
		return loginBtn;
	}

	public void clickPopupLoginBtn() {
		loginPopupLoginBtn.click();
	}

	public void login (String username, String password) {
		this.clickDashboardLoginBtn();
		this.enterUsername(username);
		this.enterPasswrod(password);
		this.clickPopupLoginBtn();
		String uname = this.verifyUserNameAfterLogin();
		Assert.assertEquals(uname, "Welcome automationQA9");
	}


}
