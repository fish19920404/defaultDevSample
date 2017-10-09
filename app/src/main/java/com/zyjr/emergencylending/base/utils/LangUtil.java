package com.zyjr.emergencylending.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LangUtil {

    public static boolean isBlank(Object objResult) {
        if (objResult == null) {
            return true;
        } else if ("".equals(objResult.toString())) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(String stringResult) {
        if (stringResult == null || "".equals(stringResult)) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String stringResult) {
        if (stringResult != null && !"".equals(stringResult)) {
            return true;
        }
        return false;
    }

    public static String objectToText(Object objResult) {
        if (objResult == null) {
            return "";
        } else {
            return objResult.toString();
        }
    }


    public static String inToString(InputStream inputStream) {

        String result = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
