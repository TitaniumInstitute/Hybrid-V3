package com.ti.testdata;

import static com.ti.framework.utils.data.JSONUtils.getJSONTableArray;

import com.ti.framework.config.PropertyManager;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

public class DataClass {
  private static String jsonFile = PropertyManager.getInstance().getProperty("JSONStudentsFile");

  @DataProvider(name="StudentsJSONData")
  public static Object[][] getDataFromJSON() throws IOException, ParseException {
    return getJSONTableArray(jsonFile, "Student", (byte)9);
  }

}
