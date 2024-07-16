package SelfLearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import SelfLearning.abstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css= "[placeholder = 'Select Country']")
	WebElement country;
	
	@FindBy(xpath= "//a[contains(text(),'Place Order')]")
	WebElement placeorder;
	
	@FindBy(css= ".ta-item:nth-of-type(2)")
	WebElement selectcountry;
	
	@FindBy(css = ".payment__type.payment__type--cc.active")
	WebElement paymode;
	
	@FindBy(xpath = "//select[@class= 'input ddl'][1]")
	WebElement date;
	
	@FindBy(xpath = "//select[@class= 'input ddl'][2]")
	WebElement year;
	
	@FindBy(xpath = "(//input[@type='text'])[2]")
	WebElement ccv;
	
	@FindBy(xpath = "(//input[@type='text'])[3]")
	WebElement name;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryname)
	{
		Actions ac = new Actions(driver);
		ac.sendKeys(country, countryname).build().perform();
		waitForElementToAppear(results);
		selectcountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)");
		placeorder.click();
		return new ConfirmationPage(driver);
		
	}
	
	public String getPaymentMode()
	{
		return paymode.getText();
	}
	
	public void enterCardDetails(String no , String value)
	{
		Select dt =  new Select(date);
		dt.selectByVisibleText("05");
		System.out.println("Selected Date" +dt.getFirstSelectedOption().getText());
		Select yr =  new Select(year);
		yr.selectByVisibleText("27");
		System.out.println("Selected Year" + yr.getFirstSelectedOption().getText());	 
		ccv.sendKeys(no);
		name.sendKeys(value);
	}
}
