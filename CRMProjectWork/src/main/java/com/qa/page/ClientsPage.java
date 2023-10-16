package com.qa.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utility.ElementUtility;
import com.qa.utility.WaitUtility;

public class ClientsPage {
	WebDriver driver;

	@FindBy(xpath="//*[text()='Clients']")
	WebElement clientlink;

	@FindBy(className="btn-default")
	WebElement addclient;

	@FindBy(xpath="//input[@name='company_name']")
	WebElement companynamefield;

	@FindBy(xpath="//textarea[@id='address']")
	WebElement addressfield;

	@FindBy(xpath="//input[@name='city']")
	WebElement cityfield;

	@FindBy(xpath="//input[@name='state']")
	WebElement statefield;

	@FindBy(xpath="//input[@name='zip']")
	WebElement zipfield;

	@FindBy(xpath="//input[@name='country']")
	WebElement countryfield;

	@FindBy(xpath="//input[@name='phone']")
	WebElement phonefield;

	@FindBy(xpath="//input[@name='website']")
	WebElement web;

	@FindBy(xpath="//input[@name='vat_number']")
	WebElement vatfield;

	@FindBy(xpath="//input[@id='currency_symbol']")
	WebElement currency;

	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement save;

	@FindBy(xpath="//h1[text()]")
	WebElement clienttext;

	@FindBy(xpath="//input[@type='search']")
	WebElement search;

	@FindBy(xpath="//table[@id='client-table']//tbody//tr[1]//td[2]")
	WebElement searchelement;

	@FindBy(xpath="//tr[@class='odd']")
	WebElement delClientRow;

	@FindBy(xpath="//a[@title='Delete client']")
	WebElement delClientbtn;

	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement confirmdel;

	@FindBy(xpath="//table[@id=\"client-table\"]/tbody/tr/td")
	WebElement delmessage;

	@FindBy(xpath="//button[@class='close' and @type='button']")
	WebElement deleterecord;

	String clientCmpName="2";

	WaitUtility waitUtil;
	ElementUtility elementUtil;

	public ClientsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this );
		waitUtil=new WaitUtility(driver);
		elementUtil=new ElementUtility(driver);
	}

	public void clickClient()
	{
		waitUtil.waitForclick(clientlink);
		elementUtil.click(clientlink);
	}

	public void addClient(String companyname,String address,String city,String state,String zip,String country,String phone,String website,String vatnumber,String currencysymbol)
	{
		elementUtil.click(addclient);
		elementUtil.sendkeys(companynamefield, companyname);
		elementUtil.sendkeys(addressfield, address);
		elementUtil.sendkeys(cityfield, city);
		elementUtil.sendkeys(statefield, state);
		elementUtil.sendkeys(zipfield, zip);
		elementUtil.sendkeys(countryfield, country);
		elementUtil.sendkeys(phonefield, phone);
		elementUtil.sendkeys(web, website);
		elementUtil.scrollintoview(vatfield);
		elementUtil.sendkeys(vatfield, vatnumber);
		elementUtil.sendkeys(currency, currencysymbol);
		elementUtil.click(save);
		elementUtil.click(clientlink);
	}

	public String searchClient(String searchname)
	{
		waitUtil.waitForclick(search);
		elementUtil.sendkeys(search, searchname);

		By locator=By.xpath("//table[@id='client-table']//tbody//tr//td//a[contains(text(),'"+searchname+"')]");
		waitUtil.waitforvisible(locator);
		List<WebElement> notetable=driver.findElements(By.xpath("//table[@id='client-table']//tbody//tr//td//a[contains(text(),'"+searchname+"')]"));
		waitUtil.waitforvisible(notetable);
		int row=elementUtil.getTableDataRowCount(notetable, searchname);

		String actualmsg="";
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='client-table']//tbody//tr["+row+"]//td["+clientCmpName+"]"));
			actualmsg=tableRow.getText();
			System.out.println("VerifySearch "  +actualmsg);
		}
		return actualmsg;
	}

	public String delClient()
	{
		Actions action=new Actions(driver);
		action.moveToElement(delClientRow).perform(); 
		elementUtil.click(delClientbtn);
		elementUtil.click(confirmdel);
		waitUtil.waitForclick(deleterecord);
		elementUtil.click(deleterecord);
		waitUtil.waitforvisible(delmessage);

		String actualmsg=elementUtil.getText(delmessage);
		System.out.println("Verify DeleteItem : " +actualmsg);
		return actualmsg;

	}
}




