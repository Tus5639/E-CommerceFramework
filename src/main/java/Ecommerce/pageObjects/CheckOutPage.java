package Ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends BaseClass {

	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class='cartSection'] h3")
	List<WebElement> product_catalogue;

	By product = By.cssSelector("div[class='cartSection'] h3");

	@FindBy(css = "li[class='totalRow'] button[class='btn btn-primary']")
	WebElement checkoutButton;

	public Boolean CheckOut(String productName) {
		WaitClass(product);
		Boolean prod = product_catalogue.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return prod;
	}

	public void CheckButton() {
		checkoutButton.click();
	}

}
