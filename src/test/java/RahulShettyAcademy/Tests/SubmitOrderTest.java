package RahulShettyAcademy.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.CheckoutPage;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.LandingPage;
import RahulShettyAcademy.pageobjects.OrderPage;
import RahulShettyAcademy.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
		@Test(dataProvider="getData",//--->here the testNg will pickup the data form the getData method as it is the mentioned DataProvider
		groups = {"PurchaseOrder"})
		public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException //the fetched data is catched as the method parameter in the same order.
		//HashMap datatype is set as String as the loginApplication()method is expecting value as String.
		{
			
			//creating object of the page object classes to pass driver object created.
			ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
			List<WebElement> ProductsListed =  productcatalogue.getProductList();
			productcatalogue.addProductToCart(input.get("productName"));
			CartPage cartpage = productcatalogue.goToCartPage();		
			Boolean match = cartpage.verifyProductDisplay(input.get("productName"));
			Assert.assertTrue(match);
			//what happens here is using stream we scan all the product names for ZARA COAT 3, here instaed of filetr we used anyMatch it returns boolean values
			//true and false. Since we have added the product to cart it will be present in the page and the stream returns trye value.
			//with assertion we check whether the returned value is true or false if true only the Assert.assertTrue(match) returns true and continues execution, if false the execution stops here.
			CheckoutPage checkoutPage = cartpage.goToCheckout();
			checkoutPage.selectCountry("india");
			ConfirmationPage confirmationPage = checkoutPage.submitOrder();
			String ConfirmationMessage = confirmationPage.getConfirmationMessage();
			
			AssertJUnit.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			System.out.println(ConfirmationMessage);
			System.out.println("Test passed");
		}
		
		//To verify ZARA COAT 3 is displaying in the orders page
		//This test will depend on the previous test results. So this is a dependency test
		
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest()
		{
			ProductCatalogue productcatalogue = landingpage.loginApplication("iamannurose@gmail.com", "Annu@2001");
			OrderPage orderpage = productcatalogue.goToOrdersPage();
			Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{			
			List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir")+"//src//test//java//RahulShettyAcademy//Data//PurcahseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
//		@DataProvider
//		public Object[][] getData()
//		{
//			return new Object[][] {{"iamannurose@gmail.com","Annu@2001","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};//Object is actually a parent data type which accepts any of the data types like int, string etc..
//		}
		
//		@DataProvider
//		public Object[][] getData()
//		{
//		HashMap <String,String>map = new HashMap();//---> here the datatype of key-value pair can be anything so used object datatype
//		//chaged the dataType to String to provide consistancy to method submitOrder().
//		map.put("email", "iamannurose@gmail.com");
//		map.put("password", "Annu@2001");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap <String,String>map1 = new HashMap();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		}
//
		
	}


