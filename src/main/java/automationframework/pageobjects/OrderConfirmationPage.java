package automationframework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import automationframwork.abstractcomponents.CommonUtils;

public class OrderConfirmationPage extends CommonUtils{

	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//String txtOrderConfirmation = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
	//Assert.assertTrue(txtOrderConfirmation.equalsIgnoreCase(orderConfirmation));
	
	@FindBy(css=".hero-primary")
	WebElement txtOrderConfirmation;
	
	public String orderConfirmation(String orderConfirmation) {
		String OrderConfirmation = txtOrderConfirmation.getText();
		return OrderConfirmation;
	}
}
