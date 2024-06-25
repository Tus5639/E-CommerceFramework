package Ecommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends BaseClass {

	public landingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement UserEmail;

	@FindBy(id = "userPassword")
	WebElement UserPassword;

	@FindBy(id = "login")
	WebElement submit;

	By errorfound = By.xpath("//div[@aria-label='Incorrect email or password.']");

	public void Ecommerce_login(String email, String password) {
		UserEmail.sendKeys(email);
		UserPassword.sendKeys(password);
		submit.click();
	}

	public WebElement error() {
		WaitClass(errorfound);
		return driver.findElement(errorfound);
	}

}
