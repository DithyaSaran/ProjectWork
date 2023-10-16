package com.qa.test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.constants.Constants;
import com.qa.page.LoginPage;

import com.qa.utility.*;

public class LoginTest extends BaseTest {
	@Test(dataProvider="userData")
	public void login(String username,String password) {
		LoginPage lp=new LoginPage(driver);
		boolean status=lp.doLogin("admin@admin.com", "12345678");
		Assert.assertTrue(status);
	}

	@DataProvider

	public Object[][] userData() throws InvalidFormatException, IOException 
	{
		Object[][] data=ExcelRead.getDataFromExcel(Constants.testdata, 
				"Login");
		return data;
	}
}
