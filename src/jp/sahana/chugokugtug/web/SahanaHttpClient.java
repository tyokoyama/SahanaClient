package jp.sahana.chugokugtug.web;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class SahanaHttpClient {
	private static final String LOG_TAG = "SahanaHttpClient";
	private String response;
	
	public String getResponse() {
		return this.response;
	}
	
	public int getData(String url) {
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
				this.response = EntityUtils.toString(entity);				
			}
			
		} catch(ClientProtocolException e) {
			Log.d(LOG_TAG, "Exception:", e);
		} catch(IOException ie) {
			Log.d(LOG_TAG, "Exception:", ie);			
		} finally {
			try {
				if(entity != null) entity.consumeContent();
			} catch(Exception e) {}
			client.getConnectionManager().shutdown();
		}

		return iRet;
	}
}
