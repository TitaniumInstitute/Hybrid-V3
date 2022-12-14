package com.ti.framework.utils.data;

import static com.ti.framework.config.Constants.DATA_FOLDER;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtils {
  private static JSONArray dataObj;
  private static JSONObject contentObj;

  public static Object[][] getJSONTableArray(String jsonFile, String userType, byte JSONAttributes)
      throws IOException, ParseException {
    JSONParser parser = new JSONParser();

    contentObj = (JSONObject) parser.parse(new FileReader(DATA_FOLDER + jsonFile));
    dataObj = (JSONArray) contentObj.get(userType);

    Object[][] arr = new String[dataObj.size()][JSONAttributes];
        for(byte i=0; i<dataObj.size();i++){
          JSONObject obj = (JSONObject) dataObj.get(i);
          arr[i][0] = String.valueOf(obj.get("Gender"));
          arr[i][1] = String.valueOf(obj.get("FirstName"));
          arr[i][2] = String.valueOf(obj.get("LastName"));
          arr[i][3] = String.valueOf(obj.get("DayOfBirth"));
          arr[i][4] = String.valueOf(obj.get("CurrentAddress"));
          arr[i][5] = String.valueOf(obj.get("Email"));
          arr[i][6] = String.valueOf(obj.get("User"));
          arr[i][7] = String.valueOf(obj.get("Password"));
          arr[i][8] = String.valueOf(obj.get("SchoolDetail"));
        }
        return arr;
  }
}
