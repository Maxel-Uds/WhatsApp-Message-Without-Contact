package com.maxel.whatsappmessage;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    private TextView phone;
    private TextView message;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.phone);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);

        send.setOnClickListener(view -> {
            if(phone.getText().length() == 0 || message.getText().length() == 0) {
                this.showPopUp("Erro", "Preencha todos os campos!");
            } else if(phone.getText().length() != 0 && !this.numberValidation()) {
                this.showPopUp("Erro", ("Este número de telefone não é válido: " + phone.getText()));
            } else {
                this.sendMessage();
            }
        });
    }

    protected void showPopUp(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Tentar de Novo", null);
        AlertDialog alerta = builder.create();
        alerta.show();
    }

    protected boolean numberValidation() {
        return phone.getText().toString().matches("[1-9]{2}9[0-9]{4}[0-9]{4}");
    }

    protected void sendMessage() {
        Uri whatsAppLink = Uri.parse(MessageFormat.format("https://wa.me/55{0}?text={1}", phone.getText(), message.getText()));
        Intent whatsAppIntent = new Intent(Intent.ACTION_VIEW, whatsAppLink);
        startActivity(whatsAppIntent);
    }

}