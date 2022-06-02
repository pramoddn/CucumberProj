package com.cucumbercraft.framework;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;


public class CustomReporter implements ISuiteListener  {
	Properties properties = Settings.getInstance();
	String filename ="";
	EmailReport emailreport=new EmailReport();
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
    
       int totalpassed = 0;
       int totalfailed =0;
       int totalskipped=0;
       long starttime=0;
       long endtime=0;
       ITestContext overview = null;
       Map<String, ISuiteResult> tests = suite.getResults();

       for (ISuiteResult r : tests.values())
       {
               overview = r.getTestContext();
               totalpassed = totalpassed+overview.getPassedTests().size();
               totalfailed=totalfailed+overview.getFailedTests().size();
               totalskipped=totalskipped+overview.getSkippedTests().size();
               starttime=starttime+overview.getEndDate().getTime();
               endtime=endtime+overview.getStartDate().getTime();
       }
        String suiteName = suite.getName();
        String passedtests=Integer.toString(totalpassed);
        String failedtests=Integer.toString(totalfailed);
        String skippedtests=Integer.toString(totalskipped);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(starttime - endtime);
        String endtimenew = String.valueOf(minutes);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
		Date date = new Date();
		String subject="Test Results Summary_" +  "_" + dateFormat.format(date);
		StringBuffer br=new StringBuffer();
		br.append("<body><i>Hi Team,<br/><br/>" + 
				"\r\n" + 
				"Below is the Test Execution Result-</i><br/><br/><strong><u>SUMMARY REPORT</u></strong><br/><br/>" + 
				"" + 
				"	<style type=\"text/css\">table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}th,td {border:1px solid #009;padding:.25em .5em}th \r\n" + 
				"	{vertical-align:bottom}td {vertical-align:top}table a {font-weight:bold}.stripe td {background-color: #E6EBF9}.num \r\n" + 
				"	{text-align:right}.passedodd td {background-color: #3F3}.passedeven td {background-color: #0A0}.skippedodd td \r\n" + 
				"	{background-color: #DDD}.skippedeven td {background-color: #CCC}.failedodd td,.attn {background-color: #F33}\r\n" + 
				"	.failedeven td,.stripe .attn {background-color: #D00}.stacktrace {white-space:pre;font-family:monospace}.totop \r\n" + 
				"	{font-size:85%;text-align:center;border-bottom:2px solid #000}</style>");
		br.append("<table style='margin-bottom:10px;border-collapse:collapse;empty-cells:show'>\r\n" + 
				"	  <tr style='background-color: #4CAF50;color: white;'>\r\n" + 
				"	   <th colspan='5'>SI Automation Test Result</th>\r\n" + 
				"	  </tr>\r\n" + 
				"         <tr style='padding-top: 12px;\r\n" + 
				"  padding-bottom: 12px;\r\n" + 
				"  text-align: left;\r\n" + 
				"  background-color: #4CAF50;\r\n" + 
				"  color: white;'>\r\n" + 
				"            <th>Suite</th>\r\n" + 
				"            <th>Passed</th>\r\n" + 
				"            <th>Skipped</th>\r\n" + 
				"            <th>Failed</th>\r\n" + 
				"            <th>Time (min)</th>\r\n" + 
				"         </tr>\r\n" + 
				"		 <tr>\r\n" + 
				"            <td>"+suiteName+"</td>\r\n" + 
				"            <td class='num'>"+passedtests+"</td>\r\n" + 
				"            <td class='num'>"+skippedtests+"</td>\r\n" + 
				"            <td class='num'>"+failedtests+"</td>\r\n" + 
				"            <td class='num'>"+endtimenew+"</td>\r\n" + 
				"         </tr>\r\n" + 
				"		 </table><br/><br/><i>Attached is the detailed html report.</i><br/><br/>"+ 
				"<strong>Regards,<br/>" + 
				"SI Automation Team\r\n</strong>" + 
				"" + 
				"		 </body>");
		
		try {
			if(properties.getProperty("DefaultPlatform").equalsIgnoreCase("WINDOWS")) 
			{
				filename=System.getProperty("user.dir")+"\\target\\ExtentReport\\CRAFTExtent.html";
			}
			else
			{
				filename="target/ExtentReport/CRAFTExtent.html";
			}
			emailreport.sendEmail(filename, br.toString(), subject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
		
}
