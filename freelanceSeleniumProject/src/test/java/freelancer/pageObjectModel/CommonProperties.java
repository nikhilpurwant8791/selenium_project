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

public class CommonProperties {
	WebDriver driver;
	WebDriverWait w;
	private String url = "https://www.demoblaze.com/";

	@FindBy (css ="#nava")
	private WebElement headerText;
	
	@FindBy(xpath = "//a[contains(text(), 'Home ')]")
	private WebElement home;
	
	@FindBy (xpath = "//a[contains(text(), 'Contact')]")
	private WebElement contact;
	
	@FindBy (xpath="//a[contains(text(), 'About us')]")
	private WebElement aboutUs;
	
	@FindBy (xpath = "//a[contains(text(), 'Cart')]")
	private WebElement cart;
	
	@FindBy(id = "login2")
	private WebElement dashboardLoginBtn;

	@FindBy (id="nameofuser")
	private WebElement dashboardUserNameBtnAfterLogin;

	@FindBy (id="logout2")
	private WebElement dashboardUserLogoutBtn;

	By usernameOnDashboard = By.id("nameofuser");
	By logInBtnDashboard = By.id("login2");
	


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

	public String verifyUserNameAfterLogin() {
		w.until(ExpectedConditions.visibilityOfElementLocated(usernameOnDashboard));
		String username = dashboardUserNameBtnAfterLogin.getText();
		return username;
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