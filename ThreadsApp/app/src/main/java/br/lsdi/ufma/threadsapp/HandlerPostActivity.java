package br.lsdi.ufma.threadsapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import br.lsdi.ufma.threadsapp.tarefas.TarefaPesadaHandlerPost;

public class HandlerPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_post);

        Button executarTarefaPesadaButton = findViewById(R.id.executarTarefaPesadaButton);
        executarTarefaPesadaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarTarefaPesada();
            }
        });

        Button mostrarMensagemButton = findViewById(R.id.mostrarMensagemButton);
        mostrarMensagemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMensagem();
            }
        });

    }

    private void mostrarMensagem() {
        TextView mensagemTextView = findViewById(R.id.mensagemTextView);
        mensagemTextView.setText("Mensagem: " + String.valueOf(new Random().nextInt()));
    }

    private void executarTarefaPesada() {

        Button executarTarefaPesadaButton = findViewById(R.id.executarTarefaPesadaButton);
        executarTarefaPesadaButton.setEnabled(false);

        final Handler handler = new Handler();
        new Thread(new TarefaPesadaHandlerPost(HandlerPostActivity.this, handler))
                .start();

    }

}