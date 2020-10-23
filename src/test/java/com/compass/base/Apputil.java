package com.compass.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.io.Files;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Apputil {
	
	public AndroidDriver<WebElement> driver;
	public DesiredCapabilities dc;
	String folder_name;
	DateFormat df;
	
	public String BusinessUnit="Anita"; 
	public String job_type="Asha";
	public String tags="Circuit Breakdown";
	public String services="Piping";
	
	
	//Dev env credential 
	public String username="z@mailinator.com";
	public String password="P@ssword1";
	public String techname="Shahavej Technician";
	
	/*//QA env credential
	public String username="max@mailinator.com";
	public String password="P@ssword1";
	public String techname="Glen Maxwell";
	*/

	public void install_and_launch_apk(String Apk_path) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.APP, Apk_path); // pass the path of the App
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		// dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0"); // you can
		// set the version for virtual device
		URL url = new URL("http://127.0.0.1:4723/wd/hub");// this is the appium server path
		driver = new AndroidDriver<WebElement>(url, dc);
		

	}

	public AndroidDriver<WebElement> launch_apk(String apk_package, String apk_activity) throws MalformedURLException {
		dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability("appPackage", apk_package);
		dc.setCapability("appActivity", apk_activity);
		URL url = new URL("http://127.0.0.1:4723/wd/hub");// this is the Appium server path
		driver = new AndroidDriver<WebElement>(url, dc);
		return driver;

	}

	public void click(WebElement ele) {
		ele.click();
	}

	public void sendkeys(WebElement ele, String txt) {
		ele.sendKeys(txt);
	}

	public void handle_dropdown(WebElement dropdown, String value) throws InterruptedException {

		// driver.findElementById("android:id/text1").click();// click on dropdown
		dropdown.click();
		List<WebElement> options = driver.findElementsById("android:id/text1");
		// System.out.println("Total number of options available in dropdown:" +
		// options.size());
		for (WebElement e : options) {
			String val = e.getText();
			if (val.equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}
		Thread.sleep(8000);
	}

	public void captureScreenShots(String file_name) throws IOException {
		folder_name = "screenshot";
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// create dir with given folder name
		new File(folder_name).mkdir();
		// Setting file name
		file_name = df.format(new Date()) + ".png";
		// copy screenshot file into screenshot folder.
		Files.copy(f, new File(folder_name + "/" + file_name));
	}

	public void scrollTillandClick(String txt) {
		MobileElement listele1 = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + txt + "\"));"));
		listele1.click();
	}

	public void scrollTill(String txt) {
		MobileElement listele1 = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + txt + "\"));"));

	}

	public String MobileNumber() {
		int num1, num2, num3; // 3 numbers in area code
		int set2, set3; // sequence 2 and 3 of the phone number
		Random generator = new Random();
		num1 = generator.nextInt(7) + 1; // add 1 so there is no 0 to begin
		num2 = generator.nextInt(8); // randomize to 8 becuase 0 counts as a number in the generator
		num3 = generator.nextInt(8);
		set2 = generator.nextInt(643) + 100;
		set3 = generator.nextInt(8999) + 1000;
		String phone = (+num1 + "" + num2 + "" + num3 + +set2 + +set3);
		return phone;
	}

	private static final String ALPHA_NUMERIC_STRING = "0123456789";
	public static String randomNumber(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	private static final String ALPHA_NUMERIC_STRING1 = "!@#$%^&*QWERTYUIOPqwertyuiopaASDFGHJK0123456789";
	public static String randomPassword(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING1.length());
			builder.append(ALPHA_NUMERIC_STRING1.charAt(character));
		}
		return builder.toString();
	}
	
	public void login1(String username, String password) throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Username']")).sendKeys(username);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Password']")).sendKeys(password);
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='signin']")).click();
	}
	public void change_password2(String old_pwd, String new_pwd) throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter Current Password']")).sendKeys(old_pwd);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter New Password']")).sendKeys(new_pwd);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Retype New Password']")).sendKeys(new_pwd);
		driver.findElement(By.xpath("//android.widget.TextView[@text='']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();
		Thread.sleep(5000);

	}
	

}
