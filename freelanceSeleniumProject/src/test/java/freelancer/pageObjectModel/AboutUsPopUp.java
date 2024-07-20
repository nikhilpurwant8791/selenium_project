package freelancer.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AboutUsPopUp extends LoginPopup {
	WebDriver driver;

	public AboutUsPopUp(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id= "videoModalLabel")
	private WebElement headerText;
	
	@FindBy (xpath = "(//span[text()='×'])[4]")
	private WebElement crossSign;
	
	@FindBy (xpath="(//button[@data-dismiss='modal'])[8]")
	private WebElement closeButton;
	
	By headtext = By.id("videoModalLabel");
	By closeBtn = By.xpath("(//button[@data-dismiss='modal'])[8]");
	
	public boolean headerText() {
		w.until(ExpectedConditions.visibilityOfElementLocated(headtext));
		boolean text = headerText.getText().equalsIgnoreCase("About us");
		return text;
	}
	
	public boolean verifyCrossSign() {
		boolean cross = crossSign.getCssValue("cursor").equalsIgnoreCase("pointer");
		return cross;
	}
	
	public boolean verifyCloseButton() {
		w.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
		boolean close = closeButton.getCssValue("cursor").equalsIgnoreCase("default");
		return close;
	}
	
	public void clickCloseButton() {
		closeButton.click();
	}
	
	
	

}
