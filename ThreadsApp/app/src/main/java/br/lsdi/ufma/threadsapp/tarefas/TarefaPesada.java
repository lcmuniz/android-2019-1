package br.lsdi.ufma.threadsapp.tarefas;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.lsdi.ufma.threadsapp.R;

public class TarefaPesada {


    private final Activity activity;

    public TarefaPesada(Activity activity) {
        this.activity = activity;
    }

    public void executar() {

        TextView tarefaTextView = activity.findViewById(R.id.tarefaTextView);
        ProgressBar progressoProgressBar = activity.findViewById(R.id.progressoProgressBar);
        Button executarTarefaPesadaButton = activity.findViewById(R.id.executarTarefaPesadaButton);

        executarTarefaPesadaButton.setEnabled(false);
        tarefaTextView.setText("Tarefa iniciada");
        progressoProgressBar.setProgress(0);
        progressoProgressBar.setVisibility(ProgressBar.VISIBLE);
        for (int i = 1; i <= 10; i++) {
            sleep();
            progressoProgressBar.setProgress(i * 10);
        }
        tarefaTextView.setText("Tarefa concluida");
        progressoProgressBar.setVisibility(ProgressBar.INVISIBLE);
        executarTarefaPesadaButton.setEnabled(true);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
