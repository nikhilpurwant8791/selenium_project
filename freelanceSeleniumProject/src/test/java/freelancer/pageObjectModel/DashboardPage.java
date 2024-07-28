package freelancer.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends LoginPopup {
	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//h4[@class='card-title']//a")
	private List<WebElement> eleList;
	
	@FindBy (xpath = "//button[text() ='Next']")
	private WebElement nextBtn;
	
	public List<WebElement> ListOfElement() {
		return eleList;
	}
	
	public void clickNextBtn() {
		nextBtn.click();
	}

}
