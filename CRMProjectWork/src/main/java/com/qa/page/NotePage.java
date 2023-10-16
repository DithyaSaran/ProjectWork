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

public class NotePage {
	WebDriver driver;

	@FindBy(xpath="//*[text()='Notes']")
	WebElement notelink;

	@FindBy(className="btn-default")
	WebElement addnotes;

	@FindBy(xpath="//input[@name='title']")
	WebElement notetitle;

	@FindBy(xpath="//textarea[@name='description']")
	WebElement descrpt;
	
	@FindBy(xpath="//input[@id='s2id_autogen2']")
	WebElement label;

	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement save;

	@FindBy(xpath="//input[@type='search']")
	WebElement search;

	@FindBy(xpath="//table[@id='note-table']//tbody//tr[1]//td[2]")
	WebElement editrowelement;

	@FindBy(xpath="//a[@title='Edit note']")
	WebElement editbutton;

	@FindBy(xpath="//tr[@class='odd']")
	WebElement delrow;

	@FindBy(xpath="//a[@title='Delete note']")
	WebElement delbutton;

	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement confirmdel;

	@FindBy(xpath="//table[@id='note-table']//tbody//tr[1]//td[2]")
	WebElement searchtext;

	@FindBy(xpath="//table[@id=\"note-table\"]/tbody/tr")
	WebElement deltext;
	
	@FindBy(xpath="//button[@class='close' and @type='button']")
	WebElement deleterecord;

	String edittitle;
	String notetitlecolumn="2";

	WaitUtility waitUtil;
	ElementUtility elementUtil;

	public NotePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this );
		waitUtil=new WaitUtility(driver);
		elementUtil=new ElementUtility(driver);
		}

	public void clickNote()
	{
		waitUtil.waitForclick(notelink);
		elementUtil.click(notelink);
	}

	public void addNote(String titlenote,String description)
	{
		elementUtil.click(addnotes);
		elementUtil.sendkeys(notetitle, titlenote);
		elementUtil.sendkeys(descrpt, description);
		elementUtil.click(save);
		waitUtil.waitForclick(notelink);
		elementUtil.click(notelink);
	}

	public String searchNote(String searchElement)
	{
		waitUtil.waitForclick(search);
		elementUtil.sendkeys(search, searchElement);

		By locator=By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'"+searchElement+"')]");
		waitUtil.waitforvisible(locator);
		List<WebElement> notetable=driver.findElements(By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'"+searchElement+"')]"));
		waitUtil.waitforvisible(notetable);
		int row=elementUtil.getTableDataRowCount(notetable, searchElement);

		String actualmsg="";
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='note-table']//tbody//tr["+row+"]//td["+notetitlecolumn+"]"));
			actualmsg=tableRow.getText();
			System.out.println("Searched Element : " +actualmsg);
		}
		return actualmsg;
	}

	public void editNote(String titlenote)
	{		
		Actions action=new Actions(driver);
		action.moveToElement(editrowelement).perform(); 

		elementUtil.click(editbutton);
		elementUtil.clear(notetitle);
		elementUtil.sendkeys(notetitle, titlenote);
		elementUtil.click(save);
		
		waitUtil.waitForclick(notelink);
		waitUtil.waitforvisible(notelink);
		elementUtil.click(notelink);
	}

	
}
