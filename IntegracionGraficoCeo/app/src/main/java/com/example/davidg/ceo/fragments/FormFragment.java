package com.example.davidg.ceo.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.davidg.ceo.AppUtil;
import com.example.davidg.ceo.R;
public class FormFragment extends Fragment implements View.OnClickListener {
  // static final String KEY_USR = "txt_usr";
  // static final String KEY_PASS = "txt_pass";
    TextInputLayout editLog;
    TextInputLayout editPass;

    public  interface Validacion {
        void validar(String login, String password);
    }


    Validacion validacion;




    public FormFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        validacion= (Validacion) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_form, container, false);
         editLog= (TextInputLayout) v.findViewById(R.id.edt_log);
         editPass= (TextInputLayout) v.findViewById(R.id.edt_pass);

        editLog.getEditText().setText(AppUtil.usr);
        editPass.getEditText().setText(AppUtil.pass);

        /*if (savedInstanceState !=null){

            String usr=savedInstanceState.getString(KEY_USR);
            String pass= savedInstanceState.getString(KEY_PASS);

            editLog.setText(usr);
            editPass.setText(pass);
        }*/

        Button btn= (Button) v.findViewById(R.id.btn_form);

        btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        String user, pass;
        user= editLog.getEditText().getText().toString();
        pass= editPass.getEditText().getText().toString();
        validacion.validar(user,pass);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppUtil.usr=editLog.getEditText().getText().toString();
        AppUtil.pass=editPass.getEditText().getText().toString();
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_USR,editLog.getText().toString());
        outState.putString(KEY_PASS,editPass.getText().toString());
        super.onSaveInstanceState(outState);
    }*/
}
