package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)//constructor of this class to write initialization code
	//the parameter passed to this constructor catches the driver object of the main class and then assign this to this particular pages driver.
	{
		super(driver);//every child class has to send driver to the parent
		this.driver = driver;// by this the scope of driver passed from the main class page is passed to this local page driver object.
		PageFactory.initElements(driver, this);//initElements method to initialize the PageFcatory elements conversion
	}

	//List <WebElement> ProductsListed = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List <WebElement> ProductsListed;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);//action method created in AbstractComponent class
		return ProductsListed;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) 
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
