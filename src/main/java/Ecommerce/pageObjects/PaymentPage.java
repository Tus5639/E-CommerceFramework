package Ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BaseClass {

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement Select_Country;

	@FindBy(xpath = "//div[@class='details__user']//div[@class='actions']/a")
	WebElement placeOrderButton;

	By CountryList = By.cssSelector("section[class='ta-results list-group ng-star-inserted'] button");
	@FindBy(css = "section[class='ta-results list-group ng-star-inserted'] button")
	List<WebElement> CountryAvailable;

	public void payment(String countryCode, String countryName) {
		Select_Country.sendKeys(countryCode);
		WaitClass(CountryList);
		for (WebElement option : CountryAvailable) {
			if (option.getText().equalsIgnoreCase(countryName)) {
				option.click();
				break;
			}
		}
	}

	public void placeOrder() {
		placeOrderButton.click();
	}

}
