package SelfLearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SelfLearning.abstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent {

	public static String getErrorMessage;
	//public  String getErrorMessage;
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		/*this.driver refers to local driver created in the class. driver1 is only scope for constructor. 
		 * driver1 brings value from the SubmitOrderTest webdriver value
		*/
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory-- you can declare web elements using below annotations.
	//POM should have elements and actions alone, input parameters should be send via main class.
	
	@FindBy(id = "userEmail")
	WebElement Emailid;
	//assigned to the variable 'Emailid' followed by the annotation. in the run time , it will constructed as driver.findElement(By.id("userEmail"))
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(css = "input[id='login']")
	WebElement submit;
	
	//@FindBy(css = "[class* = 'flyInOut']")
//	@FindBy(xpath = "//*[@id='toast-container']//div[1]//div")
//	WebElement errormsg;
	
	//@FindBy(xpath="//*[@id='toast-container']") 
     @FindBy(css = "[class* = 'flyInOut']")
	  WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email,String pw)
	{
		Emailid.sendKeys(email);
		password.sendKeys(pw);
		submit.click();
		ProductCatalogue productcatal = new ProductCatalogue(driver);
		return productcatal;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}