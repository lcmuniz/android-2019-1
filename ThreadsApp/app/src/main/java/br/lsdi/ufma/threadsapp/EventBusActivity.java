package br.lsdi.ufma.threadsapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

import br.lsdi.ufma.threadsapp.tarefas.TarefaPesadaEventBus;
import br.lsdi.ufma.threadsapp.tarefas.TarefaPesadaHandlerPost;

public class EventBusActivity extends AppCompatActivity {

    Button executarTarefaPesadaButton;
    Button mostrarMensagemButton;
    TextView mensagemTextView;
    TextView tarefaTextView;
    ProgressBar progressoProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        EventBus.getDefault().register(this);

        tarefaTextView = findViewById(R.id.tarefaTextView);
        progressoProgressBar = findViewById(R.id.progressoProgressBar);
        mensagemTextView = findViewById(R.id.mensagemTextView);

        executarTarefaPesadaButton = findViewById(R.id.executarTarefaPesadaButton);
        executarTarefaPesadaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarTarefaPesada();
            }
        });

        mostrarMensagemButton = findViewById(R.id.mostrarMensagemButton);
        mostrarMensagemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMensagem();
            }
        });

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void mostrarMensagem() {
        mensagemTextView.setText("Mensagem: " + String.valueOf(new Random().nextInt()));
    }

    private void executarTarefaPesada() {

        executarTarefaPesadaButton.setEnabled(false);

        new Thread(new TarefaPesadaEventBus()).start();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void on(TarefaPesadaEventBus.IniciarTarefaEvent tarefa) {
        executarTarefaPesadaButton.setEnabled(false);
        tarefaTextView.setText(tarefa.getMensagem());
        progressoProgressBar.setProgress(0);
        progressoProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void on(TarefaPesadaEventBus.AtualizarTarefaEvent tarefa) {
        progressoProgressBar.setProgress(tarefa.getProgresso());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void on(TarefaPesadaEventBus.FinalizarTarefaEvent tarefa) {
        tarefaTextView.setText(tarefa.getMensagem());
        progressoProgressBar.setVisibility(ProgressBar.INVISIBLE);
        executarTarefaPesadaButton.setEnabled(true);
    }

}