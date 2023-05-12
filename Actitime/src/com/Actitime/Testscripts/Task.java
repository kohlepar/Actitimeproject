package com.Actitime.Testscripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Actitime.GenericLibrary.Baseclass;
import com.Actitime.GenericLibrary.Filelibrary;
import com.Actitime.ObjectRepository.HomePage;
import com.Actitime.ObjectRepository.TaskPage;

public class Task extends Baseclass {
	
	@Test
	public void createCustomer() throws EncryptedDocumentException, IOException {
		HomePage hp=new HomePage(driver);
		hp.getTasktab().click();
		TaskPage tp=new TaskPage(driver);
		tp.getAddnewbtn().click();
		tp.getNewcust().click();
		
		Filelibrary f=new Filelibrary();
		String createcustomer= f.readDataFromExcel("Sheet1", 2, 1);
		tp.getCustname().sendKeys(createcustomer);
		String description = f.readDataFromExcel("Sheet1", 1, 2);
		tp.getCustdesp().sendKeys(description);
		tp.getCreatecust().click();
		String expectedresult = createcustomer;
		String Actualresult = driver.findElement(By.xpath("(//div[.='"+createcustomer+"'])[2]")).getText();
		SoftAssert s=new SoftAssert();
		s.assertEquals(Actualresult, expectedresult);
		s.assertAll();
		
	}

}
