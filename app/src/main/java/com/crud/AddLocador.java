package com.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLocador extends AppCompatActivity {
    EditText et_locador, et_livro;
    Button bt_salvar, bt_cancelar;
    Intent i;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locador);

        et_locador = findViewById(R.id.et_locador);
        et_livro = findViewById(R.id.et_livro);
        bt_salvar = findViewById(R.id.bt_salvar);
        bt_cancelar = findViewById(R.id.bt_cancelar);

        i = getIntent();
        db = new DBHelper(this);

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String locador = et_locador.getText().toString();
                String livro = et_livro.getText().toString();

                if (!locador.trim().isEmpty() && !livro.trim().isEmpty()) {
                    long res = db.Insert_Locador(locador, livro);
                    if (res > 0)
                        Toast.makeText(AddLocador.this, "Locador Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AddLocador.this, "Erro ao cadastrar locador!", Toast.LENGTH_LONG).show();
                }

                setResult(1, i);
                finish();

            }
        });

        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0, i);
                finish();
            }
        });

    }
}