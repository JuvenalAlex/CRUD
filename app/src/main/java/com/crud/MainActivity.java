package com.crud;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list_locador;
    List<Locador> list_locadors;
    DBHelper db;
    Button Bt_addLocador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        list_locadors = new ArrayList<>();
        list_locador = findViewById(R.id.list_locador);
        Bt_addLocador = findViewById(R.id.Bt_addLocador);

        Bt_addLocador.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, AddLocador.class);
            startActivityForResult(i, 1);
        });

        list_locador.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(MainActivity.this, DtLocador.class);
            i.putExtra("locador", list_locadors.get(position).getLocador());
            startActivityForResult(i, 2);

        });


        list_locadors();
    }

    private void list_locadors() {
        list_locadors.clear();
        Cursor c = db.SelectAll_Locador();
        c.moveToFirst();
        if (c.getCount() > 0) {
            do {
                String locador = c.getString(c.getColumnIndex("locador"));
                String livro = c.getString(c.getColumnIndex("livro"));
                list_locadors.add(new Locador(locador, livro));

            } while (c.moveToNext());
        }
        ArrayAdapter<Locador> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_locadors);
        list_locador.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            list_locadors();

        } else if (requestCode == 2 && resultCode == 2) {
            list_locadors();

        }
    }
}