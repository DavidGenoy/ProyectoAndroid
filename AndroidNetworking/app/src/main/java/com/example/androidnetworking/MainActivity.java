package com.example.androidnetworking;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnetworking.net.HttpConnection;
import com.example.androidnetworking.net.HttpThread;
import com.example.androidnetworking.net.HttpThread.HttpInterface;


public class MainActivity extends ActionBarActivity implements OnClickListener, HttpInterface{

	TextView rta;
	EditText num1, num2;
	Button btn;
	HttpThread thread;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        rta= (TextView) findViewById(R.id.rta);
        num1= (EditText) findViewById(R.id.num1);
        num2= (EditText) findViewById(R.id.num2);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View v) {
		
		if(thread ==null || (thread!=null && !thread.isRuning())){
			thread= new HttpThread(this
						, "http://10.0.2.2:8080/PlayNote/CalculadoraServlet"
						, "num1="+num1.getText().toString()+"&num2="+num2.getText().toString());
			thread.start();
		}
	}
		
	
	Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			String rtaTxt=(String) msg.obj;
			rta.setText(rtaTxt);
		}
		
	};

	@Override
	public Handler getHandler() {
		return handler;
	}

	
}
