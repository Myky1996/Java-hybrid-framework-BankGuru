package commons;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.bankGuru.payment.HompageObject;
import pageUIs.bankguru.BasePageUI;
import pageUIs.bankguru.CreateCustomerSuccessUI;

public class BasePage {
	public void openPageUrl(WebDriver driver, String pageURL) {
		driver.get(pageURL);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
		sleepInSecond(2);
	}

	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		waitForAlertPresence(driver).sendKeys(value);
	}

	public void switchWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowID = driver.getWindowHandles();
		for (String id : allWindowID) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}

	}

	public void switchWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String id : allWindowsID) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(windowTitle)) {
				break;
			}
		}
	}

	public void closeAllTabsWithoutParents(WebDriver driver, String parentID) {
		Set<String> allwindowsID = driver.getWindowHandles();
		for (String id : allwindowsID) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id).close();
			}
			driver.switchTo().window(parentID);

		}
	}

	public By getByLocator(String locatorType) {
		return By.xpath(locatorType);
	}

	public String getDynamicXpath(String locatorType, String... dynamicValues) {
		return locatorType = String.format(locatorType, (Object[]) dynamicValues);
	}

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValue) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValue)));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValue) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).click();
	}

	public void senKeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void senKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dyanmicValue) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dyanmicValue)), key).perform();
	}

	public void overrideImplicitTimeOut(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	public String getElementText(WebDriver driver, String locatorType, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... values) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, values)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectedItemInDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedItemInDefaultDropdown(WebDriver driver, String locatorType, String... values) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, values)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXPath, String itemValue) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(2);
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		List<WebElement> list = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXPath)));
		for (WebElement item : list) {
			if (item.getText().trim().equals(itemValue)) {
				JavascriptExecutor jsexcutor = (JavascriptExecutor) driver;
				jsexcutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}

		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, locatorType)).getAttribute(attributeName);
	}

	public String convertRgbaToHexa(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	public int getElementSize(WebDriver driver, String locatorType, String... values) {
		return getListWebElement(driver, getDynamicXpath(locatorType, values)).size();
	}

	public void checkToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckbox(WebDriver driver, String locatorType, String... values) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, values));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToDefaultCheckbox(WebDriver driver, String locatorType, String... values) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, values));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		List<WebElement> elements = getListWebElement(driver, locatorType);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType, String... values) {
		return getWebElement(driver, getDynamicXpath(locatorType, values)).isEnabled();

	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType, String... values) {
		driver.switchTo().frame(getWebElement(driver, getDynamicXpath(locatorType, values)));
	}

	public void switchToDefaultContent(WebDriver driver, String locatorType) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType, String... values) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, values))).perform();
	}

	public void scrollToBottomPage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void getElementValidationMsg(WebDriver driver, String locatorType) {
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript(" return arguments[0].validationMassage;", getWebElement(driver, locatorType));
	}

	public void removerAttributeInDOM(WebDriver driver, String locatorType, String attributRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributRemove + "');",
				getWebElement(driver, locatorType));

	}

	public void removerAttributeInDOM(WebDriver driver, String locatorType, String attributRemove, String... values) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributRemove + "');",
				getWebElement(driver, getDynamicXpath(locatorType, values)));

	}

	public boolean isjQueryJaxLoadSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");

			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete "
				+ "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, values))));
	}

	public void waitforAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	public void waitforAllElementVisible(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, values))));
	}

	public void waitforElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitforElementInvisible(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, values))));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));

	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, values))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType, String... values) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.long_timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, values))));
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	// payment

	public HompageObject loginSystemAsUser(WebDriver driver, String usernameValue, String passwordValue) {
		waitForElementVisible(driver, BasePageUI.USERNAME_LOGIN_TEXTBOX);
		senKeyToElement(driver, BasePageUI.USERNAME_LOGIN_TEXTBOX, usernameValue);
		senKeyToElement(driver, BasePageUI.PASSWORD_LOGIN_TEXTBOX, passwordValue);
		clickToElement(driver, BasePageUI.SUBMIT_LOGIN_BUTTON);
		return PageGeneratorManager.getHompageObject(driver);
	}

	public void openSubPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.SUBPAGE_BY_TEXTNAME, pageName);
		clickToElement(driver, BasePageUI.SUBPAGE_BY_TEXTNAME, pageName);
	}

	public void fillInTextbox(WebDriver driver, String textboxName, String textValue) {
		waitForElementClickable(driver, BasePageUI.TEXTBOX_BY_NAME, textboxName);
		senKeyToElement(driver, BasePageUI.TEXTBOX_BY_NAME, textValue, textboxName);
	}

	public void fillInTextarea(WebDriver driver, String textareaName, String textValue) {
		waitForElementClickable(driver, BasePageUI.TEXT_AREA_BY_NAME, textareaName);
		senKeyToElement(driver, BasePageUI.TEXT_AREA_BY_NAME, textValue, textareaName);
	}

	public void clickToButtonByName(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_NAME, buttonName);
		clickToElement(driver, BasePageUI.BUTTON_BY_NAME, buttonName);
	}

	public void clickToRadioButtonByName(WebDriver driver, String radioName) {
		waitForElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_NAME, radioName);
		checkToDefaultCheckbox(driver, BasePageUI.RADIO_BUTTON_BY_NAME, radioName);
	}

	public void setDateByName(WebDriver driver, String textboxName, String dateValue) {
		removerAttributeInDOM(driver, BasePageUI.TEXTBOX_BY_NAME, "type", textboxName);
		waitForElementClickable(driver, BasePageUI.TEXTBOX_BY_NAME, textboxName);
		senKeyToElement(driver, BasePageUI.BUTTON_BY_NAME, dateValue, textboxName);
	}

	public String getTextboxValueByText(WebDriver driver, String textboxName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_VALUE_BY_TEXT, textboxName);
		return getElementText(driver, BasePageUI.TEXTBOX_VALUE_BY_TEXT, textboxName);

	}

	public boolean isInfoDisplayed(WebDriver driver, String textboxName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_VALUE_BY_TEXT, textboxName);
		return isElementDisplayed(driver, BasePageUI.TEXTBOX_VALUE_BY_TEXT, textboxName);

	}

	public String getSuccessMessage(WebDriver driver, String msgText) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_BY_TEXT, msgText);
		return getElementText(driver, BasePageUI.SUCCESS_MESSAGE_BY_TEXT, msgText);
	}

	public void selectValueInDropdownByText(WebDriver driver, String dropdownName, String selectValue) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_TEXT, dropdownName);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_TEXT, selectValue, dropdownName);
	}

	public void clickToTextbox(WebDriver driver, String texboxName) {
		waitForElementClickable(driver, BasePageUI.TEXTBOX_BY_NAME, texboxName);
		clickToElement(driver, BasePageUI.TEXTBOX_BY_NAME, texboxName);
	}

	public boolean isValidationMessageDisplayed(WebDriver driver, String msgText) {
		waitForElementVisible(driver, BasePageUI.VALIDATION_MESSAGE_BY_TEXT, msgText);
		return isElementDisplayed(driver, BasePageUI.VALIDATION_MESSAGE_BY_TEXT, msgText);
	}

	public String getTextboxLabelByText(WebDriver driver, String textboxLabel) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_LABEL_BY_TEXT, textboxLabel);
		return getElementText(driver, BasePageUI.TEXTBOX_LABEL_BY_TEXT, textboxLabel);

	}
}
