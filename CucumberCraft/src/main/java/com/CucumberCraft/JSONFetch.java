package com.CucumberCraft;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFetch {

	public static void main(String[] args) throws Exception, IOException, ParseException {
		// TODO Auto-generated method stub
		 JSONParser parser = new JSONParser();
		 Object obj = parser.parse(new FileReader("C:\\SIAutomation\\service-insight-test-automation\\CRAFT_Cucumber\\src\\test\\java\\TestData\\test5.json"));
		 JSONObject jsonObject = (JSONObject) obj;
		 JSONObject ob1=(JSONObject) jsonObject.get("344");
		 System.out.println(ob1.get("UserSI").toString());
		 
	}

}
