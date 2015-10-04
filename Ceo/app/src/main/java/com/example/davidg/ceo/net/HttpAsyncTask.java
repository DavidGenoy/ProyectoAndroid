package com.example.davidg.ceo.net;

import android.os.AsyncTask;

import com.example.davidg.ceo.MainActivity;

//import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.List;

public class HttpAsyncTask extends AsyncTask<String, Integer, String> {

	static final int GET=0;
	static final int POST=0;
	
	String dataGET;
	//List<NameValuePair> dataPOST;
	int httpMethod;
	
	public interface HttpAsyncInterface{
		public void setResponse(String rta);
	}
	
	HttpAsyncInterface httpAsyncI;
	
	public HttpAsyncTask(HttpAsyncInterface httpAsyncI, String dataGET) {
		this.dataGET = dataGET;
		this.httpAsyncI=  httpAsyncI;
		httpMethod=GET;
	}

	/*public HttpAsyncTask(HttpAsyncInterface httpAsyncI,List<NameValuePair> dataPOST) {
		super();
		this.dataPOST = dataPOST;
		this.httpAsyncI=httpAsyncI;
		httpMethod=POST;
	}*/

	//Metodo que se ejecuta en background
	@Override
	protected String doInBackground(String... params) {
		String url= params[0];
		HttpConnection httpCx= new HttpConnection(url);
		String rta=null;
		try {
			if(httpMethod==GET)
				rta= httpCx.requestByGet(dataGET);
			//else
				//rta= httpCx.requestByPOST(dataPOST);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return rta;
	}
	
	//Se ejecuta en el UIThread y al retornar doInBackground
	@Override
	protected void onPostExecute(String result) {
		httpAsyncI.setResponse(result);
	}

}
