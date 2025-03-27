package automationframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationframwork.abstractcomponents.CommonUtils;

public class LoginPage extends CommonUtils{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(css = ".login-btn")
	WebElement btnLogin;
	
	@FindBy(css=".toast-message")
	WebElement loginErrorMessage;
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalogue userLogin(String emailId, String password) {
		userEmail.sendKeys(emailId);
		userPassword.sendKeys(password);
		btnLogin.click();
		return new ProductCatalogue(driver);
	}
	
	public String getLoginErrorMessage() {
		waitUntilVisibleWebElement(loginErrorMessage);
		return loginErrorMessage.getText();
	}
	
}
