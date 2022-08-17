package com.ti.pages;

import static com.ti.framework.utils.ui.SeleniumUtil.highLight;
import static com.ti.framework.utils.ui.SeleniumUtil.refresh;
import static com.ti.framework.utils.ui.WaitUtil.allElementsVisible;
import static com.ti.framework.utils.ui.WaitUtil.elementClickable;
import static com.ti.framework.utils.ui.WaitUtil.elementNotVisible;
import static com.ti.framework.utils.ui.WaitUtil.sync;

import com.ti.framework.base.BasePage;
import com.ti.framework.base.LocalDriverFactory;
import com.ti.framework.base.FrameworkException;

import java.util.List;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    WebDriver driver = LocalDriverFactory.getInstance().getDriver();

    @FindBy(className = "wpsp-preLoading")
    private WebElement dvPreloading;

    @FindBy(className = "wpsp-schoolname")
    private WebElement spnTI;

    @FindBy(xpath = "//*[text()=' Create New']")
    private WebElement btnCreateNew;

    @FindBy(id = "d_teacher")
    private List<WebElement> icnDelete;

    @FindBy(xpath = "//a[contains(text(),'Ok')]")
    private WebElement btnOk;

    public MainPage preLoading(){
        try {
            sync();
            elementNotVisible(highLight(dvPreloading));
        }catch (TimeoutException | FrameworkException te){
            refresh();
        }
        return this;
    }

    private String getSchoolName(){
        return highLight(spnTI).getText();
    }

    public MainPage verySchoolName(){
        verification.verifyTextAreEquals(getSchoolName(),"Titanium School");
        return this;
    }

    public MainPage andCreateNew(){
        elementClickable(highLight(btnCreateNew));
        preLoading();
        return this;
    }

    public MainPage deleteRow(){
        allElementsVisible(icnDelete);
        highLight(icnDelete.get(icnDelete.size()-1)).click();
        return this;
    }

    public MainPage confirmationWindow(){
        elementClickable(highLight(btnOk));
        btnOk.click();
        elementNotVisible(btnOk);
        return this;
    }
}
