package jp.sahana.chugokugtug.web;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Base64;
import android.util.Log;

public class SahanaHttpClient {
	private static final String LOG_TAG = "SahanaHttpClient";
	private String response;
	private String base64String;
	
	public SahanaHttpClient() {
		base64String = null;
	}	
	
	public SahanaHttpClient(String user, String password) {
		this();
		String basicInput = user + ":" + password;
		base64String = Base64.encodeToString(basicInput.getBytes(), Base64.DEFAULT);
	}
	
	public String getResponse() {
		return this.response;
	}
	
	public int get(String url) {		
		int iRet = -1;
		
		HttpClient client = new DefaultHttpClient();
		HttpEntity entity = null;
		
		try {
			Log.d(LOG_TAG, url);
			
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);
			
			iRet = response.getStatusLine().getStatusCode();
			if(iRet == HttpStatus.SC_OK) {
				entity = response.getEntity();
				byte[] bArray = EntityUtils.toByteArray(entity);
				this.response = new String(bArray, "UTF-8");
//				this.response = EntityUtils.toString(entity);				
			}
			
		} catch(ClientProtocolException e) {
			Log.d(LOG_TAG, "ClientProtocolException:", e);
		} catch(IOException ie) {
			Log.d(LOG_TAG, "IOException:", ie);
		} catch(IllegalStateException ille) {
			Log.d(LOG_TAG, "IllegalStateException:", ille);
		} finally {
			try {
				if(entity != null) entity.consumeContent();
			} catch(Exception e) {}
			client.getConnectionManager().shutdown();
		}

		return iRet;
	}
	
	public int post(String url, List<NameValuePair> param) {		
		int iRet = -1;
		
		HttpClient client = new DefaultHttpClient();
		HttpEntity entity = null;
		
		try {
			Log.d(LOG_TAG, url);
			
			HttpPost httpPost = new HttpPost(url);
			if(base64String != null) {
				httpPost.setHeader("Authorization", "Basic " + base64String);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(param));
			HttpResponse response = client.execute(httpPost);
			
			iRet = response.getStatusLine().getStatusCode();
			if(iRet == HttpStatus.SC_OK) {
				entity = response.getEntity();
				byte[] bArray = EntityUtils.toByteArray(entity);
				this.response = new String(bArray, "UTF-8");
//				this.response = EntityUtils.toString(entity);				
			}
			
		} catch(ClientProtocolException e) {
			Log.d(LOG_TAG, "ClientProtocolException:", e);
		} catch(IOException ie) {
			Log.d(LOG_TAG, "IOException:", ie);
		} catch(IllegalStateException ille) {
			Log.d(LOG_TAG, "IllegalStateException:", ille);
		} finally {
			try {
				if(entity != null) entity.consumeContent();
			} catch(Exception e) {}
			client.getConnectionManager().shutdown();
		}

		return iRet;
	}
}
