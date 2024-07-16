package SelfLearning.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SelfLearning.TestComponents.BaseTest;
import SelfLearning.pageobjects.CartPage;
import SelfLearning.pageobjects.CheckOutPage;
import SelfLearning.pageobjects.ConfirmationPage;
import SelfLearning.pageobjects.LandingPage;
import SelfLearning.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage lp;
	public ProductCatalogue productcatal;  
	public CartPage cartpag;
	public CheckOutPage CheckOutPag ;
	public ConfirmationPage ConfirmationPag ;
	public String confirmMsg ;
	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
	    
		lp = launchApplication();
	    
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username , String password) {
	    
		productcatal  = lp.loginApplication(username,password);	
	}

	@When("^I add the product (.+) to cart$")
	public void i_add_the_product_to_cart(String productName) {
		List<WebElement> productLists = productcatal.getProductList();
		productcatal.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
	    cartpag  = productcatal.goToCartPage();			
		boolean cartProd = cartpag.verifyTheProductDisplay(productName);
		Assert.assertTrue(cartProd);
		CheckOutPag = cartpag.goToCheckOut();
		String paymentmode  = CheckOutPag.getPaymentMode();
		System.out.println(paymentmode);
		CheckOutPag.enterCardDetails("123", "Pavithra");
		CheckOutPag.selectCountry("India");		
		ConfirmationPag = CheckOutPag.submitOrder();
	}

	@Then("Verify the message {string} is diplayed on Confirmation page")
	public void verify_the_message_is_diplayed_on_confirmation_page(String string) {
		confirmMsg = ConfirmationPag.getConfrimationmsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string)); 
		driver.close();
	}

	
	@Then("^\"([^\"]*)\" message is dispalyed")
	public void message_is_dispalyed(String string) {
	    
		Assert.assertEquals(string, "Incorrect email or password");
		driver.close();
	}

}
