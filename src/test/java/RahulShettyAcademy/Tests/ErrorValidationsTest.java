package RahulShettyAcademy.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

		@Test(groups= {"ErrorHandling"})
		public void LoginErrorValidation() throws InterruptedException, IOException 
		{
			String productName = "ZARA COAT 3";
			//creating object of the page object classes to pass driver object created.
			ProductCatalogue productcatalogue = landingpage.loginApplication("iamannurose@gmail.com", "Annu@2001");
			AssertJUnit.assertEquals("Incorrect email  password", landingpage.getErrorMessage());
		}
		
		
		@Test
		public void ProductErrorValidation() throws InterruptedException, IOException 
		{
			String productName = "ZARA COAT 3";
			//creating object of the page object classes to pass driver object created.
			ProductCatalogue productcatalogue = landingpage.loginApplication("iamannurose@gmail.com", "Annu@2001");
			List<WebElement> ProductsListed =  productcatalogue.getProductList();
			productcatalogue.addProductToCart(productName);
			CartPage cartpage = productcatalogue.goToCartPage();		
			Boolean match = cartpage.verifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
		}

}
