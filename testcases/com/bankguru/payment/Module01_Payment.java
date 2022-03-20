package com.bankguru.payment;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.bankGuru.payment.CreateCustomerSuccessPageObject;
import pageObjects.bankGuru.payment.EditCustomerInfoPO;
import pageObjects.bankGuru.payment.HompageObject;
import pageObjects.bankGuru.payment.LoginPageObject;
import pageObjects.bankGuru.payment.NewAccountPO;
import pageObjects.bankGuru.payment.NewCustomerPO;

public class Module01_Payment extends BaseTest {
	WebDriver driver;
	HompageObject homepage;
	LoginPageObject loginPage;
	NewCustomerPO newCustomerPage;
	NewAccountPO newAccountPage;
	CreateCustomerSuccessPageObject createSuccessCustomerPage;
	EditCustomerInfoPO editCustomerInfoPage;
	String userName, userPassword;
	String customerID, customerName, dob, address, city, state, PIN, mobileNum, email, emailPassword;
	String editAddress, editDob, editCity, editState, editPIN, editMobileNum, editEmail;
	String initialDeposit;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		userName = "mngr392664";
		userPassword = "dezyzUp";

		customerName = "Khoi";
		address = "TrungSon";
		dob = "2021-03-06";
		city = "NinhBinh";
		state = "TamDiep";
		PIN = "012345";
		mobileNum = "0366845289";
		email = "Rachel" + getRandomNumber() + "@yomail.com";
		emailPassword = "123123123";
		initialDeposit = "50000";

		editAddress = "1883 Avuenue";
		editCity = "Houston";
		editState = "Texas";
		editPIN = "166455";
		editMobileNum = "01664549178";
		editEmail = "Rachel" + getRandomNumber() + "@homail.com";

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "' ");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 02: Login with User account");
		homepage = loginPage.loginSystemAsUser(driver, userName, userPassword);
