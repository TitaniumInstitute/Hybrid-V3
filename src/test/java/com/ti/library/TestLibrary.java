package com.ti.library;

import com.ti.framework.base.BasePage;
import com.ti.pages.LoginPage;
import com.ti.pages.MainPage;
import com.ti.pages.NavPage;
import com.ti.pages.StudentsPage;
import java.util.HashMap;
import java.util.Map;

public class TestLibrary extends BasePage {
  Map<String, String> userCredentials = new HashMap<>();
  String personal = "";

  public void login(){
    userCredentials.put("username", "admin");
    userCredentials.put("userpass","G3-ySzY%");

    actualPage = getInstance(LoginPage.class)
        .loginAs(userCredentials.get("username"))
        .withPassword(userCredentials.get("userpass"))
        .andRememberMe(true)
        .login();
  }

  public void verifySchoolTitle(){
    actualPage.as(MainPage.class).verySchoolName();
  }

  public void navigateToCreateNewStudent(){
    actualPage = getInstance(NavPage.class);
    actualPage.as(NavPage.class).goToStudents().andCreateNew();
  }

  public void studentPersonalDetail(String... studentPersonalDetails){
    personal=studentPersonalDetails[2];
    actualPage.as(StudentsPage.class).genderAs(studentPersonalDetails[0])
        .withFirstName(studentPersonalDetails[1])
        .withLastName(studentPersonalDetails[2])
        .withDayOfBirth(studentPersonalDetails[3])
        .andCurrentAddress(studentPersonalDetails[4]);
  }

  public void studentAccountInformation(String... studentAccInfo){
    actualPage.as(StudentsPage.class)
        .userEmail(studentAccInfo[0])
        .withUserName(studentAccInfo[1])
        .withPassword(studentAccInfo[2])
        .andConfirmPassword(studentAccInfo[2])
        .schoolDetails(studentAccInfo[3]);
  }

  public void verifyUserIsAdded(){
    actualPage.as(StudentsPage.class).validateStudentIsAdded(personal);
  }

}
