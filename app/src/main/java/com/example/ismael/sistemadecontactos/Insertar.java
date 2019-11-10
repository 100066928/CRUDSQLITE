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

public class Insertar extends Fragment {
    View rootView;
    EditText etnombre,etapellido,etdireccion,ettelefono,etcorreo,clave;
    String nombre, apellido, direccion,telefono,correo,pass;
    Button btnguardar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_insertar, container, false);

        etnombre = rootView.findViewById(R.id.etNombre);
        etapellido = rootView.findViewById(R.id.etApellido);
        etdireccion = rootView.findViewById(R.id.etDireccion);
        ettelefono = rootView.findViewById(R.id.etTelefono);
        etcorreo = rootView.findViewById(R.id.etCorreo);
        clave = rootView.findViewById(R.id.etClave);

        btnguardar = rootView.findViewById(R.id.btnGuardar);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regitrarUsuario();
            }
        });

        return rootView;
    }


    public void regitrarUsuario(){
        nombre = etnombre.getText().toString();
        apellido = etapellido.getText().toString();
        direccion = etdireccion.getText().toString();
        telefono = ettelefono.getText().toString();
        correo = etcorreo.getText().toString();
        pass = clave.getText().toString();
        //Toast.makeText(getActivity(), nombre+apellido+direccion+telefono+correo+pass, Toast.LENGTH_SHORT).show();
        if (nombre.isEmpty()||apellido.isEmpty()||direccion.isEmpty()||telefono.isEmpty()||correo.isEmpty()||pass.isEmpty()){
            Toast.makeText(getActivity(), "Complete todos los Campos", Toast.LENGTH_SHORT).show();
        }else{
            bdUsuarios dh = new bdUsuarios(getActivity(), "usuarios", null, 1);
            if (dh!=null){
                SQLiteDatabase db= dh.getWritableDatabase();
                ContentValues con = new ContentValues();
                con.put("nombre",nombre);
                con.put("apellido",apellido);
                con.put("direccion",direccion);
                con.put("telefono",telefono);
                con.put("correo",correo);
                con.put("usuario",nombre);
                con.put("clave",pass);
                db.insert("usuarios",null,con);
                Toast.makeText(getActivity(), "Usuario Registrado, Tu nick sera tu nombre", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Base de Datos No disponible", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
