package com.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DtLocador extends AppCompatActivity {

    EditText dt_locador, dt_livro;
    Button dt_cancelar, bt_edit, bt_delete;
    Intent i;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dt_locador);

        dt_livro = findViewById(R.id.dt_livro);
        dt_locador = findViewById(R.id.dt_locador);
        dt_cancelar = findViewById(R.id.dt_cancelar);
        bt_edit = findViewById(R.id.bt_edit);
        bt_delete = findViewById(R.id.bt_delete);

        i = getIntent();
        String locador = i.getExtras().getString("locador");

        db = new DBHelper(this);

        carregarDataLocador(locador);


        dt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0, i);
                finish();
            }
        });

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dt_livro.getText().toString().trim().isEmpty()) {
                    long res = db.Update_Locador(locador, dt_livro.getText().toString());
                    if (res > 0) {
                        Toast.makeText(DtLocador.this, "Usuário editado!", Toast.LENGTH_SHORT).show();
                        setResult(1, i);
                        finish();
                    } else {
                        Toast.makeText(DtLocador.this, "Erro ao editar usuário!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DtLocador.this, "Você deve inserir sua senha!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long res = db.Delete_Locador(locador);
                if (res > 0) {
                    Toast.makeText(DtLocador.this, "Usuário deletado!", Toast.LENGTH_SHORT).show();
                    setResult(2, i);
                    finish();
                } else {
                    Toast.makeText(DtLocador.this, "Erro ao deletar usuário!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void carregarDataLocador(String locador) {
        Cursor c = db.SelectByLocador(locador);
        c.moveToFirst();
        if (c.getCount() == 1) {
            String livro = c.getString(c.getColumnIndex("livro"));
            dt_locador.setText(locador);
            dt_livro.setText(livro);

        } else if (c.getCount() == 0) {
            Toast.makeText(this, "Usuário inexistente!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Erro ao carregar o usuário!", Toast.LENGTH_LONG).show();
        }
    }
}