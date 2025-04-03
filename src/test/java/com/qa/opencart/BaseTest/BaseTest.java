package com.qa.opencart.BaseTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.playwrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    playwrightFactory pf;
    Page page;
    protected Properties prop;

    protected HomePage homepage;
    protected LoginPage loginpage;

    @BeforeTest
    public void setup(){
        pf = new playwrightFactory();
        prop = pf.init_prop();
        page = pf.initBrowser(prop);
        homepage = new HomePage(page);

    }

    @AfterTest
    public void teardown(){
        page.context().browser().close();
    }

}
