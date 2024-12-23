package RahulShettyAcademy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ProductCatelogue extends AbstractComponent {
	WebDriver driver;
	public ProductCatelogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
@FindBy(css=".mb-3")
List<WebElement> products;

@FindBy(css=".ng-animating")
WebElement spiner;

 By productsBy = By.cssSelector(".mb-3");
 By addtocart= By.cssSelector(".card-body button:last-of-type");
 By toastmessage = By.cssSelector("#toast-container");
 
public List<WebElement> getProdutsList()
{
	WaitForElementToAppear(productsBy);
	return products;
}
public WebElement getProductByName(String productName )
{
	WebElement prod = getProdutsList().stream().filter(product -> // filter desired product
	product.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(productName)).findFirst()
			.orElse(null);
	return prod;
}
public void AddToCart(String productName ) throws InterruptedException
{
	WebElement prod = getProductByName( productName) ;
			prod.findElement(addtocart).click(); // product added to the cart
			WaitForElementToAppear(toastmessage);
			WaitForElementToDisappear(spiner);
}


//driver.findElement(By.cssSelector(".totalRow button")).click(); // CheckOut Button

//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); // list for cart element







}
