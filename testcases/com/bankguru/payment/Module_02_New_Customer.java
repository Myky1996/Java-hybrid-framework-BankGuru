package com.bankguru.payment;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.bankGuru.payment.HompageObject;
import pageObjects.bankGuru.payment.LoginPageObject;
import pageObjects.bankGuru.payment.NewCustomerPO;

public class Module_02_New_Customer extends BaseTest {
	WebDriver driver;
	NewCustomerPO newCustomerPage;
	LoginPageObject loginPage;
	HompageObject homepage;
	String userName, userPassword;
	String customerID, customerName, dob, address, city, state, PIN, mobileNum, email, emailPassword;
	String editAddress, editDob, editCity, editState, editPIN, editMobileNum, editEmail;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		userName = "mngr392664";
		userPassword = "dezyzUp";

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "' ");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 02: Login with User account");
		homepage = loginPage.loginSystemAsUser(driver, userName, userPassword);
		homepage.openSubPageByName(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

	}

	@Test
	public void TC_01_Verify_Name_Field() {
		log.info("NC01 - Name can not be empty");
		newCustomerPage.fillInTextbox(driver, "name", "");
		newCustomerPage.clickToTextbox(driver, "city");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Customer name must not be blank"));

		log.info("NC02 - Name can not be numeric");
		newCustomerPage.fillInTextbox(driver, "name", "name123");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Numbers are not allowed"));

		log.info("NC03 - Name can not have special characrers");
		newCustomerPage.fillInTextbox(driver, "name", "name@!#");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Special characters are not allowed"));

		log.info("NC04 - Name can not have 1st characrer as a blank space");
		newCustomerPage.fillInTextbox(driver, "name", " name");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "First character can not have space"));
	}

	@Test
	public void TC_02_Verify_Address_Field() {
		log.info("NC05 - Address can not be empty");
		newCustomerPage.fillInTextarea(driver, "addr", "");
		newCustomerPage.clickToTextbox(driver, "city");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Address Field must not be blank"));

		log.info("NC06 - Address can not have 1st characrer as a blank space");
		newCustomerPage.fillInTextarea(driver, "addr", " address");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "First character can not have space"));

	}
	
 @Test
	
    public void TC_03_Verify_City_Field() {
		log.info("NC08 - City can not be empty");
		newCustomerPage.fillInTextbox(driver, "city", "");
		newCustomerPage.clickToTextbox(driver, "name");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "City Field must not be blank"));

		log.info("NC09 - City can not be numeric");
		newCustomerPage.fillInTextbox(driver, "city", "city123");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Numbers are not allowed"));

		log.info("NC010 - City can not have special characrers");
		newCustomerPage.fillInTextbox(driver, "city", "city@!#");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Special characters are not allowed"));

		log.info("NC011 - City can not have 1st characrer as a blank space");
		newCustomerPage.fillInTextbox(driver, "city", " city");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "First character can not have space"));

	}

	@Test
	public void TC_04_Verify_State_Field() {
		log.info("NC12 - City can not be empty");
		newCustomerPage.fillInTextbox(driver, "state", "");
		newCustomerPage.clickToTextbox(driver, "name");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "State must not be blank"));

		log.info("NC13 - City can not be numeric");
		newCustomerPage.fillInTextbox(driver, "state", "state123");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Numbers are not allowed"));

		log.info("NC014 - City can not have special characrers");
		newCustomerPage.fillInTextbox(driver, "state", "state@!#");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Special characters are not allowed"));

		log.info("NC015 - City can not have 1st characrer as a blank space");
		newCustomerPage.fillInTextbox(driver, "state", " state");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "First character can not have space"));

	}

	@Test
	public void TC_05_Verify_PIN_Field() {
		log.info("NC016 - PIN can not be empty");
		newCustomerPage.fillInTextbox(driver, "pinno", "");
		newCustomerPage.clickToTextbox(driver, "name");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "PIN Code must not be blank"));

		log.info("NC17 - City must be numeric");
		newCustomerPage.fillInTextbox(driver, "pinno", "123PIN");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Characters are not allowed"));

		log.info("NC018 - PIN must have 6 digits");
		newCustomerPage.fillInTextbox(driver, "pinno", "123");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "PIN Code must have 6 Digits"));

		log.info("NC019 - PIN can not have special characrers");
		newCustomerPage.fillInTextbox(driver, "pinno", "123!@#");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Special characters are not allowed"));

		log.info("NC020 - PIN can not have blank space");
		newCustomerPage.fillInTextbox(driver, "pinno", " 123");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "First character can not have space"));

	}

	@Test
	public void TC_06_Verify_Telephone_Field() {
		log.info("NC022 - Telephone can not be empty");
		newCustomerPage.fillInTextbox(driver, "telephoneno", "");
		newCustomerPage.clickToTextbox(driver, "name");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Mobile no must not be blank"));

		log.info("NC023 - Telephone can not have 1st characrer as a blank space");
		newCustomerPage.fillInTextbox(driver, "telephoneno", " 12345678");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "First character can not have space"));

		log.info("NC025 - PIN can not have special characrers");
		newCustomerPage.fillInTextbox(driver, "telephoneno", "886636!12");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Special characters are not allowed"));

	}

	@Test
	public void TC_07_Verify_Email_Field() {
		log.info("NC022 - Telephone can not be empty");
		newCustomerPage.fillInTextbox(driver, "emailid", "");
		newCustomerPage.clickToTextbox(driver, "name");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Email-ID must not be blank"));

		log.info("NC023 - Email must be in correcr format");
		newCustomerPage.fillInTextbox(driver, "emailid", "guru99@gmail");
		verifyTrue(newCustomerPage.isValidationMessageDisplayed(driver, "Email-ID is not valid"));

	}
	
	@Test
	public void TC_08_Verify_All_Labels() {
		log.info("NC022 - Verify all Labels");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"Customer Name"),"Customer Name");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"Gender"),"Gender");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"Address"),"Address");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"City"),"City");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"State"),"State");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"PIN"),"PIN");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"E-mail"),"E-mail");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"Password"),"Password");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"Date of Birth"),"Date of Birth");
		verifyEquals(newCustomerPage.getTextboxLabelByText(driver,"Mobile Number"),"Mobile Number");

	}

	
}
