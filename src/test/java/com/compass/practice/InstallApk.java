package com.compass.practice;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import com.compass.base.Apputil;

public class InstallApk extends Apputil {

	@Test
	public void install_Apk() throws MalformedURLException
	{
		install_and_launch_apk("C:\\Users\\ASUS\\Downloads\\QHL.apk");
	}
}
