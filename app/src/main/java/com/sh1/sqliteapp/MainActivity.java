package com.sh1.sqliteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    db admin = new db(this,"tienda",null,1);
    TextView txtId ;
    TextView txtCedula;
    TextView txtNombre;
    ContentValues values = new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtId = findViewById(R.id.txtId);
        txtCedula = findViewById(R.id.txtCedula);
        txtNombre = findViewById(R.id.txtNombre);

    }

    public void Clear(View v){
        txtId.setText("");
        txtCedula.setText("");
        txtNombre.setText("");
    }
    public void Insertar(View v){
        SQLiteDatabase db = admin.getWritableDatabase();
        try {
            values.put("id",txtId.getText().toString());
            values.put("cedula",txtCedula.getText().toString());
            values.put("nombre",txtNombre.getText().toString());
            db.insert("personas",null,values);
            db.close();
            Toast.makeText(this,"Se guardaron los datos correctamente",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,"Ha ocurrido un error: " + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar(View v){
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = txtId.getText().toString();
        try {
            values.put("cedula",txtCedula.getText().toString());
            values.put("nombre",txtNombre.getText().toString());
            db.update("personas",values,"id ="+ id,null);
            db.close();
            Toast.makeText(this,"Se modificaron los datos correctamente",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,"Ha ocurrido un error: " + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void Consultar(View v){
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = txtId.getText().toString();
        Cursor cursor = db.rawQuery("SELECT * FROM personas WHERE id ="+id,null);
        if(cursor.moveToNext()){
            txtCedula.setText(cursor.getString(1));
            txtNombre.setText(cursor.getString(2));
            Toast.makeText(this,"Se cargaron los datos correctamente",Toast.LENGTH_SHORT).show();
        }
        else{Toast.makeText(this,"No se encuentra los datos " ,Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View v){
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = txtId.getText().toString();
        try {
            db.delete("personas","id=" + id,null);
            db.close();
            Toast.makeText(this,"Se eliminaron los datos correctamente",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,"Ha ocurrido un error: " + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}