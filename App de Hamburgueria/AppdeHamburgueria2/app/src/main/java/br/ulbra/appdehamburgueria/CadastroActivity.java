package br.ulbra.appdehamburgueria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {
    EditText nome, email, senha, telefone;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.editTextNome);
        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextSenha);
        telefone = findViewById(R.id.editTextSenha);
        btnCadastrar = findViewById(R.id.buttonCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
        String nomeStr = nome.getText().toString();
        String emailStr = email.getText().toString();
        String senhaStr = senha.getText().toString();
        String telefoneStr = telefone.getText().toString();

        DBHelper db = new DBHelper(this);
        boolean isInserted = db.inserirUsuario(nomeStr, emailStr, senhaStr, telefoneStr);
        if (isInserted) {
            Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            // Redirecionar para a tela de login
            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(CadastroActivity.this, "Erro ao cadastrar. Tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}

