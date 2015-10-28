package com.example.poblenou.eltemps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {    //Clase que implementa un fragment para el activitymain

    private static final String APPID = "903851216670522749af2f95ed456a56";
    ListView listaTiempo;
    ArrayList items;
    ArrayAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View fragment = inflater.inflate(R.layout.fragment_main, container, false);

        listaTiempo = (ListView) fragment.findViewById(R.id.listView);              //Enlazamos el listView

        String data[] = {"Lun 26/10 - Soleado", "Mar 27/10 - Niebla", "Mier 26/10 - Nublado", "Jue 26/10 - Lluvioso",
                "Lun 26/10 - Soleado", "Sab 26/10 - Parcialmente nublado", "Dom 26/10 - Soleado"};
        //ArrayList<Object> objectos;
        items = new ArrayList(Arrays.asList(data));                                 //Añadimos el array de Strings a un ArrayList
        adapter = new ArrayAdapter<String>(getContext(),                            //Enlazamos con el adaptador los datos con el ListView
                R.layout.filas_dias, R.id.tv_row, items);
        listaTiempo.setAdapter(adapter);                                            //Seteamos el ListView con el adaptador

        listaTiempo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {                                 //Crea un Listener para que con pulsacion prolongada haga algo
                return false;
            }
        });
        return fragment;
    }

    //Creamos el onCreate y el OptionItemSelect del menu que hemos creado para el fragment en RES--> MENU, para añadir el item (refresh)
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_refresh) {
            refresh();                                      //Al presionar el item refresh invoca el metodo refresh
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        OwmApiClient apiClient = new OwmApiClient();
        apiClient.updateForecasts(adapter, getContext());
    }

}

   /* public interface OwmService {
        @GET("forecast/daily?q=barcelona&mode=json&units=metric&cnt=14&appid=bd82977b86bf27fb59a04b61b657fb6f")
        Call<Forecast> dailyForecasts();
    }

    private void refresh() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OwmService service = retrofit.create(OwmService.class);

        Call<Forecast> call = service.dailyForecasts();
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                Forecast forecast = response.body();
                Toast.makeText(
                        getContext(),
                        forecast.getCity().getName() + ", " + forecast.getCity().getCountry(),
                        Toast.LENGTH_LONG
                ).show();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }*/