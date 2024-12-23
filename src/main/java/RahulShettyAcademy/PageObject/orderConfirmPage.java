package RahulShettyAcademy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class orderConfirmPage extends AbstractComponent {
    WebDriver driver;

    public orderConfirmPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement Text;

    public String getConfirmMsg() {
        WaitForElementToAppear(By.cssSelector(".hero-primary"));
        return Text.getText();
    }
}
