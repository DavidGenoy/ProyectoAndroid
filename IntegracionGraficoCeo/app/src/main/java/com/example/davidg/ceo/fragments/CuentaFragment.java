package com.example.davidg.ceo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.davidg.ceo.R;
import com.example.davidg.ceo.net.HttpAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class CuentaFragment extends Fragment implements HttpAsyncTask.HttpAsyncInterface {
    View v;
    String user,pass;
    EditText editUser, editPass, editName, editApe, editDir, editTel;

    public CuentaFragment() { }

    public  void  init(String user, String pass){
        this.user=user;
        this.pass=pass;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_cuenta, container, false);
        editUser = (EditText) v.findViewById(R.id.edit_userName);
        editPass = (EditText) v.findViewById(R.id.edit_pass);
        editName = (EditText) v.findViewById(R.id.edit_nombre);
        editApe = (EditText) v.findViewById(R.id.edit_apellido);
        editDir = (EditText) v.findViewById(R.id.edit_direccion);
        editTel = (EditText) v.findViewById(R.id.edit_telefono);



        HttpAsyncTask task = new HttpAsyncTask(this,"user=" + user + "&pass=" + pass);
        task.execute("http://192.168.0.4/dataCuenta.php");
        return v;
    }


    @Override
    public void setResponse(String rta) {

        try{
        JSONObject jObject = new JSONObject(rta);
        JSONArray datosUser = jObject.getJSONArray("datosUser");
        JSONObject objJson = datosUser.getJSONObject(0);
        editUser.setText(objJson.getString("userName"));
        editPass.setText(objJson.getString("password"));
        editName.setText(objJson.getString("nombre"));
        editApe.setText(objJson.getString("apellido"));
        editDir.setText(objJson.getString("direccion"));
        editTel.setText(objJson.getString("telefono"));

        }catch (JSONException e){
        e.printStackTrace();
        }


    }
}
