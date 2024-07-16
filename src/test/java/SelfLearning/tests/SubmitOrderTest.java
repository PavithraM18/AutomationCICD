package SelfLearning.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SelfLearning.TestComponents.BaseTest;
import SelfLearning.pageobjects.CartPage;
import SelfLearning.pageobjects.CheckOutPage;
import SelfLearning.pageobjects.ConfirmationPage;
import SelfLearning.pageobjects.LandingPage;
import SelfLearning.pageobjects.OrderPage;
import SelfLearning.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData" , groups = "purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		
		ProductCatalogue productcatal  = lp.loginApplication(input.get("email"),input.get("password"));	
		//ProductCatalogue productcatal  = lp.loginApplication(email, password);		
		List<WebElement> productLists = productcatal.getProductList();
		productcatal.addProductToCart(input.get("product"));
		CartPage cartpag  = productcatal.goToCartPage();			
		boolean cartProd = cartpag.verifyTheProductDisplay(input.get("product"));
		Assert.assertTrue(cartProd);
		CheckOutPage CheckOutPag = cartpag.goToCheckOut();
		String paymentmode  = CheckOutPag.getPaymentMode();
		System.out.println(paymentmode);
		CheckOutPag.enterCardDetails("123", "Pavithra");
		CheckOutPag.selectCountry("India");		
		ConfirmationPage ConfirmationPag = CheckOutPag.submitOrder();
		String confirmMsg = ConfirmationPag.getConfrimationmsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));  
				
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productcatal  = lp.loginApplication("mpavithra18@gmail.com", "Test@1234");		
		OrderPage orderPag  = productcatal.goToOrderPage();
		orderPag.verifyOrderDisplay(productName);
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "mpavithra18@gmail.com");
		map.put("password", "Test@1234");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "mpavithra18@gmail.com");
		map1.put("password", "Test@1234");
		map1.put("product", "ADIDAS ORIGINAL");
		return new Object[][] { {map}, {map1} };
	 }
	

	/*public Object[][] getData() {
	 * return new Object[][] { {"mpavithra18@gmail.com", "Test@1234","ZARA COAT 3"} ,  {"mpavithra18@gmail.com", "Test@1234","ADIDAS ORIGINAL"}};
	 */
	
	/*
	 * List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\pavithra.mathivanan\\OneDrive - InnovaSolutions\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\SelfLearning\\data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	 * 
	*/
}


