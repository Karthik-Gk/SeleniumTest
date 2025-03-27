package automationframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationframwork.abstractcomponents.CommonUtils;

public class ProductCatalogue extends CommonUtils {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".card-body") 
	List<WebElement> products;
	

	By Products = By.cssSelector(".card-body");
	By productNameBy = By.cssSelector("h5 b");
	By addToCart = By.cssSelector(".btn.w-10.rounded");
	By toastMessage = By.cssSelector("#toast-container");
	

	public List<WebElement> getProducts() {
		waitUntilVisibleElement(Products);
		return products;
	}

	public WebElement productToSelect(String productName) {
		WebElement productToSelect = getProducts().stream()
				.filter(product -> product.findElement(productNameBy).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return productToSelect;
	}
	
	public void addProductToCart(String productName) {
		WebElement product = productToSelect(productName);
		product.findElement(addToCart).click();
		waitUntilVisibleElement(toastMessage);
	}
	


}
