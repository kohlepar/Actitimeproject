package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.ObjectRepository.HomePage;
import com.Actitime.ObjectRepository.LoginPage;

public class Baseclass {
	Filelibrary f=new Filelibrary();
	public static WebDriver driver;
	@BeforeSuite
	public void databaseconnection() {
		Reporter.log("database connected",true);
	}
	
	@BeforeClass
	public void launchBrowser() throws IOException {
		 driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String URL = f.readDataFromPropertyFile("url");
		driver.get(URL);
		Reporter.log("browser launched",true);
	}
	@BeforeMethod
	public void login() throws IOException {
		LoginPage lp=new LoginPage(driver);
		String UN = f.readDataFromPropertyFile("username");
		String PW = f.readDataFromPropertyFile("password");
		lp.getUntbx().sendKeys(UN);
		lp.getPwtbx().sendKeys(PW);
		lp.getLgbtn().click();
		Reporter.log("logged in successfully",true);
	}
	@AfterMethod
	public void logout() {
		HomePage hp=new  HomePage(driver);
		hp.getLgoutlnk().click();
		Reporter.log("logged out successfully",true);
	}
	
	@AfterClass
	public void closebrowser() {
		driver.close();
		Reporter.log("browser closed",true);
	}
	@AfterSuite
	public void databasedisconnection() {
		Reporter.log("database disconnected",true);
	}

}
