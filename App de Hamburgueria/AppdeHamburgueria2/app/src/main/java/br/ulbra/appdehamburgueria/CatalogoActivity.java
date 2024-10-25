package br.ulbra.appdehamburgueria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class CatalogoActivity extends AppCompatActivity {
        ImageView imgWhatsApp;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_catalogo);

            imgWhatsApp = findViewById(R.id.imgWhatts);

            // Quando o usuário clicar na imagem, será redirecionado para o WhatsApp
            imgWhatsApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirWhatsApp();
                }
            });
        }

        public void abrirWhatsApp() {
            String mensagem = "Gostaria de fazer um pedido.";
            String url = "https://api.whatsapp.com/send?phone=+55 51 98683-2337& text=" + Uri.encode(mensagem);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }

