package freelancer.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactPopup extends CommonProperties {
	WebDriver driver;
	String message = "This is for testing";

	public ContactPopup(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//h5[text()='New message']")
	private WebElement contactPopupHeaderText;
	
	@FindBy (xpath = "(//span[text()='×'])[1]")
	private WebElement contactPopupCrossSign;
	
	@FindBy (id = "recipient-email")
	private WebElement contactEmail;
	
	@FindBy(id="recipient-name")
	private WebElement contactName;
	
	@FindBy(id="message-text")
	private WebElement contactMessage;
	
	@FindBy(xpath="(//button[text()='Close'])[1]")
	private WebElement closeBtn;
	
	@FindBy(xpath="//button[text() = 'Send message']")
	private WebElement sendMessage;
	
	By contactPopUpHeadert = By.xpath("//h5[text()='New message']");
	
	public boolean contactPopupHeaderText() {
		w.until(ExpectedConditions.visibilityOfElementLocated(contactPopUpHeadert));
		boolean header = contactPopupHeaderText.getText().equalsIgnoreCase("New message");
		return header;
	}
	
	public boolean verifyContactPopupCrossSign() {
		boolean cross = contactPopupCrossSign.getCssValue("cursor").equalsIgnoreCase("pointer");
		return cross;
	}
	
	public void enterEmail(String email) {
		contactEmail.sendKeys(email);
	}
	
	public void enterName(String name) {
		contactName.sendKeys(name);
	}
	
	public void enterMessage() {
		contactMessage.sendKeys(message);
	}
	
	public boolean verifyCloseBtn() {
		return closeBtn.getCssValue("cursor").equalsIgnoreCase("default");
	}
	
	public boolean clickSendMessageBtnAndCaptureAlertText() {
		sendMessage.click();
		String alertText = driver.switchTo().alert().getText();
		boolean verifyText = alertText.equalsIgnoreCase("Thanks for the message!!");
		return verifyText;
	}
	
	public void acceptAlertPopup() {
		driver.switchTo().alert().accept();
	}
	
	
}
