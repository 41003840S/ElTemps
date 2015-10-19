package com.example.poblenou.eltemps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ListView listaTiempo;
    ArrayList items;
    ArrayAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_main, container, false);

        listaTiempo = (ListView) fragment.findViewById(R.id.listView);

        String data [] ={"Lun 26/10 - Soleado", "Mar 27/10 - Niebla", "Mier 26/10 - Nublado", "Jue 26/10 - Lluvioso",
                "Lun 26/10 - Soleado", "Sab 26/10 - Parcialmente nublado", "Dom 26/10 - Soleado"};

        items = new ArrayList(Arrays.asList(data));
        adapter = new ArrayAdapter<String>(getContext(),
                R.layout.filas_dias, R.id.textView, items);
        listaTiempo.setAdapter(adapter);




        return fragment;

    }
}
