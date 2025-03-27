package automationframework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationframwork.abstractcomponents.CommonUtils;

public class OrdersPage extends CommonUtils{

	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> myOrderItems;
	
	
	
	public Boolean checkIfItemPresentInOrderPage(String productName) {
		Boolean itemPresentInOrders = myOrderItems.stream().anyMatch(item ->
				item.getText().equalsIgnoreCase(productName));
		return itemPresentInOrders;
	}

}
