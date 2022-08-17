package com.ti.testcases;

import static com.ti.framework.utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import com.ti.framework.config.PropertyManager;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

  private static String caseId = PropertyManager.getInstance().getProperty("LoginTestId");

  @Test
  void loginWithRightCredentials(Method method){
    startTest(method.getName(),"Login using right credentials");
    verifySchoolTitle();

    caseModel.setCaseId(Integer.parseInt(caseId));
  }

}
