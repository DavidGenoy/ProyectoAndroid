package com.example.androidnetworking.net;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import android.os.Handler;
import android.os.Message;

public class HttpThread extends Thread{

	public static final int GET=0;
	public static final int POST=1;
	
	public interface HttpInterface{
		Handler getHandler();		
	}
	
	String url, dataGet;
	List<NameValuePair> dataPost;
	int httpMethod;
	boolean runing;
	
	
	
	HttpInterface httpI;
	
	public HttpThread(HttpInterface httpI,String url, String dataGet) {
		this.httpI=httpI;
		this.url = url;
		this.dataGet = dataGet;
		httpMethod=GET;
	}

	public HttpThread(HttpInterface httpI,String url, List<NameValuePair> dataPost) {
		this.httpI=httpI;
		this.url = url;
		this.dataPost = dataPost;
		httpMethod=POST;
	}

	@Override
	public void run() {
		//Thread.sleep(1000);	
		runing=true;
		HttpConnection httpCx= new HttpConnection(url);
		String rta=null;
		try {
			if(httpMethod==GET)
				rta= httpCx.requestByGet(dataGet);
			else
				rta= httpCx.requestByPOST(dataPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Message msg= httpI.getHandler().obtainMessage();
		msg.obj=rta;
		runing=false;
		httpI.getHandler().sendMessage(msg);		
	}
	
	public boolean isRuning(){
		return runing;
	}
	
}
