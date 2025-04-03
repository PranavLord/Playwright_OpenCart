package com.qa.opencart.tests;

import com.qa.opencart.BaseTest.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority=1)
    public void loginPageNavigation(){
        loginpage = homepage.navigateToLoginPage();
        String loginTitle = loginpage.getLoginPageTitle();
        System.out.println("Login page Title: "+ loginTitle);
        Assert.assertEquals(loginTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test(priority=2)
    public void forgotLinkVisible(){
        Assert.assertTrue(loginpage.isForgetPasswordLink());
    }

    @Test(priority=3)
    public void appLoginTest(){
        Assert.assertTrue(loginpage.doLogin(prop.getProperty("email"),prop.getProperty("password")));{

        }
    }




}
