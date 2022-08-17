package com.ti.pages;

import static com.ti.framework.utils.ui.SeleniumUtil.highLight;
import static com.ti.framework.utils.ui.WaitUtil.allElementsVisible;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StudentsPage extends MainPage{

    @FindBy(name = "s_gender")
    private List<WebElement> rdnGender;

    @FindBy(id = "firstname")
    private WebElement txtFirstName;

    @FindBy(id = "lastname")
    private WebElement txtLastName;

    @FindBy(id = "Dob")
    private WebElement dtpDateOfBirth;

    @FindBy(xpath = "//td[contains(@class,'day')]")
    private List<WebElement> tdSelectDay;

    @FindBy(id = "current_address")
    private WebElement txtCurrentAddress;

    @FindBy(id = "Email")
    private WebElement txtEmailAddress;

    @FindBy(name ="Username" )
    private WebElement txtUserName;

    @FindBy(name = "Password")
    private WebElement txtPassword;

    @FindBy(id = "ConfirmPassword")
    private WebElement txtConfirmPass;

    @FindBy(id = "Rollno")
    private WebElement txtRollNumber;

    @FindBy(xpath = "//tr[contains(@role,'row')]")
    private List<WebElement> trStudentsRows;

    public StudentsPage genderAs(String gender){
        for (WebElement optGender:rdnGender) {
            if (optGender.getAttribute("value").equals(gender)){
                highLight(optGender).click();
                break;
            }
        }
        return this;
    }

    public StudentsPage withFirstName(String firstName){
        txtFirstName.clear();
        highLight(txtFirstName).sendKeys(firstName);
        return this;
    }

    public StudentsPage withLastName(String lastName){
        highLight(txtLastName).clear();
        txtLastName.sendKeys(lastName);
        return this;
    }

    public StudentsPage withDayOfBirth(String selectedDay){
        highLight(dtpDateOfBirth).click();

        allElementsVisible(tdSelectDay);
        for (WebElement day:tdSelectDay) {
            if (day.getText().equals(selectedDay)){
                highLight(day).click();
                break;
            }
        }
        return this;
    }

    public StudentsPage andCurrentAddress(String currentAddress){
        highLight(txtCurrentAddress).clear();
        txtCurrentAddress.sendKeys(currentAddress);
        return this;
    }

    public StudentsPage userEmail(String email){
        highLight(txtEmailAddress).clear();
        txtEmailAddress.sendKeys(email);
        return this;
    }

    public StudentsPage withUserName(String userName){
        highLight(txtUserName).clear();
        txtUserName.sendKeys(userName);
        return this;
    }

    public StudentsPage withPassword(String password){
        highLight(txtPassword).clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public StudentsPage andConfirmPassword(String confPass){
        highLight(txtConfirmPass).clear();
        txtConfirmPass.sendKeys(confPass);
        return this;
    }

    public StudentsPage schoolDetails(String rollNumber){
        highLight(txtRollNumber).clear();
        txtRollNumber.sendKeys(rollNumber);
        txtRollNumber.submit();
        return this;
    }

    public StudentsPage validateStudentIsAdded(String name){
        try {
            allElementsVisible(trStudentsRows);
        }catch (TimeoutException te){
            preLoading();
            allElementsVisible(trStudentsRows);
        }

        WebElement newStudentRow = trStudentsRows.get(trStudentsRows.size()-1);
        verification.verifyTestIsDisplayed(highLight(newStudentRow),name);
        return this;
    }

    public StudentsPage deleteStudent(){
        deleteRow();
        confirmationWindow();
        return this;
    }
}
