package RahulShettyAcademy.StepDefinitions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.CheckoutPage;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpli extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() {
		landingPage = launchApplication();
	}
	
	@Given("^I Logged in with username (.+) and password (.+)$")
	public void I_Logged_in_with_username_and_password(String username, String password)
	{
		productcatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName)
	{
		List<WebElement> ProductsListed =  productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_productname_and _submit_order(String productName)
	{
		CartPage cartpage = productcatalogue.goToCartPage();		
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void meassage_displayed_confirmationPage(String string)
	{
		String ConfirmationMessage = confirmationPage.getConfirmationMessage();
		
		AssertJUnit.assertTrue(ConfirmationMessage.equalsIgnoreCase(string);
		System.out.println(ConfirmationMessage);
		System.out.println("Test passed");
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string)
	{
		AssertJUnit.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
	}
	

}