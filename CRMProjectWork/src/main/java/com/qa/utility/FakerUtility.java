package com.qa.utility;
import com.github.javafaker.*;

public class FakerUtility {

	public static String getName()
	{
	Faker faker = new Faker();
	return faker.name().firstName();
	}
	
	public static String getNumber()
	{
		Faker faker = new Faker();
		return faker.numerify("##########");
	}
	
	public static String getRandomName()
	{
		Faker faker=new Faker();
		return faker.name().lastName();
	}
	
	
}