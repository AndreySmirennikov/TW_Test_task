package org.ui.pages.utils;

import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;

import static org.ui.AbstractSeleniumTest.LOCAL;

public class JsonReader {
    public static String getValue(String name) {
        String resourceName = LOCAL + ".json";
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(resourceName);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
        }

        JSONObject object = new JSONObject(new JSONTokener(is));
        return object.getString(name);
    }
}
