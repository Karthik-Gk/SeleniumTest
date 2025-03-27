package automationframwork.abstractcomponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationframework.pageobjects.CartPage;
import automationframework.pageobjects.OrdersPage;

public class CommonUtils {
	
	WebDriver driver;
	
	public CommonUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement btnCartPage;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement btnOrdersPage;
	
	public CartPage goToCart() {
		btnCartPage.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrdersPage() {
		btnOrdersPage.click();
		return new OrdersPage(driver);
	}
	
	
						
	
				/* Common Util Methods */
	public void waitUntilVisibleElement(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitUntilVisibleWebElement(WebElement findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}


}
