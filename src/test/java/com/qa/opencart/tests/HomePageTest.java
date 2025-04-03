package com.qa.opencart.tests;


import com.qa.opencart.BaseTest.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageTest extends BaseTest {



    @Test
    public void homepageTitleTest(){
       String actualTitle =  homepage.getHomePageTitle();
        Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
    }

    @Test
    public void homepageurlTest(){
        String actualurl =  homepage.getHOmePageURL();
        Assert.assertEquals(actualurl,prop.getProperty("url"));
    }

    @DataProvider
    public Object[][] getProductData() {
        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };
    }

    @Test(dataProvider = "getProductData")
    public void searchTest(String productName){
       String searchheader =  homepage.doSearch(productName);
       Assert.assertEquals(searchheader,"Search - "+productName);
    }


}
