package alancasasarevalo.com.repository.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReadJsonFile {
    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = null;
            if (ReflectionUtil.hasMethod(ReadJsonFile.class, "getAssets")) {
                Method methodGetAssets = ReflectionUtil.getMethod(ReadJsonFile.class, "getAssets");
                try {
                    Object result = methodGetAssets.invoke(this, fileName);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                // is = getAssets().open(fileName);
            } else {
                // file from "resources" folder
                is = ReadJsonFile.class.getClassLoader().getResourceAsStream(fileName);
            }

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
