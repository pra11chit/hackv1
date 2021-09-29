package TestSuite;

import org.testng.annotations.Test;

import Pages.LoginPage;

public class LoginTest 
{
	public void  testingLogin() throws InterruptedException
	{
		LoginPage l= new LoginPage();
		l.openUrl();
		l.clickLogin();
		l.clickGoogleSignIn();
		l.captureErrorMessage();	
	}

}
