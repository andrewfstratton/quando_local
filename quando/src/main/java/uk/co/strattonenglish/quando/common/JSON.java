package uk.co.strattonenglish.quando.common;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class JSON {
  private JSONObject object;

  private JSON(JSONObject obj) {
    object = obj;
  }
  
  public JSON(HttpServletRequest request) throws IOException, JSONException {
    StringBuffer buffer = new StringBuffer();
    BufferedReader reader = request.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
        buffer.append(line);
    }
    object = new JSONObject(buffer.toString());
  }
    
  public JSON(String str) {
    object = new JSONObject(str);
  }
    
  public JSON createOnKey(String key) {
    return new JSON(object.getJSONObject(key));
  }
    
  public String getString(String key) {
    return object.getString(key);
  }
    
  public String getString(String key, String null_value) {
    String result = null_value;
    if (object.has(key)) {
      result = object.getString(key);
    }
    return result;
  }
    
  public int getInteger(String key, int null_value) {
    int result = null_value;
    if (object.has(key)) {
      result = object.getInt(key);
    }
    return result;
  }
    
  public float getFloat(String key, float null_value) {
    float result = null_value;
    if (object.has(key)) {
      result = object.getFloat(key);
    }
    return result;
  }
    
  public boolean getBoolean(String key, boolean null_value) {
    boolean result = null_value;
    if (object.has(key)) {
      result = object.getBoolean(key);
    }
    return result;
  }
}