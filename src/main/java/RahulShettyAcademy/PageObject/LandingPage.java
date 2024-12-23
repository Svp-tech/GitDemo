package RahulShettyAcademy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement UserEmail = driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");

@FindBy(id="userEmail")
WebElement UserEmail;

@FindBy(id="userPassword")
WebElement passwordEle;

@FindBy(id="login")
WebElement Submit;

@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]")
WebElement errormsg;

//div[aria-label='Incorrect email or password.'] name attribute is not available for this element   
public ProductCatelogue LoginApplication(String Email,String Password)
{
	UserEmail.sendKeys(Email);
	passwordEle.sendKeys(Password);
	Submit.click();
    ProductCatelogue productcatelogue = new ProductCatelogue(driver);
return productcatelogue;
}
public void goTo()
{
	driver.get("https://rahulshettyacademy.com/client"); // get url

}
public String getErrorMessage()
{
	WaitForWebElementToAppear(errormsg );
	String ErrorText= errormsg.getText();
	return ErrorText;
}

}
