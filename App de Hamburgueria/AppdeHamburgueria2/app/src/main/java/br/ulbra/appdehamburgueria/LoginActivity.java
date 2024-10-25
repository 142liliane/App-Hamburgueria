package br.ulbra.appdehamburgueria;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText email, senha;
    Button btnLogin, btnCriar;
    TextView linkCadastro;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCriar = findViewById(R.id.btnCriar);
        db = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String pass = senha.getText().toString();

                Cursor res = db.buscarUsuario(user, pass);
                if (res.getCount() > 0) {
                    // Usuário encontrado, navegue para o catálogo
                    Intent intent = new Intent(LoginActivity.this, CatalogoActivity.class);
                    startActivity(intent);
                } else {
                    // Exibir erro
                    Toast.makeText(LoginActivity.this, "Credenciais incorretas", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Usuário encontrado, navegue para o catálogo
                    Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                    startActivity(intent);

            }
        });
    }
}
