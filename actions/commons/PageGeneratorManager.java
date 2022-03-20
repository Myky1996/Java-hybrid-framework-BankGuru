package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.bankGuru.payment.CreateCustomerSuccessPageObject;
import pageObjects.bankGuru.payment.DeleteAccountPO;
import pageObjects.bankGuru.payment.DeleteCustomerPO;
import pageObjects.bankGuru.payment.DepositPO;
import pageObjects.bankGuru.payment.EditAccountPO;
import pageObjects.bankGuru.payment.EditCustomerInfoPO;
import pageObjects.bankGuru.payment.EnquiryPO;
import pageObjects.bankGuru.payment.HompageObject;
import pageObjects.bankGuru.payment.LoginPageObject;
import pageObjects.bankGuru.payment.NewAccountPO;
import pageObjects.bankGuru.payment.NewCustomerPO;
import pageObjects.bankGuru.payment.WithdrawalPO;

public class PageGeneratorManager {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static HompageObject getHompageObject(WebDriver driver) {
		return new HompageObject(driver);
	}

	public static NewCustomerPO getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPO(driver);
	}

	public static CreateCustomerSuccessPageObject getCreateCustomerSuccessPage(WebDriver driver) {
		return new CreateCustomerSuccessPageObject(driver);
	}

	public static EditCustomerInfoPO getEditCustomerInfoPage(WebDriver driver) {
		return new EditCustomerInfoPO(driver);
	}

	public static NewAccountPO getNewAccountPage(WebDriver driver) {
		return new NewAccountPO(driver);
	}

	public static DeleteAccountPO deleteAccountPage(WebDriver driver) {
		return new DeleteAccountPO(driver);
	}

	public static DeleteCustomerPO deleteCustomerPage(WebDriver driver) {
		return new DeleteCustomerPO(driver);
	}

	public static DepositPO getDepositPage(WebDriver driver) {
		return new DepositPO(driver);
	}

	public static EditAccountPO getEditAccountPage(WebDriver driver) {
		return new EditAccountPO(driver);
	}

	public static EnquiryPO getEnquirytPage(WebDriver driver) {
		return new EnquiryPO(driver);
	}

	public static WithdrawalPO getWithdrawalPage(WebDriver driver) {
		return new WithdrawalPO(driver);
	}
}
