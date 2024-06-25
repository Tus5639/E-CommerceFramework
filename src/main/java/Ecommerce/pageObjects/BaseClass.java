package Ecommerce.pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	String url = "https://rahulshettyacademy.com/client";

	public void initialisation() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Ecommerce\\data\\GlobalData.properties");
		prop.load(fis);
		String browser_name = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browser_name = prop.getProperty("browser");

		if (browser_name.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();

			if (browser_name.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(url);
		}

		if (browser_name.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
		}
		if (browser_name.equalsIgnoreCase("edge")) {
//			driver = new ChromeDriver();
//			driver.manage().window().maximize();
//			driver.get(url);
		}
	}

	public void WaitClass(By find) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(find));
	}

	public void WaitToInvisible(By find) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(find));
	}

	@BeforeMethod(alwaysRun = true)
	public void StartInitialisation() throws IOException {
		try {
			initialisation();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void close() {
		driver.close();
	}

	public List<HashMap<String, String>> convertJsontoString(String filepath) throws IOException {

		String file = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper objectmap = new ObjectMapper();
		List<HashMap<String, String>> data = objectmap.readValue(file,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public String takeScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination
		File DestFile = new File(System.getProperty("user.dir") + "\\screenShot\\" + testcaseName + ".png");

		// Copy file to destination
		FileUtils.copyFile(SrcFile, DestFile);
		return System.getProperty("user.dir") + "\\screenshot\\" + testcaseName + ".png";
	}

}
