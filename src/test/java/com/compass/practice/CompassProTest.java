package com.compass.practice;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import util.Apputil;
import util.Test_Data;

public class CompassProTest extends com.compass.base.Apputil {
	
	String username="albus@yopmail.com";
	String password="P@ssword5";

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
	/* test cases section */
	@Test(description = "As a Technician I should be able to login with the valid credential", priority = 1)
	public void Login_Test1() throws InterruptedException {
		
		login1(username, password );
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click();
		boolean s1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Shahavej Technician']"))
				.isDisplayed();
		assertEquals(s1, true);
	}
	
	@Test(description = "At login Screen Each field should have proper validation, labels and links", priority = 2)
	public void Login_Test2() throws InterruptedException {
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='signin']")).click();
		Thread.sleep(2000);
		boolean v1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Email is required']")).isDisplayed();
		boolean v2 = driver.findElement(By.xpath("//android.widget.TextView[@text='Please enter valid password']"))
				.isDisplayed();
		assertEquals(v1, true);
		assertEquals(v2, true);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Username']")).sendKeys("z@mailina@tor.com");
		boolean v3 = driver.findElement(By.xpath("//android.widget.TextView[@text='Please enter valid username']"))
				.isDisplayed();
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

	@Test(description = "Forgot Password screen should have proper valdation", priority = 3)
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
	
	@Test(description = "Change password screen should have proper validation and user should be able to change password", priority = 4)
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
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Old password is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='New password is required']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Please, confirm password']")).isDisplayed();

		// changing password

		String old_pwd = "P@ssword1";
		String new_pwd = "Alpha@123";

		change_password2(old_pwd, new_pwd);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Sign In']")).click();
		login1("z@mailinator.com", old_pwd);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Either username or password is wrong']"))
				.isDisplayed();
		Thread.sleep(10000);
		// login with the new password
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='username']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc='username']"))
				.sendKeys("z@mailinator.com");
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

	@Test(description = "As a App user I should be able to see my profile details", priority = 5)
	public void profile_Test1() throws InterruptedException, ParseException {
		
		login1(username, password);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[864,1897][1080,2020]']")).click();
		JSONObject user = Test_Data.Read_Data("user");
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + user.get("User Name") + "']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + user.get("Email") + "']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + user.get("phone") + "']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + user.get("Office phone") + "']"))
				.isDisplayed();

	}
	
	@Test(description = "During add the Customer each field should have proper validation", priority = 6)
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
		// driver.findElement(By.xpath("//android.widget.TextView[@text='Home Phone
		// can't be same as Phone Number']")).isDisplayed();
		scrollTill("Customer Name*");

		/* faker data */
		JSONObject obj = new JSONObject();
		Faker fake = new Faker();
		String fname = fake.name().firstName();
		String lname = fake.name().lastName();
		String email = fname + "@mailinator.com";
		String street = fake.address().streetAddress();
		String zip = fake.address().zipCode();
		String city = fake.address().city();
		String state = fake.address().state();
		String phone = MobileNumber();
		String homephone = MobileNumber();
		// driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[948,820][998,871]']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,312][1018,382]']"))
				.sendKeys(fname + " " + lname);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,561][1018,631]']")).sendKeys(email);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,1053][953,1123]']")).sendKeys(street);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,1305][1018,1375]']")).sendKeys(zip);
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,1554][1018,1624]']")).sendKeys(city);
		scrollTill("Home Phone");
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
		Test_Data.Write_Data(obj, "Customer");
	}

	@Test(description = "At Customer list I should be able to search Customer by their name, address, zip and Phone", priority = 7)
	public void customer_List_Test1() throws InterruptedException {
		login1(username, password);
		Thread.sleep(8000);
		driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[432,1897][648,2020]']")).click();
		Thread.sleep(3000);
		String name = "James Bond";
		String zipcode = "56556";
		String address="Brown Boys in the building";
		String phone="8374384384";
		
		
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).sendKeys(name);
		boolean v1 = driver.findElement(By.xpath("//android.widget.TextView[@text='" + name + "']")).isDisplayed();
		assertEquals(v1, true);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + name + "']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).sendKeys(zipcode);
		String txt = driver.findElement(By.xpath("//android.widget.TextView[@bounds='[138,601][1005,695]']")).getText();
		boolean v2 = txt.contains(zipcode);
		assertEquals(v2, true);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + zipcode + "']")).clear();
		driver.findElement(By.xpath("//android.widget.EditText[@bounds='[63,343][958,413]']")).sendKeys(address);
		String txt1 = driver.findElement(By.xpath("//android.widget.TextView[@bounds='[138,601][1005,695]']")).getText();
		boolean v3 = txt1.contains(address);
		assertEquals(v3, true);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//android.widget.EditText[@text='" + address + "']")).clear();
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
	@Test(description="As a App User I should be able to add project along with the job",priority=8)
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
	
	@Test(description="As a App User I should be able to add estimates", priority=9)
	public void Estimates() throws InterruptedException
	{
		login1(username, password);
		Thread.sleep(10000);
		
	}
	
	
}
