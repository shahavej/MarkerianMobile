package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Apputil;

public class LoginPage extends Apputil{
	
	
	@FindBy(xpath="")
	WebElement username_fld;
	
	@FindBy(xpath="")
	WebElement password_fld;
	
	@FindBy(xpath="")
	WebElement login_btn;
	
	@FindBy(xpath="")
	WebElement dashboard;
	
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	/* there are some methods to define the action on login page */
	
	public boolean valid_login(String Username, String Password) throws InterruptedException
	{
		username_fld.sendKeys(Username);
		password_fld.sendKeys(Password);
		login_btn.click();
		Thread.sleep(3000);
		return dashboard.isDisplayed();
	
	}
	
	public boolean check_fld_validation()
	{
		// here should be the code for check validation of each field
		return true;
	}
	
	// we can add n no of method those define the action of this page 
	

}
