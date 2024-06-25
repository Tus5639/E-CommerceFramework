package Ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Products_Catalogue extends BaseClass {

	public Products_Catalogue(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "div[class='card']")
	List<WebElement> product_list;
	By product = By.cssSelector("div[class='card']");

	By product_name = By.cssSelector("h5 b");

	By addtoCartButton = By.cssSelector("button[class='btn w-10 rounded']");

	public List<WebElement> product_visible() {
		WaitClass(product);
		return product_list;

	}

	public WebElement getProductName(String productToBeSelected) {
		WaitClass(product_name);
		WebElement product = product_visible().stream()
				.filter(prod -> prod.findElement(By.cssSelector("h5 b")).getText().equalsIgnoreCase(productToBeSelected))
				.findFirst().orElse(null);
		return product;
	}

	public void AddtoCart(String productToBeSelected) {
		WebElement pro = getProductName(productToBeSelected);
		pro.findElement(addtoCartButton).click();

	}

	// div h5 b

	@FindBy(css = "div h5 b")
	List<WebElement> products;

//	public void AddProduct(String productToBeSelected) {
//		WaitClass(product);
//		for(WebElement option:products) {
//			if(option.getText().equalsIgnoreCase(productToBeSelected)) {
//				driver.findElement(addtoCartButton).click();
//				break;
//			}
//		}
//	}

}