//		thu tat di 
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void TC_01_Create_New_Customer() {
		log.info("Payment_01 - Step 1: Open New customer page");
		loginPage.openSubPageByName(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		log.info("Payment_01 - Step 2: Fill in Customer Name textbox");
		newCustomerPage.fillInTextbox(driver, "name", customerName);

		log.info("Payment_01 - Step 3: Select Gender radio button");
		newCustomerPage.clickToRadioButtonByName(driver, "f");

		log.info("Payment_01 - Step 4: Fill in DOB textbox");
		newCustomerPage.setDateByName(driver, "dob", dob);

		log.info("Payment_01 - Step 5: Fill in Address textbox");
		newCustomerPage.fillInTextarea(driver, "addr", address);

		log.info("Payment_01 - Step 6: Fill in City textbox");
		newCustomerPage.fillInTextbox(driver, "city", city);

		log.info("Payment_01 - Step 7: Fill in State textbox");
		newCustomerPage.fillInTextbox(driver, "state", state);

		log.info("Payment_01 - Step 8: Fill in PIN textbox");
		newCustomerPage.fillInTextbox(driver, "pinno", PIN);

		log.info("Payment_01 - Step 9: Fill in Mobile textbox");
		newCustomerPage.fillInTextbox(driver, "telephoneno", mobileNum);

		log.info("Payment_01 - Step 10: Fill in Email textbox");
		newCustomerPage.fillInTextbox(driver, "emailid", email);

		log.info("Payment_01 - Step 11: Fill in Password textbox");
		newCustomerPage.fillInTextbox(driver, "password", emailPassword);

		log.info("Payment_01 - Step 12: Click submit button");
		newCustomerPage.clickToButtonByName(driver, "sub");
		createSuccessCustomerPage = PageGeneratorManager.getCreateCustomerSuccessPage(driver);

		log.info("Payment_01 - Step 13: Verify msg displays with content 'Customer Registerd Successfully'");
		verifyEquals(createSuccessCustomerPage.getSuccessMessage(driver, "Customer Registered Successfully!!!"),
				"Customer Registered Successfully!!!");

		log.info("Payment_01 - Step 14: Verify info displayed");
		customerID = createSuccessCustomerPage.getTextboxValueByText(driver, "Customer ID");
		verifyTrue(createSuccessCustomerPage.isInfoDisplayed(driver, "Customer ID"));
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "Customer Name"), customerName);
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "Gender"), "female");
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "Birthdate"), dob);
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "Address"), address);
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "City"), city);
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "State"), state);
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "Pin"), PIN);
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "Mobile No."), mobileNum);
		verifyEquals(createSuccessCustomerPage.getTextboxValueByText(driver, "Email"), email);

	}

	@Test
	public void TC_02_Edit_Customer_Infor() {
		log.info("Payment_01 - Step 01: Open Edit Customer Page");
		createSuccessCustomerPage.openSubPageByName(driver, "Edit Customer");
		editCustomerInfoPage = PageGeneratorManager.getEditCustomerInfoPage(driver);

		log.info("Payment_02 - Step 02: Fill in CustomerID textbox");
		editCustomerInfoPage.fillInTextbox(driver, "cusid", customerID);

		log.info("Payment_02 - Step 03: Click submit button");
		editCustomerInfoPage.clickToButtonByName(driver, "AccSubmit");

		log.info("Payment_02 - Step 04: Edit Address textbox");
		newCustomerPage.fillInTextarea(driver, "addr", editAddress);

		log.info("Payment_02 - Step 5: Edit City textbox");
		newCustomerPage.fillInTextbox(driver, "city", editCity);

		log.info("Payment_02 - Step 6: Edit State textbox");
		newCustomerPage.fillInTextbox(driver, "state", editCity);

		log.info("Payment_02 - Step 7: Edit PIN textbox");
		newCustomerPage.fillInTextbox(driver, "pinno", editPIN);

		log.info("Payment_02 - Step 8: Edit Mobile textbox");
		newCustomerPage.fillInTextbox(driver, "telephoneno", editMobileNum);

		log.info("Payment_02 - Step 9: Edit Email textbox");
		newCustomerPage.fillInTextbox(driver, "emailid", editEmail);

		log.info("Payment_02 - Step 10: Click submit button");
		editCustomerInfoPage.clickToButtonByName(driver, "sub");

		editCustomerInfoPage.acceptAlert(driver);

		// log.info("Payment_02 - Step 11: Verify msg displays with content 'Customer
		// Registerd Successfully'");
		// verifyTrue(createSuccessCustomerPage.ise(driver, "Customer details updated
		// Successfully!!!")

	}

	@Test
	public void TC_03_Add_New_Account() {
		log.info("Payment_01 - Step 1: Open New account page");
		loginPage.openSubPageByName(driver, "New Account");
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);

		log.info("Payment_01 - Step 2: Fill in Customer ID textbox");
		newAccountPage.fillInTextbox(driver, "cusid", customerID);

		log.info("Payment_01 - Step 03: Select Account Type = 'Saving'");
		newAccountPage.selectValueInDropdownByText(driver, " Account type", "Savings");

		log.info("Payment_01 - Step 04: Fill '" + initialDeposit + "'  in Initial Deposit textbox");
		newAccountPage.fillInTextbox(driver, "inideposit", initialDeposit);

		log.info("Payment_01 - Step 05: Click submit button");
		newAccountPage.clickToButtonByName(driver, "button2");

		log.info("Payment_01 - Step 05: Click submit button");
		verifyEquals(newAccountPage.getSuccessMessage(driver, "Account Generated Successfully!!!"),
				"Account Generated Successfully!!!");

	}

	@Test
	public void TC_04_Edit_Account() {

	}
	@Test
	public void TC_05_Transfer_Money_Into_Current_Account() {
		
	}
	@Test
	public void TC_06_Withdraw_Money() {
		
	}
	@Test
	public void TC_07_Transfer_Money_Into_Another_Account() {
		
	}
	@Test
	public void TC_08_Check_Current_Account_Balance() {
		
	}
	@Test
	public void TC_09_Delete_All_Account() {
		
	}

	@Test
	public void TC_10_Delete_Customer() {

	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
