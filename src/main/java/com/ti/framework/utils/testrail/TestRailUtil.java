package com.ti.framework.utils.testrail;

import com.ti.framework.base.FrameworkException;
import com.ti.framework.config.APIClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.ti.framework.config.Constants.*;

public class TestRailUtil {
    public static void addResultforTestCase(CaseModel caseModel) throws FrameworkException, IOException {
        APIClient client = new APIClient(TESTRAIL_ENGINE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        Map data = new HashMap();
        data.put("status_id", caseModel.getStatusId());
        data.put("comment", caseModel.getComment());
        client.sendPost("add_result_for_case/"+TEST_RUN_ID+"/"+caseModel.getCaseId(), data);
    }
}
