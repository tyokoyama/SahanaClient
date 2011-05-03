package jp.sahana.chugokugtug.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppSetting {
	private static final String SITEURL = "siteURL";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	private SharedPreferences mPref;
	private String siteURL;
	private String userName;
	private String password;
	
	public AppSetting(Context context) {
		mPref = PreferenceManager.getDefaultSharedPreferences(context);
		
		siteURL = mPref.getString(SITEURL, null);
		userName = mPref.getString(USERNAME, null);
		password = mPref.getString(PASSWORD, null);
	}
	
	public String getSiteURL() {
		return siteURL;
	}
	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void commit() {
		SharedPreferences.Editor edit = mPref.edit();
		edit.putString(SITEURL, siteURL);
		edit.putString(USERNAME, userName);
		edit.putString(PASSWORD, password);
		edit.commit();
	}
}
