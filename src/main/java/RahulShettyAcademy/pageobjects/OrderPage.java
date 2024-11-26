package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	public OrderPage(WebDriver driver)//constructor of this class to write initialization code
	//the parameter passed to this constructor catches the driver object of the main class and then assign this to this particular pages driver.
	{
		super(driver);//every child class has to send driver to the parent
		this.driver = driver;// by this the scope of driver passed from the main class page is passed to this local page driver object.
		PageFactory.initElements(driver, this);//initElements method to initialize the PageFcatory elements conversion
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> orderList;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement Checkout;
	
	By ItemInCart = By.cssSelector(".cartSection h3");
	
	public Boolean verifyOrderDisplay(String productName) 
	{
		waitForElementToAppear(ItemInCart);
		Boolean match = orderList.stream().anyMatch(product->product.getText().equals(productName));
		return match;
	}
	
}
	