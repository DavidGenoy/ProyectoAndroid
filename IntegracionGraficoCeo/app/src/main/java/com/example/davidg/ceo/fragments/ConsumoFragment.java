package com.example.davidg.ceo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.davidg.ceo.R;
import com.example.davidg.ceo.net.HttpAsyncTask;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * A simple {@link Fragment} subclass.
 */
public class ConsumoFragment extends Fragment implements HttpAsyncTask.HttpAsyncInterface, View.OnClickListener {
    View v;
    String user, nombreMes;
    Date curDate;
    Button btn;
    TextView txtKw;
    double consumoIni;
    ArrayList<BarEntry> entradas;
    BarDataSet dataset;
    ArrayList<String> etiquetas;
    BarChart grafica;
    BarData datos;

    int minInit,minFin ;
    int min;


    public ConsumoFragment() { }

    public  void  init(String user){
        this.user=user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_consumo, container, false);
        HttpAsyncTask task = new HttpAsyncTask(this,"user=" + user);
        task.execute("http://192.168.0.4/consumoActual.php");
        btn= (Button) v.findViewById(R.id.btnConsumo);
        txtKw= (TextView) v.findViewById(R.id.txt_consumoKw);
        btn.setOnClickListener(this);
        return v;
    }


    @Override
    public void setResponse(String rta) {
        try{
            JSONObject jObject = new JSONObject(rta);
            if(jObject.has("enero")) {

                Log.i("rta", "llego a enerooooo:" + jObject.getDouble("enero"));
            }else if(jObject.has("febrero")){


            }else if(jObject.has("marzo")){


            }else if(jObject.has("abril")){


            }else if(jObject.has("mayo")){
                consumoIni = jObject.getDouble("mayo");
                txtKw.setText(consumoIni+"");
                minInit= capturarMin();
                Log.i("init", "inicial" + minInit);


            }else if(jObject.has("junio")){


            }
            graficar();

        }catch (JSONException e){
            e.printStackTrace();
        }



    }


    @Override
    public void onClick(View v) {
        minFin=capturarMin();
        if (minFin>minInit){
            //consumoFinal = consumoIni;
            consumoIni  = consumoIni+0.5;
            txtKw.setText(consumoIni + "");
            graficar();


        }

    }

    public int capturarMin(){
        curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("ss");
        String DateToStr = format.format(curDate);
        min=Integer.parseInt(DateToStr);
        Log.i("ent","sisisisisisi:" + min);
        return min;
    }
    public  void graficar(){
        entradas = new ArrayList<>();
        entradas.add(new BarEntry((float) consumoIni, 0));


        //Creamos el conjunto de datos a partir de las entradas

        dataset = new BarDataSet(entradas, "");

        //Etiquetas para el eje X

        etiquetas = new ArrayList<String>();
        etiquetas.add("Enero");

        //Aplicamos una plantillas de colores al conjunto de datos
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        //Definimos la gráfica

            /*  grafica = new BarChart(getApplicationContext());
            setContentView(grafica);*/

        grafica = (BarChart) v.findViewById(R.id.chartConsumo);
        //Incluimos los datos y etiquetas en la gráfica

        datos = new BarData(etiquetas, dataset);

        grafica.setData(datos);
        //Aplicamos una animación al eje Y
        grafica.animateY(5000);

    }

}
