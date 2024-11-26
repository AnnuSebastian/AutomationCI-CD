package RahulShettyAcademy.Tests;

import java.time.Duration;
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

import RahulShettyAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestCopy {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//implicit wait globally
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//creating object for explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		//opening the URL
		driver.get("https://rahulshettyacademy.com/client/");

		//creating object of the page object classes to pass driver object created.
		LandingPage landingpage = new LandingPage(driver);
		String productName = "ZARA COAT 3";
		
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("iamannurose@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Annu@2001");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List <WebElement> ProductsListed = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = ProductsListed.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//explicit wait - wait until the toast msg appears
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//wait until the loading animation disappears
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//after all this operations click on cart
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		Assert.assertTrue(match);
		//what happens here is using stream we scan all the product names for ZARA COAT 3, here instaed of filetr we used anyMatch it returns boolean values
		//true and false. Since we have added the product to cart it will be present in the page and the stream returns trye value.
		//with assertion we check whether the returned value is true or false if true only the Assert.assertTrue(match) returns true and continues execution, if false the execution stops here.

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(300);
		
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		
		driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")).click();	
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		
		String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(ConfirmMessage);
		System.out.println("Test passed");
		driver.close();		
	}

}
