package br.lsdi.ufma.threadsapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import br.lsdi.ufma.threadsapp.tarefas.TarefaPesadaHandlerMessage;
import br.lsdi.ufma.threadsapp.tarefas.TarefaPesadaHandlerPost;

public class HandlerMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_message);

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

        final Button executarTarefaPesadaButton = findViewById(R.id.executarTarefaPesadaButton);
        executarTarefaPesadaButton.setEnabled(false);

        final TarefaPesadaHandlerMessage.Handler handler = new TarefaPesadaHandlerMessage.Handler(HandlerMessageActivity.this);
        new Thread(new TarefaPesadaHandlerMessage(HandlerMessageActivity.this, handler))
                .start();

    }

}