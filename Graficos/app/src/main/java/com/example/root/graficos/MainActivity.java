package com.example.root.graficos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Valores a mostrar en la gráfica

        ArrayList<BarEntry> entradas = new ArrayList<>();
        entradas.add(new BarEntry(18f, 0));
        entradas.add(new BarEntry(20f, 1));
        entradas.add(new BarEntry(21f, 2));
        entradas.add(new BarEntry(24f, 3));
        entradas.add(new BarEntry(25f, 4));
        entradas.add(new BarEntry(27f, 5));

        //Creamos el conjunto de datos a partir de las entradas

        BarDataSet dataset = new BarDataSet(entradas, "# de recargas");

        //Etiquetas para el eje X

        ArrayList<String> etiquetas = new ArrayList<String>();
        etiquetas.add("Enero");
        etiquetas.add("Febrero");
        etiquetas.add("Marzo");
        etiquetas.add("Abril");
        etiquetas.add("Mayo");
        etiquetas.add("Junio");

        //Aplicamos una plantillas de colores al conjunto de datos
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        //Definimos la gráfica

        BarChart grafica = new BarChart(getApplicationContext());
        setContentView(grafica);

        //Incluimos los datos y etiquetas en la gráfica

        BarData datos = new BarData(etiquetas, dataset);
        grafica.setData(datos);

        //Añadimos una descripción a la gráfica
        grafica.setDescription("# de veces que Juan recarga el móvil");

        //Aplicamos una animación al eje Y
        grafica.animateY(5000);

        //Incluímos una línea límite
        LimitLine linea = new LimitLine(22f);
        YAxis ejeY = grafica.getAxisLeft();
        ejeY.addLimitLine(linea);
    }
}
