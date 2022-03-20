package pageUIs.bankguru;

public class BasePageUI {
	public final static String TEXTBOX_BY_NAME = "//input[@name='%s']"; 
	public final static String BUTTON_BY_NAME = "//input[@name='%s']"; 
	public final static String RADIO_BUTTON_BY_NAME = "//input[@value='%s']"; 
	public final static String TEXT_AREA_BY_NAME = "//textarea[@name='%s']"; 
	public final static String SUBPAGE_BY_TEXTNAME = "//a[text()= '%s']"; 
	public final static String ANY_FIELD_BY_NAME = "//.[@name='%s']"; 
	public final static String SUCCESS_MESSAGE_BY_TEXT = "//p[text()='%s']";
	public final static String TEXTBOX_LABEL_BY_TEXT = "//td[text()='%s']";

	public final static String TEXTBOX_VALUE_BY_TEXT = "//td[text()='%s']/following-sibling::td";
	public final static String DROPDOWN_BY_TEXT = "//td[text()='%s']//following-sibling::td/select";
	public final static String VALIDATION_MESSAGE_BY_TEXT = "//label[text()='%s']";
	
	public final static String USERNAME_LOGIN_TEXTBOX = "//input[@name='uid']"; 
	public final static String PASSWORD_LOGIN_TEXTBOX = "//input[@name='password']"; 
	public final static String SUBMIT_LOGIN_BUTTON = "//input[@name='btnLogin']";
}
