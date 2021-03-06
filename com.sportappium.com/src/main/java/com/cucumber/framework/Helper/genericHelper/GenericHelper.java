package com.cucumber.framework.Helper.genericHelper;

import java.io.File;
import java.util.List;
import com.cucumber.framework.Helper.Logger.LoggerHelper;
import com.cucumber.framework.Helper.TestBase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import org.apache.commons.io.FileUtils;

public class GenericHelper {

	private static final Logger log=LoggerHelper.getLogger(GenericHelper.class);
	
	
	public String readValueFromElement(WebElement element)
	{
		if (element==null)
		{
			log.info("Element is null");
			return null;
		}
	
		try
		{
			isDisplayed(element);
		}
		
		catch (Exception ex){
			log.error(ex);
			return null;
		}
		
		String text=element.getText();
		log.info("WebElement value is : " + text);
		return text;
	}
	
	public String readValueFromInput(WebElement element)
	{
		if(null==element)
			return null;
		if(!isDisplayed(element))
			return null;
		String value=element.getAttribute("value");
		log.info("webelement value is : " + value);
		return value;
	}
	
	public static boolean isDisplayed(WebElement element)
	{
		try
		{
			element.isDisplayed();
			log.info("Element is displayed " + element.getText().toString());
			return false;
		}
		catch (Exception ex)
		{
			log.info(ex);
			System.out.println(ex.getMessage());
			//log.info("Throwing run time exception for Element is not displayed for elemenet  " + element.getTagName().toString());
			//TestBase.strErrMsg_GenLib=ex.getMessage()+ element.getTagName().toString();
			TestBase.strErrMsg_GenLib=ex.getMessage();
			System.out.println(TestBase.strErrMsg_GenLib);
			return true;
		}
	}
	
	
	public static boolean click(WebElement element)
	{
		boolean flag=false;
		try
		{
		
			
		if(GenericHelper.isDisplayed(element))
			{
				element.click();
				//System.out.println(element.getText().toString() + "is clicked successfully");
				flag=true;		
		}
		else
			{
				throw new RuntimeException("Throwing run time exception for Element is not displayed" + element.getTagName().toString());
			}
		}
		
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			log.info("Throwing run time exception for Element is not displayed for elemenet  " + element.getTagName().toString());
			TestBase.strErrMsg_GenLib=ex.getMessage();
			//strErrMsg_GenLib=e.getMessage()+ element.getTagName().toString();
			
		}
		return flag;
	}
	
	public static boolean hoverOverElement(WebElement we,WebDriver dr,List<WebElement> dropDown)
	{
		boolean flag=false;	
		try
		{
			Actions act = new Actions(dr);
			act.moveToElement(we).build().perform();
			//Thread.sleep(3000);
			
			if(dropDown.size()>1)
				flag=true;	
			
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return flag;
	}
	
	public static boolean defaultSelection(WebElement we)
	{
		boolean flag=false;
		try
		{
		if( isDisplayed (we))
			//&& we.isSelected()
			return true;
		}
		
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return flag;
		
	}
	
	public static String captureScreenShot3(WebDriver driver, String screenShotName,String folderName,String deviceID,String modelName)
    {
        String dest = null;
        String timeStamp;
        File dest1=null;
        
    	/*File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());*/
    
        try 
        {    
        	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());   
            if(deviceID.equalsIgnoreCase("42003a0fd3148479"))
            {
            	dest=System.getProperty("user.dir")+"\\ScreenShots\\"+ modelName +"\\"+folderName+"\\"+timeStamp+"__"+screenShotName+".png";
            	dest1=new File (System.getProperty("user.dir")+"\\ScreenShots\\"+ modelName +"\\"+folderName +"\\"+timeStamp+"__"+screenShotName+".png");
            	FileUtils.copyFile(scrFile, dest1);
                System.out.println("Screenshot take");

            }
            if(deviceID.equalsIgnoreCase("chrome"))
            {
            	dest=System.getProperty("user.dir")+"\\Screenshots\\"+ folderName +"\\"+deviceID +"\\"+screenShotName+".png";
                File destination=new File(dest);
               // FileUtils.copyFile(source, destination);
                System.out.println("Screenshot take");

            }
        }
        catch (Exception e)
        {
            System.out.println("Exception while taking a screen shot"+ e.getMessage());
            return e.getMessage();
        }

        return dest;
}
}
