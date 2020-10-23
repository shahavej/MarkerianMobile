package com.compaspro.testcases;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.compass.base.Apputil;


public class Project extends Apputil {
	
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
	
	@Test(description="As a App User I should be able to add project along with the job",priority=1)
	public void Add_job() throws InterruptedException
	{
		login1(username, password);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[432,1897][648,2020]']")).click();
		String customer_name="James Bond";
		scrollTillandClick(customer_name);
		boolean v1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Select Project']")).isDisplayed();
		assertEquals(v1, true);
		driver.findElement(By.xpath("//android.widget.TextView[@bounds='[940,1905][1010,1976]']")).click();
		boolean v2 = driver.findElement(By.xpath("//android.widget.TextView[@text='Create Project']")).isDisplayed();
		assertEquals(v2, true);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[40,1865][1040,1985]']")).click();
		boolean v3 = driver.findElement(By.xpath("//android.widget.TextView[@text='Project name required']")).isDisplayed();
		assertEquals(v3, true);
		
	}
}
