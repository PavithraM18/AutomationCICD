package SelfLearning.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("mpavithra18@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
		driver.findElement(By.cssSelector("input[id='login']")).click();
		
		
		//List<WebElement> prodList = driver.findElements(By.className(".col-lg-4.col-md-6.col-sm-10.offset-md-0.offset-sm-1.mb-3.ng-star-inserted"));
		String item = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".col-lg-4")));
		List<WebElement> productLists = driver.findElements(By.cssSelector(".col-lg-4"));
		
		WebElement prod = productLists.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst().orElse(null); 
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean cartProd = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(item));
		Assert.assertTrue(cartProd);
		
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		
		String paymentmode = driver.findElement(By.cssSelector(".payment__type.payment__type--cc.active")).getText();
		System.out.println(paymentmode);
		
		
		WebElement date = driver.findElement(By.xpath("//select[@class= 'input ddl'][1]"));
		Select dt =  new Select(date);
		dt.selectByVisibleText("05");
		System.out.println("Selected Date" +dt.getFirstSelectedOption().getText());
		
		WebElement year = driver.findElement(By.xpath("//select[@class= 'input ddl'][2]"));
		Select yr =  new Select(year);
		yr.selectByVisibleText("27");
		System.out.println("Selected Year" + yr.getFirstSelectedOption().getText());	 
		
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("123");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Pavithra");
		//driver.findElement(By.xpath("(//input[name='coupon']")).sendKeys("mpavithra18@gmail.com");
		
				
		Actions ac = new Actions(driver);
		ac.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
					Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(".action__submit")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));  
		
		//driver.close();
	}

}
