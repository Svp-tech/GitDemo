package RahulShettyAcademy.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
	 public OrderPage(WebDriver driver) {
	        super(driver);
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(css = "tr td:nth-child(3)")
	    List<WebElement> Orders;

	    @FindBy(css = ".totalRow button")
	    WebElement Checkout;

	    public boolean verifyOrderDisplay(String productName) 
	    {
	        Boolean match = Orders.stream()
	            .anyMatch(Product -> Product.getText().equalsIgnoreCase(productName));
	        return match;
	    }

	   
}
