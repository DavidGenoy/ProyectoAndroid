package com.example.davidg.ceo.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.davidg.ceo.R;



public class FormFragment extends Fragment implements View.OnClickListener {

    EditText editLog;
    EditText editPass;

    public  interface Validacion {
   public   void validar(String login, String password);
}

    Validacion validacion;

    public FormFragment() {}

    @Override
    public void onAttach(Context context) {
        validacion= (Validacion) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_form, container, false);
         editLog= (EditText) v.findViewById(R.id.edt_log);
         editPass= (EditText) v.findViewById(R.id.edt_pass);
        Button btn= (Button) v.findViewById(R.id.btn_form);

        btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        String user, pass;
        user= editLog.getText().toString();
        pass= editPass.getText().toString();
        validacion.validar(user,pass);

    }



}
