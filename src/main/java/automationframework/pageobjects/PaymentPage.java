package automationframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationframwork.abstractcomponents.CommonUtils;

public class PaymentPage extends CommonUtils{
	
	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement fieldSelectCountry;
	
	@FindBy(xpath="(//button[contains(@class,'list-group-item')])[2]")
	WebElement dpSelectCountry;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement btnPlaceOrder;
	
	By ddSelectCountry = By.xpath("(//button[contains(@class,'list-group-item')])[2]");
	
	
	
	public void enterDetails(String country) throws InterruptedException {
		fieldSelectCountry.sendKeys(country);
		waitUntilVisibleElement(ddSelectCountry);
		dpSelectCountry.click();
	}
	
	public OrderConfirmationPage placeOrder() {
		btnPlaceOrder.click();
		
		return new OrderConfirmationPage(driver);
	}
	
	public void enterDetailsAndPlaceOrder(String country) throws InterruptedException {
		enterDetails(country);
		placeOrder();
		
	} 

}