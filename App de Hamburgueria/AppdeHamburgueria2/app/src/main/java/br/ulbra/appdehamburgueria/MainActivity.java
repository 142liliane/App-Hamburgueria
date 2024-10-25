package br.ulbra.appdehamburgueria;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText email, senha;
    Button btnLogin, btnCadastro;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referenciando os elementos do layout
        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCriar);

        // Inicializando o DBHelper
        db = new DBHelper(this);

        // Ação de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString().trim();
                String userPass = senha.getText().toString().trim();

                // Validando os campos
                if (userEmail.isEmpty() || userPass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, insira email e senha.", Toast.LENGTH_SHORT).show();
                } else {
                    // Verificando login no banco de dados
                    Cursor res = db.buscarUsuario(userEmail, userPass);
                    if (res.getCount() > 0) {
                        // Login bem-sucedido, redirecionar para o catálogo
                        Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
                        startActivity(intent);
                    } else {
                        // Exibir mensagem de erro
                        Toast.makeText(MainActivity.this, "Email ou senha incorretos.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Redirecionar para a tela de cadastro
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}


