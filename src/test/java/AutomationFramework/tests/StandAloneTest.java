package AutomationFramework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationFramework.testcomponents.BaseTest;
import automationframework.pageobjects.CartPage;
import automationframework.pageobjects.LoginPage;
import automationframework.pageobjects.OrderConfirmationPage;
import automationframework.pageobjects.OrdersPage;
import automationframework.pageobjects.PaymentPage;
import automationframework.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest{
	
	@Test
	public void SubmitOrder() throws IOException, InterruptedException {		
		String emailId = "hellotesting@gmail.com";
		String password = "Hellotest@123";
		String productName = "Zara coat 3";
		String country = "India";
		String orderConfirmation = "Thankyou for the order.";
		System.out.println("This is from Local");

		ProductCatalogue productCatalogue = loginPage.userLogin(emailId, password);
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		
		Boolean itemMatchInCart = cartPage.checkIfItemPresentInCart(productName);
		Assert.assertTrue(itemMatchInCart);
		PaymentPage paymentPage = cartPage.checkOut();
		
		paymentPage.enterDetails(country);
		OrderConfirmationPage orderConfirmationPage = paymentPage.placeOrder();
		String OrderConfirmation = orderConfirmationPage.orderConfirmation(orderConfirmation);
		AssertJUnit.assertTrue(OrderConfirmation.equalsIgnoreCase(orderConfirmation));
	}
	
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistory() {
		String emailId = "hellotesting@gmail.com";
		String password = "Hellotest@123";
		String productName = "Zara coat 3";
		
		ProductCatalogue productCatalogue = loginPage.userLogin(emailId, password);
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Boolean itemPresentInOrderPage = ordersPage.checkIfItemPresentInOrderPage(productName);
		Assert.assertTrue(itemPresentInOrderPage);
	}
	
	


}
