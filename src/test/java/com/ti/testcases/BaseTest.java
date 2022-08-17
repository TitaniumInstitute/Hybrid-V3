package com.ti.testcases;

import com.ti.framework.base.BrowserType;
import com.ti.framework.base.FrameworkException;
import com.ti.framework.base.LocalDriverFactory;
import com.ti.framework.config.PropertyManager;
import com.ti.framework.utils.logger.Log;
import com.ti.framework.utils.testrail.CaseModel;
import com.ti.framework.utils.video.VideoRecorder;
import com.ti.library.TestLibrary;
import com.ti.pages.StudentsPage;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.ti.framework.config.Constants.*;
import static com.ti.framework.utils.testrail.TestRailUtil.addResultforTestCase;

public class BaseTest extends TestLibrary {
    protected static VideoRecorder videoRec;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    CaseModel caseModel;
    protected int i;

    @BeforeTest
    @Parameters("browser")
    void setup(String browser) {
        i=1;
        //Arrange
        LocalDriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        if (Boolean.parseBoolean(PropertyManager.getInstance().getProperty("Video"))){
            videoRec = new VideoRecorder();
            videoRec.startRecording(VIDEO_FOLDER);
        }
        LocalDriverFactory.getInstance().getDriver().navigate().to(baseURL);
        Log.info("Test is starting!");
        login();
    }

    @BeforeMethod
    void caseInstance(){caseModel = new CaseModel();}

    @AfterMethod
    void checkResult(ITestResult iTestResult) throws FrameworkException, IOException {
        Map<Object,Object> results = new HashMap<>();
        results.put(ITestResult.SUCCESS,TEST_CASE_PASSED_STATUS);
        results.put(ITestResult.FAILURE, TEST_CASE_FAILED_STATUS);

        caseModel.setStatusId((int)results.get(iTestResult.getStatus()));
        caseModel.setComment(caseModel.getStatusId() == 1?
                iTestResult.getName() + "Passed! in iteration "+ i:
                iTestResult.getName() + "Failed! in iteration "+i);

        addResultforTestCase(caseModel);
    }

    @AfterTest
    void turnDown() throws Exception {
        for (int i=0;i<3;i++){
            actualPage.as(StudentsPage.class).deleteStudent();
        }
        LocalDriverFactory.getInstance().removeDriver();
        Log.info("Test are ending!");
        if (videoRec!=null){
            videoRec.stopRecording();
        }
    }
}
