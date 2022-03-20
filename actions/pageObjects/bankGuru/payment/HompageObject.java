package pageObjects.bankGuru.payment;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.bankguru.HompageUI;

public class HompageObject extends BasePage {
	private WebDriver driver;

	public HompageObject(WebDriver driver) {
		this.driver = driver;
	}

}
