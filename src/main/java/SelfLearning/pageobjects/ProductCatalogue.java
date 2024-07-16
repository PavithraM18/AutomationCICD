package SelfLearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SelfLearning.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		// page elements passed the life driver from main page to this constructor using Super keyword 
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

		
	@FindBy(css = ".col-lg-4")
	List<WebElement> ProductsList;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
		
	By productsby = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.xpath("//div[@id='toast-container']");
		
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsby);
		return ProductsList;
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