package com.example.ismael.sistemadecontactos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Eliminar extends Fragment {
    EditText etnombre;
    Button boton;
    String nombre;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_eliminar, container, false);
        etnombre = rootView.findViewById(R.id.etNombreEliminar);
        boton = rootView.findViewById(R.id.btnEliminar);
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });

        return rootView;
    }
    public void Eliminar(){
        nombre = etnombre.getText().toString();
        String cadenasql="delete from usuarios where nombre ='"+nombre+"'";

        if (nombre.isEmpty()){
            Toast.makeText(getActivity(), "Coloque un nombre", Toast.LENGTH_SHORT).show();
        }else{
            bdUsuarios dh = new bdUsuarios(getActivity(), "usuarios", null, 1);
            if (dh!=null){
                SQLiteDatabase db= dh.getWritableDatabase();
                db.execSQL(cadenasql);
                Toast.makeText(getActivity(), "Usuario Eliminado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Base de Datos No disponible", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
