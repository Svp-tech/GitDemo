package RahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.PageObject.CartPage;
import RahulShettyAcademy.PageObject.OrderPage;

public class AbstractComponent {
	
	
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement myorders;

	public void WaitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // explicit Wait

	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public  CartPage goToCart()
	{
		cart.click();
        CartPage cartPage = new CartPage(driver);
return cartPage;
			//driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); // go to the cart

	}
	
	public  OrderPage goToOrderPage()
	{
		myorders.click();
        OrderPage orderPage = new OrderPage(driver);
return orderPage;
			//driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); // go to the cart

	}
	
	public void WaitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // explicit Wait
//			wait.until(ExpectedConditions.invisibilityOf(ele));// wait to
	}
	public void WaitForWebElementToAppear(WebElement findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // explicit Wait

	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	
	
}
