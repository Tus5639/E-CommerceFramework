package Ecommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends BaseClass {
	
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="td h1")
	
	WebElement confirmationMessage;
	
	By mesage = By.cssSelector("td h1");
	
	public String ConfirmationMessage() {
		WaitClass(mesage);
		String Message = confirmationMessage.getText();
		return Message;
	}

}
