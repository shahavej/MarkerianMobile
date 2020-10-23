package com.compaspro.testcases;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.compass.base.Apputil;
import com.github.javafaker.Business;
import com.github.javafaker.Faker;

import util.Test_Data;

public class CustomerTest extends Apputil{

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
	

	@Test(description = "During add the Customer each field should have proper validation", priority = 1)
	public void add_Customer_Test1() throws InterruptedException {
		
		login1(username, password);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[432,1897][648,2020]']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.widget.TextView[@bounds='[940,1900][1010,1971]']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Add New Customer']")).isDisplayed();
		
		/* check validation of each field on Add customer screen */
		scrollTillandClick("Continue");
		scrollTill("Customer Name*");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Customer Name is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Email is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Business Unit is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Street is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Zipcode is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='City is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='State is required']")).isDisplayed();
		scrollTill("Continue");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Phone Number is required']")).isDisplayed();
		scrollTill("Customer Name*");
		

		/* faker data */
		JSONObject obj = new JSONObject();
		Faker fake = new Faker();
		String fname = fake.name().firstName();
		String lname = fake.name().lastName();
		String email = fname + "@mailinator.com";
		String street = fake.address().streetAddress();
		String zip = randomNumber(5);
		String city = fake.address().city();
		String state = "Alabama";
		String phone = MobileNumber();
		String homephone = MobileNumber();
		String BusinessUnit1= BusinessUnit;
		String p=fake.name().lastName();
		String Project_name=p+" Project";
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,312][1018,382]']"))
				.sendKeys(fname + " " + lname);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,561][1018,631]']")).sendKeys(email);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,1053][953,1123]']")).sendKeys(street);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Select Bussiness Unit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" +BusinessUnit1+ "']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,1305][1018,1375]']")).sendKeys(zip);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,1554][1018,1624]']")).sendKeys(city);
		
		scrollTill("Home Phone");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Select State']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" +state+ "']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,1359][1018,1429]']")).sendKeys(phone);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,1603][1018,1673]']")).sendKeys(homephone);
		
		obj.put("fname", fname);
		obj.put("lname", lname);
		obj.put("email", email);
		obj.put("street", street);
		obj.put("zip", zip);
		obj.put("city", city);
		obj.put("state", state);
		obj.put("phone", phone);
		obj.put("homephone", homephone);
		obj.put("BusinessUnit1", BusinessUnit1);
		obj.put("Project_name", Project_name);
		
		Test_Data.Write_Data(obj, "Customer");
		scrollTillandClick("Continue");
		/***till here working fine*/
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Project name required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Project Name']")).sendKeys(Project_name);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.TextView[@bounds='[40,434][1040,481]']")).getText().equalsIgnoreCase(Project_name);
		driver.findElement(By.xpath("//android.widget.TextView[@bounds='[40,230][1040,284]']")).getText().equalsIgnoreCase(fname+" "+lname);
		scrollTillandClick("Book Job");
		scrollTill("Job Type*");
		
		//check validation on add job screen 
		driver.findElement(By.xpath("//android.widget.TextView[@text='Job type is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Business unit is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Start date & time is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Lead source is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Select Services']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Services addresses are required']")).isDisplayed();
		scrollTill("Book Job");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Billing addresses are required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Select Tags']")).isDisplayed();
		scrollTill("Job Type*");
		
		//fill the all fields with the positive inputs
		
		String job_type1=job_type;
		String business_unit=BusinessUnit;
		String lead_source="Source "+ randomPassword(3);
		String services1=services;
		String tags1=tags;
		String description=fake.lorem().sentence(5);
		String service_address=street+", "+city+", "+zip;
		String billing_address=street+", "+city+", "+zip;
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='Select Job Type']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='"+job_type1+"']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Select business unit']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='"+business_unit+"']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter lead source']")).sendKeys(lead_source);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Select Services']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='"+services1+"']")).click();
		scrollTill("Book Job");
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='Select Service Addresses']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='"+service_address+"']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Service Address*']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Select Billing Addresses']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='"+billing_address+"']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Billing Address*']")).click();
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='Select Tags']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='"+tags1+"']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Tags*']")).click();
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Job Description…']")).sendKeys(description);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	//@Test(description = "At Customer list I should be able to search Customer by their name, address, zip and Phone", priority = 7)
	public void customer_List_Test1() throws InterruptedException, ParseException {
		login1(username, password);
		Thread.sleep(8000);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[432,1897][648,2020]']")).click();
		Thread.sleep(3000);
		
		JSONObject cust = Test_Data.Read_Data("Customer");
		
		String name = (String) cust.get("fname");
		System.out.println(name);
		String zip = (String) cust.get("zip");
		System.out.println(zip);
		String phone = (String) cust.get("phone");
		System.out.println(phone);
		String street = (String) cust.get("street");
		System.out.println(street);
		
		/*
		String name = "James Bond";
		String zipcode = "56556";
		String address="Brown Boys in the building";
		String phone="8374384384";
		*/
		

		
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).sendKeys(name);
		boolean v1 = driver.findElement(By.xpath("//android.widget.TextView[@text='" + name + "']")).isDisplayed();
		assertEquals(v1, true);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + name + "']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).sendKeys(zip);
		String txt = driver.findElement(By.xpath("//android.widget.TextView[@bounds='[138,601][1005,695]']")).getText();
		boolean v2 = txt.contains(zip);
		assertEquals(v2, true);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + zip + "']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).sendKeys(street);
		String txt1 = driver.findElement(By.xpath("//android.widget.TextView[@bounds='[138,601][1005,695]']")).getText();
		boolean v3 = txt1.contains(street);
		assertEquals(v3, true);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + street + "']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).sendKeys(phone);
		boolean v4 = driver.findElement(By.xpath("//android.widget.TextView[@text='" + name + "']")).isDisplayed();
		assertEquals(v4, true);
		
		
		/* verify the customer data at detail screen */
		/*
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + phone + "']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).sendKeys(name);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click(); 
		//driver.findElement(By.xpath("//android.widget.TextView[@bounds='[970,517][1020,568]']")).click();
		*/
		
		
	}
}
