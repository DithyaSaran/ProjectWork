package com.qa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utility.ElementUtility;

public class LoginPage {
	WebDriver driver;

	@FindBy(name="email")            // @FindBy(locator="value")
	WebElement mail ;                 // WebElement element;                

	@FindBy(name="password")
	WebElement pswd;

	@FindBy(xpath="//button[@type='submit']")
	WebElement sign;

	@FindBy(xpath="//img[@src='https://qalegend.com/crm/files/system/_file64a83fc696ade-site-logo.png']")
	WebElement crmImage;

	ElementUtility elementUtil;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this ); //initialize webelements declared using @FindBY
		elementUtil=new ElementUtility(driver);
	}

	public boolean doLogin(String email,String password)
	{
		elementUtil.sendkeys(mail, email);
		elementUtil.sendkeys(pswd, password);
		elementUtil.click(sign); 

		boolean status=crmImage.isDisplayed();
		System.out.println("CRM Logo is displayed " +status);
		return status;
	}

}
