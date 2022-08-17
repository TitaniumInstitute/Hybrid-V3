package com.ti.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.ti.framework.utils.ui.SeleniumUtil.highLight;

public class LoginPage extends MainPage{

    @FindBy(id = "user_login")
    private WebElement txtUser;

    @FindBy(name = "pwd")
    private WebElement txtPassword;

    @FindBy(css = "#rememberme")
    private WebElement chkRememberMe;

    @FindBy(xpath = "//input[contains(@value, 'Log')]")
    private WebElement btnLogin;

    public LoginPage loginAs(String userName){
        txtUser.clear();
        highLight(txtUser).sendKeys(userName);
        return this;
    }

    public LoginPage withPassword(String password){
        txtPassword.clear();
        highLight(txtPassword).sendKeys(password);
        return this;
    }

    public LoginPage andRememberMe(boolean checked){
        if (checked){
            highLight(chkRememberMe).click();
        }
        return this;
    }

    public MainPage login(){
        highLight(btnLogin).click();
        return (MainPage) (actualPage = getInstance(MainPage.class));
    }
}
