package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.page.ClientsPage;
import com.qa.page.LoginPage;
import com.qa.utility.ElementUtility;
import com.qa.utility.FakerUtility;

public class ClientsTest extends BaseTest {
	String companynamefield=FakerUtility.getName();

	@Test(priority=1,groups= {"smoke"})
	public void verifyAddClient() {

		LoginPage lp=new LoginPage(driver);
		lp.doLogin(ElementUtility.getPropertyValue("username"),ElementUtility.getPropertyValue("password"));

		ClientsPage clientpg=new ClientsPage(driver);
		clientpg.clickClient();

		clientpg.addClient(companynamefield, "xyz", "tvm", "kerala", "657897", "India", FakerUtility.getNumber(), "www.abc.com", "1234", "0");
		String actualmsg=clientpg.searchClient(companynamefield);
		String expectedmsg=companynamefield;
		Assert.assertEquals(actualmsg, expectedmsg);
	}

	@Test(priority=2,groups= {"regression"})
	public void verifySearch() {

		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ClientsPage clientpg=new ClientsPage(driver);
		clientpg.clickClient();
		String actualmsg=clientpg.searchClient(companynamefield);
		String expectedmsg=companynamefield;
		Assert.assertEquals(actualmsg, expectedmsg);
	}

	@Test(priority=3,groups= {"smoke"})
	public void verifyDelete() {

		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		ClientsPage clientpg=new ClientsPage(driver);
		clientpg.clickClient();
		clientpg.searchClient(companynamefield);
		String actualmsg=clientpg.delClient();
		String expectedmsg="No record found.";
		Assert.assertEquals(actualmsg, expectedmsg);	
	}
}
