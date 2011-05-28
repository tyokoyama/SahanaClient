package jp.sahana.chugokugtug.web;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Base64;
import android.util.Log;

public class SahanaHttpClient {
	private static final String LOG_TAG = "SahanaHttpClient";
	private String response;
	private String user;
	private String pass;
	
	public SahanaHttpClient() {
	}	
	
	public SahanaHttpClient(String user, String password) {
		this();
		
		this.user = user;
		this.pass = password;
	}
		
	public String getResponse() {
		return this.response;
	}
	
	public int get(String url) {		
		int iRet = -1;
		
		DefaultHttpClient client = new DefaultHttpClient();
		HttpEntity entity = null;
		
		try {
			Log.d(LOG_TAG, url);
			
			HttpGet httpGet = new HttpGet(url);

			addAuthorization(httpGet);
			
			HttpResponse response = client.execute(httpGet);
			
			iRet = response.getStatusLine().getStatusCode();
			entity = response.getEntity();
			byte[] bArray = EntityUtils.toByteArray(entity);
			this.response = new String(bArray, "UTF-8");
//			this.response = EntityUtils.toString(entity);				
			
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
		
		DefaultHttpClient client = new DefaultHttpClient();
		HttpEntity entity = null;
		
		try {
			Log.d(LOG_TAG, url);
			
			HttpPost httpPost = new HttpPost(url);

			addAuthorization(httpPost);

			httpPost.setEntity(new UrlEncodedFormEntity(param));
			HttpResponse response = client.execute(httpPost);
			
			iRet = response.getStatusLine().getStatusCode();
			entity = response.getEntity();
			byte[] bArray = EntityUtils.toByteArray(entity);
			this.response = new String(bArray, "UTF-8");
//			this.response = EntityUtils.toString(entity);				
			
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
	
	public int put(String url, String strPutData) {
		int iRet = -1;

		DefaultHttpClient client = new DefaultHttpClient();
		
		HttpEntity httpEntity = null;
		StringEntity entity = null;
		
		Log.d(LOG_TAG, url);
		
		try {
			HttpPut httpPut = new HttpPut(url);
			
			addAuthorization(httpPut);
									
			entity = new StringEntity(strPutData, "UTF-8");
			entity.setContentType("application/xml");
			
			httpPut.setEntity(entity);
			
			HttpResponse response = client.execute(httpPut);
			iRet = response.getStatusLine().getStatusCode();

			httpEntity = response.getEntity();
			byte[] bArray = EntityUtils.toByteArray(httpEntity);
			this.response = new String(bArray, "UTF-8");
			
		} catch(ClientProtocolException e) {
			Log.d(LOG_TAG, "ClientProtocolException:", e);
		} catch(IOException ie) {
			Log.d(LOG_TAG, "IOException:", ie);
		} catch(IllegalStateException ille) {
			Log.d(LOG_TAG, "IllegalStateException:", ille);
		} finally {
			try {
				if(httpEntity != null) httpEntity.consumeContent();
			} catch(Exception e) {}
			client.getConnectionManager().shutdown();
		}
	
		return iRet;
	}
	
	/**
	 * HTTPヘッダにBasic認証ヘッダと認証情報を追加する。
	 * @param req HttpRequestオブジェクト
	 */
	private void addAuthorization(HttpRequest req) {
		if(req != null && user != null) {
			// この認証方法もあるが、利用できなかった。（HTTPヘッダに認証情報がつかなかった。）
//			client.getCredentialsProvider().setCredentials(new AuthScope(httpPut.getURI().getHost(), httpPut.getURI().getPort())
//														 , new UsernamePasswordCredentials(user, pass));
			
			// apache-commons codecからライブラリをダウンロードし、libディレクトリに含め、ビルドパスに追加すれば実行可能
			// しかし今回は標準のライブラリを利用することにする。
//			httpPut.addHeader("Authorization", "Basic " + new String(Base64.encodeBase64((user + ":" + pass).getBytes())));
			
			req.setHeader("Authorization", "Basic " + new String(Base64.encodeToString((user + ":" + pass).getBytes(), Base64.NO_WRAP)));
		}
	}
}
