package com.qa.opencart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HomePage {


    //1.string locators
    //2.page Constructor
    //3. Page method

     private Page page;

    private String search = "input[name='search']";
    private String searchBtn = "div#search button";
    private String searchHeader = "div#content h1";
    private String Accountoption = "a[title='My Account']";
    private String loginLink = "a:text('Login')";

    public  HomePage(Page page){
        this.page=page;

    }

    public String getHomePageTitle(){
        String Title =  page.title();
        System.out.println("page Title: " +Title);
        return Title;
    }

    public String getHOmePageURL(){
        String url = page.url();
        System.out.println("page url: " +url);
        return url;
    }

    public String doSearch(String product){
        page.fill(search,product);
        page.click(searchBtn);
        String resultheader =  page.textContent(searchHeader);
        System.out.println("header result: " +resultheader);
        return resultheader;
    }

    public LoginPage navigateToLoginPage() {
        page.click(Accountoption);

        // Wait until the login link is visible before clicking
        page.locator(loginLink).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        page.click(loginLink);
        return new LoginPage(page);
    }



}
