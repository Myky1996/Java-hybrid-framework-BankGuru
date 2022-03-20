package pageObjects.bankGuru.payment;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.bankguru.CreateCustomerSuccessUI;
import pageUIs.bankguru.HompageUI;

public class CreateCustomerSuccessPageObject extends BasePage {
	private WebDriver driver;

	public CreateCustomerSuccessPageObject(WebDriver driver) {

		this.driver = driver;
	}
}
