package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		String CountryName = "India";

		//ChromeOptions options = new ChromeOptions();

		//options.addArguments("--remote-allow-origins=*");

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximize the window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // wait

		driver.get("https://rahulshettyacademy.com/client"); // get url

		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
        // LandingPage landingpage =new LandingPage(driver);
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");

		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // explicit Wait

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); // made list of all products

		// List<WebElement> products =
		// driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));

		WebElement prod = products.stream().filter(product -> // filter desired product
		product.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		// product.findElement(By.xpath(".//div[@class='card-body']//b")).getText().equals(productName)).findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); // product added to the cart
		// prod.findElement(By.xpath(".//div[@class='card-body']/button[2]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // wait for
																										// msg-product
																										// is added

		// ng-animating

		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));// wait to
			Thread.sleep(2000)	;																							// invisible
																											// loading
																											// icon

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); // go to the cart

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); // list for cart element

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click(); // CheckOut Button

		Actions a = new Actions(driver); // handling autosuggestive dropdown
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), CountryName).build().perform();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class*=ta-results]")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click(); // PlaceOrder button
		
		String confirmmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("Thankyou for the order.")); // order confirmation
		driver.close();
	}

}
