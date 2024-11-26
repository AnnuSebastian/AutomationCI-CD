package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)//constructor of this class to write initialization code
	//the parameter passed to this constructor catches the driver object of the main class and then assign this to this particular pages driver.
	{
		super(driver);//every child class has to send driver to the parent
		this.driver = driver;// by this the scope of driver passed from the main class page is passed to this local page driver object.
		PageFactory.initElements(driver, this);//initElements method to initialize the PageFcatory elements conversion
	}
	
	@FindBy(xpath="//a[normalize-space()='Place Order']")
	WebElement Submit;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")
	WebElement selectIndia;
	
	By results = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();		
		waitForElementToAppear(results);
		selectIndia.click();
	}
	
	public ConfirmationPage submitOrder() 
	{
		Submit.click();
		return new ConfirmationPage(driver);
	}
	
}
	