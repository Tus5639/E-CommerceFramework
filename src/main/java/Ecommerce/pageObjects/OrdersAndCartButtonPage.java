package Ecommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersAndCartButtonPage extends BaseClass {

	public OrdersAndCartButtonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li button [class='fa fa-shopping-cart']")
	WebElement CartButton;

	@FindBy(css = "li button i[class='fa fa-handshake-o']")
	WebElement OrderButton;

	By toastWait = By.cssSelector("div [id=\"toast-container\"]");

	By spinWait = By.cssSelector(".ng-animating");

	public void OrderClick() {
		WaitClass(toastWait);
		WaitToInvisible(spinWait);
		CartButton.click();

	}

	//
}
