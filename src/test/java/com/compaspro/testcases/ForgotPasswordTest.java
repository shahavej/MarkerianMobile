package com.compaspro.testcases;

import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.compass.base.Apputil;

import util.Mailinator;

public class ForgotPasswordTest extends Apputil {

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

	@Test(description = "Forgot Password screen should have proper validations and labels", priority = 1)
	public void forgot_password_Test1() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Forgot Password?']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Send Code']")).click();
		boolean v1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Username is required']"))
				.isDisplayed();
		assertEquals(v1, true);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Email Address']"))
				.sendKeys("z@maili@nator.com");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Send Code']")).click();
		boolean v2 = driver.findElement(By.xpath("//android.widget.TextView[@text='Not a valid e-mail address']"))
				.isDisplayed();
		assertEquals(v2, true);
	}

	@Test(description = "As A app user I should be able to reset password by forgot password", priority = 2)
	public void forgot_password1() throws InterruptedException {
		String userEmail = username;
		driver.findElement(By.xpath("//android.widget.TextView[@text='Forgot Password?']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Email Address']")).sendKeys(userEmail);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Send Code']")).click();
		String otp = Mailinator.set_password(userEmail);
		System.out.println(otp);
		Thread.sleep(4000);
		//need to write the code for insert the OTP into the OTP fields 
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[40,913][1040,1033]']")).sendKeys(otp);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[40,1285][1040,1405]']")).click();
		Thread.sleep(3000);
		// reset password screen 
		String new_pwd = "P@ssword1";
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,720][953,790]']")).sendKeys(new_pwd);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,995][953,1065]']")).sendKeys(new_pwd);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[40,1175][1040,1295]']")).click();
		//confirmation screen 
		boolean v1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Your password has been reset successfully.']")).isDisplayed();
		assertEquals(v1, true);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[40,1880][1040,2000]']")).click();
		login1(username, new_pwd);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click();
		boolean s1 = driver.findElement(By.xpath("//android.widget.TextView[@text='" +techname+ "']")).isDisplayed();
		assertEquals(s1, true);
		
	}
}
