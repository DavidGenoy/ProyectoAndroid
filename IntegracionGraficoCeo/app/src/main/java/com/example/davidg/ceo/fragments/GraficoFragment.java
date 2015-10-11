package com.example.davidg.ceo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidg.ceo.R;
import com.example.davidg.ceo.net.HttpAsyncTask;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraficoFragment extends Fragment implements HttpAsyncTask.HttpAsyncInterface{

    ArrayList<BarEntry> entradas;
    BarDataSet dataset;
    ArrayList<String> etiquetas;
    BarChart grafica;
    BarData datos;
    LimitLine linea;
    Boolean flag=false;
    double ene=0,feb=0,mar=0,abr=0,may=0,jun=0;
    float enef=0,febf=0,marf=0,abrf=0,mayf=0,junf=0;
    View v;

    public GraficoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v=inflater.inflate(R.layout.fragment_grafico, container, false);


            HttpAsyncTask task = new HttpAsyncTask(this,"");
            task.execute("http://192.168.0.4/dataChart.php");


        return v;
    }





    @Override
    public void setResponse(String rta) {
        try{
            JSONObject jObject = new JSONObject(rta);
            JSONArray meses = jObject.getJSONArray("meses");
            Log.i("rta", "los meses: " + meses);
            for (int i=0; i<meses.length(); i++){
                JSONObject objJSON = meses.getJSONObject(i);

                switch (i){
                    case 0:
                        ene = objJSON.getDouble("enero");
                        enef = (float) ene;
                        break;
                    case 1:
                        feb = objJSON.getDouble("febrero");
                        febf = (float) feb;
                        break;
                    case 2:
                        mar = objJSON.getDouble("marzo");
                        marf = (float) mar;
                        break;
                    case 3:
                        abr = objJSON.getDouble("abril");
                        abrf = (float) abr;
                        break;
                    case 4:
                        may = objJSON.getDouble("mayo");
                        mayf = (float) may;
                        break;
                    case 5:
                        jun = objJSON.getDouble("junio");
                        junf = (float) jun;
                        break;
                }
            }

            ////////////////////////////////////////////////////////////////////////////////////////
            entradas = new ArrayList<>();
            entradas.add(new BarEntry(enef, 0));
            entradas.add(new BarEntry(febf, 1));
            entradas.add(new BarEntry(marf, 2));
            entradas.add(new BarEntry(abrf, 3));
            entradas.add(new BarEntry(mayf, 4));
            entradas.add(new BarEntry(junf, 5));

            //Creamos el conjunto de datos a partir de las entradas

            dataset = new BarDataSet(entradas, "# de recargas");

            //Etiquetas para el eje X

            etiquetas = new ArrayList<String>();
            etiquetas.add("Enero");
            etiquetas.add("Febrero");
            etiquetas.add("Marzo");
            etiquetas.add("Abril");
            etiquetas.add("Mayo");
            etiquetas.add("Junio");

            //Aplicamos una plantillas de colores al conjunto de datos
            dataset.setColors(ColorTemplate.COLORFUL_COLORS);

            //Definimos la gráfica

            /*  grafica = new BarChart(getApplicationContext());
            setContentView(grafica);*/

            grafica = (BarChart) v.findViewById(R.id.chart);
            //Incluimos los datos y etiquetas en la gráfica

            datos = new BarData(etiquetas, dataset);

            grafica.setData(datos);

            //Añadimos una descripción a la gráfica
            grafica.setDescription("");

            //Aplicamos una animación al eje Y
            grafica.animateY(5000);
            grafica.animateX(3000);

            //Incluímos una línea límite
            /*
            linea = new LimitLine(22f);
            YAxis ejeY = grafica.getAxisLeft();
            ejeY.addLimitLine(linea);*/

            ///////////////////////////////////////////////////////////////////////

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
