package com.qa.opencart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {

        private Page page;

        private String emailField="input#input-email";
        private String passwordField="input#input-password";
        private String forgetLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
        private String loginBtn = "//input[@value='Login']";
        private String logoutOption = "//a[@class='list-group-item'][normalize-space()='Logout']";

        public LoginPage(Page page){

            this.page=page;
        }

        public String getLoginPageTitle(){
            return page.title();
        }

        public boolean isForgetPasswordLink(){
            return page.isVisible(forgetLink);
        }

    public boolean doLogin(String appEmail, String appPassword) {
        System.out.println("App Creds: " + appEmail + ":" + appPassword);

        page.fill(emailField, appEmail);
        page.fill(passwordField, appPassword);
        page.click(loginBtn);

        // Wait for the logout option to appear (indicating a successful login)
        page.locator(logoutOption).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        if (page.isVisible(logoutOption)) {
            System.out.println("User logged in successfully....");
            return true;
        }
        return false;
    }

}
