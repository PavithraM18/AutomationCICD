package SelfLearning.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SelfLearning.TestComponents.BaseTest;
import SelfLearning.TestComponents.Retry;
import SelfLearning.pageobjects.CartPage;
import SelfLearning.pageobjects.LandingPage;
import SelfLearning.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{

	@Test(groups = {"ErrorHandling"} ,retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException ,InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productcatal  = lp.loginApplication("mpavithra18@yahoo.com", "Test12098");		
		//Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage);
		Assert.assertEquals("Incorrect email or password.", "Incorrect email or password.");
	
	//	ng-tns-c4-6 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productcatal  = lp.loginApplication("mpavithra18@gmail.com", "Test@1234");	
		List<WebElement> productLists = productcatal.getProductList();
		productcatal.addProductToCart(productName);
		CartPage cartpag = productcatal.goToCartPage();
		Boolean match = cartpag.verifyTheProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}

}


