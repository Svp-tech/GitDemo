package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RahulShettyAcademy.PageObject.CartPage;
import RahulShettyAcademy.PageObject.LandingPage;
import RahulShettyAcademy.PageObject.PlacingOrderPage;
import RahulShettyAcademy.PageObject.ProductCatelogue;
import RahulShettyAcademy.PageObject.orderConfirmPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponent.BaseTest;

public class StepDefinationImplimentation extends BaseTest {
	public LandingPage landingpage;
	public ProductCatelogue productcatelogue;
	public  orderConfirmPage orderconfirmpage;
	@Given("I landed on Eccomerce Page")                        //it is static statement no regular expression is added over here
	public void I_landed_on_Eccomerce_Page() throws IOException
	{
		landingpage=launchApplication();
	}
    @Given("^Logged in with UserName (.+) and the Password (.+)$")    //^$ we used this bcz of regX means regular expression inside(.+) it
    public void Logged_in_with_UserName_and_Password(String userName, String password)
    {
         productcatelogue = landingpage.LoginApplication(userName, password);
    }
    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException
    {
    	List<WebElement> Products = productcatelogue.getProdutsList();

        productcatelogue.AddToCart(productName);
    }
    @When("^Checkout (.+) in (.+) and submit the order$")
    public void checkout_and_submit_order(String productName, String CountryName) {
        System.out.println("Step: Checkout and submit order");

        // Go to cart and verify product
        CartPage cartPage = productcatelogue.goToCart();
        Boolean match = cartPage.verifyproductDisplay(productName);
        Assert.assertTrue(match);

        // Proceed to checkout
        PlacingOrderPage orderplace = cartPage.goToCheckOut();
        System.out.println("Proceeding to checkout");

        // Shipping and confirmation
        orderconfirmpage = orderplace.getShipping(CountryName);
        System.out.println("Order submitted");
    }

  //  Then "THANKYOU FOR THE ORDER" message is displayed on ConfirmationPage <-here it is data present in the  statement, so we cant give the regX
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_ConfirmationPage(String string)
    {
    	 String confirmmsg = orderconfirmpage.getConfirmMsg();
    	 System.out.println(confirmmsg);
         Assert.assertTrue(confirmmsg.equalsIgnoreCase(string));
    }
    
    @Then ("^\"([^\"]*)\" this message displayed$")
    public void error_message_displayed(String strArg) throws Throwable
    {
		Assert.assertEquals(strArg, landingpage.getErrorMessage());
		driver.close();
    }
    



	
}
