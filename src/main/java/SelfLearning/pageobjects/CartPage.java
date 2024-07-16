package SelfLearning.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SelfLearning.abstractComponents.AbstractComponent;


public class CartPage extends AbstractComponent{

	WebDriver driver;
	public CartPage(WebDriver driver)
	{	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css= ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement Checkout;
	
	public boolean verifyTheProductDisplay(String productName)
	{
		boolean cartProd = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return cartProd;
	}
	
	public CheckOutPage goToCheckOut()
	{
		Checkout.click();
		return new CheckOutPage(driver);
		
	}
	
}