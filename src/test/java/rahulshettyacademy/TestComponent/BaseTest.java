//package rahulshettyacademy.TestComponent;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Properties;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//
//import RahulShettyAcademy.PageObject.LandingPage;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class BaseTest {
//    public WebDriver driver;
//
//    public WebDriver initializeDriver() throws IOException {
//        Properties prop = loadProperties();
//        String browserName = prop.getProperty("browser");
//
//        if (browserName.equalsIgnoreCase("chrome")) {        
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//        } else if (browserName.equalsIgnoreCase("firefox")) {
//            // Initialize Firefox driver
//        } else if (browserName.equalsIgnoreCase("edge")) {
//            System.setProperty("webdriver.Edge.driver", "edge.exe");
//            driver = new EdgeDriver();
//        }
//        driver.manage().window().maximize(); // maximize the window
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // wait
//        return driver;
//    }
//
//    private Properties loadProperties() throws IOException {
//        Properties prop = new Properties();
//        String filePath = "D:\\Swati\\eclipse-workspace-20241031T141546Z-001\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\RahulShettyAcademy\\Resources\\GlobalData.properties";
//        FileInputStream fis = new FileInputStream(filePath);
//        prop.load(fis);
//        fis.close();
//        return prop;
//    }
//
//    public LandingPage launchApplication() throws IOException {
//        driver = initializeDriver();
//        LandingPage landingpage = new LandingPage(driver);
//        landingpage.goTo();
//        return landingpage;
//    }
//}

package rahulshettyacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = loadProperties();
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// in above statement create one condition that checks is there any declaration
		// of browser in cmd if yes then follow the same else follow the global data
		// from base test

		if (browserName.contains("chrome")) {
			System.setProperty("WebDriver.chrome.driver", "C:\\Users\\Sourabh Patil\\Documents\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));// to avoid fleckyness at backend execution we
																		// have to do the execution in full screen mode
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// Initialize Firefox driver
			WebDriverManager.firefoxdriver().setup();
			System.setProperty("WebDriver.gecko.driver",
					"C:\\Users\\Sourabh Patil\\Documents\\geckodriver-v0.35.0-win-aarch64.exe");
			WebDriver driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			System.setProperty("WebDriver.Edge.driver", "C:\\Users\\Sourabh Patil\\Documents\\edge.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize(); // maximize the window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // wait
		return driver;
	}

	// getJsonDataToMap
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert string to HashMap- Jackson Datbind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot tc = (TakesScreenshot) driver;
		File source = tc.getScreenshotAs(OutputType.FILE);
		File file = new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\Report" + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\Report" + ".png";

	}

	private Properties loadProperties() throws IOException {
		Properties prop = new Properties();
//        String filePath = "D:\\Swati\\eclipse-workspace-20241031T141546Z-001\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\RahulShettyAcademy\\Resources\\GlobalData.properties";
//        FileInputStream fis = new FileInputStream(filePath);
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\RahulShettyAcademy\\Resources\\GlobalData.properties");
		prop.load(fis);
		fis.close();
		return prop;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.close();
	}
}
