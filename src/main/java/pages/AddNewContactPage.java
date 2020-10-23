package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.Apputil;

public class AddNewContactPage extends Apputil {

	@FindBy(id = "com.android.contacts:id/add_contact_button")
	WebElement AddContact_btn;

	/*
	 * @FindBy(id = "") WebElement fname_fld;
	 * 
	 * @FindBy(id = "") WebElement lname_fld;
	 */

	public AddNewContactPage() {
		super();
		PageFactory.initElements(driver, this); // imp line
	}

	// method for action

	public void createcontact() {

		AddContact_btn.click();
	}

}
