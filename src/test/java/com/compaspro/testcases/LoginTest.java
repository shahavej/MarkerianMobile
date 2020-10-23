package com.compaspro.testcases;

import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.compass.base.Apputil;

public class LoginTest extends Apputil {
	
	@BeforeMethod
	public void login() throws MalformedURLException, InterruptedException {
		launch_apk("com.compasspro", "com.compasspro.MainActivity");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		boolean s = driver.findElement(By.className("android.widget.FrameLayout")).isDisplayed();
		assertEquals(s, true);
		Thread.sleep(10000);
	}
	
	@AfterMethod
	public void killapk() {
		driver.quit();
	}
	
	@Test(description = "As a Technician I should be able to login with the valid credential and logout successfully", priority = 1)
	public void Login_Test1() throws InterruptedException {
		
		login1(username, password );
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click();
		boolean s1 = driver.findElement(By.xpath("//android.widget.TextView[@text='" +techname+ "']")).isDisplayed();
		assertEquals(s1, true);
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Sign Out']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Are you sure you want to sign out?']")).isDisplayed();
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[560,1149][997,1269]']")).click();
		Thread.sleep(3000);
		boolean s2 = driver.findElement(By.xpath("//android.widget.TextView[@text='Please sign in with your registered Email Address or User ID.']")).isDisplayed();
		assertEquals(s2, true);
	}
	
	@Test(description = "At login Screen Each field should have proper validation, labels and links", priority = 2)
	public void Login_Test2() throws InterruptedException {
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='signin']")).click();
		Thread.sleep(2000);
		boolean v1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Email is required']")).isDisplayed();
		boolean v2 = driver.findElement(By.xpath("//android.widget.TextView[@text='Please enter valid password']")).isDisplayed();
		assertEquals(v1, true);
		assertEquals(v2, true);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Username']")).sendKeys("z@mailina@tor.com");
		boolean v3 = driver.findElement(By.xpath("//android.widget.TextView[@text='Please enter valid username']")).isDisplayed();
		assertEquals(v3, true);
		// verify the forgot password link
		driver.findElement(By.xpath("//android.widget.TextView[@text='Forgot Password?']")).click();
		Thread.sleep(3000);
		boolean v4 = driver.findElement(By.xpath("//android.widget.TextView[@text='Forgot Password']")).isDisplayed();
		assertEquals(v4, true);
		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click();
		Thread.sleep(2000);
		boolean v5 = driver.findElement(By.xpath("//android.widget.TextView[@text='Sign In']")).isDisplayed();
		assertEquals(v5, true);
		
	}
}
