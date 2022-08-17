package com.ti.pages;

import static com.ti.framework.utils.ui.SeleniumUtil.highLight;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavPage extends MainPage{

    @FindBy(linkText = "Students")
    private WebElement navStudents;

    public StudentsPage goToStudents(){
        highLight(navStudents).click();
        return (StudentsPage) (actualPage = getInstance(StudentsPage.class));
    }
}
