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

public class ItemsPage {

	WebDriver driver;

	@FindBy(xpath="//*[text()='Items']")            
	WebElement itemlink ; 

	@FindBy(xpath="//a[@title='Add item']")            
	WebElement addItem ; 

	@FindBy(name="title")           
	WebElement itemtitlefield ; 

	@FindBy(id="description")            
	WebElement description ;

	@FindBy(name="unit_type")            
	WebElement unittype ;

	@FindBy(name="item_rate")            
	WebElement rate;

	@FindBy(xpath="//button[@type='submit']")           
	WebElement save ; 

	@FindBy(xpath="//tr[@class='odd']")
	WebElement editItemRow;

	@FindBy(xpath="//a[@title='Edit item']")
	WebElement editItemBtn;

	@FindBy(xpath="//input[@type='search']")
	WebElement searchItem;

	@FindBy(xpath="//tr[@class='odd']")
	WebElement delItemRow;

	@FindBy(xpath="//a[@title='Delete']")
	WebElement delItembtn;
	
	@FindBy(xpath= "//table[@id='item-table']//tbody//tr//td[1]")	 
	List<WebElement>  itemtable;
	
	@FindBy(xpath="//table[@id='item-table']//tbody//tr[1]//td[1]")
	WebElement edititemtext;
	
	@FindBy(xpath="//table[@id=\"item-table\"]/tbody/tr/td")
	WebElement delmessage;
	
	@FindBy(xpath="//button[@class='close' and @type='button']")
	WebElement deleterecord;

	String itemtitlecolumn="1";
	String editcolumn="2";

	ElementUtility elementUtil;
	WaitUtility waitUtil;
	
	String searchElement;

	public ItemsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this );
		elementUtil=new ElementUtility(driver);
		waitUtil=new WaitUtility(driver);
	}

	public void clickItem()
	{
		waitUtil.waitForclick(itemlink);
		elementUtil.click(itemlink);
	}

	public void addItem(String itemtitle,String itemdescription,String itemunit,String itemrate)
	{
		elementUtil.click(addItem);
		elementUtil.sendkeys(itemtitlefield, itemtitle);
		elementUtil.sendkeys(description, itemdescription);
		elementUtil.sendkeys(unittype, itemunit);
		elementUtil.sendkeys(rate, itemrate);
		elementUtil.click(save);
		
		waitUtil.waitForclick(itemlink);
		elementUtil.click(itemlink);
	}

	public String editItem(String edititem)
	{		
		Actions action=new Actions(driver);
		action.moveToElement(editItemRow).perform(); 

		elementUtil.click(editItemBtn);
		elementUtil.clear(description);
		waitUtil.waitforvisible(description);
		elementUtil.sendkeys(description, edititem);
		elementUtil.click(save);
		
		By locator=By.xpath("//table[@id='item-table']//tbody//tr//td[contains(text(),'"+edititem+"')]");
		waitUtil.waitforvisible(locator);
		List<WebElement> itemtable=driver.findElements(By.xpath("//table[@id='item-table']//tbody//tr//td["+editcolumn+"]"));
		waitUtil.waitforvisible(itemtable);
		int row=elementUtil.getTableDataRowCount(itemtable, edititem);
		String actualmsg="";
		
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='item-table']//tbody//tr["+row+"]//td["+editcolumn+"]"));
			actualmsg=tableRow.getText();
			System.out.println("Verify EditItem Description :  " +actualmsg);
		}
		return actualmsg;
	}
		
	public String searchItem(String searchElement)
	{
		waitUtil.waitForclick(searchItem); 
		elementUtil.sendkeys(searchItem, searchElement);

		By locator=By.xpath("//table[@id='item-table']//tbody//tr//td[contains(text(),'"+searchElement+"')]");
		waitUtil.waitforvisible(locator);
		List<WebElement> itemtable=driver.findElements(By.xpath("//table[@id='item-table']//tbody//tr//td["+itemtitlecolumn+"]"));
		waitUtil.waitforvisible(itemtable);
		int row=elementUtil.getTableDataRowCount(itemtable, searchElement);
		String actualmsg="";
		
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='item-table']//tbody//tr["+row+"]//td["+itemtitlecolumn+"]"));
			actualmsg=tableRow.getText();
			System.out.println("Verify SearchItem : " +actualmsg);
		}
		return actualmsg;
	}

	public String delItem()
	{
		Actions action=new Actions(driver);
		action.moveToElement(delItemRow).perform(); 
		elementUtil.click(delItembtn);
		waitUtil.waitForclick(deleterecord);
		elementUtil.click(deleterecord);
		waitUtil.waitforvisible(delmessage);
		
		String actualmsg=elementUtil.getText(delmessage);
		System.out.println("Verify DeleteItem : " +actualmsg);
		return actualmsg;
		

	}



}