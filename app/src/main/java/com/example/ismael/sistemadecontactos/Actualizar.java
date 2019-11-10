package com.example.ismael.sistemadecontactos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import java.util.ArrayList;


public class Actualizar extends Fragment {
    View rootView;
    EditText etnombre,etapellido,etdireccion,ettelefono,etcorreo,clave;
    String codigo,nombre, apellido, direccion,telefono,correo,pass;
    Button btnActualizar,btnRefrescar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_actualizar, container, false);
        etnombre = rootView.findViewById(R.id.etNombreActualizar);
        etapellido = rootView.findViewById(R.id.etApellidoActualizar);
        etdireccion = rootView.findViewById(R.id.etDireccionActualizar);
        ettelefono = rootView.findViewById(R.id.etTelefonoActualizar);
        etcorreo = rootView.findViewById(R.id.etCorreoActualizar);
        clave = rootView.findViewById(R.id.etClaveActualizar);

        btnActualizar = rootView.findViewById(R.id.btnActualizar);
        btnRefrescar= rootView.findViewById(R.id.btnRefrescar);


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarRegistro();
                llenar();
            }
        });
        btnRefrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llenar();
            }
        });
        return rootView;
    }

    public void ActualizarRegistro(){

        codigo = Mostrar.bundle.getString("codigo");
        Toast.makeText(getActivity(), codigo, Toast.LENGTH_SHORT).show();
    }

    public void llenar(){
        bdUsuarios dh = new bdUsuarios(getActivity(), "usuarios", null, 1);
        SQLiteDatabase db= dh.getReadableDatabase();
        Cursor fila = db.rawQuery("select * from usuarios where codigo='"+codigo+"'",null);
        ArrayList<Usuarios> usuarios = new ArrayList<>();
        if (fila.moveToFirst()){
            do{
                usuarios.add(new Usuarios(fila.getInt(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5),fila.getString(6)));
            }while (fila.moveToNext());
        }
        String []arreglo = new String[usuarios.size()];
        for (int i=0; i<arreglo.length;i++){
            arreglo[i]=usuarios.get(i).getCodigo()+" "+usuarios.get(i).getNombre();
            etnombre.setText(usuarios.get(i).getNombre());
        }
    }
}
