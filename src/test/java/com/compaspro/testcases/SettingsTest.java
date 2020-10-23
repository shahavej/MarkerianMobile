package com.compaspro.testcases;

import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.compass.base.Apputil;

public class SettingsTest extends Apputil {


	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException {
		launch_apk("com.compasspro", "com.compasspro.MainActivity");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean s = driver.findElement(By.className("android.widget.FrameLayout")).isDisplayed();
		assertEquals(s, true);
		Thread.sleep(15000);
	}

	@AfterMethod
	public void killapk() {
		driver.quit();

	}

	// for change password
	@Test(description = "Change password screen should have proper validation and user should be able to change password", priority = 1)
	public void change_password_Test1() throws InterruptedException, MalformedURLException {

		login1(username, password);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click();
		driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Change Password']")).click();

		// checking placeholder
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Current Password']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter New Password']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Retype New Password']")).isDisplayed();

		// verify the field validation
		driver.navigate().back();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Old password is required']")).isDisplayed(); 

		// changing password
		String old_pwd = password;
		String new_pwd = "Alpha@123";

		change_password2(old_pwd, new_pwd);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Sign In']")).click();
		login1(username, old_pwd);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Either username or password is wrong']"))
				.isDisplayed();
		Thread.sleep(5000);
		// login with the new password
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='username']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='username']")).sendKeys(username);
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='password']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='password']")).sendKeys(new_pwd);
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='signin']")).click();
		Thread.sleep(5000);
		// again reset the password
		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click();
		driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Change Password']")).click();
		change_password2(new_pwd, old_pwd);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Sign In']")).click();
	}

	// test case for change language
	//@Test(description = "As a App User I should be able to change app language", priority = 2)
	public void change_lang() {

		// this feature will come soon

	}

	// test case of on-off notifications
	//@Test(description = "As a App User I should be able to change app language", priority = 3)
	public void notifications() {

		// this feature will come soon

	}

}
