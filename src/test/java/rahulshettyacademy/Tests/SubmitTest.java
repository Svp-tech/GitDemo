package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.PageObject.CartPage;
import RahulShettyAcademy.PageObject.OrderPage;
import RahulShettyAcademy.PageObject.PlacingOrderPage;
import RahulShettyAcademy.PageObject.ProductCatelogue;
import RahulShettyAcademy.PageObject.orderConfirmPage;
import rahulshettyacademy.TestComponent.BaseTest;

public class SubmitTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider="getData", groups= {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        String CountryName = "India";


        ProductCatelogue productcatelogue = landingpage.LoginApplication(input.get("email"), input.get("password"));

        List<WebElement> Products = productcatelogue.getProdutsList();

        productcatelogue.AddToCart(input.get("product"));

        CartPage cartPage = productcatelogue.goToCart();

        Boolean match = cartPage.verifyproductDisplay(input.get("product"));
        Assert.assertTrue(match);

        PlacingOrderPage orderplace = cartPage.goToCheckOut();

        orderConfirmPage orderconfirmpage = orderplace.getShipping(CountryName);

        String confirmmsg = orderconfirmpage.getConfirmMsg();
        Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order."));
            }

    @Test(dependsOnMethods= {"submitOrder"})
  public void OrderHistory()
    {
        ProductCatelogue productcatelogue = landingpage.LoginApplication("pujashetty10@gmail.com", "Puja@123");

        OrderPage orderPage = productcatelogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }
    
   
    
    
    
    
    
    //To run the program with multiple DataSets
   // @DataProvider
//    public Object[][] getData()
//    {
//    	return new Object[] [] {
//    		{"pujashetty10@gmail.com", "Puja@123","ZARA COAT 3"},
//    		{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}
//    		};
//    }
    
    
    //Above method there are only 3 parameter for many parameter we can use HashMap
//    @DataProvider
//    public Object[][] getData()
//    {
//HashMap <String,String> map = new HashMap<String,String>()	;
//map.put("email", "pujashetty10@gmail.com");
//map.put("password", "Puja@123");
//map.put("product", "ZARA COAT 3");
//
//HashMap <String,String> map1 = new HashMap<String,String>()	;
//map1.put("email", "shlok23@gmail.com");
//map1.put("password", "Shlok&123");
//map1.put("product", "ADIDAS ORIGINAL");
//return new Object[] [] {{map},{map1}};
//    		
//    		}
    			
    
    
    //data drive by using json file
    @DataProvider
  public Object[][] getData() throws IOException
  {
    	List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
    	return new Object[] [] {{data.get(0)},{data.get(1)}};
  }
}
