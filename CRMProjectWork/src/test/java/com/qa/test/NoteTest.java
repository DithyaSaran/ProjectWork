package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.page.LoginPage;
import com.qa.page.NotePage;
import com.qa.utility.ElementUtility;
import com.qa.utility.FakerUtility;

public class NoteTest extends BaseTest {
	String notetitle=FakerUtility.getName();
	String descrpt=FakerUtility.getRandomName();
	

	@Test(priority=1,groups= {"smoke"})
	public void verifyAddNotes() {

		LoginPage lp=new LoginPage(driver);
		lp.doLogin(ElementUtility.getPropertyValue("username"),ElementUtility.getPropertyValue("password"));

		NotePage notepg=new NotePage(driver);
		notepg.clickNote();
		notepg.addNote(notetitle, descrpt);
		String actualmsg=notepg.searchNote(notetitle);
		String expectedmsg=notetitle;
		Assert.assertEquals(actualmsg, expectedmsg);
	}

	@Test(priority=2,groups= {"smoke"},retryAnalyzer = generalTest.Retry.class )
	public void verifySearch() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		NotePage notepg=new NotePage(driver);
		notepg.clickNote();
		String actualmsg=notepg.searchNote(notetitle);
		String expectedmsg=notetitle;
		Assert.assertEquals(actualmsg, expectedmsg);
	}

	@Test(priority=3,groups= {"regression"})
	public void verifyEditNote() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");

		NotePage notepg=new NotePage(driver);
		notepg.clickNote();
		notepg.searchNote(notetitle);

		notepg.editNote("editedName");
		String actualmsg=notepg.searchNote("editedName");
		String expectedmsg="editedName";
		Assert.assertEquals(actualmsg, expectedmsg);
	}

	
	}

