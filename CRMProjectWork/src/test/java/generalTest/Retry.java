package generalTest;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


	

	public class Retry implements IRetryAnalyzer 
	{


	   private static final int    maxTry = 2;
	   private              int    count  = 0;

	   @Override
	   public boolean retry (final ITestResult iTestResult) {
	       if (!iTestResult.isSuccess ()) {
	           if (this.count < maxTry) {
	     
	               this.count++;
	               return true;
	           }
	       }
	       return false;
	   }


	}


