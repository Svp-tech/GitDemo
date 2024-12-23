package RahulShettyAcademy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class PlacingOrderPage extends AbstractComponent {
	WebDriver driver;
public PlacingOrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

}
@FindBy(css="[placeholder='Select Country']")
WebElement Autosuggest;

@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
WebElement AutoList;

@FindBy(css =".btnn.action__submit.ng-star-inserted")
WebElement placeorder;

By allele =By.cssSelector("[class*=ta-results]");
	
public orderConfirmPage getShipping(String CountryName )
	{
		Actions a = new Actions(driver); // handling autosuggestive dropdown
		a.sendKeys(Autosuggest, CountryName).build().perform();
		WaitForElementToAppear(allele);
		AutoList.click();
		placeorder.click();
		orderConfirmPage orderconfirmpage= new orderConfirmPage(driver);
return orderconfirmpage;
	}	
}
