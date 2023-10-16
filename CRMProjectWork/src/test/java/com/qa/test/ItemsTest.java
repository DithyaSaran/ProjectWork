package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.page.ItemsPage;
import com.qa.page.LoginPage;
import com.qa.utility.FakerUtility;

public class ItemsTest extends BaseTest{

	String itemtitleName=FakerUtility.getName();
	String description=FakerUtility.getRandomName();
	
	@Test(priority=1,groups= {"smoke"})
	public void verifyAddItem() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ItemsPage itempg=new ItemsPage(driver);
		itempg.clickItem();
		itempg.addItem(itemtitleName,description,"xyz",FakerUtility.getNumber());
		String actualmsg=itempg.searchItem(itemtitleName);
		String expectedMsg=itemtitleName;
		Assert.assertEquals(actualmsg, expectedMsg);
	}

	@Test(priority=2,groups= {"smoke"})
	public void verifySearchItem() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ItemsPage itempg=new ItemsPage(driver);
		itempg.clickItem();

		String actualmsg=itempg.searchItem(itemtitleName);
		String expectedmsg=itemtitleName;
		Assert.assertEquals(actualmsg, expectedmsg);
	}

	@Test(priority=3,groups= {"regression"})
	public void verifyEditItem() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ItemsPage itempg=new ItemsPage(driver);
		itempg.clickItem();
		itempg.searchItem(itemtitleName);
		
		String actualmsg=itempg.editItem("NameEdited");
		String expectedmsg="NameEdited";
		Assert.assertEquals(actualmsg, expectedmsg);
	} 

	@Test(priority=4,groups= {"smoke"})
	public void verifyDeleteItem() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ItemsPage itempg=new ItemsPage(driver);
		itempg.clickItem();
		itempg.searchItem(itemtitleName);
		String actualmsg=itempg.delItem();
		String expectedmsg="No record found.";
		Assert.assertEquals(actualmsg, expectedmsg);
	}

}

