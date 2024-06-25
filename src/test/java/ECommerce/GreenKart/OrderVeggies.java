package ECommerce.GreenKart;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderVeggies {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

		String[] veggies = { "Beetroot", "Brinjal", "Beans" };
		List<WebElement> products = driver.findElements(By.cssSelector("div h4"));
		System.out.println(veggies.length);
		for (int i = 0; i < veggies.length; i++) {
			Thread.sleep(5000);
			System.out.println("ina loop");
			String[] vegetableName = products.get(i).getText().split("-");
			String veggiesTobeOrdered = vegetableName[0].trim();

			List<String> itemstoAddtoCart = Arrays.asList(veggies);
			if (itemstoAddtoCart.contains(veggiesTobeOrdered)) {
				System.out.println("In if loop");
				driver.findElements(By.cssSelector("div[class='product-action'] button[type='button']")).get(i).click();
			}

		}

	}

}
