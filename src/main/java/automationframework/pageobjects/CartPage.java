package automationframework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationframwork.abstractcomponents.CommonUtils;

public class CartPage extends CommonUtils {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3") 
	List<WebElement> myCartItems;
	
	@FindBy(xpath="//li/button[@type='button']")
	WebElement btnCheckout;
	
	
	public Boolean checkIfItemPresentInCart(String productName) {
		Boolean itemMatchInCart = myCartItems.stream().anyMatch(item -> 
		item.getText().equalsIgnoreCase(productName));
		return itemMatchInCart;
	}
	
	public PaymentPage checkOut() {
		btnCheckout.click();
		
		return new PaymentPage(driver);
	}

}
