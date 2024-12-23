package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.PageObject.CartPage;
import RahulShettyAcademy.PageObject.ProductCatelogue;
import rahulshettyacademy.TestComponent.BaseTest;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void LogInErrorValidation() throws IOException, InterruptedException {

		
		landingpage.LoginApplication("pujashetty10@gmail.com", "Puj@123");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}
	
	@Test
	 public void ProductErrorValidation() throws IOException, InterruptedException {

        String productName = "ZARA COAT 3";
        String CountryName = "India";


        ProductCatelogue productcatelogue = landingpage.LoginApplication("pujashetty10@gmail.com", "Puja@123");

        List<WebElement> Products = productcatelogue.getProdutsList();

        productcatelogue.AddToCart(productName);

        CartPage cartPage = productcatelogue.goToCart();

        Boolean match = cartPage.verifyproductDisplay(productName);
        Assert.assertTrue(match);

        
            }
}
