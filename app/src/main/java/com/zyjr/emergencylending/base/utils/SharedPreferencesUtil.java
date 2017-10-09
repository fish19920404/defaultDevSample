package com.zyjr.emergencylending.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.io.Serializable;
import java.util.HashMap;

public class SharedPreferencesUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String sharedPreferencesInfo = "medicalCare.info";

	private static Context myContext;
	private static SharedPreferences saveInfo;
	private static Editor saveEditor;
	private static SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();

	public static void init(Context context) {
		myContext = context;
	}

	public static SharedPreferencesUtil getInstance(Context context) {
		myContext = context.getApplicationContext();
		if (saveInfo == null && myContext != null) {
			saveInfo = myContext.getSharedPreferences(sharedPreferencesInfo, Context.MODE_PRIVATE);
			saveEditor = saveInfo.edit();
		}
		return sharedPreferencesUtil;
	}


	public boolean isContainKey(String key) {
		return saveInfo.contains(key);
	}

	public String getString(String key) {
		return saveInfo.getString(key, "");
	}

	public String getString(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getAll() {
		return (HashMap<String, String>) saveInfo.getAll();
	}

	

	public  boolean getBoolean(String key, boolean defaultValue) {
		return saveInfo.getBoolean(key, defaultValue);
	}
	
	public  boolean setBoolean(String key, boolean value) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putBoolean(key, value);
		return saveEditor.commit();
	}

	public boolean setString(String key, String value) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putString(key, value);
		return saveEditor.commit();
	}

	public boolean clearItem(String key) {
		saveEditor.remove(key);
		return saveEditor.commit();
	}
	
	public boolean setInt(String key, int value){
		saveEditor.putInt(key, value);
		return saveEditor.commit();
	}
	
	public int getInt(String key, int value){
		return saveInfo.getInt(key, value);
	}
	
}
