package com.example.davidg.ceo;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.davidg.ceo.fragments.ConsumoFragment;
import com.example.davidg.ceo.fragments.CuentaFragment;
import com.example.davidg.ceo.fragments.FormFragment;
import com.example.davidg.ceo.fragments.GraficoFragment;
import com.example.davidg.ceo.fragments.MasterFragment;
import com.example.davidg.ceo.net.HttpAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements FormFragment.Validacion, HttpAsyncTask.HttpAsyncInterface, MasterFragment.OnItemSelectedList, MasterFragment.ListMasterFragment {
    FormFragment    formFragment;

    MasterFragment  masterFragment;
    GraficoFragment graficoFragment;
    CuentaFragment cuentaFragment;
    ConsumoFragment consumoFragment;
    ArrayAdapter<String> adapter;
    String data[];
    String user;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formFragment  = new FormFragment();
        masterFragment= new MasterFragment();
        graficoFragment=new GraficoFragment();
        cuentaFragment= new CuentaFragment();
        consumoFragment= new ConsumoFragment();

        data=getResources().getStringArray(R.array.list_master);
        setContentView(R.layout.activity_main);
       // putFragment(R.id.contenedor1, headerFragment);
        putFragment(R.id.contenedor2, formFragment);

    }
    @Override
    public void validar(String login, String password) {
        user=login;
        pass=password;

        HttpAsyncTask task = new HttpAsyncTask(this,"user=" + login + "&pass=" + password);
        task.execute("http://192.168.0.4/conexion.php");


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
                toast = Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT);
                putFragment(R.id.contenedor2, masterFragment);
            }else
                toast = Toast.makeText(this, "Datos Incorrectos", Toast.LENGTH_SHORT);
                toast.show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onItemSelectedList(int pos) {
        Toast toast= Toast.makeText(this, "seleccionaste posicion " + pos, Toast.LENGTH_SHORT);
        switch (pos){
            case 0: showGrafic(); break;
            case 1: showConsum(); break;
            case 2: showAccount();break;
        }

        //toast.show();
    }




    @Override
    public void listMasterFragment(ListView listView) {
        ListView list =listView;

        adapter=new ArrayAdapter<String>(this,R.layout.template1,R.id.txt_template1,data);
        list.setAdapter(adapter);
    }

    //Metodo que llama el fragment graficoFragment que es donde se lanza el grafico del consumo promedio
    private void showGrafic() {

        putFragment(R.id.contenedor2,graficoFragment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void showConsum() {
        consumoFragment.init(user);
        putFragment(R.id.contenedor2, consumoFragment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void showAccount() {
        cuentaFragment.init(user,pass);
        putFragment(R.id.contenedor2,cuentaFragment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public  void putFragment(int idContainer,Fragment fragment){
        FragmentTransaction fT=getSupportFragmentManager().beginTransaction();
        fT.replace(idContainer,fragment);
        fT.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            putFragment(R.id.contenedor2,masterFragment);
        }
        return super.onOptionsItemSelected(item);
    }
}





























