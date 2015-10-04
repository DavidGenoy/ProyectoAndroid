package com.example.davidg.ceo;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.davidg.ceo.fragments.FormFragment;
import com.example.davidg.ceo.fragments.HeaderFragment;
import com.example.davidg.ceo.net.HttpAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements FormFragment.Validacion, HttpAsyncTask.HttpAsyncInterface {
    FormFragment formFragment;
    HeaderFragment headerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        formFragment=new FormFragment();
        headerFragment=new HeaderFragment();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction fT=getSupportFragmentManager().beginTransaction();
        fT.replace(R.id.contenedor1,headerFragment);
        fT.replace(R.id.contenedor2,formFragment);
        fT.commit();

    }


    @Override
    public void validar(String login, String password) {
        //HttpAsyncTask task = new HttpAsyncTask(this, "num1=" + login + "&num2=" + password);
        HttpAsyncTask task = new HttpAsyncTask(this,"user=" + login + "&pass=" + password);
        task.execute("http://192.168.0.2/conexion.php");


    }

    @Override
    public void setResponse(String rta) {
        //this.rta.setText(rta);
        try {
            JSONObject jObject = new JSONObject(rta);//aqui convertimos este texto q me llega, a un objeto de tipo json.
            String result = jObject.getString("result");
           // this.rta.setText(result + "");
            Toast toast;
            if(result.equals("exito")){
                toast = Toast.makeText(this,"millitos",Toast.LENGTH_SHORT);


            }else
                toast = Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT);
                toast.show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}





























