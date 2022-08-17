package com.ti.testcases;

import static com.ti.framework.utils.extentreports.ExtentTestManager.startTest;

import com.ti.framework.config.PropertyManager;
import com.ti.testdata.DataClass;
import java.lang.reflect.Method;
import org.testng.annotations.Test;

public class StudentTest extends BaseTest{

  private static String caseId = PropertyManager.getInstance().getProperty("AddStudentTestId");

  @Test(dataProviderClass = DataClass.class, dataProvider = "StudentsJSONData")
  void createNewStudent(Method method, String... studentInfo){
    startTest(method.getName(), "Verify new student is created");
    navigateToCreateNewStudent();
    studentPersonalDetail(studentInfo[0],studentInfo[1],studentInfo[2],studentInfo[3],studentInfo[4]);
    studentAccountInformation(studentInfo[5],studentInfo[6],studentInfo[7],studentInfo[8]);
    verifyUserIsAdded();

    caseModel.setCaseId(Integer.parseInt(caseId));
    i++;
  }

}
