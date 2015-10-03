package com.example.androidnetworking;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidnetworking.net.HttpAsyncTask;
import com.example.androidnetworking.net.HttpAsyncTask.HttpAsyncInterface;


public class AsyncMainActivity extends ActionBarActivity implements OnClickListener, HttpAsyncInterface{

	TextView rta;
	EditText num1, num2;
	Button btn;

	
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
		
		HttpAsyncTask task= new HttpAsyncTask(this
				,"num1="+num1.getText().toString()+"&num2="+num2.getText().toString());
		//task.execute("http://10.0.2.2:8080/PlayNote/CalculadoraServlet");
        task.execute("http://192.168.1.49/conexion.php");
        //task.execute("http://10.0.2.2/conexion.php");
	}


	@Override
	public void setResponse(String rta) {
		//this.rta.setText(rta);
        try {
            JSONObject jObject = new JSONObject(rta);//aqui convertimos este texto q me llega, a un objeto de tipo json.
            int result = jObject.getInt("result");
            this.rta.setText(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
		
	


	
}
