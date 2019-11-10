package com.example.ismael.sistemadecontactos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.example.ismael.sistemadecontactos.Actualizar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class Mostrar extends Fragment {
    public static Bundle bundle;
    View rootView;
    ListView lista;
    ArrayList<Usuarios> usuarios = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_mostrar, container, false);
        lista = rootView.findViewById(R.id.listaUsuarios);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String codigo = lista.getItemAtPosition(i).toString().substring(0,1);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = new Actualizar();

                bundle = new Bundle();
                bundle.putString("codigo",codigo);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.idMostrar,fragment).addToBackStack("tag").commit();
            }
        });
        mostrar();
        return rootView;
    }
    public void mostrar(){
        usuarios.clear();
        bdUsuarios dh = new bdUsuarios(getActivity(), "usuarios", null, 1);
        if (dh!=null){
            SQLiteDatabase db= dh.getReadableDatabase();
            Cursor fila = db.rawQuery("select * from usuarios",null);
            if (fila.moveToFirst()){
                do{
                    usuarios.add(new Usuarios(fila.getInt(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5),fila.getString(6)));
                }while (fila.moveToNext());
            }
        }else{
            Toast.makeText(getActivity(), "Base de Datos No disponible", Toast.LENGTH_SHORT).show();
        }

        String []arreglo = new String[usuarios.size()];
        for (int i=0; i<arreglo.length;i++){
            arreglo[i]=usuarios.get(i).getCodigo()+" "+usuarios.get(i).getNombre();
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, arreglo);
        lista.setAdapter(adapter);
    }

}
