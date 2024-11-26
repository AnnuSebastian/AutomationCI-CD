package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)//constructor of this class to write initialization code
	//the parameter passed to this constructor catches the driver object of the main class and then assign this to this particular pages driver.
	{
		super(driver);//every child class has to send driver to the parent
		this.driver = driver;// by this the scope of driver passed from the main class page is passed to this local page driver object.
		PageFactory.initElements(driver, this);//initElements method to initialize the PageFcatory elements conversion
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}

}
