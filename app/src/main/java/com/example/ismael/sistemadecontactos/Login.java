package com.example.ismael.sistemadecontactos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText txtusuario,txtclave, etnombre;
    String usuario, clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusuario = findViewById(R.id.etUsuario);
        txtclave = findViewById(R.id.etClave);


        bdUsuarios dh = new bdUsuarios(this, "usuarios", null, 1);
        SQLiteDatabase db= dh.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put("nombre","Manuel");
        con.put("apellido","Abanto");
        con.put("direccion","Ate vitarte");
        con.put("telefono","989059335");
        con.put("correo","abanto@gmail.com");
        con.put("usuario","admin");
        con.put("clave","123");
        db.insert("usuarios",null,con);

    }

    public void verificar(View view){
        usuario = txtusuario.getText().toString();
        clave = txtclave.getText().toString();
        if (usuario.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, "Campos Vacios", Toast.LENGTH_SHORT).show();
        }else{
            bdUsuarios bh = new bdUsuarios(this, "usuarios", null, 1);
            if (bh != null) {
                SQLiteDatabase db = bh.getReadableDatabase();
                String consulta = "select * from usuarios where usuario='" + usuario + "' and clave ='" + clave + "'";
                int contador = 0;
                Cursor c = db.rawQuery(consulta, null);

                while (c.moveToNext()) {
                    contador++;
                }
                //Toast.makeText(this, "Hay " + contador + "0 usuarios", Toast.LENGTH_SHORT).show();
                //Verificar si existe el usuario y contraseñas
                if (contador>0){
                    Toast.makeText(this, "Bienvenido al Sistema", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Login.this, MainActivity.class);
                    startActivity(in);
                }else{
                    Toast.makeText(this, "Error de ingreso", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Error de Base de Datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void regitrarUsuario(View view){
        etnombre = findViewById(R.id.etNombre);
        String nombre = etnombre.getText().toString();
        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
    }
}
