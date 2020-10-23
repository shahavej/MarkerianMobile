package com.compass.practice;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.compass.base.Apputil;


public class SmokeTestCases extends Apputil{

	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException
	{
		launch_apk("com.compasspro", "com.compasspro.MainActivity");
	}
	
	@AfterMethod
	public void killApk()
	{	
		driver.quit();  // this is nothing but quit the driver
	}
	
	@Test(priority=1,description="As a user i should be able to login with the valid credential")
	public void valid_login() throws InterruptedException
	{
		Thread.sleep(20000);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Username']")).sendKeys("z@mailinator.com");
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Password']")).sendKeys("P@ssword1");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='signin']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='You Have No Scheduled Job']")).isDisplayed();
		
	}	
}
