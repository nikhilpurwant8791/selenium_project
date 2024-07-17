package freelancer.pageObjectModel;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonProperties {
	WebDriver driver;
	private String url = "https://www.demoblaze.com/";

	@FindBy(id = "login2")
	private WebElement dashboardLoginBtn;

	@FindBy(xpath = "(//span[text()=\"×\"])[3]")
	private WebElement loginPopupCrossSign;

	@FindBy(id = "loginusername")
	private WebElement loginPopupUsername;

	@FindBy(css = "#loginpassword")
	private WebElement loginPopupPassword;

	@FindBy(xpath = "(//button[text()=\"Close\"])[3]")
	private WebElement loginPopupCloseBtn;

	@FindBy(xpath = "//button[@onclick='logIn()']")
	private WebElement loginPopupLoginBtn;

	@FindBy (id="nameofuser")
	private WebElement dashboardUserNameBtnAfterLogin;

	@FindBy (id="logout2")
	private WebElement dashboardUserLogoutBtn;

	By usernameOnDashboard = By.id("nameofuser");
	By logInBtnDashboard = By.id("login2");
	WebDriverWait w;


	public CommonProperties(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		w = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public void visitURL() {
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}

	public void clickDashboardLoginBtn() {
		dashboardLoginBtn.click();
	}

	public boolean verifyLoginPopupCrossSign() {
		boolean cross = loginPopupCrossSign.getCssValue("cursor").equalsIgnoreCase("pointer");
		return cross;
	}
	
	public void enterUsername(String username) {
		loginPopupUsername.sendKeys(username);
	}
	
	public void enterPasswrod(String pass) {
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

	public String verifyUserNameAfterLogin() {
		w.until(ExpectedConditions.visibilityOfElementLocated(usernameOnDashboard));
		String username = dashboardUserNameBtnAfterLogin.getText();
		return username;
	}
	
	public void login (String username, String password) {
		this.clickDashboardLoginBtn();
		boolean cross = this.verifyLoginPopupCrossSign();
		Assert.assertEquals(cross, true, "Cursor: Pointer");
		this.enterUsername(username);
		this.enterPasswrod(password);
		boolean close = this.verifyPopupCloseBtn();
		Assert.assertEquals(close, true, "Cursor: default");
		boolean loginPopup = this.verifyPopupLoginBtn();
		Assert.assertEquals(loginPopup, true);
		this.clickPopupLoginBtn();
		String uname = this.verifyUserNameAfterLogin();
		Assert.assertEquals(uname, "Welcome "+username);
	}

	public void clickLogoutBtn() {
		dashboardUserLogoutBtn.click();
	}

	public String verifyLoginBtnOnDashboard() {
		w.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(logInBtnDashboard));
		String login = dashboardLoginBtn.getText();
		return login;
	}
}